package apiPackage;

import java.sql.SQLException;

import util.api.DBColoumnVerify;
import util.common.*;
import Configuration.PropertiesHandle;


public class IsoBopissue extends BaseClass implements API 
{
	public IsoBopissue(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new DatabaseOperation();
		
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
	}
}