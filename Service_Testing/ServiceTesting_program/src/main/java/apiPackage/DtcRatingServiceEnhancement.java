package apiPackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;
import util.api.*;
import util.common.*;
import com.jayway.jsonpath.PathNotFoundException;
import Configuration.PropertiesHandle;

public class DtcRatingServiceEnhancement extends BaseClass implements API 
{
 protected String Planname[] = null;
 protected String Plancode[] = null;
 public DtcRatingServiceEnhancement(PropertiesHandle config) throws SQLException
 {
  this.config = config;
  jsonElements = new DatabaseOperation();
  
     InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
	OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
	StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
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
    case 1:   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request1.json"); break;
    case 2:   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request2.json"); break;
    case 3:   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request3.json"); break;
    case 4:   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request4.json"); break;
    case 5:   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request5.json"); break;
    case 6:   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request6.json"); break;
    case 7:   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request7.json"); break;
   
    default:
   }
   
 }

    @Override
 public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException
 {
   InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
  request = new JsonHandle(config.getProperty("request_location")+input.ReadData("testdata")+"_request_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_name")+".json");
  request.StringToFile(sampleInput.FileToString());
do
{
if(InputColVerify.DbCol(input))
{
  if(!input.ReadData(InputColVerify.ReadData(config.getProperty("InputColumn"))).equals(""))
  {
	if(InputColVerify.ReadData(config.getProperty("InputColumn")).equals("Plan_name"))
    {
     for(int j=0;j<Planname.length;j++)
     {
      String DynamicPlannameJson = InputColVerify.ReadData(config.getProperty("InputJsonPath"));
      String SplitPlanJson[] = DynamicPlannameJson.split("##");
      request.write(SplitPlanJson[0]+j+SplitPlanJson[1], Planname[j]);
     }
    }
    else if(InputColVerify.ReadData(config.getProperty("InputColumn")).equals("Plan_code"))
    {
        for(int j=0;j<Plancode.length;j++)
        {
         String DynamicPlanCodeJson = InputColVerify.ReadData(config.getProperty("InputJsonPath"));
         String SplitCodeJson[] = DynamicPlanCodeJson.split("##");     
         request.write(SplitCodeJson[0]+j+SplitCodeJson[1], Plancode[j]);
        }
     }
    else
    {
     request.write(InputColVerify.ReadData(config.getProperty("InputJsonPath")), input.ReadData(InputColVerify.ReadData(config.getProperty("InputColumn"))));
    }
   }
  }
}while(InputColVerify.MoveForward());
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
  
  response = new JsonHandle(config.getProperty("response_location")+input.ReadData("testdata")+"_response_"+input.ReadData("State_code")+"_"+input.ReadData("Plan_name")+".json");
  
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
 public DatabaseOperation SendResponseDataToFile(DatabaseOperation output)
   throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException 
 {
	 OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
		do 	
		{
		  if(OutputColVerify.DbCol(input))
			{
			try
				{
				System.out.println(OutputColVerify.ReadData(config.getProperty("OutputColumn")));
				System.out.println(config.getProperty("OutputColumn")); 
				System.out.println(OutputColVerify.ReadData(config.getProperty("OutputColumn")));
				String actual = (response.read(OutputColVerify.ReadData(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");

				System.out.println(actual);
				output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), actual);
				System.out.println(actual);
				output.WriteData("flag_for_execution", "Completed");
				}
				catch(PathNotFoundException e)
				{
					output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), "Path not Found");
				}
			}
		}while(OutputColVerify.MoveForward());
	
		
	return output;
}
}