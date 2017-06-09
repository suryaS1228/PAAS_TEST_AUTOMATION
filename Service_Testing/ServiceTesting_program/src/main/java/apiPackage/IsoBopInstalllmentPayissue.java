package apiPackage;

import java.sql.SQLException;
import util.api.*;
import util.common.*;
import Configuration.PropertiesHandle;

public class IsoBopInstalllmentPayissue extends BaseClass implements API 
{
	public IsoBopInstalllmentPayissue(PropertiesHandle config) throws SQLException
	{
		this.config = config;

		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		
	}
}