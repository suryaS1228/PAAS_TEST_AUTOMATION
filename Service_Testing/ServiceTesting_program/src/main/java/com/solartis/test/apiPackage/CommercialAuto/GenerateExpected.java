package com.solartis.test.apiPackage.CommercialAuto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

	public List<String> generateExpectedMel(PropertiesHandle configFile) throws DatabaseException, SQLException
	{
		List<String> queryList = new ArrayList<String>();
		LinkedHashMap<Integer, LinkedHashMap<String, String>> OutputTable=inputoutputtable.GetDataObjects("SELECT * FROM `INPUT_Quote_GL_V6` a LEFT JOIN `INPUT_GL_PolicyIssuance_V3` b ON a.S_No=b.S_No LEFT JOIN `INPUT_GL_Cancel_V2` c on a.S_No=c.S_No LEFT JOIN `OTUPUT_Quote_GL_V6` d on a.S_No=d.`S.No` LEFT JOIN `OUTPUT_GL_PolicyIssuance_V3` e on a.S_No=e.`S.No` LEFT JOIN `OUTPUT_GL_Cancel_V2` f on a.S_No=f.`S.No`");
		for(Entry<Integer, LinkedHashMap<String, String>> entry1 : OutputTable.entrySet())
		{
			LinkedHashMap<String, String> InputOutputRow = entry1.getValue();
			if(InputOutputRow.get("Flag_for_execution").equals("Y"))
			{
				LinkedHashMap<Integer, LinkedHashMap<String, String>> coverageData = configTable.GetDataObjects("Select * from MEL_CoverageOrder");
				for (Entry<Integer, LinkedHashMap<String, String>> entry : coverageData.entrySet())	
				{
					LinkedHashMap<String, String> configtablerow = entry.getValue();
					if(configtablerow.get("Flag_for_execution").equals("Y"))
					{
						if(verify.ConditionReading(configtablerow.get("Condition"), InputOutputRow))
						{
							String insterQuery = "INSERT INTO Output_FormSelection VALUES("+InputOutputRow.get("S_No")+", temp2)";	
							StringBuffer temp2 = new StringBuffer();
							
							temp2=temp2.append("'").append(configtablerow.get("FormName")).append("'").append(",");
							temp2=temp2.append("'").append(configtablerow.get("FormNumber")).append("'").append(",");
							temp2=temp2.append("'").append(configtablerow.get("FormType")).append("'").append(",");
							temp2=temp2.append("'").append(configtablerow.get("FormHierachy")).append("'").append(",");
							temp2=temp2.append("'").append(configtablerow.get("Sequence")).append("'").append(",");
							temp2=temp2.append("'").append(configtablerow.get("State")).append("'").append(",");
							
							insterQuery=insterQuery.replace("temp2", temp2.substring(0, temp2.length() - 1));
							temp2=temp2.delete(0, temp2.length());
							queryList.add(insterQuery);
						}
					}
				}
			}
		}
		return queryList;
	}	
}
