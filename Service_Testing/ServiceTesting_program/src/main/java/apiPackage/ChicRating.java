package apiPackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import Supporting_Classes.DatabaseOperation;
import Supporting_Classes.HttpHandle;

import Supporting_Classes.PropertiesHandle;
import Supporting_Classes.RequestResponse;
import Supporting_Classes.XmlHandle;

public class ChicRating implements API
{
	private RequestResponse sampleInput = null;
	private RequestResponse request = null;
	private RequestResponse response = null;
	private DatabaseOperation XmlElements = null;
	private PropertiesHandle config = null;
	private DatabaseOperation input = null;
	private String[] actualColumnCol = null;
	private String[] inputColumnCol = null;
	private String[] statusColumnCol = null;
	private int statusColumnSize;
	private int actualColumnSize;
	private int inputColumnSize;
	private HttpHandle http = null;
	
	public ChicRating(PropertiesHandle config) throws SQLException
	{
		this.config=config;
		XmlElements = new DatabaseOperation();
		XmlElements.GetDataObjects(config.getProperty("json_query"));
		actualColumnCol = config.getProperty("actual_column").split(";");
		inputColumnCol = config.getProperty("input_column").split(";");
		statusColumnCol = config.getProperty("status_column").split(";");
		statusColumnSize = statusColumnCol.length;
		
		actualColumnSize = actualColumnCol.length;
		inputColumnSize = inputColumnCol.length;
		
		
	}
	
    

	
	public void LoadSampleRequest(DatabaseOperation InputData) throws SQLException 
	{
		this.input = InputData;
		sampleInput = new XmlHandle(config.getProperty("sample_request"));
		
	}


	
	public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException
	{
		request = new XmlHandle(config.getProperty("request_location")+input.ReadData("testdata")+"_request"+".xml");
		request.StringToFile(sampleInput.FileToString());
		
		for(int i=0;i<inputColumnSize;i++)
		{
			if(!input.ReadData(inputColumnCol[i]).equals(""))
			{
			request.write(XmlElements.ReadData(inputColumnCol[i]), input.ReadData(inputColumnCol[i]));
			}
		}
		
	}


	
	public void AddHeaders() throws IOException 
	{
		http = new HttpHandle(config.getProperty("test_url"),"POST");
		http.AddHeader("Content-Type", config.getProperty("content_type"));
		http.AddHeader("Token", config.getProperty("token"));
		
		
	}


	
	public void SendAndReceiveData() throws SQLException 
	{
		String input_data= null;
		try {
			input_data = request.FileToString();
		} catch (IOException | ParseException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			http.SendData(input_data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String response_string = null;
		
		try {
			response_string = http.ReceiveData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = new XmlHandle(config.getProperty("response_location")+input.ReadData("testdata")+"_response"+".xml");
		try {
			response.StringToFile(response_string);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output)
			throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException
	{

		
		for(int i=0;i<actualColumnSize;i++)
		{
			

			String actual = (response.read(XmlElements.ReadData(actualColumnCol[i])).replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
			output.WriteData(actualColumnCol[i], actual);
	        System.out.println(actual);
			output.WriteData("flag_for_execution", "Completed");
			
		
		}
		return output;
		
	}


	
	public void CompareFunction(DatabaseOperation output) throws SQLException
	{
		for(int i=0;i<statusColumnSize;i++)
		{
			String[] StatusIndividualColumn = statusColumnCol[i].split("-");
			String ExpectedColumn = StatusIndividualColumn[0];
			String ActualColumn = StatusIndividualColumn[1];
			String StatusColumn = StatusIndividualColumn[2];
			if(premium_comp(output.ReadData(ExpectedColumn),output.ReadData(ActualColumn)))
			{
				output.WriteData(StatusColumn, "Pass");
			}
			else
			{
				output.WriteData(StatusColumn, "Fail");
			}
			
		}
		
	}
	
	private static boolean premium_comp(String expected,String actual)
	{
		
		boolean status = false;
		if(expected == null || actual == null ||expected.equals("") || actual.equals(""))
		{
			status = false;
		}
		else
		{
			expected = expected.replaceAll("\\[\"", "");
			actual = actual.replaceAll("\\[\"", "");
			expected = expected.replaceAll("\"\\]", "");
			actual = actual.replaceAll("\"\\]", "");
			expected = expected.replaceAll("\\.[0-9]*", "");
			actual = actual.replaceAll("\\.[0-9]*", "");
			
			System.out.println(actual);
			System.out.println(expected);
			if(expected.equals(actual))
			{
				status = true;
			}
			else 
			{
				status = false;
			}
		}
		return status;	
		
	}
	
	
	
}
