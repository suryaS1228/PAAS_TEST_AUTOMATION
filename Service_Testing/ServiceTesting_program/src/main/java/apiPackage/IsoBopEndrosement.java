package apiPackage;

import java.sql.SQLException;
import Supporting_Classes.DatabaseOperation;
import Supporting_Classes.PropertiesHandle;

public class IsoBopEndrosement extends BaseClass implements API 
{
		public IsoBopEndrosement(PropertiesHandle config) throws SQLException
		{
			this.config = config;
			jsonElements = new DatabaseOperation();
			jsonElements.GetDataObjects(config.getProperty("json_query"));
			actualColumnCol = config.getProperty("actual_column").split(";");
			inputColumnCol = config.getProperty("input_column").split(";");
			actualColumnSize = actualColumnCol.length;
			inputColumnSize = inputColumnCol.length;
		}
}