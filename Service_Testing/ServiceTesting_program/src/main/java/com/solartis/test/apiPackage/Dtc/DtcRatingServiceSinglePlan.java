package com.solartis.test.apiPackage.Dtc;

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
import com.solartis.test.macroPackage.DtcRatingSinglePlan;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.JsonHandle;

public class DtcRatingServiceSinglePlan extends BaseClass implements API 
{
 MacroInterface macro = null;
 public DtcRatingServiceSinglePlan(PropertiesHandle config) throws SQLException, MacroException
 {
  this.config = config;
  jsonElements = new LinkedHashMap<String, String>();
  
     InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
	OutputColVerify = new DBColoumnVerify("OutputColumnCondtn");	
	StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	if(config.getProperty("status").equals("Y"))
	{
		macro=new DtcRatingSinglePlan(config);	
	}
 }
 
 public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
 {
		if(config.getProperty("status").equals("Y"))
		{
			try 
			{
				macro.LoadSampleRatingmodel(config, InputData);		
				macro.GenerateExpected(InputData, config);
			} catch (MacroException e) 
			{
				throw new APIException("ERROR LoadSampleRequest FUNCTION -- GL-RATING CLASS", e);
			}
		}
		super.LoadSampleRequest(InputData);
}
 
 public void PumpDataToRequest(LinkedHashMap<String, String> InputData) throws  APIException
	{			
		if(config.getProperty("status").equals("Y"))
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
		super.PumpDataToRequest(InputData);
	}

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
		  throw new APIException("ERROR ADD HEADER FUNCTION -- DTC-RatingService CLASS", e);
	  }
 }

 @Override
 public void SendAndReceiveData() throws APIException
 {
  String input_data= null;
  
  try
  {
    input_data = request.FileToString();
    http.SendData(input_data);  
    String response_string = null; 
    response_string = http.ReceiveData();  
    response = new JsonHandle(config.getProperty("response_location")+input.get("Testdata")+".json");  
    response.StringToFile(response_string);
  }
  catch(HTTPHandleException | RequestFormatException e)
  {
	  throw new APIException("ERROR SendAndReceiveData FUNCTION -- DTC-RatingService CLASS", e);
  }
   
  
 }

 @Override
 public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output)   throws APIException
 {
	try
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
			output.put("AnalyserResult","Rule-"+RuleName);
			output.put("User_message",Message);
		}
		if(config.getProperty("status").equals("Y"))
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
 
 
 public LinkedHashMap<String, String> CompareFunction(LinkedHashMap<String, String> inputrow,LinkedHashMap<String, String> outputrow) throws APIException
	{		 
	 if(outputrow.get("Flag_for_execution").equals("SUCCESS"))
	{		
		
	    try
	    {
	    	LinkedHashMap<Integer, LinkedHashMap<String, String>> tableStatusColVerify = StatusColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
	    	for (Entry<Integer, LinkedHashMap<String, String>> entry : tableStatusColVerify.entrySet()) 	
			{	
			    LinkedHashMap<String, String> rowStatusColVerify = entry.getValue();
			    String condition = rowStatusColVerify.get("OutputColumnCondtn");
			    System.out.println(condition+"---------------"+outputrow);
			    if(conditioncheck.ConditionReading(condition, inputrow) && (rowStatusColVerify.get("Comaparision_Flag").equalsIgnoreCase("Y")))
				{
					String ExpectedColumn = rowStatusColVerify.get(config.getProperty("ExpectedColumn"));
					String ActualColumn = rowStatusColVerify.get(config.getProperty("OutputColumn"));
					String StatusColumn = rowStatusColVerify.get(config.getProperty("StatusColumn"));
					if(!(StatusColumn.equals("")) && !(ExpectedColumn.equals("")))
					{
						if(premium_comp(outputrow.get(ExpectedColumn),outputrow.get(ActualColumn)))
						{
							outputrow.put(StatusColumn, "Pass");
						}
						else
						{
							
							outputrow.put(StatusColumn, "Fail");
							//outputrow.UpdateRow();
							analyse(rowStatusColVerify,outputrow);
						}
					}
				}
			}
	    	
	    	
			String message = "";
			for(int i=0;i<errorMessage.size();i++)
			{
				message=message+errorMessage.get(i)+" & ";
			}
			if(message.equals(""))
			{
				outputrow.put("AnalyserResult", "Pass");
			}
			else
			{
				outputrow.put("AnalyserResult", message.substring(0, message.length() - 2)+" Failed");
			}
			errorMessage.clear();
			errorParentname.clear();
			return outputrow;

	    }	
	    catch(DatabaseException e)
	    {
	    	System.out.println(e);
	    	throw new APIException("ERROR IN DB COMPARISON FUNCTION -- BASE CLASS", e);
	    }
	}
	 return outputrow;
 }
}