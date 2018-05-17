package com.solartis.test.servicetesting.ServiceTestingProgram;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.SQLException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.apiPackage.BaseClass;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.POIException;
import com.solartis.test.exception.PropertiesHandleException;
import com.solartis.test.listener.FireEventAPI;
import com.solartis.test.listener.Listener;
import com.solartis.test.listener.LogListener;
import com.solartis.test.util.common.DatabaseOperation;
import com.solartis.test.util.common.DirectoryManipulation;

public class MainClass 
{
	static API api=null;
	static FireEventAPI fireEventAPI;
	public static PropertiesHandle config; 
	public static String actualchoice;
	public static String comparisonchoice;
	public static String outputtablechoice;
	public static DatabaseOperation input;
	public static DatabaseOperation output;
	public static LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable;
	public static LinkedHashMap<Integer, LinkedHashMap<String, String>> outputtable;
	public static Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator;
	public static Iterator<Entry<Integer, LinkedHashMap<String, String>>> outputtableiterator;
	public static ObjectMapper inputtableobjectMapper;
	public static ObjectMapper outputtableobjectMapper;
	public static LinkedHashMap<String, String> inputrow;
	public static LinkedHashMap<String, String> outputrow;
	public static HashMap<Object,Object> result;
	public static Connection Conn=null;  //added
	public static DatabaseOperation db=null;
	public static String Token;
	@BeforeTest
	public void loadconfig() throws DatabaseException, PropertiesHandleException,  POIException
	{
		try 
		{
			System.setProperty("jsse.enableSNIExtension", "false");
			try
			{
			config = new PropertiesHandle(System.getProperty("Project"), System.getProperty("Api"), System.getProperty("Env"), System.getProperty("OutputChioce"), System.getProperty("UserName"), System.getProperty("JDBC_DRIVER"), System.getProperty("DB_URL"), System.getProperty("USER"), System.getProperty("password"),System.getProperty("Priority"),System.getProperty("ExecutionName"),System.getProperty("ModeofExecution"));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			Conn=DatabaseOperation.ConnectionSetup(config);
			if(config.getProperty("ModeofExecution").equalsIgnoreCase("New"))
			{
		     this.beforeTesting();
			}
			System.out.println(config.getProperty("actualFlag")+"========"+config.getProperty("ComparisonFlag"));
			actualchoice = config.getProperty("actualFlag");
			comparisonchoice = config.getProperty("ComparisonFlag");
			outputtablechoice = config.getProperty("output_in_same_table");
			String classname = config.getProperty("ClassName");
			Class<?> cl = Class.forName("com.solartis.test.apiPackage."+classname);
			Constructor<?> cons = cl.getConstructor(com.solartis.test.Configuration.PropertiesHandle.class);
			api = (API) cons.newInstance(config);
			fireEventAPI = new FireEventAPI(api);
			Listener listener = new LogListener();
			fireEventAPI.addListener(listener);
			BaseClass baseclass = new BaseClass();
		    Token=baseclass.tokenGenerator(config);
		} 
		catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			e.printStackTrace();
			System.out.println("Error in Selecting API");
		}
	} 
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider="PaaSTest")
	public static void apiTest(Integer RowIterator, Object inputtablerowobj, Object outputtablerowobj)throws InterruptedException, DatabaseException, InterruptedException
    {   		
		try 
		{	
			LinkedHashMap<String, String> inputrow = inputtableobjectMapper.convertValue(inputtablerowobj, LinkedHashMap.class);
			LinkedHashMap<String, String> outputrow = outputtableobjectMapper.convertValue(outputtablerowobj, LinkedHashMap.class);
				System.out.println("Si_NO :"+inputrow.get("S_No")+"TestData : " + inputrow.get("Testdata"));  	
						if(inputrow.get("Flag_for_execution").equals("Y"))
						{							
						    fireEventAPI.LoadSampleRequest(inputrow);//LOADING SAMPLE REQUEST
                            
						    fireEventAPI.PumpDataToRequest(inputrow);//PUMPING TESTDATA TO SAMPLEREQUEST s
						    
						    fireEventAPI.RequestToString(Token);//SHOWING REQUEST IN LOG 
						
						    fireEventAPI.AddHeaders(Token);//ADDING HEADER || TOKENS || EVENTS FOR HITTING REQUEST
							
						    fireEventAPI.SendAndReceiveData();//RECIEVING AND STORING RESPONSE TO THE FILE
							
						    fireEventAPI.ResponseToString();//SHOWING RESPONSE IN LOG 
							
							if(actualchoice.equals("Y"))
							{
								  
								if(outputtablechoice.equals("Y"))//INPUT AND OUT DB TABLE ARE SAME
								{

									inputrow = fireEventAPI.SendResponseDataToFile(inputrow);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
								
									input.UpdateRow(RowIterator, inputrow);//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA
								}
								else//INPUT AND OUT DB TABLE ARE DIFFERENT
								{
									outputrow = fireEventAPI.SendResponseDataToFile(outputrow);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
									 output.UpdateRow(RowIterator, outputrow);//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA	
								
								}
							} 
							
							if(comparisonchoice.equals("Y"))
							{
								if(outputtablechoice.equals("Y"))
								{
									
									inputrow = fireEventAPI.CompareFunction(inputrow,outputrow);//CALLING COMPARING FUNCTION
								     
									input.UpdateRow(RowIterator, inputrow);
								}
								else
								{
									
									outputrow = fireEventAPI.CompareFunction(inputrow,outputrow);//CALLING COMPARING FUNCTION
								    
									output.UpdateRow(RowIterator, outputrow);
									
								}
							} 
							
							inputrow.put("Flag_for_execution", "Completed");
							//input.UpdateRow(RowIterator, inputrow);//UPDATE DB TABLE ROWS AFTER COMPARSION
							}
						else
						{
							//System.out.println("TestData" + inputrow.get("S.No") + "---flag_for_execution N");
						}
				
				/*if(actualchoice.equals("Y") || statuschoice.equals("Y"))
				{
					output.MoveForward();
				}*/
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			//e1.getCause().getMessage();
			System.exit(0);	

		}
		
    }	
			
	
	@AfterTest
	public void connectionclose() throws DatabaseException, POIException, APIException
	{
		BaseClass base = new BaseClass();
		try
		{
	
		base.generateReport(config,comparisonchoice);

		DirectoryManipulation.zipFolder(config.getProperty("ZipFolderPath"), config.getProperty("OverallResults"));
		
		DatabaseOperation.CloseConn();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void beforeTesting() throws POIException
	{
		try
		{
		    db=new DatabaseOperation();
		    db.truncateTable(config.getProperty("inputTable"));
		    db.truncateTable(config.getProperty("outputTable"));
			db.ImportDatatoDB(config.getProperty("TestdataPath"),Conn, config.getProperty("inputTable"), "Sheet1", "Import");
			db.insetRowWithSNO(config.getProperty("outputTable"), config.getProperty("inputTable"));
			DirectoryManipulation.deleteFileFromDirectory(config.getProperty("request_location"));
			DirectoryManipulation.deleteFileFromDirectory(config.getProperty("response_location"));
			DirectoryManipulation.deleteFileFromDirectory(config.getProperty("TargetPath"));
			DirectoryManipulation.deleteFileFromDirectory(config.getProperty("report_location"));
		}
		catch(SQLException | ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
			
	}
	
	
	@SuppressWarnings("unused")
	@DataProvider(name="PaaSTest", parallel=false)
	 public Object[][] getDataFromDataprovider() throws DatabaseException
	 {
		 input = new DatabaseOperation();
		 inputtable = input.GetDataObjects(config.getProperty("input_query"));
		 Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator = inputtable.entrySet().iterator();
		 output = new DatabaseOperation();
		 outputtable = output.GetDataObjects(config.getProperty("output_query"));
		 Iterator<Entry<Integer, LinkedHashMap<String, String>>> outputtableiterator = outputtable.entrySet().iterator();
	
		 int rowIterator = 0;
		 Object[][] combined = new Object[inputtable.size()][3];
		 while (inputtableiterator.hasNext() && outputtableiterator.hasNext()) 
			{
				Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
				Entry<Integer, LinkedHashMap<String, String>> outputentry = outputtableiterator.next();
				Integer inputtablekey = inputentry.getKey();
		        LinkedHashMap<String, String> inputrow = inputentry.getValue();
		        LinkedHashMap<String, String> outputrow = outputentry.getValue();
		         
		         inputtableobjectMapper = new ObjectMapper();
				 Object inputtablerowobject = inputtableobjectMapper.convertValue(inputrow, Object.class);
				 
				 outputtableobjectMapper = new ObjectMapper();
				 Object outputtablerowobject = outputtableobjectMapper.convertValue(outputrow, Object.class);
				 
				 combined[rowIterator][0] = rowIterator+1;
				 combined[rowIterator][1] = inputtablerowobject;
				 combined[rowIterator][2] = outputtablerowobject;
				 rowIterator++;
			}  
		 
		 return combined;
	 }
	
}
