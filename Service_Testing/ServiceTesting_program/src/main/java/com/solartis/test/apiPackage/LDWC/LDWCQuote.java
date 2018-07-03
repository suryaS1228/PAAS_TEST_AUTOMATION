package com.solartis.test.apiPackage.LDWC;

import java.io.IOException;
import java.util.Iterator;
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
import com.solartis.test.exception.PropertiesHandleException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.RequestHandler;
import com.solartis.test.util.api.XmlHandle;
import com.solartis.test.util.common.DatabaseOperation;

import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class LDWCQuote extends BaseClass implements API
{
	MacroInterface macro = null;
	public LDWCQuote(PropertiesHandle config) throws APIException
	{
	    this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify("OutputColumnCondtn");	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		
		if(config.getProperty("Execution_Flag").equals("ExpectedOnly")||config.getProperty("Execution_Flag").equals("Comparison"))
		{
		//macro=new LDWCMacro(config);	
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
	
	public void PumpDataToRequest(LinkedHashMap<String, String> InputData) throws  APIException
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
			 try
				{
					LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
					sampleInput.LoadData(tableInputColVerify, InputData);
					sampleInput.PumpinDatatoRequest(tableInputColVerify,InputData);	
					sampleInput.saveJsontoPath(config.getProperty("request_location")+input.get("Testdata")+".xml");
				}
					
				catch(DatabaseException | TemplateException | IOException  e)
				{
					throw new APIException("ERROR OCCURS IN PUMPDATATOREQUEST FUNCTION -- BASE CLASS", e);
				}
		 }
	}
	
	public String RequestToString(String Token) throws APIException
	{
	  try 
	  {
		  request = new XmlHandle(config.getProperty("request_location")+input.get("Testdata")+".xml");
		  //request.write("$..Token", Token);
		  return request.FileToString();
	  } 
	  catch (RequestFormatException e)
	  {
		  throw new APIException("ERROR OCCURS IN REQUEST TO STRING FUNCTION -- BASE CLASS", e);
	   }
	}
	
	@Override
	public void AddHeaders(String Token) throws APIException
	{
		try
		{
			http = new HttpHandle(config.getProperty("test_url"),"POST");
			http.AddHeader("Content-Type", "application/xml");
		}
    	catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- GL-RATING CLASS", e);
		}
	}
	
	public void SendAndReceiveData() throws APIException 
	{
		try
		{
			String input_data= null;
			input_data = request.FileToString();
		    http.SendData(input_data);
			String response_string = http.ReceiveData();	
			response = new XmlHandle(config.getProperty("response_location")+input.get("Testdata")+".xml");
			response.StringToFile(response_string);
		}
		catch(RequestFormatException | HTTPHandleException e)
		{
			System.out.println(e);
			throw new APIException("ERROR IN SEND AND RECIEVE DATA FUNCTION -- BASE CLASS", e);
		}
	}
	
//-------------------------------------------------------------CONVERTING RESPONSE TO STRING------------------------------------------------------------
	public String ResponseToString() throws APIException 
	{
		try
		{
			return response.FileToString();
		}
		catch(RequestFormatException e)
		{
			throw new APIException("ERROR IN RESPONSE TO STRING FUNCTION -- BASE CLASS", e);
		}
	}
	
//-----------------------------------------------------------UPDATING RESPONSE DATA TO DATABASE---------------------------------------------------------	

	
	@Override
	 public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output)   throws APIException
	 {
		try
		{
			if(config.getProperty("Execution_Flag").equals("ActualOnly")||config.getProperty("Execution_Flag").equals("ActualandComparison")||config.getProperty("Execution_Flag").equals("Comparison")||config.getProperty("Execution_Flag").equals("ResponseOnly"))
			{
				LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
				
				String ResponseStatus=response.read("//MsgStatusCd");
				if(ResponseStatus.equals("Error"))
				{					
						output.put("Flag_for_execution", "FailedResponse");
						String RuleName=response.read("//MsgStatusCd");
						String Message=response.read("//MsgErrorCd");
						String MessageStatusDes=response.read("//MsgStatusDesc");
						output.put("AnalyserResult","Rule-"+RuleName);
						output.put("MsgErrorCd",Message);	
						output.put("MsgStatusCd",RuleName);
						output.put("MsgStatusDesc",MessageStatusDes);
				}
				else
				{
					for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
					{
						LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
	    				if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&conditioncheck.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
						{
							try
							{
							
								String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))));
				
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
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws DatabaseException, PropertiesHandleException, ClassNotFoundException, TemplateNotFoundException, MalformedTemplateNameException, IOException, TemplateException
	  {
	 	 PropertiesHandle config = new PropertiesHandle("E:\\RestFullAPIDeliverable\\Devolpement\\admin\\STARR-LDWC\\rating\\config\\config.properties");
	 	 DatabaseOperation input = new DatabaseOperation();
	 	 input.ConnectionSetup(config);
	 		LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable = input.GetDataObjects("SELECT * FROM INPUT_Rating_LDWC");
	 		Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator = inputtable.entrySet().iterator();
	 		while (inputtableiterator.hasNext()) 
	 		{
	 			Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
	 			LinkedHashMap<String, String> inputrow = inputentry.getValue();
	 			
	 			DatabaseOperation requestconfig = new DatabaseOperation();
	 			LinkedHashMap<Integer, LinkedHashMap<String, String>> requstdetail = requestconfig.GetDataObjects("Select * from ConditionInputTable_LDWC_Rate");
	 			
	 			
	 			 RequestHandler req = new RequestHandler(config);
	 			 req.openTemplate();
	 			 req.LoadData(requstdetail, inputrow);
	 			 req.PumpinDatatoRequest(requstdetail, inputrow);
	 			 req.saveJsontoPath("E:\\RestFullAPIDeliverable\\Devolpement\\admin\\STARR-LDWC\\rating\\Results\\Request\\Testdata1.xml");
	 		}
	 }
}