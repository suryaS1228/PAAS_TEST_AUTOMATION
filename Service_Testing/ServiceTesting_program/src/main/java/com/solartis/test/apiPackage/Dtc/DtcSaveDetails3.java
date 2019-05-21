package com.solartis.test.apiPackage.Dtc;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.util.api.*;

public class DtcSaveDetails3 extends BaseClass implements API
{
	public DtcSaveDetails3(PropertiesHandle config)
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
			http.AddHeader("Token", Token);
			http.AddHeader("EventName", config.getProperty("EventName"));
			System.out.println(config.getProperty("test_url")+config.getProperty("content_type")+config.getProperty("EventName"));
		}
		catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- DTC-SAVEDETAILS3 CLASS", e);
		}
	}
	public void SendAndReceiveData() throws APIException 
	{
		try
		{
			String input_data= null;
			input_data = request.FileToString();
			start = System.currentTimeMillis();
		    http.SendData(input_data);
			String response_string = http.ReceiveData();
		    end = System.currentTimeMillis();
		    //System.out.println("Folder name  ---- "+this.getFolderName(config, input));
		    //System.out.println("Test Data name  --------- "+input.get("Testdata"));
		    response = new JsonHandle(this.getFolderName(config, input)+input.get("Testdata")+"_"+config.getProperty("APIName")+"_response"+".json");
		    response.StringToFile(response_string);
		}
		catch(RequestFormatException | HTTPHandleException e)
		{
			e.printStackTrace();
			throw new APIException("ERROR IN SEND AND RECIEVE DATA FUNCTION -- BASE CLASS", e);
		}
	}
	
	public String getFolderName(PropertiesHandle config, LinkedHashMap<String, String> InputData) 
	{
		//System.out.println("InputData --------- "+InputData);
		//System.out.println("API Name ----- "+config.getProperty("APIName"));
		String path=(String) config.get("request_response_Location")+"Results/"+"Test_Results/"+config.getProperty("APIName")+"/"+InputData.get("Testdata");	
		//System.out.println("path -------- " + path);
		if (new File(path).exists())
		{
			/*for(File file: new File(path).listFiles()) 
			    if (!file.isDirectory()) 
			        file.delete();*/
		}
		else 
		{
			new File(path).mkdirs();
		}
		//System.out.println(path+"/");
		return path+"/";
		
	}

	@Override
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException 
	{
		try
		{
			String StatusCode=(response.read("..RequestStatus").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&OutputColVerify.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
				{
					 try
				      {	
							if(StatusCode.equals("SUCCESS"))
							{
								System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
								String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
								output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
								System.out.println(actual);
								output.put("Flag_for_execution", StatusCode);
								
							}
							else
							{
								String MessageCode=(response.read("..Message").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
								String UserMessage=(response.read("..RuleName").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
								output.put("Flag_for_execution", "Error response");
								output.put("Message_code", MessageCode);
								output.put("User_message", UserMessage);
								
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
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- 	DTC-SAVEDETAILS3 CLASS", e);
		}
}
}

