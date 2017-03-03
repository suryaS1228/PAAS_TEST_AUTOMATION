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


public class IsoBopissue implements API 
{
	private RequestResponse sampleInput = null;
	private RequestResponse request = null;
	private RequestResponse response = null;
	private DatabaseOperation jsonElements = null;
	private PropertiesHandle config = null;
	private DatabaseOperation input = null;
	private String[] actualColumnCol = null;
	private String[] inputColumnCol = null;
	private String[] statusColumnCol = null;
	private int statusColumnSize;
	private int actualColumnSize;
	private int inputColumnSize;
	private HttpHandle http = null;
	
	public IsoBopissue(PropertiesHandle config) throws SQLException
	{
		jsonElements.GetDataObjects(config.getProperty("json_query"));
		actualColumnCol = config.getProperty("actual_column").split(";");
		inputColumnCol = config.getProperty("input_column").split(";");
		statusColumnCol = config.getProperty("status_column").split(";");
		statusColumnSize = statusColumnCol.length;
		
		actualColumnSize = actualColumnCol.length;
		inputColumnSize = inputColumnCol.length;
		
		
	}
	
	public void LoadSampleRequest(DatabaseOperation InputData) throws SQLException
	{
		sampleInput = new JsonHandle(config.getProperty("sample_request"));
	}
	
	public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException
	{
		request = new JsonHandle(config.getProperty("request_location")+input.ReadData("testdata")+"_request_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_type"));
		request.StringToFile(sampleInput.FileToString());
		
		for(int i=0;i<inputColumnSize;i++)
		{
			if(!input.ReadData(inputColumnCol[i]).equals(""))
			{
			request.write(jsonElements.ReadData(inputColumnCol[i]), input.ReadData(inputColumnCol[i]));
			}
		}

	}
	
	public void AddHeaders() throws IOException
	{
		http = new HttpHandle(config.getProperty("test_url"),"POST");
		http.AddHeader("Content-Type", config.getProperty("content_type"));
		http.AddHeader("Token", config.getProperty("token"));
		//http.AddHeader("EventName", config.getProperty("EventName"));
		
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
		response = new JsonHandle(config.getProperty("response_location")+input.ReadData("testdata")+"_response_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_type"));
		try {
			response.StringToFile(response_string);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void SendResponseDataToFile(DatabaseOperation output) throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException
	{
		String StatusCode=(response.read("..RequestStatus").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
		
		for(int i=0;i<actualColumnSize;i++)
		{
			
			if(StatusCode.equals("SUCCESS"))
			{
				String actual=null;
				actual = (response.read(jsonElements.ReadData(actualColumnCol[i])).replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
				output.WriteData(actualColumnCol[i], actual);
				output.WriteData("Flag_for_execution", StatusCode);
				
			}
			else
			{
				String MessageCode=(response.read("..messageCode").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
				String UserMessage=(response.read("..UserMessage").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
				output.WriteData("Flag_for_execution", "Error response");
				output.WriteData("Message_code", MessageCode);
				output.WriteData("User_maessage", UserMessage);
				
			}
		}
	
	}
	
	
	public void CompareFunction(DatabaseOperation output) throws SQLException
	{/*
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
			
		}*/
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




