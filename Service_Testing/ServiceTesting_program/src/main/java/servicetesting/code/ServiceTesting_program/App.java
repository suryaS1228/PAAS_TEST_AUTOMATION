package servicetesting.code.ServiceTesting_program;

import Supporting_Classes.DatabaseOperation;
import apiPackage.API;
import apiPackage.ChicForms;
import apiPackage.ChicRating;
import apiPackage.DtcFindPolicy;
import apiPackage.DtcGetCustomerDetails;
import apiPackage.DtcGetPolicy;
import apiPackage.DtcPayIssue;
import apiPackage.DtcPreviewPDF;
import apiPackage.DtcRatingService;
import apiPackage.DtcSaveDetails1;
import apiPackage.DtcSaveDetails2;
import apiPackage.DtcSaveDetails3;
import apiPackage.DtcSaveDetails4;
import apiPackage.IsoBopEndrosement;
import apiPackage.IsoBopInstalllmentPayissue;
import apiPackage.IsoBopPayissue;
import apiPackage.IsoBopPayissueCancel;
import apiPackage.IsoBopQuote;
import apiPackage.IsoBopRateCancel;
import apiPackage.IsoBopissue;
import apiPackage.IsoBoprating;
import apiPackage.SolartisIsoBopRating;
import apiPackage.StarrSearchRescueIssueCertificate;

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
		PropertiesHandle config = new PropertiesHandle("A:/1 Projects/08 DTC/Release6/Rating/configuration_file/config_json_CFAR.properties");
		//PropertiesHandle config = new PropertiesHandle(args[0]);
		DatabaseOperation.ConnectionSetup(config);
		DatabaseOperation input = new DatabaseOperation();
		input.GetDataObjects(config.getProperty("input_query"));
		DatabaseOperation output = new DatabaseOperation();
		output.GetDataObjects(config.getProperty("output_query"));
		
		String ApiType = config.getProperty("ApiType");
		
		switch(ApiType.toLowerCase())
        {
      
          case "isoquote":
           	    api = new IsoBopQuote(config);
    	        break;
      
          case "isorate":
    	        api = new IsoBoprating(config);
    	        break;
    	  
          case "isobopendrosement":
    	        api = new IsoBopEndrosement(config);
    	        break;
    	  
          case "isobopinstalllmentpayissue":
    	        api = new IsoBopInstalllmentPayissue(config);
    	        break;
    	  
          case "isobopissue":
    	        api = new IsoBopissue(config);
    	        break;
    	  
          case "isoboppayissue":
    	        api = new IsoBopPayissue(config);
    	        break;
    	  
          case "isoboppayissuecancel":
    	        api = new IsoBopPayissueCancel(config);
    	        break;
    	  
          case "solartisisoboprating":
    	        api = new SolartisIsoBopRating(config);	
    	        break;
    	  
          case "isobopratecancel":
    	        api = new IsoBopRateCancel(config);	  
    	        break;
    	        
          case "chicform":
  	            api = new ChicForms(config);
  	            break;
  	        
          case "chicrating":
  	           api = new ChicRating(config);
  	           break;
  	        
          case "dtcfindpolicy":
  	            api = new DtcFindPolicy(config);
  	            break;
  	            
          case "dtcgetpolicy":
        	    api = new DtcGetPolicy(config);
	            break;
  	        
          case "dtcgetcustomerdetails":
  	            api = new DtcGetCustomerDetails(config);
  	            break;
  	        
          case "dtcpayissue":
  	            api = new DtcPayIssue(config);
  	            break;
  	        
          case "dtcpreviewpdf":
  	            api = new DtcPreviewPDF(config);
  	            break;
  	        
          case "dtcratingservice":
  	           api = new DtcRatingService(config);
  	           break;
  	        
          case "dtcsavedetails1":
  	            api = new DtcSaveDetails1(config);
  	            break;
  	        
          case "dtcsavedetails2":
  	            api = new DtcSaveDetails2(config);
  	            break;
  	        
          case "dtcsavedetails3":
    	        api = new DtcSaveDetails3(config);
    	        break;
    	        
          case "dtcsavedetails4":
    	        api = new DtcSaveDetails4(config);
    	        break;
    	        
          case "starrsearchrescueissuecertificate":
  	            api = new StarrSearchRescueIssueCertificate(config);
  	             break;
    	        
          default :
        	     System.out.println("API is not coded"); 
    	  
       }
		System.out.println("Code Startsss");
		int i = 1;
		do
		{
			System.out.println("TestData : " + i);
			if(input.ReadData("flag_for_execution").equals("Y"))
			{
				api.LoadSampleRequest(input);
				api.PumpDataToRequest();
				api.AddHeaders();
				api.SendAndReceiveData();
				output = api.SendResponseDataToFile(output);
				output.UpdateRow();
				output = api.CompareFunction(output);
				output.UpdateRow();
				input.WriteData("Flag_for_execution", "Completed");
				input.UpdateRow();
			}
			i++;
		}while(input.MoveForward() && output.MoveForward());
		DatabaseOperation.CloseConn();
   }
}	
