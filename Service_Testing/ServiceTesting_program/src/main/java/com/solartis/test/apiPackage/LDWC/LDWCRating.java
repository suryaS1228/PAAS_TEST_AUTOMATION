package com.solartis.test.apiPackage.LDWC;

import java.io.IOException;
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

import freemarker.template.TemplateException;


public class LDWCRating extends BaseClass implements API
{
	public LDWCRating(PropertiesHandle config)
	{
		this.config = config;
		XmlElements = new LinkedHashMap<String, String>();
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		
	}	
		
	@Override
	public void PumpDataToRequest(LinkedHashMap<String, String> InputData) throws APIException
	{
		try
		{
			
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
			sampleInput.LoadData(tableInputColVerify, InputData);
			sampleInput.PumpinDatatoRequest(tableInputColVerify,InputData);	
			sampleInput.saveJsontoPath(config.getProperty("request_location")+input.get("Testdata")+".xml");
		}
			
		catch(DatabaseException | TemplateException | IOException  e)
		{
			throw new APIException("ERROR OCCURS IN PUMPDATATOREQUEST FUNCTION -- BASE CLASS", e);
		}
		
	}
	
	public String RequestToString() throws APIException
	{
	  try 
	  {
		  request = new XmlHandle(config.getProperty("request_location")+input.get("Testdata")+".xml");
		  return request.FileToString();
	  } 
	  catch (RequestFormatException e)
	  {
		  throw new APIException("ERROR OCCURS IN REQUEST TO STRING FUNCTION -- BASE CLASS", e);
	   }
	}
	
	@Override
	public void AddHeaders() throws APIException
	{
		try 
		{
			http = new HttpHandle(config.getProperty("test_url"),"POST");
			http.AddHeader("Content-Type", config.getProperty("content_type"));
		}
		catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- LDWC RATING CLASS", e);
		}
	}

	@Override
	public void SendAndReceiveData() throws APIException
	{
		try
		{
			String input_data = request.FileToString();
			http.SendData(input_data);
			String response_string = http.ReceiveData();
			response = new XmlHandle(config.getProperty("response_location")+input.get("testdata")+"_response"+".xml");
			response.StringToFile(response_string.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""));
		}
		catch(RequestFormatException | HTTPHandleException e)
		{
			throw new APIException("ERROR IN SEND AND RECIEVE DATA FUNCTION -- BASE CLASS", e);
		}
	}

	@Override
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		try
		{ 
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				if(OutputColVerify.DbCol(rowOutputColVerify) && (rowOutputColVerify.get("Flag").equalsIgnoreCase("Y")))
				{
					 try
				      {	
					   System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
					   String actual = (response.read((rowOutputColVerify.get(config.getProperty("OutputJsonPath")))).replaceAll("\\[\"","")).replaceAll("\"\\]","").replaceAll("\\\\","");
					   output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
					   output.put("flag_for_execution", "Completed");
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
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- LDWC RATING CLASS", e);
		}
		
	}
}

