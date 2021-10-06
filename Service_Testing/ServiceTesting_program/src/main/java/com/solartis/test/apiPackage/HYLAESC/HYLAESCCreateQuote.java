package com.solartis.test.apiPackage.HYLAESC;
import java.sql.SQLException;
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
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;


public class HYLAESCCreateQuote extends BaseClass implements API 
{
	public HYLAESCCreateQuote(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	}
	
	public void AddHeaders(String Token) throws APIException 
	 {
		try
		{
		  http = new HttpHandle(config.getProperty("test_url"),"POST");
		  http.AddHeader("Content-Type", config.getProperty("content_type"));
		  http.AddHeader("Token", Token);
			http.AddHeader("EventName", config.getProperty("EventName"));
			http.AddHeader("MODE", config.getProperty("MODE"));
			http.AddHeader("OwnerId", config.getProperty("OwnerID"));
			http.AddHeader("Environment", config.getProperty("Environment"));
		 }
		catch(HTTPHandleException e)
		{
			throw new APIException("ERROR OCCURS IN AddHeaders FUNCTION -- Coverwallet CLASS", e);
		}
	 }
	
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		try
		{
			if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("ActualandComparison")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ResponseOnly"))
			{
				LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
				for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
				{
					LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
					if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&OutputColVerify.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
					{
						try
						{
							//System.out.println("Writing Response to Table");
							String responseStatus=response.read("..ResponseStatus").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
							//System.out.println(responseStatus);
							if(responseStatus.equals("Success"))
							{
								//System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
								String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
								output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
								//System.out.println(actual);
								output.put("Flag_for_execution", "Completed");
								output.put("RuleName","");
								output.put("User_message","");
								output.put("Time", (end-start) + " Millis");
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
			}
			return output;
		
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
	}
}