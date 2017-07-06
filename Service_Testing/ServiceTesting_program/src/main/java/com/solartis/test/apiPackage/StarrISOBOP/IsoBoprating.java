package com.solartis.test.apiPackage.StarrISOBOP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import jxl.read.biff.BiffException;
import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.macroPackage.IsoMacro;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.common.DatabaseOperation;

public class IsoBoprating extends BaseClass implements API
{
	MacroInterface macro = null;
	public IsoBoprating(PropertiesHandle config) throws SQLException
	{
	
		this.config = config;
		jsonElements = new DatabaseOperation();
	
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		if(config.getProperty("status").equals("Y"))
		{
		macro=new IsoMacro(config);	
		}
		
	}
	
	public void AddHeaders() throws IOException
	{
		http = new HttpHandle(config.getProperty("test_url"),"POST");
		http.AddHeader("Content-Type", config.getProperty("content_type"));
		http.AddHeader("Token", config.getProperty("token"));
		http.AddHeader("EventName", config.getProperty("EventName"));	
	}
	
	public void LoadSampleRequest(DatabaseOperation InputData) throws SQLException, BiffException, IOException
	{
		if(config.getProperty("status").equals("Y"))
		{
		macro.LoadSampleRatingmodel(config, InputData);
		macro.GenerateExpected(InputData, config);
		}
		super.LoadSampleRequest(InputData);
	}
	
	public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException,ClassNotFoundException, NumberFormatException, java.text.ParseException, BiffException 
	{			
		if(config.getProperty("status").equals("Y"))
		{
		macro.PumpinData(input, config);
		}
		super.PumpDataToRequest();
	}
	
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output) throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException, ClassNotFoundException, NumberFormatException, java.text.ParseException
	{
		if(config.getProperty("status").equals("Y"))
		{
		macro.PumpoutData(output, input, config);
		}
		super.SendResponseDataToFile(output);
		return output;		
	}
	
}