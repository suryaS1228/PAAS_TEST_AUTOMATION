package com.solartis.test.apiPackage.CommercialAuto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.jayway.jsonpath.PathNotFoundException;
import com.mysql.jdbc.Statement;
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
import com.solartis.test.macroPackage.MarineGL;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.RequestHandler;
import com.solartis.test.util.common.DatabaseOperation;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class CommercialAuto_Rating extends BaseClass implements API 
{
 MacroInterface macro = null;
 Statement stmt =null;
 public CommercialAuto_Rating(PropertiesHandle config) throws SQLException, MacroException
 {
  this.config = config;
  jsonElements = new LinkedHashMap<String, String>();
  
     InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
	OutputColVerify = new DBColoumnVerify("OutputColumnCondtn");	
	StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	
	if(config.getProperty("ComparisonFlag").equals("Y"))
	{
		macro=new MarineGL(config);	
	}
 }
 
 public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
 {
		if(config.getProperty("ComparisonFlag").equals("Y"))
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
		if(config.getProperty("ComparisonFlag").equals("Y"))
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
	 
	 String actual="";
	 String updatequery=null;
	 LinkedHashMap<String, String> rowOutputColVerify =null;
	try
	{
		stmt = (Statement) DatabaseOperation.conn.createStatement();
		LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
		String ResponseStatus=response.read("..ResponseStatus").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
		if(ResponseStatus.equals("SUCCESS"))
		{
		
		for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
		{
			rowOutputColVerify = entry.getValue();
			//System.out.println("flag is-----"+rowOutputColVerify.get("Flag")+"======"+(rowOutputColVerify.get("Flag").equalsIgnoreCase("Y")));
			//System.out.println("Condition is----"+conditioncheck.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input));
			//System.out.println("boolean is----"+((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&conditioncheck.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input)));
			  if((rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))&&conditioncheck.ConditionReading(rowOutputColVerify.get("OutputColumnCondtn"),input))
				{
				try
					{
				
					try
					{
					actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
					}
					catch(Exception e)
					{
						actual="no value in response";
					}
					updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowOutputColVerify.get("TableName")+"."+rowOutputColVerify.get(config.getProperty("OutputColumn")) +"='"+actual+"' where "+rowOutputColVerify.get("TableName")+".Testdata='"+output.get("Testdata")+"'";
	                try {
	                stmt.executeUpdate(updatequery);
	                }
	                catch(SQLException e)
	                {
	                	System.out.println("error in update query");
	                	e.printStackTrace();
	                }
	                updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowOutputColVerify.get("TableName")+".Flag_for_execution ='"+ResponseStatus+"' where "+rowOutputColVerify.get("TableName")+".Testdata='"+output.get("Testdata")+"'";
					stmt.executeUpdate(updatequery);
	                output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
					output.put("Flag_for_execution", ResponseStatus);
					}
					catch(PathNotFoundException e)
					{
						updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowOutputColVerify.get("TableName")+"."+rowOutputColVerify.get(config.getProperty("OutputColumn")) +"='pathnotfoundException' where "+rowOutputColVerify.get("TableName")+".Testdata='"+output.get("Testdata")+"'";
						stmt.executeUpdate(updatequery);
						output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
					}
				}
		}
		}
		else
		{
			output.put("Flag_for_execution", "FailedResponse");
			 updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowOutputColVerify.get("TableName")+".Flag_for_execution ='FailedResponse' where "+rowOutputColVerify.get("TableName")+".Testdata='"+output.get("Testdata")+"'";
			 stmt.executeUpdate(updatequery);
			String RuleName=response.read("..RuleName").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
			String Message=response.read("..Message").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
			if(Message.equals("Server Busy, Request cannot be processed right now"))
			{
				output.put("AnalyserResult","Error-ServerBusy");
				 updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowOutputColVerify.get("TableName")+".AnalyserResult ='Error-ServerBusy' where "+rowOutputColVerify.get("TableName")+".Testdata='"+output.get("Testdata")+"'";
				 stmt.executeUpdate(updatequery);

			}
			output.put("AnalyserResult","Rule-"+RuleName);
			updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowOutputColVerify.get("TableName")+".AnalyserResult ='"+RuleName+"' where "+rowOutputColVerify.get("TableName")+".Testdata='"+output.get("Testdata")+"'";
			 stmt.executeUpdate(updatequery);
			output.put("User_message",Message);
			 updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowOutputColVerify.get("TableName")+".User_message ='"+Message+"' where "+rowOutputColVerify.get("TableName")+".Testdata='"+output.get("Testdata")+"'";
			 stmt.executeUpdate(updatequery);
		}
		if(config.getProperty("ComparisonFlag").equals("Y"))
		{
			macro.PumpoutData(output, input, config);   //	data pumped out from expected rating model to db table
		}
	}
	catch(DatabaseException | POIException | MacroException | RequestFormatException|SQLException e)
	{
		 throw new APIException("ERROR SendResponseDataToFile FUNCTION -- DTC-RatingService CLASS", e);
	}
	return output;
}
//---------------------------------------------------------------COMAPRISION FUNCTION-------------------------------------------------------------------	
	public LinkedHashMap<String, String> CompareFunction(LinkedHashMap<String, String> inputrow,LinkedHashMap<String, String> outputrow) throws APIException
	{		
		String updatequery=null;
		  LinkedHashMap<String, String> rowStatusColVerify=null;
		  
	 if(outputrow.get("Flag_for_execution").equals("SUCCESS"))
	{		
	    try
	    {
	    	stmt = (Statement) DatabaseOperation.conn.createStatement();
	    	LinkedHashMap<Integer, LinkedHashMap<String, String>> tableStatusColVerify = StatusColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
	    	for (Entry<Integer, LinkedHashMap<String, String>> entry : tableStatusColVerify.entrySet()) 	
			{	
			    rowStatusColVerify = entry.getValue();
			    String condition = rowStatusColVerify.get("OutputColumnCondtn");
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
							//
							 updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowStatusColVerify.get("TableName")+"."+StatusColumn+"='Pass' where "+rowStatusColVerify.get("TableName")+".Testdata='"+outputrow.get("Testdata")+"'";
							 stmt.executeUpdate(updatequery);
						}
						else
						{
							outputrow.put(StatusColumn, "Fail");
							//
							 updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowStatusColVerify.get("TableName")+"."+StatusColumn+"='Fail' where "+rowStatusColVerify.get("TableName")+".Testdata='"+outputrow.get("Testdata")+"'";
							 stmt.executeUpdate(updatequery);
							analyse(rowStatusColVerify,outputrow);
						}
					}
				}
			}
			String message = "";
			for(int i=0;i<errorMessage.size();i++)
			{
				message=message+errorMessage.get(i)+",";
			}
			if(message.equals(""))
			{
				outputrow.put("AnalyserResult", "Pass");
				updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowStatusColVerify.get("TableName")+".AnalyserResult ='Pass' where "+rowStatusColVerify.get("TableName")+".Testdata='"+outputrow.get("Testdata")+"'";
				 stmt.executeUpdate(updatequery);
				//
			}
			else
			{
				outputrow.put("AnalyserResult", message.substring(0, message.length() - 2)+" Failed");
				String FailMessage=message.substring(0, message.length() - 2)+" Failed";
				//
				updatequery="update "+ config.getProperty("outputTable")+ " SET "+ rowStatusColVerify.get("TableName")+".AnalyserResult ='"+FailMessage+"' where "+rowStatusColVerify.get("TableName")+".Testdata='"+outputrow.get("Testdata")+"'";
				 stmt.executeUpdate(updatequery);
			}
			errorMessage.clear();
			errorParentname.clear();
			return outputrow;

	    }	
	    catch(DatabaseException|SQLException e)
	    {
	    	throw new APIException("ERROR IN DB COMPARISON FUNCTION -- BASE CLASS", e);
	    }
	}
	 return outputrow;
}
 @SuppressWarnings("static-access")
public static void main(String args[]) throws DatabaseException, PropertiesHandleException, ClassNotFoundException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException
 {
	 PropertiesHandle config = new PropertiesHandle("R:\\RestFullAPIDeliverable\\Devolpement\\admin\\CommercialAuto\\Rating\\Config\\config.properties");
	 DatabaseOperation input = new DatabaseOperation();
	 input.ConnectionSetup(config);
		LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable = input.GetDataObjects("SELECT * FROM INPUT_CA_Rate_Policy a LEFT JOIN INPUT_CA_Rate_TruckDetails b on a.`S_No` = b.`S_No` LEFT JOIN INPUT_CA_Rate_PrivatePassengerDetails c on b.`S_No` = c.`S_No` LEFT JOIN INPUT_CA_Rate_HI_NON_AI d on c.`S_No` = d.`S_No`");
		Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator = inputtable.entrySet().iterator();
		while (inputtableiterator.hasNext()) 
		{
			Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
			LinkedHashMap<String, String> inputrow = inputentry.getValue();
			
			DatabaseOperation requestconfig = new DatabaseOperation();
			LinkedHashMap<Integer, LinkedHashMap<String, String>> requstdetail = requestconfig.GetDataObjects("Select * from `ConditionInputTable_CA_Rate`");
			
			
			 RequestHandler req = new RequestHandler(config);
			 req.openTemplate();
			 req.LoadData(requstdetail, inputrow);
			 req.PumpinDatatoRequest(requstdetail, inputrow);
			 req.saveJsontoPath("R:\\RestFullAPIDeliverable\\Devolpement\\admin\\CommercialAuto\\Rating\\Results\\Request\\Testdata1.json");
		}
 }
 
}