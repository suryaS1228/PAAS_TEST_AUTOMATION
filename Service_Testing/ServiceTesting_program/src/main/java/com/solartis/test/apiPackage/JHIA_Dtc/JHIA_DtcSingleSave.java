package com.solartis.test.apiPackage.JHIA_Dtc;
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
public class JHIA_DtcSingleSave extends BaseClass implements API 
{
	public JHIA_DtcSingleSave(PropertiesHandle config)
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
		}
		catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- DTC-SAVEDETAILS1 CLASS", e);
		}
	}
	@Override
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
			String StatusCode=(response.read("..RequestStatus").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&OutputColVerify.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
				{
				try
					{
				
				if(StatusCode.equals("SUCCESS"))
				{
	
					String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
					output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
					output.put("Flag_for_execution", StatusCode);
					
				}
				else
				{
					String MessageCode=(response.read("..Message").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
					String UserMessage=(response.read("..RuleName").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
					output.put("Flag_for_execution", "Error response");
					output.put("Message_code", MessageCode);
					output.put("User_maessage", UserMessage);
					
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
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- 	DTC-SAVEDETAILS1 CLASS", e);
		}
}
}