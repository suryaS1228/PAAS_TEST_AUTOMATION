package apiPackage;

import java.io.IOException;
import java.sql.SQLException;
import util.api.*;
import util.common.*;
import Configuration.PropertiesHandle;

public class StarrSearchRescueIssueCertificate extends BaseClass implements API
{
	public StarrSearchRescueIssueCertificate(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new DatabaseOperation();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
	}

	@Override
	public void AddHeaders() throws IOException
	{
		http = new HttpHandle(config.getProperty("test_url"),"POST");
		http.AddHeader("Content-Type", config.getProperty("content_type"));
		http.AddHeader("Token", config.getProperty("token"));
		http.AddHeader("EventName", config.getProperty("EventName"));
	}
}
