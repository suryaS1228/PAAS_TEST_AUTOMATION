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

	public void generateExpectedMel(PropertiesHandle configFile, LinkedHashMap<String, String> inputrow, LinkedHashMap<String, String> output) throws DatabaseException, SQLException
	{
		expectedMelTable.GetDataObjects("Output_FormSelection_Expected");		
		if(inputrow.get("Flag_for_execution").equals("Y"))
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> coverageData = configTable.GetDataObjects("Select * from ConditionTable_CA_FormSelection");
			for (Entry<Integer, LinkedHashMap<String, String>> entry : coverageData.entrySet())	
			{
				LinkedHashMap<String, String> configtablerow = entry.getValue();
				if(configtablerow.get("Flag_for_execution").equals("Y"))
				{
					if(verify.ConditionReading(configtablerow.get("Condition"), inputrow))
					{
						String insterQuery = "INSERT INTO Output_FormSelection VALUES("+inputrow.get("S_No")+", temp2)";	
						StringBuffer temp2 = new StringBuffer();
						
						temp2=temp2.append("'").append(configtablerow.get("FormName")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("FormNumber")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("FormType")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("FormHierachy")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("Sequence")).append("'").append(",");
						temp2=temp2.append("'").append(configtablerow.get("State")).append("'").append(",");
						
						insterQuery=insterQuery.replace("temp2", temp2.substring(0, temp2.length() - 1));
						temp2=temp2.delete(0, temp2.length());
						expectedMelTable.insertRow(insterQuery);
					}
				}
			}
		}
	}	
}
