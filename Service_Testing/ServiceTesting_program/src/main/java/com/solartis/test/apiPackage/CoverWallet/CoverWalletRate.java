package com.solartis.test.apiPackage.CoverWallet;

import java.util.LinkedHashMap;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.MacroException;
import com.solartis.test.exception.POIException;
import com.solartis.test.macroPackage.MacroInterface;
import com.solartis.test.macroPackage.coverWalletMacro;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.JsonHandle;

public class CoverWalletRate extends BaseClass implements API
{
	MacroInterface macro = null;
	public CoverWalletRate(PropertiesHandle config) throws MacroException
	{
	
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
	
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		if(config.getProperty("status").equals("Y"))
		{
		macro=new coverWalletMacro(config);	
		}
		
	}
	
	
	
	public void PumpDataToRequest() throws APIException
	{	
		try
		{
			if(config.getProperty("status").equals("Y"))
			{
			macro.PumpinData(input, config);	
			}
			//super.PumpDataToRequest();		
		}
		catch(DatabaseException | POIException | MacroException  e)
		{
			throw new APIException("ERROR OCCURS IN PUMPDATATOREQUEST FUNCTION -- Coverwallet CLASS", e);
		}
	}
	
	@Override
	 public void AddHeaders() throws APIException 
	 {
		try
		{
		  http = new HttpHandle(config.getProperty("test_url"),"POST");
		  http.AddHeader("Content-Type", config.getProperty("content_type"));
		  http.AddHeader("Token", config.getProperty("token"));
		  http.AddHeader("EventName", config.getProperty("EventName")); 
		  http.AddHeader("EventVersion", config.getProperty("EventVersion")); 
		 }
		catch(HTTPHandleException e)
		{
			throw new APIException("ERROR OCCURS IN AddHeaders FUNCTION -- Coverwallet CLASS", e);
		}
	 }
	
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		try
		{
			if(config.getProperty("status").equals("Y"))
			{
			macro.PumpoutData(output, input, config);
			}
			super.SendResponseDataToFile(output);
		}
		catch( POIException | MacroException| DatabaseException e)
		{
			throw new APIException("ERROR OCCURS IN SendResponseDataToFile FUNCTION -- Coverwallet CLASS", e);
		}
		return output;		
	}
}
