package apiPackage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import Configuration.PropertiesHandle;
import jxl.read.biff.BiffException;

import macroPackage.MacroInterface;
import macroPackage.coverWalletMacro;
import util.api.DBColoumnVerify;
import util.api.HttpHandle;
import util.api.JsonHandle;
import util.common.DatabaseOperation;

public class CoverWallet extends BaseClass implements API
{
	MacroInterface macro = null;
	public CoverWallet(PropertiesHandle config) throws SQLException
	{
	
		this.config = config;
		jsonElements = new DatabaseOperation();
	
		InputColVerify = new DBColoumnVerify(config.getProperty("InputCondColumn"));
		OutputColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));	
		StatusColVerify = new DBColoumnVerify(config.getProperty("OutputCondColumn"));
		macro=new coverWalletMacro(config);	
		
	}
	
	public void LoadSampleRequest(DatabaseOperation InputData) throws SQLException, BiffException, IOException
	{
		String numofprovider=InputData.ReadData("NumOfProviders");
		switch(numofprovider)
		   
		   {
		    case "1":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request1.json"); break;
		    case "2":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request2.json"); break;
		    case "3":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request3.json"); break;
		    case "4":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request4.json"); break;
		    case "5":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request5.json"); break;
		    case "6":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request6.json"); break;
		    case "7":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request7.json"); break;
		    case "8":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request8.json"); break;
		    case "9":   sampleInput = new JsonHandle(config.getProperty("sample_request")+"request9.json"); break;
		    case "10":  sampleInput = new JsonHandle(config.getProperty("sample_request")+"request10.json"); break;
		   
		    default:
		   }
		  
		macro.LoadSampleRatingmodel(config, InputData);
		macro.GenerateExpected(InputData, config);
		super.LoadSampleRequest(InputData);
	}
	
	public void PumpDataToRequest() throws SQLException, IOException, DocumentException, ParseException,ClassNotFoundException, NumberFormatException, java.text.ParseException, BiffException 
	{			
		macro.PumpinData(input, config);		
		super.PumpDataToRequest();
		
		
	}
	
	@Override
	 public void AddHeaders() throws IOException 
	 {
	  http = new HttpHandle(config.getProperty("test_url"),"POST");
	  http.AddHeader("Content-Type", config.getProperty("content_type"));
	  http.AddHeader("Token", config.getProperty("token"));
	  http.AddHeader("EventName", config.getProperty("EventName")); 
	  http.AddHeader("EventVersion", config.getProperty("EventVersion")); 
	 }
	
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output) throws UnsupportedEncodingException, IOException, ParseException, DocumentException, SQLException, ClassNotFoundException, NumberFormatException, java.text.ParseException
	{
		macro.PumpoutData(output, input, config);
		super.SendResponseDataToFile(output);
		return output;		
	}
}
