package com.solartis.test.servicetesting.ServiceTestingProgram;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.PropertiesHandleException;
import com.solartis.test.listener.FireEventAPI;
import com.solartis.test.listener.Listener;
import com.solartis.test.listener.LogListener;
import com.solartis.test.util.common.DatabaseOperation;

public class TestEngine 
{
	static API api=null;
	static FireEventAPI fireEventAPI;
	
	public static void main( String[] args ) throws DatabaseException, PropertiesHandleException, APIException
    {   
		System.setProperty("jsse.enableSNIExtension", "false");
		
		//PropertiesHandle config = new PropertiesHandle(System.getProperty("Project"), System.getProperty("Api"), System.getProperty("Env"), System.getProperty("OutputChioce"), System.getProperty("UserName"), System.getProperty("JDBC_DRIVER"), System.getProperty("DB_URL"), System.getProperty("USER"), System.getProperty("password"), System.getProperty("Priority"));
		PropertiesHandle config = new PropertiesHandle(args[0],args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
		
		DatabaseOperation.ConnectionSetup(config);
		
		String actualchoice = config.getProperty("actual");
		String statuschoice = config.getProperty("status");
		String outputtablechoice = config.getProperty("output_in_same_table");
		String classname = config.getProperty("ClassName");
		
		DatabaseOperation input = new DatabaseOperation();
		
		LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable = input.GetDataObjects(config.getProperty("input_query"));
		
		Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator = inputtable.entrySet().iterator();
		
		DatabaseOperation output = new DatabaseOperation();
		
		LinkedHashMap<Integer, LinkedHashMap<String, String>> outputtable = output.GetDataObjects(config.getProperty("output_query"));
		
		Iterator<Entry<Integer, LinkedHashMap<String, String>>> outputtableiterator = outputtable.entrySet().iterator();
		
		try 
		{
			Class<?> cl = Class.forName("com.solartis.test.apiPackage."+classname);
			Constructor<?> cons = cl.getConstructor(com.solartis.test.Configuration.PropertiesHandle.class);
		    api = (API) cons.newInstance(config);
		    fireEventAPI = new FireEventAPI(api);
		    Listener listener = new LogListener();
		    fireEventAPI.addListener(listener);
		    
		} 
		catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			System.out.println("Error in Selecting API");
		}
	    

			try 
			{

				//for (Entry<Integer, LinkedHashMap<String, String>> entry : inputtable.entrySet())
				while (inputtableiterator.hasNext() && outputtableiterator.hasNext()) 
				{
					Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
					Entry<Integer, LinkedHashMap<String, String>> outputentry = outputtableiterator.next();
					Integer inputtablekey = inputentry.getKey();
			        LinkedHashMap<String, String> inputrow = inputentry.getValue();
			        LinkedHashMap<String, String> outputrow = outputentry.getValue();	
					System.out.println("TestData : " + inputrow.get("S.No"));  	
							if(inputrow.get("Flag_for_execution").equals("Y"))
							{
							    System.out.println("TestData" + inputrow.get("S.No") + "flag_for_execution = Y" );					 
								
							    fireEventAPI.LoadSampleRequest(inputrow);//LOADING SAMPLE REQUEST
                                
							    fireEventAPI.PumpDataToRequest();//PUMPING TESTDATA TO SAMPLEREQUEST s
							    
							    fireEventAPI.RequestToString();//SHOWING REQUEST IN LOG 
							
							    fireEventAPI.AddHeaders();//ADDING HEADER || TOKENS || EVENTS FOR HITTING REQUEST
								
							    fireEventAPI.SendAndReceiveData();//RECIEVING AND STORING RESPONSE TO THE FILE
								
							    fireEventAPI.ResponseToString();//SHOWING RESPONSE IN LOG 
								
								if(actualchoice.equals("Y"))
								{
									  
									if(outputtablechoice.equals("Y"))//INPUT AND OUT DB TABLE ARE SAME
									{	
										inputrow = fireEventAPI.SendResponseDataToFile(inputrow);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
									
										input.UpdateRow(inputtablekey, inputrow);//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA
									}
									else//INPUT AND OUT DB TABLE ARE DIFFERENT
									{	
										
										outputrow = fireEventAPI.SendResponseDataToFile(outputrow);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
										
										output.UpdateRow(inputtablekey, outputrow);//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA	
										
									}
								} 
								
								if(statuschoice.equals("Y"))
								{
									if(outputtablechoice.equals("Y"))
									{
										inputrow = fireEventAPI.CompareFunction(inputrow);//CALLING COMPARING FUNCTION
									     
										input.UpdateRow(inputtablekey, inputrow);
									}
									else
									{
										outputrow = fireEventAPI.CompareFunction(outputrow);//CALLING COMPARING FUNCTION
									    
										output.UpdateRow(inputtablekey, outputrow);
										
										output.UpdateTable(outputtable);
									}
								} 
								
								inputrow.put("Flag_for_execution", "Completed");
								input.UpdateRow(inputtablekey, inputrow);//UPDATE DB TABLE ROWS AFTER COMPARSION
								}
							else
							{
								System.out.println("TestData" + inputrow.get("S.No") + "---flag_for_execution N");
							}
					
					if(actualchoice.equals("Y") || statuschoice.equals("Y"))
					{
						output.MoveForward();
					}
				}
			} 
			catch (APIException e1)
			{
				e1.getCause().getMessage();
				System.exit(0);	

			}

		DatabaseOperation.CloseConn();
   }
}	
