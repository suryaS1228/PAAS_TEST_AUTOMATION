package com.solartis.test.servicetesting.ServiceTestingProgram;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.PropertiesHandleException;
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
	
	public static void main( String[] args ) throws DatabaseException, PropertiesHandleException, APIException
    {   
		System.setProperty("jsse.enableSNIExtension", "false");
		
		PropertiesHandle config = new PropertiesHandle(System.getProperty("Project"), System.getProperty("Api"), System.getProperty("Env"), System.getProperty("OutputChioce"), System.getProperty("UserName"), System.getProperty("JDBC_DRIVER"), System.getProperty("DB_URL"), System.getProperty("USER"), System.getProperty("password"));
		
		logInfo.info("Connecting DataBase");
		DatabaseOperation.ConnectionSetup(config);
		
		String actualchoice = config.getProperty("actual");
		String statuschoice = config.getProperty("status");
		String outputtable = config.getProperty("output_in_same_table");
		String classname = config.getProperty("ClassName");
		
		DatabaseOperation input = new DatabaseOperation();
		logInfo.info("Creating Instance for Input");
		
		input.GetDataObjects(config.getProperty("input_query"));
		
		DatabaseOperation output = new DatabaseOperation();
		logInfo.info("Creating Instance for Output");
		
		output.GetDataObjects(config.getProperty("output_query"));
		
		logInfo.info("Selecting API");
			try 
			{
				Class<?> cl = Class.forName("com.solartis.test.apiPackage."+classname);
	    	    Constructor<?> cons = cl.getConstructor(com.solartis.test.Configuration.PropertiesHandle.class);
	    	    api = (API) cons.newInstance(config);
			} 
			catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) 
			{
				logError.error("Failed Failed Selecting API -- SQLError");
				throw new TestEngineException("ERROR OCCUR IN SELECTING API");
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
							    
								logInfo.info("Loading Sample Request for Testdata--" + input.ReadData("S.No"));
								api.LoadSampleRequest(input);//LOADING SAMPLE REQUEST
								try 
								{
									logInfo.info("Pumping Testdata--" + input.ReadData("S.No") + "To Sample Request");
								     api.PumpDataToRequest();//PUMPING TESTDATA TO SAMPLEREQUEST 
								} 
								catch (APIException e)
								{
									logError.error("Failed Pumping Testdata--" + input.ReadData("S.No") + "To Sample Request---IOException | DocumentException | ParseException");
									throw new TestEngineException("FAILED TO PUMP A TESTDATA INTO SAMPLE REQUEST");
								} 
								
								try 
								{
									logInfo.info("REQUEST For Testdata--" + input.ReadData("S.No"));
									  api.RequestToString();//SHOWING REQUEST IN LOG 
								} 
								catch (APIException e1) 
								{
									logError.error("Failed Rquest For Testdata--" + input.ReadData("S.No") + "---IOException | ParseException | DocumentException");
									throw new TestEngineException("FAILED TO CONVERT TEST DATA " + input.ReadData("S.No") + " INTO REQUEST");
								} 
								
								try 
								{
									logInfo.info("Adding Header For Testdata--" + input.ReadData("S.No"));
									api.AddHeaders();//ADDING HEADER || TOKENS || EVENTS FOR HITTING REQUEST
								} 
								catch (APIException e) 
								{
									logError.error("Failed Adding Header For Testdata--" + input.ReadData("S.No") + "---IOException");
									throw new TestEngineException("FAILED TO ADDING HEADER , TOKEN , EVENT - NAME FOR HITTING");
								}
								
									logInfo.info("Respone For Testdata--" + input.ReadData("S.No") + "is Received");
									api.SendAndReceiveData();//RECIEVING AND STORING RESPONSE TO THE FILE
								
									try 
									{
										logInfo.info("REQUEST For Testdata--" + input.ReadData("S.No"));
										api.ResponseToString();//SHOWING RESPONSE IN LOG 
									} 
									catch (APIException e1) 
									{
										logError.error("Failed Rquest For Testdata--" + input.ReadData("S.No") + "---IOException | ParseException | DocumentException e1");
										throw new TestEngineException("FAILED TO CONVERT REQUEST " + input.ReadData("S.No") + " INTO RESPONSE");
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
								catch (NumberFormatException| APIException e) 
								{
										 logError.error("Failed Storing Response--" + input.ReadData("S.No") + "Data into DB---IOException | ParseException | DocumentException e");
										 throw new TestEngineException("FAILED TO STORE A RESPONSE");
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
								catch (NumberFormatException | APIException e) 
							    {
										 logError.error("Failed Storing Response--" + input.ReadData("S.No") + "Data into DB---IOException | ParseException | DocumentException e");
										 throw new TestEngineException("FAILED TO WRITE A DATA FOR TESTDATA--" + input.ReadData("S.No") + " INTO DB");
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
			catch (Exception e1)
			{
				System.exit(0);	

			}

		logInfo.info("Closing DB Connection");
		DatabaseOperation.CloseConn();
   }
}	
