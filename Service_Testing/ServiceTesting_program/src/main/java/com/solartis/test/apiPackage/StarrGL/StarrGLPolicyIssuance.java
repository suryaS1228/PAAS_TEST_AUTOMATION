package com.solartis.test.apiPackage.StarrGL;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.MacroException;
import com.solartis.test.exception.PropertiesHandleException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.common.DatabaseOperation;

public class StarrGLPolicyIssuance extends BaseClass implements API 
{
	public StarrGLPolicyIssuance(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	}
	
	@Override
	 public void AddHeaders(String Token) throws APIException 
	 {
		try
		{
		  http = new HttpHandle(config.getProperty("test_url"),"POST");
		  http.AddHeader("Content-Type", config.getProperty("content_type"));
		  http.AddHeader("Token",Token);
		  http.AddHeader("EventName", config.getProperty("EventName")); 
		  http.AddHeader("EventVersion", config.getProperty("EventVersion")); 
		 }
		catch(HTTPHandleException e)
		{
			throw new APIException("ERROR OCCURS IN AddHeaders FUNCTION -- Coverwallet CLASS", e);
		}
	 }
	
	//=========================================================================================================================================
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&OutputColVerify.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
				{
				try
				{
					System.out.println("Writing Response to Table");
					String responseStatus=response.read("..ResponseStatus").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
					System.out.println(responseStatus);
					if(responseStatus.equals("SUCCESS"))
					{
						System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
						String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
						output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
						System.out.println(actual);
						output.put("Flag_for_execution", "Completed");
						output.put("RuleName","");
						output.put("User_message","");
					}
					else
					{
						output.put("Flag_for_execution",responseStatus);
						output.put("RuleName",response.read("..RuleName"));
						output.put("User_message",response.read("..Message"));
					}
				}
				catch(PathNotFoundException e)
				{
						output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
				}
				}
			}
			
			return output;
		
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
	}
	
	
	public static void main(String args[]) throws PropertiesHandleException, DatabaseException, MacroException, IOException
	{
		System.out.println("coming to flow1");
		DatabaseOperation objectInput = new DatabaseOperation();
		PropertiesHandle configFile=null;
		
		configFile = new PropertiesHandle("E:\\RestFullAPIDeliverable\\Devolpement\\admin\\STARR-GL\\CancelPreview\\Config\\config.properties");
		DatabaseOperation.ConnectionSetup(configFile);
		System.out.println("coming to flow2");
		 LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable = objectInput.GetDataObjects(configFile.getProperty("input_query"));
		 Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator = inputtable.entrySet().iterator();
		 int rowIterator = 1;
			System.out.println("coming to flow3");
		 while (inputtableiterator.hasNext() ) 
			{
			 //System.out.println("coming to flow4");
				Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
		        LinkedHashMap<String, String> inputrow = inputentry.getValue();
		        
		        if(inputrow.get("Flag_for_execution").equals("Y"))
				{
					System.out.println("coming to  "+inputrow.get("PolicyNumber"));
					String url=inputrow.get("ISSUANCE");
					String filename = inputrow.get("PolicyNumber");
					/*if (filename.indexOf(".") > 0)
						filename = filename.substring(0, filename.lastIndexOf("."));
					
					filename=filename.substring(filename.lastIndexOf('/')+1);*/
					
					String path="Q:\\Manual Testing\\Starr\\Starr-GL\\Releases\\CA-reduction factor-Prod issue\\UAT\\PDFS\\";
					urltopdf(url,path,filename);
				}
		        //inputrow.put("Flag_for_execution", "Completed");	
		        objectInput.UpdateRow(rowIterator, inputrow);
		        rowIterator++;
		        
			}
	}
	
	public static void urltopdf(String URL,String path,String filename) throws IOException
	{
		System.setProperty("jsse.enableSNIExtension", "false");	
		URL website = new URL(URL);
		Path targetPath = new File(path + File.separator + filename+".pdf").toPath();
		InputStream in = website.openStream();		
		Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);		
	}
}