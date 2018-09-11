package com.solartis.test.apiPackage.StarrGL;


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
import com.solartis.test.macroPackage.StarrGLMacro;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;


public class StarrGLEndorsement extends BaseClass implements API
{
	MacroInterface macro = null;
	public StarrGLEndorsement(PropertiesHandle config) throws  APIException
	{
		 try
		    {
				this.config = config;
				jsonElements = new LinkedHashMap<String, String>();
				
				InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
				OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
				StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
				if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
				{
				macro=new StarrGLMacro(config);	
				}
		    }
		    catch(MacroException e)
		    {
		    	throw new APIException("ERROR INITATING MACRO- GL CLASS", e);
		    }
	}
	
	public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
	{
		if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
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
			http.AddHeader("Token", Token);
			http.AddHeader("EventName", config.getProperty("EventName"));
			http.AddHeader("EventVersion", config.getProperty("EventVersion"));
			System.out.println(config.getProperty("test_url")+config.getProperty("content_type")+config.getProperty("EventName")+config.getProperty("EventVersion"));
		}
    	catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- GL-QUOTE CLASS", e);
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
						if(responseStatus.equals("SUCCESS"))
						{
							//System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
							String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
							output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
							//System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn"))+"----------"+actual);
							output.put("Flag_for_execution", "Completed");
							output.put("MessageType"," ");
							output.put("UserMessage"," ");
							output.put("Time", (end-start) + " Millis");
						}
						else
						{
							output.put("Flag_for_execution",responseStatus);
							output.put("MessageType",response.read("..RuleName"));
							output.put("UserMessage",response.read("..Message"));
							break;
						}
						if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
						{
							macro.PumpoutData(output, input, config);   //	data pumped out from expected rating model to db table
						}
					}
					catch(PathNotFoundException | POIException | MacroException e)
					{
							output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
					}
					}
				}
			}
			//System.out.println(output.get("MessageType"));
			return output;
		
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
}
}
