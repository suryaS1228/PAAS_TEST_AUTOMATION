package com.solartis.test.apiPackage.AC;

import java.sql.SQLException;

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
import com.solartis.test.macroPackage.AssistCard;
import com.solartis.test.macroPackage.DtcMacro;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.util.api.*;
import com.solartis.test.util.common.*;

public class ACRatingService extends BaseClass implements API 
{

 MacroInterface macro = null;
 public ACRatingService(PropertiesHandle config) throws SQLException, MacroException
 {
  this.config = config;
  jsonElements = new DatabaseOperation();
  
     InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
	OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
	StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	if(config.getProperty("status").equals("Y"))
	{
	macro=new AssistCard(config);	
	}
 }
 
 @Override
 public void LoadSampleRequest(DatabaseOperation InputData) throws  APIException
 {
	try
	{
	 	  this.input = InputData;
		  input = InputData;
		  
		
		  String noofTravelers = InputData.ReadData("No_of_travelers");
		  switch(noofTravelers)
		   
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
		    	System.out.println("invalid request");
		   }
		  if(config.getProperty("status").equals("Y"))
			{
				  macro.LoadSampleRatingmodel(config, InputData);  //Load sample rating model 
				  macro.GenerateExpected(InputData, config);        //Generate expected rating models  
			}
	}
	catch(DatabaseException | MacroException e)
	{
		 throw new APIException("ERROR LoadSampleRequest FUNCTION -- DTC-RatingService CLASS", e);
	}
 }

    @Override
 public void PumpDataToRequest() throws APIException
 {
		
		if(config.getProperty("status").equals("Y"))
		{
			try 
			{
				macro.PumpinData(input, config);
			} 
			catch (DatabaseException | POIException | MacroException e) 
			{
				throw new APIException("ERROR PumpDataToRequest FUNCTION -- GL-RATING CLASS", e);
			}
		}
    	try
		{
			InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
			request = new JsonHandle(config.getProperty("request_location")+input.ReadData("testdata")+"_request_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_type")+".json");
			request.StringToFile(sampleInput.FileToString());
			do
			{
				if(InputColVerify.DbCol(input)&& (InputColVerify.ReadData("Flag").equalsIgnoreCase("Y")))
				{
					if(!input.ReadData(InputColVerify.ReadData(config.getProperty("InputColumn"))).equals(""))
					{
						request.write(InputColVerify.ReadData(config.getProperty("InputJsonPath")), input.ReadData(InputColVerify.ReadData(config.getProperty("InputColumn"))));
					}
				}	
			}while(InputColVerify.MoveForward());
			
		}
		catch(DatabaseException | RequestFormatException   e)
		{
			throw new APIException("ERROR OCCURS IN PUMPDATATOREQUEST FUNCTION -- DTC-SAVEDETAILS1 CLASS", e);
		}
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
    response = new JsonHandle(config.getProperty("response_location")+input.ReadData("testdata")+"_response_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_name")+".json");  
    response.StringToFile(response_string);
  }
  catch(HTTPHandleException | RequestFormatException | DatabaseException e)
  {
	  throw new APIException("ERROR SendAndReceiveData FUNCTION -- DTC-RatingService CLASS", e);
  }
 }

 @Override
 public DatabaseOperation SendResponseDataToFile(DatabaseOperation output)   throws APIException
 {
	try
	{
		OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
			do 	
			{
			  if(OutputColVerify.DbCol(input)&& (OutputColVerify.ReadData("Flag").equalsIgnoreCase("Y")))
				{
				try
					{
					System.out.println(OutputColVerify.ReadData(config.getProperty("OutputColumn")));
					System.out.println(config.getProperty("OutputColumn")); 
					System.out.println(OutputColVerify.ReadData(config.getProperty("OutputColumn")));
					String actual = (response.read(OutputColVerify.ReadData(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
	
					System.out.println(actual);
					output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), actual);
					System.out.println(actual);
					output.WriteData("flag_for_execution", "Completed");
					}
					catch(PathNotFoundException | RequestFormatException e)
					{
						output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), "Path not Found");
					}
				}
			}while(OutputColVerify.MoveForward());
		
			macro.PumpoutData(output, input, config);   //	data pumped out from expected rating model to db table
	}
	catch(DatabaseException | POIException | MacroException e)
	{
		 throw new APIException("ERROR SendResponseDataToFile FUNCTION -- DTC-RatingService CLASS", e);
	}
	return output;
}
}