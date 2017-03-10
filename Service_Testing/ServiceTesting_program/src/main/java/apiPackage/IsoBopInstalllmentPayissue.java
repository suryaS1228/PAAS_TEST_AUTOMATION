package apiPackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import Supporting_Classes.DatabaseOperation;
import Supporting_Classes.HttpHandle;
import Supporting_Classes.JsonHandle;
import Supporting_Classes.PropertiesHandle;
import Supporting_Classes.RequestResponse;


public class IsoBopInstalllmentPayissue extends BaseClass implements API 
{
	public IsoBopInstalllmentPayissue(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new DatabaseOperation();
		jsonElements.GetDataObjects(config.getProperty("json_query"));
		actualColumnCol = config.getProperty("actual_column").split(";");
		inputColumnCol = config.getProperty("input_column").split(";");
		actualColumnSize = actualColumnCol.length;
		inputColumnSize = inputColumnCol.length;
		
	}
	
	@Override
	public void LoadSampleRequest(DatabaseOperation InputData) throws SQLException
	{
		input = InputData;
		switch(InputData.ReadData("Plan"))
		{
		case "TA01":
		 	 
			sampleInput = new JsonHandle(config.getProperty("json_query_TA01")); 
			actualColumnCol = config.getProperty("actual_column_TA01").split(";");
			actualColumnSize = actualColumnCol.length;
			break;
		 	
		case "BM2M":
		
			sampleInput = new JsonHandle(config.getProperty("json_query_BM2M"));
			actualColumnCol = config.getProperty("actual_column_BM2M").split(";");
			actualColumnSize = actualColumnCol.length;
			break;
			
		case "BQ25":
		
			sampleInput = new JsonHandle(config.getProperty("json_query_BQ25")); 
			actualColumnCol = config.getProperty("actual_column_BQ25").split(";");
			actualColumnSize = actualColumnCol.length;
			break;
		
		case "BQ50":
		
			sampleInput = new JsonHandle(config.getProperty("json_query_BQ50")); 
			actualColumnCol = config.getProperty("actual_column_BQ50").split(";");
			actualColumnSize = actualColumnCol.length;
			break;
			
		default :
			System.out.println(InputData.ReadData("Plan") + "is not available");
		}
	}
}