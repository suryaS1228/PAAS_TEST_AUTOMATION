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
	
	@Override
	public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException
	{
		request = new JsonHandle(config.getProperty("request_location")+input.ReadData("testdata")+".json");
		request.StringToFile(sampleInput.FileToString());
		
		for(int i=0;i<inputColumnSize;i++)
		{
			if(!input.ReadData(inputColumnCol[i]).equals(""))
			{
			request.write(jsonElements.ReadData(inputColumnCol[i]), input.ReadData(inputColumnCol[i]));
			}
		}

	}
	
	@Override
	public void SendAndReceiveData() throws SQLException
	{
		String input_data= null;
		try 
		{
			input_data = request.FileToString();
		} 
		catch (IOException | ParseException | DocumentException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			http.SendData(input_data);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		String response_string = null;
		
		try 
		{
			response_string = http.ReceiveData();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		response = new JsonHandle(config.getProperty("response_location")+input.ReadData("testdata")+".json");
		try 
		{
			response.StringToFile(response_string);
		} 
		catch (IOException | DocumentException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output) throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException
	{
		for(int i=0;i<actualColumnSize;i++)
		{
			String actual = (response.read(jsonElements.ReadData(actualColumnCol[i])).replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
			output.WriteData(actualColumnCol[i], actual);
			System.out.println(actual);
			output.WriteData("flag_for_execution", "Completed");
		}
	return output;
	}
}