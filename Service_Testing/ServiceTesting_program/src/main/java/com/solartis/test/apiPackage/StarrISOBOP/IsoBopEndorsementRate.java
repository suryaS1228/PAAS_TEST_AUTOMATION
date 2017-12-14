package com.solartis.test.apiPackage.StarrISOBOP;

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
import com.solartis.test.util.common.DatabaseOperation;

public class IsoBopEndorsementRate extends BaseClass implements API 
{
	public IsoBopEndorsementRate(PropertiesHandle config)
	{
		this.config = config;
		jsonElements = new DatabaseOperation();

		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	}
	
	@Override
	public void AddHeaders() throws APIException
	{
		try 
		{
			http = new HttpHandle(config.getProperty("test_url"),"POST");
			http.AddHeader("Content-Type", config.getProperty("content_type"));
			http.AddHeader("Token", config.getProperty("token"));
			http.AddHeader("EventName", config.getProperty("EventName"));
		}
		catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- ISO-BOPENDROSEMENTRATE CLASS", e);
		}
	}
	
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output) throws APIException
	{
		try
		{
			this.output=output;
			OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
			do 	
			{
			  if(OutputColVerify.DbCol(input) && (OutputColVerify.ReadData("Flag").equalsIgnoreCase("Y")))
				{
				try
					{
					String responseStatus=response.read("..ResponseStatus").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
					System.out.println(responseStatus);
					if(responseStatus.equals("SUCCESS"))
					{
					System.out.println(OutputColVerify.ReadData(config.getProperty("OutputColumn")));
					String actual = (response.read(OutputColVerify.ReadData(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
					output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), actual);
					System.out.println(actual);
					output.WriteData("flag_for_execution", "Completed");
					}
					else
					{
						output.WriteData("flag_for_execution",responseStatus);
						//String UserMessage=(response.read("..UserMessage").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
						String UserMessage=(response.read("..RuleName").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
						output.WriteData("User_message", UserMessage);
					}
					}
					catch(PathNotFoundException e)
					{
						output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), "Path not Found");
					}
				}
			}while(OutputColVerify.MoveForward());
		
			
			return output;
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
	}
}

