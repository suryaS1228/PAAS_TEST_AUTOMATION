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
import com.solartis.test.macroPackage.StarrGLRenewalCancelMacro;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;

public class StarrGLRenewalCancel extends BaseClass implements API 
{
 MacroInterface macro = null;
 public StarrGLRenewalCancel(PropertiesHandle config) throws SQLException, MacroException, DatabaseException
 {
  this.config = config;
  jsonElements = new LinkedHashMap<String, String>();
  
     InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
	OutputColVerify = new DBColoumnVerify("OutputColumnCondtn");	
	StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
	{
		macro=new StarrGLRenewalCancelMacro(config);	
	}
 }
 
 public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
 {
	 this.input = InputData;
		if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
		{
		 	try 
			{
			    macro.LoadSampleRatingmodel(config, InputData);		
				macro.GenerateExpected(InputData, config);
			}
		 	catch (MacroException e) 
			{
				throw new APIException("ERROR LoadSampleRequest FUNCTION -- GL-RATING CLASS", e);
			}
		}
		if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("ActualandComparison")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ResponseOnly"))
		{
		super.LoadSampleRequest(InputData);
		}
}
 
 public void PumpDataToRequest(LinkedHashMap<String, String> Commanmap,LinkedHashMap<String, String> InputData) throws  APIException
	{			
	 if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
		{
			try 
			{
				macro.PumpinData(input, config);
			} 
			catch (DatabaseException | POIException | MacroException e) 
			{
				throw new APIException("ERROR PumpDataToRequest FUNCTION -- GL-RATING CLASS");
			}
		}
	 if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("ActualandComparison")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ResponseOnly"))
	 {
		super.PumpDataToRequest(Commanmap,InputData);
	 }
	}

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
	  catch (HTTPHandleException e) 
	  {
		  throw new APIException("ERROR ADD HEADER FUNCTION -- DTC-RatingService CLASS", e);
	  }
 }



 @Override
 public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output)   throws APIException
 {
	try
	{
		 if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ActualandComparison"))
		 {
		LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
		
		String ResponseStatus=response.read("..RequestStatus").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
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
			output.put("User_maessage",Message);
			output.put("Message_code", RuleName);
		}
		 }
		if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
		{
			macro.PumpoutData(output, input, config);   //	data pumped out from expected rating model to db table
		}
	}
	catch(DatabaseException | POIException | MacroException | RequestFormatException e)
	{
		 throw new APIException("ERROR SendResponseDataToFile FUNCTION -- DTC-RatingService CLASS", e);
	}
	return output;
}
 
 
 
}