package servicetesting.code.ServiceTesting_program;

import Supporting_Classes.DatabaseOperation;
import apiPackage.API;
import apiPackage.IsoBopEndrosement;
import apiPackage.IsoBopInstalllmentPayissue;
import apiPackage.IsoBopPayissue;
import apiPackage.IsoBopPayissueCancel;
import apiPackage.IsoBopQuote;
import apiPackage.IsoBopRateCancel;
import apiPackage.IsoBopissue;
import apiPackage.IsoBoprating;
import apiPackage.SolartisIsoBopRating;

import java.io.IOException;
import java.sql.SQLException;

import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import Supporting_Classes.PropertiesHandle;

/**
 * Hello world!
 *
 */
public class App 
{
	static API api=null;
	public static void main( String[] args ) throws SQLException, IOException, DocumentException, ParseException, ClassNotFoundException
    {
		System.setProperty("jsse.enableSNIExtension", "false");
		PropertiesHandle config = new PropertiesHandle("Q:/Automation Team/1 Projects/08 DTC/Release2/SaveCustomer/configuration_file/config_json_save1.properties");
		DatabaseOperation.ConnectionSetup(config);
		DatabaseOperation input = new DatabaseOperation();
		input.GetDataObjects(config.getProperty("input_query"));
		DatabaseOperation output = new DatabaseOperation();
		output.GetDataObjects(config.getProperty("output_query"));
		
		String ApiType = config.getProperty("ApiType");
		
		switch(ApiType)
        {
      
          case "IsoQuote":
    	        api = new IsoBopQuote(config);
    	        break;
      
          case "IsoRate":
    	        api = new IsoBoprating(config);
    	        break;
    	  
          case "IsoBopEndrosement":
    	        api = new IsoBopEndrosement(config);
    	        break;
    	  
          case "IsoBopInstalllmentPayissue":
    	        api = new IsoBopInstalllmentPayissue(config);
    	        break;
    	  
          case "IsoBopissue":
    	        api = new IsoBopissue(config);
    	        break;
    	  
          case "IsoBopPayissue":
    	        api = new IsoBopPayissue(config);
    	        break;
    	  
          case "IsoBopPayissueCancel":
    	        api = new IsoBopPayissueCancel(config);
    	        break;
    	  
          case "SolartisIsoBopRating":
    	        api = new SolartisIsoBopRating(config);	
    	        break;
    	  
          case "IsoBopRateCancel":
    	        api = new IsoBopRateCancel(config);	  
    	        break;
    	        
          default :
        	     System.out.println("API is not coded"); 
    	  
       }
		System.out.println("Code Startsss");
		do
		{
			int i = 1;
			System.out.println("TestData : " + i);
			if(input.ReadData("flag_for_execution").equals("Y"))
			{
				api.LoadSampleRequest(input);	
				api.PumpDataToRequest();
				api.AddHeaders();
				api.SendAndReceiveData();
				output = api.SendResponseDataToFile(output);
				//output = api.CompareFunction(output);
				i++;
			}
		}while(input.MoveForward() && output.MoveForward());
		DatabaseOperation.CloseConn();
   }
}	
