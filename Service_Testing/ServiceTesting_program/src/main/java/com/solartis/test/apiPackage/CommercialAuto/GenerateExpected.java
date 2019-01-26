package com.solartis.test.apiPackage.CommercialAuto;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.common.DatabaseOperation;

public class GenerateExpected 
{
	protected DatabaseOperation configTable = null;
	protected DatabaseOperation inputoutputtable;
	protected DatabaseOperation expectedMelTable;
	protected PropertiesHandle configFile;
	protected DBColoumnVerify verify;
	
	public GenerateExpected(PropertiesHandle configFile)
	{
		this.configFile = configFile;
		inputoutputtable = new DatabaseOperation();
		configTable = new DatabaseOperation();
		expectedMelTable = new DatabaseOperation();
		verify= new DBColoumnVerify();
	}

	public void generateExpectedMel(PropertiesHandle configFile, LinkedHashMap<String, String> inputrow, LinkedHashMap<String, String> output, int multiStateIndicatior) throws DatabaseException, SQLException
	{
		expectedMelTable.GetDataObjects("SELECT * FROM Output_FormSelection_Expected");		
		if(inputrow.get("Flag_for_execution").equals("Y"))
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> coverageData = configTable.GetDataObjects("Select * from ConditionTable_CA_FormSelection");
			for (Entry<Integer, LinkedHashMap<String, String>> entry : coverageData.entrySet())	
			{
				LinkedHashMap<String, String> configtablerow = entry.getValue();
				if(configtablerow.get("Flag_for_execution").equals("Y"))
				{
					//System.out.println(configtablerow.get("Condition"));
					if(verify.ConditionReading(configtablerow.get("Condition"), inputrow))
					{
						String insterQuery = "INSERT INTO Output_FormSelection_Expected VALUES("+inputrow.get("TestCaseID")+", temp2)";	
						StringBuffer temp2 = new StringBuffer();
						
						temp2=temp2.append("\"").append(configtablerow.get("FormName")).append("\"").append(",");
						temp2=temp2.append("'").append(configtablerow.get("FormNumber")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("FormType")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("FormHierachy")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("Sequence")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("State")).append("'").append(",");
						temp2=temp2.append("\"").append("State["+multiStateIndicatior+"]").append("\"").append(",");
						
						insterQuery=insterQuery.replace("temp2", temp2.substring(0, temp2.length() - 1));
						temp2=temp2.delete(0, temp2.length());
						//System.out.println(insterQuery);
						expectedMelTable.insertRow(insterQuery.replaceAll("'", "\\'"));
					}
				}
			}
		}
	}	
	
	public LinkedHashMap<String,String> analyser(String rowNumber, int multiStateIndicator) throws DatabaseException
	{
		
		String[] vehicleArr = { "Policy", "Truck Detail", "Public Transportation Detail","Special Type Detail", "Private Passenger Detail", "Zone Rated Truck Detail"};
		LinkedHashMap<String,String> analyserResult = new LinkedHashMap<String,String>();
		
		for(int i=0;i<vehicleArr.length;i++)
		{
			StringBuffer temp2 = new StringBuffer();
			String QueryString = "SELECT  Output_FormSelection.FormName, Output_FormSelection.FormNumber "
					+ "FROM Output_FormSelection "
					+ "WHERE  Output_FormSelection.S_No='"+rowNumber+"' and Output_FormSelection.FormHierachy='"+vehicleArr[i]+"' and Output_FormSelection.MultiStateIndicator='State["+multiStateIndicator+"]' and Output_FormSelection.FormNumber NOT IN "
					+ "( "
					+ "SELECT  Output_FormSelection_Expected.FormNumber "
					+ "FROM    Output_FormSelection_Expected WHERE  Output_FormSelection_Expected.S_No='"+rowNumber+"' and Output_FormSelection_Expected.FormHierachy='"+vehicleArr[i]+"' and Output_FormSelection_Expected.multiStateIndicatior='State["+multiStateIndicator+"]' "
					+ ") "
					+ "UNION ALL "
					+ "SELECT  Output_FormSelection_Expected.FormName, Output_FormSelection_Expected.FormNumber "
					+ "FROM Output_FormSelection_Expected "
					+ "WHERE   Output_FormSelection_Expected.S_No='"+rowNumber+"' and Output_FormSelection_Expected.FormHierachy='"+vehicleArr[i]+"' and Output_FormSelection_Expected.multiStateIndicatior='State["+multiStateIndicator+"]' and Output_FormSelection_Expected.FormNumber NOT IN "
					+ "( "
					+ "SELECT  Output_FormSelection.FormNumber "
					+ "FROM Output_FormSelection WHERE Output_FormSelection.S_No='"+rowNumber+"' and Output_FormSelection_Expected.FormHierachy='"+vehicleArr[i]+"' and Output_FormSelection.MultiStateIndicator='State["+multiStateIndicator+"]' "
					+ ")";
			//System.out.println(QueryString);
			LinkedHashMap<Integer, LinkedHashMap<String, String>> coverageData = configTable.GetDataObjects(QueryString);
			for (Entry<Integer, LinkedHashMap<String, String>> entry : coverageData.entrySet())	
			{
				LinkedHashMap<String, String> result = entry.getValue();
				
				temp2=temp2.append(result.get("FormNumber")).append(", ");
			}
			if(temp2.length()==0)
			{
				temp2 = temp2.append("Pass");
			}else {
			temp2=temp2.delete(temp2.length()-2, temp2.length());
			}
			
			analyserResult.put(vehicleArr[i], temp2.toString());
		}
		//System.out.println(temp2);
		return analyserResult;
	}
}
