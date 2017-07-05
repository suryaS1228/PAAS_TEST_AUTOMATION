package com.solartis.test.macroPackage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.util.common.DatabaseOperation;

import jxl.read.biff.BiffException;

public interface MacroInterface 
{
	public void LoadSampleRatingmodel(PropertiesHandle configFile,DatabaseOperation inputData)throws SQLException;
	public void GenerateExpected(DatabaseOperation inputData,PropertiesHandle configFile) throws SQLException, BiffException, IOException;
	public void PumpinData(DatabaseOperation inputData,PropertiesHandle configFile) throws NumberFormatException, BiffException, SQLException, IOException, ParseException;
	public void PumpoutData(DatabaseOperation outputData,DatabaseOperation inputData,PropertiesHandle configFile) throws NumberFormatException, SQLException, ParseException;
	
}
