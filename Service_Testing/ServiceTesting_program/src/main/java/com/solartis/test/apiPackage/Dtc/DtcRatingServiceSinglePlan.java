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
import com.solartis.test.macroPackage.DtcRatingSinglePlanMacro;
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
			macro=new DtcRatingSinglePlanMacro(config);	
		}
	 }
	 
	 public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
	 {
		 try
			{
			 	  this.input = InputData;
				  input = InputData;
				 
				  switch(InputData.get("No_of_Travelers"))
				   
				   {
				    case "1":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request1.json"); break;
				    case "2":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request2.json"); break;
				    case "3":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request3.json"); break;
				    case "4":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request4.json"); break;
				    case "5":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request5.json"); break;
				    case "6":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request6.json"); break;
				    case "7":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request7.json"); break;
				    case "8":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request8.json"); break;
				    case "9":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request9.json"); break;
				   
				    default:
				   }
				  if(config.getProperty("status").equals("Y"))
					{
						  macro.LoadSampleRatingmodel(config, InputData);  //Load sample rating model 
						  macro.GenerateExpected(InputData, config);        //Generate expected rating models  
					}
			}
			catch(MacroException e)
			{
				 throw new APIException("ERROR LoadSampleRequest FUNCTION -- DTC-RatingService CLASS", e);
	}
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
			super.PumpDataToRequest();
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
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				  if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&OutputColVerify.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
					{
					try
						{
						System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
						System.out.println(config.getProperty("OutputColumn")); 
						System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
						String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
		
						System.out.println(actual);
						output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
						System.out.println(actual);
						output.put("flag_for_execution", "Completed");
						}
						catch(PathNotFoundException | RequestFormatException e)
						{
							output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
						}
					}
				}
			if(config.getProperty("status").equals("Y"))
			{
				macro.PumpoutData(output, input, config);   //	data pumped out from expected rating model to db table
			}
		}
		catch(DatabaseException | POIException | MacroException e)
		{
			 throw new APIException("ERROR SendResponseDataToFile FUNCTION -- DTC-RatingService CLASS", e);
		}
		return output;
	}
}
