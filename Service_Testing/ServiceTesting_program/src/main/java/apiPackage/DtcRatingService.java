package apiPackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.PathNotFoundException;

import Supporting_Classes.DatabaseOperation;
import Supporting_Classes.HttpHandle;
import Supporting_Classes.JsonHandle;
import Supporting_Classes.PropertiesHandle;



public class DtcRatingService extends BaseClass implements API 
{
	protected String Planname[] = null;
	protected String Plancode[] = null;
	public DtcRatingService(PropertiesHandle config) throws SQLException
	{
		this.config = config;
		jsonElements = new DatabaseOperation();
		jsonElements.GetDataObjects(config.getProperty("json_query"));
		actualColumnCol = config.getProperty("actual_column").split(";");
		inputColumnCol = config.getProperty("input_column").split(";");
		statusColumnCol = config.getProperty("status_column").split(";");
		statusColumnSize = statusColumnCol.length;
		actualColumnSize = actualColumnCol.length;
		inputColumnSize = inputColumnCol.length;	
	}
	
	@Override
	public void LoadSampleRequest(DatabaseOperation InputData) throws SQLException
	{
		this.input = InputData;
		input = InputData;
		Planname=InputData.ReadData("Plan_name").split(";");
		Plancode=InputData.ReadData("Plan_code").split(";");
		int numofplan = Planname.length;
		switch(numofplan)
			
			{
				case 1:			sampleInput = new JsonHandle(config.getProperty("Sample_request_1")); break;
				case 2:			sampleInput = new JsonHandle(config.getProperty("Sample_request_2")); break;
				case 3:			sampleInput = new JsonHandle(config.getProperty("Sample_request_3")); break;
				case 4:			sampleInput = new JsonHandle(config.getProperty("Sample_request_4")); break;
				case 5:			sampleInput = new JsonHandle(config.getProperty("Sample_request_5")); break;
				case 6:			sampleInput = new JsonHandle(config.getProperty("Sample_request_6")); break;
				case 7:			sampleInput = new JsonHandle(config.getProperty("Sample_request_7")); break;
			
				default:
			}
			
	}

    @Override
	public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException
	{
		request = new JsonHandle(config.getProperty("request_location")+input.ReadData("testdata")+"_request_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_name")+".json");
		request.StringToFile(sampleInput.FileToString());
		
		for(int i=0;i<inputColumnSize;i++)
		{
			//System.out.println(input.ReadData(inputColumnCol[i]));
			if(!input.ReadData(inputColumnCol[i]).equals(""))
			{
				if(inputColumnCol[i].equals("Plan_name"))
				{
					for(int j=0;j<Planname.length;j++)
					{
						String DynamicPlannameJson = jsonElements.ReadData("Plan_name");
						String DynamicPlanCodeJson = jsonElements.ReadData("Plan_code");
						String SplitPlanJson[] = DynamicPlannameJson.split("##");
						String SplitCodeJson[] = DynamicPlanCodeJson.split("##");
						/*System.out.println(DynamicPlannameJson);
						System.out.println(DynamicPlanCodeJson);
						System.out.println(SplitPlanJson[0]+j+SplitPlanJson[1]+" - "+Planname[j]);
						System.out.println(SplitCodeJson[0]+j+SplitCodeJson[1]+" - "+Plancode[j]);*/
						
						request.write(SplitPlanJson[0]+j+SplitPlanJson[1], Planname[j]);
						request.write(SplitCodeJson[0]+j+SplitCodeJson[1], Plancode[j]);
					}
				}
				else
				{
					request.write(jsonElements.ReadData(inputColumnCol[i]), input.ReadData(inputColumnCol[i]));
				}
			}
		}
		
	}

	@Override
	public void AddHeaders() throws IOException 
	{
		http = new HttpHandle(config.getProperty("test_url"),"POST");
		http.AddHeader("Content-Type", config.getProperty("content_type"));
		http.AddHeader("Token", config.getProperty("token"));
		http.AddHeader("EventName", config.getProperty("EventName"));	
	}

	@Override
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
		response = new JsonHandle(config.getProperty("response_location")+input.ReadData("testdata")+"_response_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_name")+".json");
		try {
			response.StringToFile(response_string);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output)
			throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException 
	{
     String StatusCode=(response.read("..RequestStatus").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
		
		for(int i=0;i<actualColumnSize;i++)
		{
			
			if(StatusCode.equals("SUCCESS"))
			{
				try
				{
					String actual=null;
					//System.out.println(jsonElements.ReadData(actualColumnCol[i]));
					System.out.println((response.read(jsonElements.ReadData(actualColumnCol[i])).replaceAll("\\[\"", "")).replaceAll("\"\\]", ""));
					actual = ((response.read(jsonElements.ReadData(actualColumnCol[i])).replaceAll("\\[\"", "")).replaceAll("\"\\]", "")).replace("\\", "");
					output.WriteData(actualColumnCol[i], actual);
					output.WriteData("Flag_for_execution", StatusCode);
				}
				catch(PathNotFoundException e)
				{
					output.WriteData(actualColumnCol[i], "Path Not Found");
					
				}
				
			}
			else
			{
				//String MessageCode=(response.read("..messageCode").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
				//String UserMessage=(response.read("..UserMessage").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
				//output.WriteData("Flag_for_execution", "Error response");
				//output.WriteData("Message_code", MessageCode);
				//output.WriteData("User_message", UserMessage);
				
			}
		}
		return output;
		
	}
}
