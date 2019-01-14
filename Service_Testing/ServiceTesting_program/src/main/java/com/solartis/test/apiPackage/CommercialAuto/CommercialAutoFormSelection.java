package com.solartis.test.apiPackage.CommercialAuto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API2;
import com.solartis.test.apiPackage.BaseClass2;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.RequestHandler;

import freemarker.template.TemplateException;


public class CommercialAutoFormSelection extends BaseClass2 implements API2  
{

	public CommercialAutoFormSelection(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
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
	
	@SuppressWarnings("unlikely-arg-type")
	public void LoadSampleRequest(LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException
	{
		this.input = InputData.get("1");
		try
		{
			sampleInput = new RequestHandler(config);
			sampleInput.openTemplate();
		}
		catch(IOException|ClassNotFoundException| DatabaseException e)
		{
			throw new APIException("Error in load sample request", e);
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void PumpDataToRequest(LinkedHashMap<String, String> commonmap,LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException 
	{
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
			for(int i=1 ;i<=InputData.size();i++)
			{
				sampleInput.LoadData(tableInputColVerify, InputData.get(i),i);
				sampleInput.PumpinDatatoRequest(tableInputColVerify,InputData.get(String.valueOf(i)),commonmap,i);	
			}
			sampleInput.saveJsontoPath(config.getProperty("request_location")+input.get("Testdata")+".json");
		}
		catch( DatabaseException| TemplateException| IOException e)
		{
			e.printStackTrace();
			throw new APIException("Error in pumpData to request", e);
		}
		
	}
	
	public List<String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		List<String> queryList = new ArrayList<String>();
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
			String[] vehicleArr = { "", "Truck.", "PublicTransportation.","Special.", "PrivatePassenger.", "ZoneRated."};
			String NoOfStates = response.read("$.State.length()");
			for(int k=0; k<Integer.parseInt(NoOfStates);k++)
			{
				for(int j=0; j<vehicleArr.length;j++)
				{
					String arraylength = response.read("$.State["+k+"]"+vehicleArr[j]+".Forms.FormDetail.length()").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
					arraylength = arraylength.replaceAll("\\[", "").replaceAll("\\]","");
					System.out.println(arraylength);
					for (int i=0;i<Integer.parseInt(arraylength);i++) 
					{
						String insterQuery = "INSERT INTO Output_FormSelection VALUES("+input.get("S_No")+", temp2)";	
						StringBuffer temp2 = new StringBuffer();
						for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
						{
							LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
							String condition = rowOutputColVerify.get("OutputColumnCondtn");
							if(rowOutputColVerify.get("Flag").equalsIgnoreCase("Y"))
							{
								if(conditioncheck.ConditionReading(condition, input))
								{
									try
									{
										///System.out.println("Writing Response to Table");
										//System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
										String jsonpath = rowOutputColVerify.get(config.getProperty("OutputJsonPath"));
										String Rep=jsonpath.replaceAll("###", vehicleArr[j]).replaceAll("##", Integer.toString(i));
										
										String actual = response.read(Rep).replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
										
										temp2=temp2.append("'").append(actual).append("'").append(",");
										//System.out.println(actual);
										output.put("flag_for_execution", "Completed");
										output.put("Time", (end-start) + " Millis");
									}
									catch(PathNotFoundException e)
									{
										e.printStackTrace();
										output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
									}
								}
								
							}
						}
						insterQuery=insterQuery.replace("temp2", temp2.substring(0, temp2.length() - 1));
						temp2=temp2.delete(0, temp2.length());
						queryList.add(insterQuery);
					}
				}
			}
			return queryList;
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public LinkedHashMap<Integer,LinkedHashMap<String, String>> CompareFunction(LinkedHashMap<Integer,LinkedHashMap<String, String>> inputrow, LinkedHashMap<String, String> output) throws APIException
	{
		GenerateExpected expected = new GenerateExpected(config);
		try 
		{
			for(int i=1 ;i<=inputrow.size();i++)
			{
				expected.generateExpectedMel(config, inputrow.get(String.valueOf(i)), output);
				inputrow.get(String.valueOf(i)).put("AnalyserResult", expected.analyser(inputrow.get(String.valueOf(i)).get("TestCaseID")));
			}
		} 
		catch (DatabaseException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		return inputrow;
	
	}

	
}