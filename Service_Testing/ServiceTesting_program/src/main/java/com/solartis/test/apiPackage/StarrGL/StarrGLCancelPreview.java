package com.solartis.test.apiPackage.StarrGL;
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
import com.solartis.test.exception.MacroException;
import com.solartis.test.exception.POIException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.macroPackage.StarrGLCancelPreviewMacro;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;

public class StarrGLCancelPreview extends BaseClass implements API 
{
	MacroInterface macro = null;
	public StarrGLCancelPreview(PropertiesHandle config) throws SQLException, DatabaseException, MacroException
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		
		macro=new StarrGLCancelPreviewMacro(config);	
		
	}
	
	public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
	{
		
			try 
			{
				macro.LoadSampleRatingmodel(config, InputData);		
				macro.GenerateExpected(InputData, config);
			} catch (MacroException e) 
			{
				throw new APIException("ERROR LoadSampleRequest FUNCTION -- GL-RATING CLASS", e);
			}
		
		super.LoadSampleRequest(InputData);
	}
	
	public void PumpDataToRequest(LinkedHashMap<String, String> InputData) throws  APIException
	{			
		
			try 
			{
				macro.PumpinData(input, config);
			} 
			catch (DatabaseException | POIException | MacroException e) 
			{
				throw new APIException("ERROR PumpDataToRequest FUNCTION -- GL-RATING CLASS");
			}
		
		super.PumpDataToRequest(InputData);
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
		  http.AddHeader("EventVersion", config.getProperty("EventVersion"));
		 }
		catch(HTTPHandleException e)
		{
			throw new APIException("ERROR OCCURS IN AddHeaders FUNCTION -- Coverwallet CLASS", e);
		}
	 }
	
	@Override
	 public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output)   throws APIException
	 {
		DBColoumnVerify conditioncheck = new DBColoumnVerify();
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
			
			String ResponseStatus=response.read("..ResponseStatus").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
			if(ResponseStatus.equals("SUCCESS"))
			{
			
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				  if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&conditioncheck.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
					{
					try
						{
					
						String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
		
						output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
						output.put("Flag_for_execution", ResponseStatus);
						}
						catch(PathNotFoundException | RequestFormatException e)
						{
							output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
						}
					}
			}
			}
			else
			{
				output.put("Flag_for_execution", "FailedResponse");
				
				String RuleName=response.read("..RuleName").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
				String Message=response.read("..Message").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
				if(Message.equals("Server Busy, Request cannot be processed right now"))
				{
					output.put("AnalyserResult","Error-ServerBusy");
				}
				output.put("AnalyserResult","Rule-"+RuleName);
				output.put("Rule_message",Message);
			}
			
				macro.PumpoutData(output, input, config);   //	data pumped out from expected rating model to db table
			
		}
		catch(DatabaseException | POIException | MacroException | RequestFormatException e)
		{
			 throw new APIException("ERROR SendResponseDataToFile FUNCTION -- DTC-RatingService CLASS", e);
		}
		return output;
	}
}