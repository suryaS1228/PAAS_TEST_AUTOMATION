package com.solartis.test.apiPackage.CommercialAuto;

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
		  http = new HttpHandle(config.getProperty("test_url"),"POST");
		  http.AddHeader("Content-Type", config.getProperty("content_type"));
		  http.AddHeader("Token", Token);
		  http.AddHeader("EventName", config.getProperty("EventName"));
		  http.AddHeader("EventVersion", config.getProperty("EventVersion"));

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
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
			String[] vehicleArr = { "Policy", "Policy.Truck.", "Policy.PublicTransportation.",
					"Policy.Special.", "Policy.PrivatePassenger.", "Policy.ZoneRated."//, "FormLogic-Garage","FormLogic-GarageServices"
					};
			for(int j=0; j<vehicleArr.length;j++)
			{
				String arraylength = response.read("$."+vehicleArr[j]+".Forms.FormDetail.length()").replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
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
			return queryList;
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
	}
	
	public LinkedHashMap<String, String> CompareFunction(LinkedHashMap<String, String> inputrow, LinkedHashMap<String, String> output) throws APIException
	{
		GenerateExpected expected = new GenerateExpected(config);
		try 
		{
			expected.generateExpectedMel(config, inputrow, output);
			inputrow.put("AnalyserResult", expected.analyser(inputrow.get("S_No")));
		} 
		catch (DatabaseException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		return inputrow;
		
	}
}