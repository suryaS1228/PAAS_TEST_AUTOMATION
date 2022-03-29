package com.solartis.test.apiPackage.MarineGL;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.api.JsonHandle;

public class MarineGL_RenewalPayIssue extends BaseClass implements API 
{
	public MarineGL_RenewalPayIssue(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new LinkedHashMap<String, String>();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	}
	public String tokenGenerator(PropertiesHandle config)
	{
		
		String Token="";
		try
		{
			System.out.println(config.getProperty("AuthenticationURL"));
			HttpHandle http = new HttpHandle(config.getProperty("AuthenticationURL"),"POST");
			http.AddHeader("Content-Type", config.getProperty("content_type"));
			String input_data = "{  \"ServiceRequestDetail\": { \"OwnerId\": \""+config.getProperty("OwnerID")+"\", \"ResponseType\": \"JSON\", \"BrowserIp\": \"192.168.5.140\", \"ServiceRequestVersion\": \"2.0\" }, \"UserCredential\": { \"UserName\": \""+config.getProperty("Userneme")+"\",    \"Password\": \""+config.getProperty("Password")+"\"  } }";
			http.SendData(input_data);
			String response_string = http.ReceiveData();	
			//System.out.println(input_data+"/n/n/n"+response_string);
			JsonHandle response = new JsonHandle();
			//response.StringToFile(response_string);
			//response.FileToString();
			Token = Token+response.readToken("$..Token",response_string).replaceAll("\\[\"", "").replaceAll("\"\\]", "").replaceAll("\\\\","");
			//System.out.println(Token);
		}
		catch(Exception e)
		{
			System.out.println("Error in Generating Token");
			e.printStackTrace();
		}
		return Token;
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
			throw new APIException("ERROR OCCURS IN AddHeaders FUNCTION -- Coverwallet CLASS", e);
		}
	 }

}
