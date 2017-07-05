package com.solartis.test.apiPackage.LDWC;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Random;
import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;
import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.util.api.*;
import com.solartis.test.util.common.*;


public class LDWCRating extends BaseClass implements API
{
	final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+-=";
	final int RANDOM_STRING_LENGTH = 10;
	public LDWCRating(PropertiesHandle config) throws SQLException
	{

		this.config = config;
		XmlElements = new DatabaseOperation();
		//XmlElements.GetDataObjects(config.getProperty("json_query"));
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		
	}
	
	@Override
	public void LoadSampleRequest(DatabaseOperation InputData) throws SQLException
	{	 
		this.input = InputData;	
		sampleInput = new XmlHandle(config.getProperty("sample_request")+ "request.xml");	
	}
		
	@Override
	public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException 
	{
		InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
		request = new XmlHandle(config.getProperty("request_location")+input.ReadData("testdata")+"_request"+".xml");
		request.StringToFile(sampleInput.FileToString());
		String Reqid = this.RqUID();
		System.out.println(Reqid);
		request.write("//InsuranceSvcRq/RqUID",Reqid);
		input.WriteData("RequestUID", Reqid);
		
		do
		{
			if(InputColVerify.DbCol(input) && (InputColVerify.ReadData("Flag").equalsIgnoreCase("Y")))
			{
				if(!input.ReadData(InputColVerify.ReadData(config.getProperty("InputColumn"))).equals(""))
				{
					request.write(InputColVerify.ReadData(config.getProperty("InputJsonPath")), input.ReadData(InputColVerify.ReadData(config.getProperty("InputColumn"))));
				}
			}	
		}while(InputColVerify.MoveForward());
		
	}
	
	@Override
	public void AddHeaders() throws IOException
	{
		http = new HttpHandle(config.getProperty("test_url"),"POST");
		http.AddHeader("Content-Type", config.getProperty("content_type"));
	
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
			//System.out.println(input_data);
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

	@Override
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output)
			throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException
	{
		 OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
		do 	
		{
		if(OutputColVerify.DbCol(input) && (OutputColVerify.ReadData("Flag").equalsIgnoreCase("Y")))
		{
			 try
		      {	
			   System.out.println(OutputColVerify.ReadData(config.getProperty("OutputColumn")));
			   String actual = (response.read((OutputColVerify.ReadData(config.getProperty("OutputJsonPath")))).replaceAll("\\[\"","")).replaceAll("\"\\]","").replaceAll("\\\\","");
			   output.WriteData(OutputColVerify.ReadData(config.getProperty("OutputColumn")), actual);
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
	
	  public String RqUID()
	  {
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            StringBuffer s = randStr.append(ch);
	            }
	        return randStr.toString();
	  }
	     
	  private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        }else {
	            return randomInt - 1;
	             }
	  }
}

