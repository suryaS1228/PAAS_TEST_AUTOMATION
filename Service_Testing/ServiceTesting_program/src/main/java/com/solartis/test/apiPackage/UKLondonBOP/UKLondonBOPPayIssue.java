package com.solartis.test.apiPackage.UKLondonBOP;

import java.util.LinkedHashMap;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;

public class UKLondonBOPPayIssue extends BaseClass implements API

{ 
	public UKLondonBOPPayIssue(PropertiesHandle config)
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
	}

	@Override
	public void AddHeaders(String Token) throws APIException
	{
		try 
		{
			http = new HttpHandle(config.getProperty("test_url"),"POST");
			http.AddHeader("Content-Type", config.getProperty("content_type"));
			http.AddHeader("Token", Token);
			http.AddHeader("EventName", config.getProperty("EventName"));
		}
		catch (HTTPHandleException e) 
		{
			e.printStackTrace();
			throw new APIException("ERROR ADD HEADER FUNCTION -- STARSEARCH&RESCUE-ISSUECERTIFICATE CLASS", e);
		}
	}


}
