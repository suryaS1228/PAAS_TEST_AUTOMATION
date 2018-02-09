package com.solartis.test.apiPackage.LDWC;

<<<<<<< HEAD
=======
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Map.Entry;
>>>>>>> refs/remotes/origin/Testng
import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.util.api.*;

import java.util.UUID;



public class LDWCRating extends BaseClass implements API
{
	protected String numofclasscode;
	public LDWCRating(PropertiesHandle config)
	{
		this.config = config;
		XmlElements = new LinkedHashMap<String, String>();
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		
	}
	
	@Override
<<<<<<< HEAD
	public void LoadSampleRequest(DatabaseOperation InputData) throws APIException
=======
	public void LoadSampleRequest(LinkedHashMap<String, String> InputData)
>>>>>>> refs/remotes/origin/Testng
	{	 
		this.input = InputData;	
		sampleInput = new XmlHandle(config.getProperty("sample_request")+ "request.xml");
		
		//classcode=InputData.ReadData("Class_code");
		try
		{
		 numofclasscode = InputData.ReadData("classcode");
		}
		catch(Exception e)
		{
			
		}
		//int numofclasscode=numclasscode.length;
		switch(numofclasscode)
		   
		 {
		    case "1":   sampleInput = new XmlHandle(config.getProperty("sample_request")+"request1.xml"); break;
		    case "2":   sampleInput = new XmlHandle(config.getProperty("sample_request")+"request2.xml"); break;
		    case "3":   sampleInput = new XmlHandle(config.getProperty("sample_request")+"request3.xml"); break;
		    case "4":   sampleInput = new XmlHandle(config.getProperty("sample_request")+"request4.xml"); break;
		    case "5":   sampleInput = new XmlHandle(config.getProperty("sample_request")+"request5.xml"); break;
		    
		    default:
		 }
	}
		
	@Override
	public void PumpDataToRequest() throws APIException
	{
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
			request = new XmlHandle(config.getProperty("request_location")+input.get("testdata")+"_request"+".xml");
			request.StringToFile(sampleInput.FileToString());
			String Reqid = UUID.randomUUID().toString();
		    System.out.println(Reqid);
			request.write("//InsuranceSvcRq/RqUID",Reqid);
<<<<<<< HEAD
			input.WriteData("RequestUID", Reqid);
			System.out.println(InputColVerify.ReadData(config.getProperty("InputJsonPath")));//+".............."+ input.ReadData(InputColVerify.ReadData(config.getProperty("InputColumn"))));
=======
			input.put("RequestUID", Reqid);
>>>>>>> refs/remotes/origin/Testng
			
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableInputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowInputColVerify = entry.getValue();
				if(InputColVerify.DbCol(rowInputColVerify) && (rowInputColVerify.get("Flag").equalsIgnoreCase("Y")))
				{
					if(!input.get(rowInputColVerify.get(config.getProperty("InputColumn"))).equals(""))
					{
						request.write(rowInputColVerify.get(config.getProperty("InputJsonPath")), input.get(rowInputColVerify.get(config.getProperty("InputColumn"))));
					}
				}	
			}
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR OCCURS IN PUMPDATATOREQUEST FUNCTION -- LDWC RATING CLASS", e);
		}
		
	}
	
	@Override
	public void AddHeaders() throws APIException
	{
		try 
		{
			http = new HttpHandle(config.getProperty("test_url"),"POST");
			http.AddHeader("Content-Type", config.getProperty("content_type"));
		}
		catch (HTTPHandleException e) 
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- LDWC RATING CLASS", e);
		}
	}

	@Override
	public void SendAndReceiveData() throws APIException
	{
		try
		{
			String input_data = request.FileToString();
			http.SendData(input_data);
			String response_string = http.ReceiveData();
			response = new XmlHandle(config.getProperty("response_location")+input.get("testdata")+"_response"+".xml");
			response.StringToFile(response_string);
		}
		catch(RequestFormatException | HTTPHandleException e)
		{
			throw new APIException("ERROR IN SEND AND RECIEVE DATA FUNCTION -- BASE CLASS", e);
		}
	}

	@Override
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		try
		{ 
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				if(OutputColVerify.DbCol(rowOutputColVerify) && (rowOutputColVerify.get("Flag").equalsIgnoreCase("Y")))
				{
					 try
				      {	
					   System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
					   String actual = (response.read((rowOutputColVerify.get(config.getProperty("OutputJsonPath")))).replaceAll("\\[\"","")).replaceAll("\"\\]","").replaceAll("\\\\","");
					   output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
					   output.put("flag_for_execution", "Completed");
				      }
						catch(PathNotFoundException e)
						{
							output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
						}
					}
			}
	
				return output;	
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- LDWC RATING CLASS", e);
		}
		
	}
	
<<<<<<< HEAD
=======
	  public String RqUID()
	  {
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            @SuppressWarnings("unused")
				StringBuffer s = randStr.append(ch);
	            }
	        return randStr.toString();
	  }
	     
	  private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        }else {
	            return randomInt - 1;
	             }
	  }
>>>>>>> refs/remotes/origin/Testng
}

