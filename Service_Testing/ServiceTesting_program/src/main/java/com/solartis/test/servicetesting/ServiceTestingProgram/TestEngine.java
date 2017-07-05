package com.solartis.test.servicetesting.ServiceTestingProgram;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import jxl.read.biff.BiffException;
import org.dom4j.DocumentException;
import org.json.simple.parser.ParseException;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.util.common.DatabaseOperation;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class TestEngine 
{
	
	static Logger logError = Logger.getLogger("ERRORlog");
	static Logger logInfo = Logger.getLogger("INFOlog");
	static API api=null;
	
	public static void main( String[] args ) throws Exception 
    {   
		System.setProperty("jsse.enableSNIExtension", "false");
		
		PropertiesHandle config = new PropertiesHandle(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
		
		try                                      
		{
			logInfo.info("Connecting DataBase");
			DatabaseOperation.ConnectionSetup(config);
		} 
		catch (ClassNotFoundException e) 
		{
			logError.error("Failed DataBase Connection -- ClassNotFoundError");
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			logError.error("Failed DataBase Connection -- SQLError");
			e.printStackTrace();
		}
		String actualchoice = config.getProperty("actual");
		String statuschoice = config.getProperty("status");
		String outputtable = config.getProperty("output_in_same_table");
		String classname = config.getProperty("ClassName");
		
		DatabaseOperation input = new DatabaseOperation();
		try 
		{
			logInfo.info("Creating Instance for Input");
			input.GetDataObjects(config.getProperty("input_query"));
		}
		catch (SQLException e1) 
		{
			logError.error("Failed Creating Instance for Input -- SQLError");
			e1.printStackTrace();
		}
		
		DatabaseOperation output = new DatabaseOperation();
		try 
		{
			logInfo.info("Creating Instance for Output");
			output.GetDataObjects(config.getProperty("output_query"));	
		} 
		catch (SQLException e1) 
		{
			logError.error("Failed Creating Instance for Output -- SQLError");
			e1.printStackTrace();
		}
		
		logInfo.info("Selecting API");
			try 
			{
				Class<?> cl = Class.forName("com.solartis.test.apiPackage."+classname);
	    	    Constructor<?> cons = cl.getConstructor(com.solartis.test.Configuration.PropertiesHandle.class);
	    	    api = (API) cons.newInstance(config);
			} 
			catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				logError.error("Failed Failed Selecting API -- SQLError");
				e.printStackTrace();
			}
    	    
			try 
			{

				do
				{
					logInfo.info("TestData" + input.ReadData("S.No") + "Running" );
					System.out.println("TestData : " + input.ReadData("S.No"));  	
							if(input.ReadData("flag_for_execution").equals("Y"))
							{
							    logInfo.info("TestData" + input.ReadData("S.No") + "flag_for_execution = Y" );	
								try
								{
									logInfo.info("Loading Sample Request for Testdata--" + input.ReadData("S.No"));
									
									api.LoadSampleRequest(input);//LOADING SAMPLE REQUEST
								}
								catch (SQLException| BiffException| IOException e)
								{
									logError.error("Failed to Load Sample; Testdata--" + input.ReadData("S.No") + "To Sample Request---SQLException | BiffException | IOException");
									e.printStackTrace();
								}
								try 
								{
									logInfo.info("Pumping Testdata--" + input.ReadData("S.No") + "To Sample Request");
								      api.PumpDataToRequest();//PUMPING TESTDATA TO SAMPLEREQUEST 
								} 
								catch (IOException | DocumentException | ParseException |NumberFormatException | java.text.ParseException |BiffException e)
								{
									logError.error("Failed Pumping Testdata--" + input.ReadData("S.No") + "To Sample Request---IOException | DocumentException | ParseException");
									e.printStackTrace();
								} 
								
								try 
								{
									logInfo.info("REQUEST For Testdata--" + input.ReadData("S.No"));
									  api.RequestToString();//SHOWING REQUEST IN LOG 
								} 
								catch (IOException | ParseException | DocumentException e1) 
								{
									logError.error("Failed Rquest For Testdata--" + input.ReadData("S.No") + "---IOException | ParseException | DocumentException");
									e1.printStackTrace();
								} 
								
								try 
								{
									logInfo.info("Adding Header For Testdata--" + input.ReadData("S.No"));
									  api.AddHeaders();//ADDING HEADER || TOKENS || EVENTS FOR HITTING REQUEST
								} 
								catch (IOException e) 
								{
									logError.error("Failed Adding Header For Testdata--" + input.ReadData("S.No") + "---IOException");
									throw new Exception("New"); 
								}
								
									logInfo.info("Respone For Testdata--" + input.ReadData("S.No") + "is Received");
									api.SendAndReceiveData();//RECIEVING AND STORING RESPONSE TO THE FILE
								
									logInfo.info("REQUEST For Testdata--" + input.ReadData("S.No"));
									try 
									{
										logInfo.info("REQUEST For Testdata--" + input.ReadData("S.No"));
										api.ResponseToString();//SHOWING RESPONSE IN LOG 
									} 
									catch (IOException | ParseException | DocumentException e1) 
									{
										logError.error("Failed Rquest For Testdata--" + input.ReadData("S.No") + "---IOException | ParseException | DocumentException e1");
										e1.printStackTrace();
									}						
								
								if(actualchoice.equals("Y"))
								{
									 logInfo.info("Fetching Data from Response to Store in DB is selected");
									  
								if(outputtable.equals("Y"))//INPUT AND OUT DB TABLE ARE SAME
								{	
							    try 
							    {
										logInfo.info("Storing Response--" + input.ReadData("S.No") + "Data into DB");
										input = api.SendResponseDataToFile(input);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
								} 
								catch (IOException | ParseException | DocumentException | NumberFormatException| java.text.ParseException e) 
								{
										 logError.error("Failed Storing Response--" + input.ReadData("S.No") + "Data into DB---IOException | ParseException | DocumentException e");
										 e.printStackTrace();
								}
									 
									     logInfo.info("Updating DB For Testdata--" + input.ReadData("S.No"));
									     input.UpdateRow();//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA
								}
								else//INPUT AND OUT DB TABLE ARE DIFFERENT
								{	
								try 
							    {
										logInfo.info("Storing Response--" + input.ReadData("S.No") + "Data into DB");
										output = api.SendResponseDataToFile(output);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
								} 
								catch (IOException | ParseException | DocumentException |NumberFormatException | java.text.ParseException e) 
							    {
										 logError.error("Failed Storing Response--" + input.ReadData("S.No") + "Data into DB---IOException | ParseException | DocumentException e");
										 e.printStackTrace();
								}
									 
									     logInfo.info("Updating DB For Testdata--" + input.ReadData("S.No"));
									     output.UpdateRow();//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA	
								}
								} 
								
								if(statuschoice.equals("Y"))
								{
									if(outputtable.equals("Y"))
									{
										logInfo.info("Comparison is selected");
										 logInfo.info("Starts Copmaring Expected and atual");
										 input = api.CompareFunction(input);//CALLING COMPARING FUNCTION
									     logInfo.info("Updating DB For Comparison");
									     input.UpdateRow();
									}
									else
									{
										 logInfo.info("Comparison is selected");
										 logInfo.info("Starts Copmaring Expected and atual");
										 output = api.CompareFunction(output);//CALLING COMPARING FUNCTION
									     logInfo.info("Updating DB For Comparison");
										 output.UpdateRow();
									}
								} 
								
								input.WriteData("Flag_for_execution", "Completed");
								  input.UpdateRow();//UPDATE DB TABLE ROWS AFTER COMPARSION
								}
							else
							{
								logError.error("TestData" + input.ReadData("S.No") + "---flag_for_execution N");
								logInfo.info("TestData" + input.ReadData("S.No") + "---flag_for_execution N" );
							}
					logInfo.info("Moving To Next TestData");
					if(actualchoice.equals("Y") || statuschoice.equals("Y"))
					{
						output.MoveForward();
					}
				}while(input.MoveForward());
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
				System.exit(0);
				

			}

		try 
		{
			logInfo.info("Closing DB Connection");
			DatabaseOperation.CloseConn();
		}
		catch (SQLException e) 
		{
			logError.error("Failed DataBase Closing -- SQLError");
			e.printStackTrace();
			System.exit(0);
		}
   }
}	
