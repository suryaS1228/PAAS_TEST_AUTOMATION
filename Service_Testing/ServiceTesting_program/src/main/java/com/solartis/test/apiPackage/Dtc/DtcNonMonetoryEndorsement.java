package com.solartis.test.apiPackage.Dtc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.util.api.DBColoumnVerify;
import com.solartis.test.util.api.HttpHandle;
import com.solartis.test.util.common.DatabaseOperation;

public class DtcNonMonetoryEndorsement extends BaseClass implements API
{
	public DtcNonMonetoryEndorsement(PropertiesHandle config) throws SQLException
	{ 
		this.config=config;
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
	
	@Override
public DatabaseOperation SendResponseDataToFile(DatabaseOperation output) throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException 
{
	String StatusCode=(response.read("..RequestStatus").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
	OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
do 	
{
if(OutputColVerify.DbCol(input))
{
	 try
      {	
			if(StatusCode.equals("SUCCESS"))
			{
				System.out.println(OutputColVerify.ReadData(config.getProperty("OutputColumn")));
				String actual = (response.read(OutputColVerify.ReadData(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
				output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), actual);
				System.out.println(actual);
				output.WriteData("Flag_for_execution", StatusCode);
				
			}
			else
			{
				String MessageCode=(response.read("..messageCode").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
				String UserMessage=(response.read("..UserMessage").replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
				output.WriteData("Flag_for_execution", "Error response");
				output.WriteData("Message_code", MessageCode);
				output.WriteData("User_message", UserMessage);
				
			}
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
