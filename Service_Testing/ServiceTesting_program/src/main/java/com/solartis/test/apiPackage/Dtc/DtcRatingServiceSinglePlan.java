package com.solartis.test.apiPackage.Dtc;

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
import com.solartis.test.macroPackage.StarrGLMacro;
import com.solartis.test.macroPackage.AssistCard;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.JsonHandle;
import com.solartis.test.util.common.DatabaseOperation;

public class DtcRatingServiceSinglePlan extends BaseClass implements API
{
	MacroInterface macro = null;
	public DtcRatingServiceSinglePlan(PropertiesHandle config) throws APIException
	{
	    this.config = config;
		jsonElements = new DatabaseOperation();
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		if(config.getProperty("status").equals("Y"))
		{
		//macro=new AssistCard(config);	
		}
		
	}
	
	public void LoadSampleRequest(DatabaseOperation InputData) throws APIException
	{
		try
		{
		 	  this.input = InputData;
			  input = InputData;
			  
			 
			 
			  switch(InputData.ReadData("No_of_travelers"))
			   
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
		catch(DatabaseException | MacroException e)
		{
			 throw new APIException("ERROR LoadSampleRequest FUNCTION -- DTC-RatingService CLASS", e);
		}
	}
	
	public void PumpDataToRequest() throws  APIException
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
		super.PumpDataToRequest();
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
    	catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- GL-RATING CLASS", e);
		}
	}
	
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output) throws APIException
	{
		if(config.getProperty("status").equals("Y"))
		{
			try 
			{
				macro.PumpoutData(output, input, config);
			} 
			catch (POIException | MacroException | DatabaseException e) 
			{
				throw new APIException("ERROR SendResponseDataToFile FUNCTION -- GL-RATING CLASS", e);
			}
		}
		
			try
			{
				String responseStatus = response.read("..ResponseStatus").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
				System.out.println(responseStatus);
				this.output=output;
				OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
				do 	
				{
				  if(OutputColVerify.DbCol(input) && (OutputColVerify.ReadData("Flag").equalsIgnoreCase("Y")))
					{
					try
						{
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
						output.WriteData("flag_for_execution", responseStatus);
						output.WriteData("ErrorMessage", response.read("..Message").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\",""));
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