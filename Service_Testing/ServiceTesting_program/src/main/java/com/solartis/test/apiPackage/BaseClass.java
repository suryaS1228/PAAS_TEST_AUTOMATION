package com.solartis.test.apiPackage;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jayway.jsonpath.PathNotFoundException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.HTTPHandleException;
import com.solartis.test.exception.POIException;
import com.solartis.test.exception.RequestFormatException;
import com.solartis.test.util.api.*;
import com.solartis.test.util.common.DatabaseOperation;
import com.solartis.test.util.common.ExcelOperationsPOI;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class BaseClass 
{
	protected RequestHandler sampleInput = null;
	protected RequestResponse request = null;
	protected RequestResponse response = null;
	protected LinkedHashMap<String, String> XmlElements = null;
	protected LinkedHashMap<String, String> jsonElements = null;
	protected PropertiesHandle config = null;
	protected LinkedHashMap<String, String> input = null;
	protected LinkedHashMap<String, String> output = null;
	protected HttpHandle http = null;
	protected DBColoumnVerify InputColVerify = null;
	protected DBColoumnVerify OutputColVerify = null;
	protected DBColoumnVerify StatusColVerify = null;
	protected ArrayList<String> errorParentname = new ArrayList<String>();
	protected ArrayList<String> errorMessage=new ArrayList<String>();
	protected DBColoumnVerify conditioncheck = new DBColoumnVerify();
	protected LinkedHashMap<Integer, LinkedHashMap<String, String>> table1;

//---------------------------------------------------------------LOAD SAMPLE REQUEST--------------------------------------------------------------------	
	public void LoadSampleRequest(LinkedHashMap<String, String> InputData) throws APIException
	{
		this.input = InputData;
		
		try
		{
		sampleInput = new RequestHandler(config);
		sampleInput.openTemplate();
		}
		
		catch(DatabaseException | IOException | ClassNotFoundException e)
		{
			throw new APIException("ERROR OCCURS IN load Template FUNCTION -- BASE CLASS", e);
		}
		
	}

//-----------------------------------------------------------PUMPING TEST DATA TO REQUEST--------------------------------------------------------------- 	
	public void PumpDataToRequest(LinkedHashMap<String, String> InputData) throws APIException 
	{
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  InputColVerify.GetDataObjects(config.getProperty("InputColQuery"));
			sampleInput.LoadData(tableInputColVerify, InputData);
			sampleInput.PumpinDatatoRequest(tableInputColVerify,InputData);	
			sampleInput.saveJsontoPath(config.getProperty("request_location")+input.get("Testdata")+".json");
		}
			
		catch(DatabaseException | TemplateException | IOException  e)
		{
			throw new APIException("ERROR OCCURS IN PUMPDATATOREQUEST FUNCTION -- BASE CLASS", e);
		}
	}

//------------------------------------------------------------CONVERTING REQUEST TO STRING--------------------------------------------------------------	
	public String RequestToString() throws APIException
	{
	  try 
	  {
		  request = new JsonHandle(config.getProperty("request_location")+input.get("Testdata")+".json");
		  return request.FileToString();
	  } 
	  catch (RequestFormatException e)
	  {
		  throw new APIException("ERROR OCCURS IN REQUEST TO STRING FUNCTION -- BASE CLASS", e);
	   }
	}
	
//-------------------------------------------------------------ADDING HEADER || TOKENS------------------------------------------------------------------	
	public void AddHeaders() throws APIException
	{
		try
		{
			http = new HttpHandle(config.getProperty("test_url"),"POST");
			http.AddHeader("Content-Type", config.getProperty("content_type"));
			http.AddHeader("Token", config.getProperty("token"));
		}
		catch(HTTPHandleException e)
		{
			throw new APIException("ERROR ADD HEADER FUNCTION -- BASE CLASS", e);
		}
	}

//------------------------------------------------------------STORING RESPONSE TO FOLDER----------------------------------------------------------------	
	public void SendAndReceiveData() throws APIException 
	{
		try
		{
			String input_data= null;
			input_data = request.FileToString();
		    http.SendData(input_data);
			String response_string = http.ReceiveData();	
			response = new JsonHandle(config.getProperty("response_location")+input.get("Testdata")+".json");
			response.StringToFile(response_string);
		}
		catch(RequestFormatException | HTTPHandleException e)
		{
			System.out.println(e);
			throw new APIException("ERROR IN SEND AND RECIEVE DATA FUNCTION -- BASE CLASS", e);
		}
	}
	
//-------------------------------------------------------------CONVERTING RESPONSE TO STRING------------------------------------------------------------
	public String ResponseToString() throws APIException 
	{
		try
		{
			return response.FileToString();
		}
		catch(RequestFormatException e)
		{
			throw new APIException("ERROR IN RESPONSE TO STRING FUNCTION -- BASE CLASS", e);
		}
	}
	
//-----------------------------------------------------------UPDATING RESPONSE DATA TO DATABASE---------------------------------------------------------	
	public LinkedHashMap<String, String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException
	{
		try
		{
			LinkedHashMap<Integer, LinkedHashMap<String, String>> tableOutputColVerify = OutputColVerify.GetDataObjects(config.getProperty("OutputColQuery"));		
			for (Entry<Integer, LinkedHashMap<String, String>> entry : tableOutputColVerify.entrySet())	
			{
				LinkedHashMap<String, String> rowOutputColVerify = entry.getValue();
				String condition = rowOutputColVerify.get("OutputColumnCondtn");
				if(conditioncheck.ConditionReading(condition, input)&& (rowOutputColVerify.get("Flag").equalsIgnoreCase("Y")))
				{
					try
					{
						//System.out.println("Writing Response to Table");
						//System.out.println(rowOutputColVerify.get(config.getProperty("OutputColumn")));
						String actual = (response.read(rowOutputColVerify.get(config.getProperty("OutputJsonPath"))).replaceAll("\\[\"", "")).replaceAll("\"\\]", "").replaceAll("\\\\","");
						output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), actual);
						//System.out.println(actual);
						output.put("flag_for_execution", "Completed");
					}
					catch(PathNotFoundException e)
					{
							output.put(rowOutputColVerify.get(config.getProperty("OutputColumn")), "Path not Found");
					}
				}
			}
			
			return output;
		}
		catch(DatabaseException | RequestFormatException e)
		{
			throw new APIException("ERROR IN SEND RESPONSE TO FILE FUNCTION -- BASE CLASS", e);
		}
	}

//---------------------------------------------------------------COMAPRISION FUNCTION-------------------------------------------------------------------	
	public LinkedHashMap<String, String> CompareFunction(LinkedHashMap<String, String> inputrow,LinkedHashMap<String, String> outputrow) throws APIException
	{
		boolean passflag = true;
	    try
	    {
	    	LinkedHashMap<Integer, LinkedHashMap<String, String>> tableStatusColVerify = StatusColVerify.GetDataObjects(config.getProperty("OutputColQuery"));
	    	for (Entry<Integer, LinkedHashMap<String, String>> entry : tableStatusColVerify.entrySet()) 	
			{	
			    LinkedHashMap<String, String> rowStatusColVerify = entry.getValue();
			    String condition = rowStatusColVerify.get("OutputColumnCondtn");
			   // System.out.println(condition+"---------------"+outputrow);
			    if(conditioncheck.ConditionReading(condition, inputrow) && (rowStatusColVerify.get("Comaparision_Flag").equalsIgnoreCase("Y")))
				{
					String ExpectedColumn = rowStatusColVerify.get(config.getProperty("ExpectedColumn"));
					String ActualColumn = rowStatusColVerify.get(config.getProperty("OutputColumn"));
					String StatusColumn = rowStatusColVerify.get(config.getProperty("StatusColumn"));
					if(!(StatusColumn.equals("")) && !(ExpectedColumn.equals("")))
					{
						if(premium_comp(outputrow.get(ExpectedColumn),outputrow.get(ActualColumn)))
						{
							outputrow.put(StatusColumn, "Pass");
						}
						else
						{
							passflag=false;
							outputrow.put(StatusColumn, "Fail");
							//outputrow.UpdateRow();
							analyse(rowStatusColVerify,outputrow);
						}
					}
				}			    
			}
	    	if(passflag)
		    {
		    	outputrow.put("AnalyserResult", "Pass");
		    }	
	    	
			String message = "";
			for(int i=0;i<errorMessage.size();i++)
			{
				message=message+errorMessage.get(i)+" & ";
			}
			outputrow.put("AnalyserResult", message+" Failed");
			errorMessage.clear();
			errorParentname.clear();
			return outputrow;

	    }	
	    catch(DatabaseException e)
	    {
	    	System.out.println(e);
	    	throw new APIException("ERROR IN DB COMPARISON FUNCTION -- BASE CLASS", e);
	    }
	}
	
//-----------------------------------------------------PRIVATE FUNCTION FOR SUPPORTING COMPARISON FUNCTION---------------------------------------------------	
	protected static boolean premium_comp(String expected,String actual)
	{
		
		boolean status = false;
		if(actual == null||actual.equals(""))
		{
			if((expected == null || expected.equals("")||expected.equals("0") || expected.equals("0.0")))
			{
				status = true;
			}
		}
		if(expected == null||expected.equals(""))
		{
			if(actual == null|| actual.equals("")||actual.equals("0") || actual.equals("0.0"))
			{
				status = true;
			}
		}
		if(actual!=null && expected!=null)
		{
			expected = expected.replaceAll("\\[\"", "");
    		actual = actual.replaceAll("\\[\"", "");	
    		expected = expected.replaceAll("\"\\]", "");
    		actual = actual.replaceAll("\"\\]", "");
    		expected = expected.replaceAll("\\.[0-9]*", "");
    		actual = actual.replaceAll("\\.[0-9]*", "");
    		if(expected.equals(actual))
    		{
    			status = true;
    		}
		}

		return status;	
		
	}

	protected void analyse(LinkedHashMap<String, String> Conditiontablerow,LinkedHashMap<String, String> outputrow ) throws DatabaseException 
	{		
		boolean flag = false;
		
		if(outputrow.get(Conditiontablerow.get("StatusColumn")).equals("Pass"))
		{		

		}

		else if(outputrow.get(Conditiontablerow.get("StatusColumn")).equals("Fail"))
		{	
			String[] Parentname =Conditiontablerow.get("ParentName").split(";");
			int noOfParentname=Parentname.length;
			for(int i=0;i<noOfParentname;i++)
			{
				errorParentname.add(Parentname[i]);
				if(!this.ifexist(Conditiontablerow.get("NodeName")))
				{
					if(flag == false)
					{
						errorMessage.add(Conditiontablerow.get("Message"));
						flag = true;
					}
				}
			}
						
		}

	}

	protected boolean ifexist (String NodeName)
	{
		boolean exist = false;
		int arraylength =errorParentname.size();
		for(int i = 0; i<arraylength;i++)
		{
			String existParentName =errorParentname.get(i);
			if(existParentName.equals(NodeName))
			{
				exist = true;
				break;
			}
		}
		return exist;	

	}
	protected String excelreportlocation;
	public void generateChart(PropertiesHandle config) throws DatabaseException, POIException, FileNotFoundException, SQLException, IOException
	{
		DatabaseOperation db=new DatabaseOperation();
		Date date = new Date();
		String DateandTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date);
		//DatabaseOperation.ConnectionSetup("com.mysql.jdbc.Driver","jdbc:mysql://192.168.84.225:3700/Starr_DTC_Development_ADMIN","root","redhat");
		table1=db.GetDataObjects("SELECT AnalyserResult, COUNT(*) as NoOfCount FROM OUTPUT_DTC_Rating_SinglePlan  GROUP BY AnalyserResult");
		Iterator<Entry<Integer, LinkedHashMap<String,String>>> inputtableiterator = table1.entrySet().iterator();
		excelreportlocation="AnalysisReport "+DateandTime+".xls";
		String excelreportlocation1=config.getProperty("report_location")+config.getProperty("ExecutionName")+"_AnalysisReport_"+DateandTime+".xls";
		 ExcelOperationsPOI ob=new ExcelOperationsPOI(config.getProperty("report_template_location")+"ResultTemplate.xls");
		 ob.getsheets("TestReport");
		 ob.write_data(5, 4,config.getProperty("Project")+"-"+config.getProperty("API"));
		 Date today=new Date();
		 ob.write_data(5, 7,today);
		 ob.write_data(5, 14,config.getProperty("ExecutionName"));
		int	row=9;
		int si_no=1;
		 while (inputtableiterator.hasNext()) 
		 {
			 Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
			 LinkedHashMap<String, String> inputrow = inputentry.getValue();
			
			    ob.write_data(row, 2,si_no );
			    ob.write_data(row,3,inputrow.get("AnalyserResult"));
			    ob.write_data(row,4,Integer.parseInt(inputrow.get("NoOfCount")));
				
			 row++;
			 si_no++;
			 
		 }
		 ob.refresh();
		ob.saveAs(excelreportlocation1);
		
		this.ExportToExcelTable(config.getProperty("resultQuery"), excelreportlocation, "TestCases");
	}
	
	public void Report (PropertiesHandle config) throws APIException
	{
		
		Iterator<Entry<Integer, LinkedHashMap<String,String>>> inputtableiterator = table1.entrySet().iterator();
		Date date = new Date();
		String DateandTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date);
		Template template = null;
		Map<String, Object> root = new HashMap<String, Object>();
		String Requesttemplatepath="src/main/java/com/solartis/test/report/"+"Report.ftl";
		String outputfilepath=config.getProperty("report_location")+config.getProperty("ExecutionName")+"_AnalysisReport_"+DateandTime+".html";
		System.out.println(outputfilepath);
		try
		{			
			System.setProperty("org.freemarker.loggerLibrary", "none");
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("UTF-8");
			cfg.setNumberFormat("0.######");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			//System.out.println(Requesttemplatepath);
			template = cfg.getTemplate(Requesttemplatepath);
			
			root.put("ReportInformation", new ArrayList<Object>());
			while (inputtableiterator.hasNext()) 
			{
				Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
				LinkedHashMap<String, String> inputrow = inputentry.getValue();
				((List<Object>) root.get("ReportInformation")).add(new Attribute(inputrow.get("AnalyserResult"),inputrow.get("NoOfCount")));
			}
			root.put("ExcelReport",excelreportlocation );
			File file= new File(outputfilepath);
			Writer writer = new FileWriter (file);
			template.process(root, writer);
			//System.out.println(writer.toString());
			writer.flush();
			writer.close();
			
			File htmlFile = new File(outputfilepath);
			Desktop.getDesktop().browse(htmlFile.toURI());
		}
		catch(IOException | TemplateException e)
		{
			throw new APIException("ERROR OCCURS IN load Template FUNCTION -- BASE CLASS", e);
		}
	}
	
	
	public void ExportToExcelTable(String Query,String FileToExport,String Sheet) throws DatabaseException, SQLException, FileNotFoundException, IOException
	{
		DatabaseOperation db=new DatabaseOperation();
		ResultSet rs=null;
		HSSFWorkbook workBook=null;
		HSSFSheet sheet =null;
	    rs=db.GetQueryResultsSet(Query);
	    File file = new File(FileToExport);
	    if(!file.exists())                               //Creation of Workbook and Sheet
	    {
	    	workBook =new HSSFWorkbook();
	    }
	    else
	    {
	    	workBook = new HSSFWorkbook(new FileInputStream(FileToExport));
	    }
        sheet = workBook.createSheet(Sheet);
                                                         //import columns to Excel
		ResultSetMetaData metaData=rs.getMetaData();
		int columnCount=metaData.getColumnCount();
		ArrayList<String> columns = new ArrayList<String>();
		for (int i = 1; i <= columnCount; i++) 
		{
		      String columnName = metaData.getColumnName(i);
		      columns.add(columnName);
		}
		    
		HSSFRow row = sheet.createRow(0);
		int  Fieldcol=0; 
		for (String columnName : columns) 
		{
		      row.createCell(Fieldcol).setCellValue(columnName);
		      System.out.println(columnName);
		      Fieldcol++;
		}
                                                            //import column values to Excel	
		int ValueRow=1;
		do
		 {
		    int Valuecol=0;
			HSSFRow valrow = sheet.createRow(ValueRow);
	          for (String columnName : columns)
	           {
	            String value = rs.getString(columnName);
	            valrow.createCell(Valuecol).setCellValue(value);
	            Valuecol++;
	           }
	         ValueRow++;
	     } while (rs.next());
		                                                    //Save the Details and close the File
		try
	     {
	          FileOutputStream out = new FileOutputStream(FileToExport);
	          workBook.write(out);
	          out.close();
	          System.out.println("first_excel.xls written successfully on disk.");
	      } 
	      catch (Exception e) 
	      {
	          e.printStackTrace();
	      }
		
	}
}
