package com.solartis.test.apiPackage.CommercialAuto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API2;
import com.solartis.test.apiPackage.BaseClass2;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.JsonHandle;
import com.solartis.test.util.api.RequestHandler;

import freemarker.template.TemplateException;


public class DocGen extends BaseClass2 implements API2  
{

	public DocGen(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	}
	
	public void SendAndReceiveData() throws APIException 
	{
		try
		{
			//String input_data= null;
			//input_data = request.FileToString();
			start = System.currentTimeMillis();
		    //http.SendData(input_data);
			String response_string = this.enable_read(config.getProperty("response_location")+input.get("Testdata")+".json");
		    end = System.currentTimeMillis();
			response = new JsonHandle(config.getProperty("response_location")+input.get("Testdata")+".json");
			response.StringToFile(response_string);
		}
		catch(RequestFormatException e)
		{
			e.printStackTrace();
			throw new APIException("ERROR IN SEND AND RECIEVE DATA FUNCTION -- BASE CLASS", e);
		}
	}
	
	public void AddHeaders(String Token) throws APIException 
	 {
		try
		{
			System.out.println(config.getProperty("test_url")+"---"+config.getProperty("EventName"));
			System.out.println(config.getProperty("token"));
		  http = new HttpHandle(config.getProperty("test_url"),"POST");
		  http.AddHeader("Content-Type", config.getProperty("content_type"));
		  http.AddHeader("Token", config.getProperty("AuthenticationToken"));
		  http.AddHeader("EventName", config.getProperty("EventName"));
		 }
		catch(HTTPHandleException e)
		{
			e.printStackTrace();
			throw new APIException("ERROR OCCURS IN AddHeaders FUNCTION -- Coverwallet CLASS", e);
		}
	 }
	
	public void LoadSampleRequest(LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException
	{
		Map.Entry<Integer,LinkedHashMap<String, String>>  entry = InputData.entrySet().iterator().next();
		this.input = entry.getValue();
		try
		{
			sampleInput = new RequestHandler(config);
			sampleInput.openTemplate();
		}
		catch(IOException|ClassNotFoundException| DatabaseException e)
		{
			throw new APIException("Error in load sample request", e);
		}
	}
	
	public void PumpDataToRequest(LinkedHashMap<String, String> commonmap,LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException 
	{
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
			int i=1;
			for (Map.Entry<Integer,LinkedHashMap<String, String>> entry : InputData.entrySet())  
			{
				sampleInput.LoadData(tableInputColVerify, entry.getValue(),i);
				sampleInput.PumpinDatatoRequest(tableInputColVerify,entry.getValue(),commonmap,i);	
				i++;
			}
			sampleInput.saveJsontoPath(config.getProperty("request_location")+input.get("Testdata")+".json");
		}
		catch( DatabaseException| TemplateException| IOException e)
		{
			e.printStackTrace();
			throw new APIException("Error in pumpData to request", e);
		}
		
	}
	
	public List<String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		List<String> queryList = new ArrayList<String>();
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));

			String insterQuery = "INSERT INTO Output_DocGen VALUES(\""+input.get("S_No")+"\", \""+input.get("Testdata")+"\", \""+input.get("TestCaseID")+"\", "+"temp2)";	
			StringBuffer temp2 = new StringBuffer();
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				String condition = rowOutputColVerify.get("OutputColumnCondtn");
				if(rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))
				{
					if(conditioncheck.ConditionReading(condition, input))
					{
						try
						{
							///System.out.println("Writing Response to Table");
							//System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
							String jsonpath = rowOutputColVerify.get(config.getProperty("OutputJsonPath"));
							String actual = response.read(jsonpath).replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
							
							temp2=temp2.append("\"").append(actual).append("\"").append(",");
							//System.out.println(actual);
							output.put("flag_for_execution", "Completed");
							output.put("Time", (end-start) + " Millis");
						}
						catch(PathNotFoundException e)
						{
							temp2=temp2.append("\"").append("###").append("\"").append(",");
							//e.printStackTrace();
							//output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
						}
					}
					
				}
			}
			//temp2=temp2.append("\"").append("State["+k+"]").append("\"").append(",");
			insterQuery=insterQuery.replace("temp2", temp2.substring(0, temp2.length() - 1));
			temp2=temp2.delete(0, temp2.length());
			queryList.add(insterQuery);
					System.out.println(insterQuery);
			return queryList;
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
	}
	
	public LinkedHashMap<Integer,LinkedHashMap<String, String>> CompareFunction(LinkedHashMap<Integer,LinkedHashMap<String, String>> inputrow, LinkedHashMap<String, String> output) throws APIException
	{
		
		return inputrow;
	
	}

	@SuppressWarnings({ "resource" })
	private String enable_read(String file_location) throws RequestFormatException
	{
		StringBuffer si = new StringBuffer();
		BufferedReader read_file = null;
		try 
		{
			//read_file = new FileReader(file_location);
			read_file = new BufferedReader(new InputStreamReader(new FileInputStream(file_location), "UTF-8"));
			String s = null;			

			while (( s=read_file.readLine())!=null)
		    {
					si=si.append(s);
		          // System.out.println(s);
		    }
		} 
		catch (IOException e) 
		{
			throw new RequestFormatException("ERROR OCCURS WHILE SPECIFIED JSON FILEPATH = " + file_location +" TO READ", e);
		}
		
		read_file = null;
		//System.out.println(obj.toJSONString());
		//System.out.println(si);
		return si.toString();
		
	}
}