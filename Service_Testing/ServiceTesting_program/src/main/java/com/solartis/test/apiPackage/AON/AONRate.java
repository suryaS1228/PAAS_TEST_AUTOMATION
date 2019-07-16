package com.solartis.test.apiPackage.AON;

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
import com.solartis.test.macroPackage.AONMacro;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.macroPackage.coverWalletMacro;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;

public class AONRate extends BaseClass implements API
{
	MacroInterface macro = null;
	public AONRate(PropertiesHandle config) throws MacroException
	{
		
			this.config = config;
			jsonElements = new LinkedHashMap<String, String>();
		
			InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
			OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
			StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
			if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
			{
			macro=new AONMacro(config);	
			}
			
	}
		
		/*public String tokenGenerator(PropertiesHandle config)
		{
			String Token="";
			try
			{
				System.out.println(config.getProperty("AuthenticationURL"));
				HttpHandle http = new HttpHandle(config.getProperty("AuthenticationURL"),"POST");
				http.AddHeader("Content-Type", config.getProperty("content_type"));
			   String input_data = "{  \"ServiceRequestDetail\": { \"OwnerId\": \""+config.getProperty("OwnerID")+"\", \"ResponseType\": \"JSON\", \"BrowserIp\": \"192.168.5.98\", \"ServiceRequestVersion\": \"2.0\" }, \"UserCredential\": { \"UserName\": \""+config.getProperty("Userneme")+"\",    \"Password\": \""+config.getProperty("Password")+"\"  } }";
				//System.out.println(input_data);
			   http.SendData(input_data);
				String response_string = http.ReceiveData().toString();	
				System.out.println(input_data+"/n/n/n"+response_string);
				JsonHandle response = new JsonHandle();
				//response.StringToFile(response_string);
				//response.FileToString();
				Token = Token+response.readToken("$..Token",response_string).replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
				System.out.println("Token is--------------------"+Token);
			}
			catch(Exception e)
			{
				System.out.println("Error in Generating Token");
				e.printStackTrace();
			}
			return Token;
			
		}*/
	public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
	{
			 this.input = InputData;
			 if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
				{
					try 
					{
						macro.LoadSampleRatingmodel(config, InputData);		
						System.out.println("After Load Sample Rating Model");
						macro.GenerateExpected(InputData, config);
					} catch (MacroException e) 
					{
						throw new APIException("ERROR LoadSampleRequest FUNCTION -- GL-RATING CLASS", e);
					}
				}
			 if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("ActualandComparison")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ResponseOnly"))
			 {
				super.LoadSampleRequest(InputData);
			 }
	}
		
	public void PumpDataToRequest(LinkedHashMap<String, String> Commanmap,LinkedHashMap<String, String> InputData) throws APIException
	{	
			
			try
			{
				if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
				{
				macro.PumpinData(InputData, config);	
				}
			  if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("ActualandComparison")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ResponseOnly"))
			   {
				  super.PumpDataToRequest(Commanmap,InputData);
			   }

			}
			catch(DatabaseException | POIException | MacroException  e)
			{
				throw new APIException("ERROR OCCURS IN PUMPDATATOREQUEST FUNCTION -- Coverwallet CLASS", e);
			}
	}
		
	@Override
	public void AddHeaders(String Token) throws APIException 
	{
			try
			{
			  http = new HttpHandle(config.getProperty("test_url"),"POST");
			  http.AddHeader("Content-Type", config.getProperty("content_type"));
			  http.AddHeader("Token", Token);
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
			try
			{
				 if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ActualandComparison"))
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
					
					String Message=response.read("..Message").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
					String Message2=response.read("..UserMessage").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
					output.put("User_message",Message);
					output.put("User_message2",Message2);
					output.put("AnalyserResult","Rule-"+Message2);
					
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
