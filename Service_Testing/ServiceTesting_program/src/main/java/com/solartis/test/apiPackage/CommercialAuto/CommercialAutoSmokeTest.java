package com.solartis.test.apiPackage.CommercialAuto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API2;
import com.solartis.test.apiPackage.BaseClass2;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.JsonHandle;

public class CommercialAutoSmokeTest extends BaseClass2 implements API2  
{
	JsonHandle expectedresponse= null;
	public CommercialAutoSmokeTest(PropertiesHandle config) throws SQLException	
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	}
/*	public String readFileAsStringRequest(String data) throws IOException
  {     	
    return Files.readAllBytes(Paths.get(data)).toString();			
  } */		
	public void LoadSampleRequest(LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException
	{	
		Map.Entry<Integer,LinkedHashMap<String, String>>  entry = InputData.entrySet().iterator().next();
		this.input = entry.getValue();
		/*try
		{
			sampleInput = new RequestHandler(config);
			sampleInput.openTemplate();
		}
		catch(IOException|ClassNotFoundException| DatabaseException e)
		{
			throw new APIException("Error in load sample request", e);
		}*/
	}
	public void PumpDataToRequest(LinkedHashMap<String, String> commonmap,LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException 
	{
		/*try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
			int i=1;
			for (Map.Entry<Integer,LinkedHashMap<String, String>> entry : InputData.entrySet())  
			{
				sampleInput.LoadData(tableInputColVerify, entry.getValue(),i);
				sampleInput.PumpinDatatoRequest(tableInputColVerify,entry.getValue(),commonmap,i);	
				i++;
			}
			sampleInput.saveJsontoPath(config.getProperty("request_location")+input.get("Testdata")+".json");
		}
		catch( DatabaseException| TemplateException| IOException e)
		{
			e.printStackTrace();
			throw new APIException("Error in pumpData to request", e);
		}*/
		
	}
	
	public String RequestToString(String Token) throws APIException
	{
	  try 
	  {   
		  System.out.println(input.get("FileName_Response"));
		  
		  expectedresponse = new JsonHandle("E:\\RestFullAPIDeliverable\\Devolpement\\admin\\CommercialAuto\\CASmoke\\ResponseExisting\\"+input.get("FileName_Response")+".txt"); 
		  request = new JsonHandle("E:\\RestFullAPIDeliverable\\Devolpement\\admin\\CommercialAuto\\CASmoke\\RequestExisting\\"+input.get("FileName_Request")+".txt");
		  return request.FileToString();
		  
	  } 
	  catch (RequestFormatException e)
	  {
		  throw new APIException("ERROR OCCURS IN REQUEST TO STRING FUNCTION -- BASE CLASS", e);
	   }
	}	
	
   public void AddHeaders(String Token) throws APIException 
	 {
		try
		{
		System.out.println(config.getProperty("test_url")+"---"+config.getProperty("EventName"));
		System.out.println(config.getProperty("token"));
		http = new HttpHandle(config.getProperty("test_url"),"POST");
		http.AddHeader("Content-Type", config.getProperty("content_type"));
		http.AddHeader("Token", config.getProperty("AuthenticationToken"));
		http.AddHeader("EventName", config.getProperty("EventName"));
		 }
		catch(HTTPHandleException e)
		{
			e.printStackTrace();
			throw new APIException("ERROR OCCURS IN AddHeaders FUNCTION -- Coverwallet CLASS", e);
		}
	 }
  
   public List<String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
	   List<String> queryList = new ArrayList<String>();
	   try
	   {
		   String Query="";
		   String comparision_output=jsonDifference(expectedresponse.FileToString(),response.FileToString());
		   
		   if(comparision_output.equals("[]"))
		   {
			   Query = "INSERT INTO `OUTPUT_CA_Smoke` VALUES (\""+input.get("S_No")+"\",\""+input.get("TestCaseID")+"\",\""+"Completed\" ,\" Pass\",\""+comparision_output.replaceAll("\"", "\\\\\"").replaceAll(",", "\\,")+"\")"; 			   
		   }
		   else
		   {
			   Query = "INSERT INTO `OUTPUT_CA_Smoke` VALUES (\""+input.get("S_No")+"\",\""+input.get("TestCaseID")+"\",\""+"Completed\" ,\" Fail\",\""+comparision_output.replaceAll("\"", "\\\\\"").replaceAll(",", "\\,")+"\")";
		   }
		   System.out.println(Query);
		   queryList.add(Query);
	   }	   
	   catch (IOException | RequestFormatException e)
	   {
		   System.out.println("Error in JSONcComparision");
	   }  
	   return queryList;
	}
	
    public String jsonDifference(String expected, String actual) throws  IOException 
	    { 
    	 
	        
    	    ObjectMapper jacksonObjectMapper = new ObjectMapper();
	        JsonNode beforeNode = jacksonObjectMapper.readTree(expected);
	       //  System.out.println(beforeNode);
	        JsonNode afterNode = jacksonObjectMapper.readTree(actual);
	       // System.out.println(afterNode);
	        JsonNode patch = JsonDiff.asJson(beforeNode, afterNode);        
	        
	      	
	    return patch.toString();
	    }
	
	public LinkedHashMap<Integer,LinkedHashMap<String, String>> CompareFunction(LinkedHashMap<Integer,LinkedHashMap<String, String>> inputrow, LinkedHashMap<String, String> output) throws APIException
	{
		
		return inputrow;
	
	}
	
	

}
