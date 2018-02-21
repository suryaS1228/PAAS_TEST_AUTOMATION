package com.solartis.test.servicetesting.ServiceTestingProgram;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solartis.test.util.common.ExcelOperationsPOI;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.POIException;
import com.solartis.test.exception.PropertiesHandleException;
import com.solartis.test.listener.FireEventAPI;
import com.solartis.test.listener.Listener;
import com.solartis.test.listener.LogListener;
import com.solartis.test.util.common.DatabaseOperation;

public class MainClass 
{
	static API api=null;
	static FireEventAPI fireEventAPI;
	public static PropertiesHandle config; 
	public static String actualchoice;
	public static String statuschoice;
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
	
	@BeforeTest
	public void loadconfig() throws DatabaseException, PropertiesHandleException
	{
		System.setProperty("jsse.enableSNIExtension", "false");
		disableSslVerification();
		config = new PropertiesHandle(System.getProperty("Project"), System.getProperty("Api"), System.getProperty("Env"), System.getProperty("OutputChioce"), System.getProperty("UserName"), System.getProperty("JDBC_DRIVER"), System.getProperty("DB_URL"), System.getProperty("USER"), System.getProperty("password"), System.getProperty("Priority"));
		
		DatabaseOperation.ConnectionSetup(config);
		
		actualchoice = config.getProperty("actual");
		statuschoice = config.getProperty("status");
		outputtablechoice = config.getProperty("output_in_same_table");
		String classname = config.getProperty("ClassName");
		
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
	} 
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider="PaaSTest")
	public static void apiTest(Integer RowIterator, Object inputtablerowobj, Object outputtablerowobj)throws InterruptedException, DatabaseException, InterruptedException
    {   		
		try 
		{
			LinkedHashMap<String, String> inputrow = inputtableobjectMapper.convertValue(inputtablerowobj, LinkedHashMap.class);
			LinkedHashMap<String, String> outputrow = outputtableobjectMapper.convertValue(outputtablerowobj, LinkedHashMap.class);
			//for (Entry<Integer, LinkedHashMap<String, String>> entry : inputtable.entrySet())
				System.out.println("TestData : " + inputrow.get("S.No"));  	
						if(inputrow.get("Flag_for_execution").equals("Y"))
						{
						    System.out.println("TestData" + inputrow.get("S.No") + "flag_for_execution = Y" );					 
							
						    fireEventAPI.LoadSampleRequest(inputrow);//LOADING SAMPLE REQUEST
                            
						    fireEventAPI.PumpDataToRequest(inputrow);//PUMPING TESTDATA TO SAMPLEREQUEST s
						    
						    fireEventAPI.RequestToString();//SHOWING REQUEST IN LOG 
						
						    fireEventAPI.AddHeaders();//ADDING HEADER || TOKENS || EVENTS FOR HITTING REQUEST
							
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
							
							if(statuschoice.equals("Y"))
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
							input.UpdateRow(RowIterator, inputrow);//UPDATE DB TABLE ROWS AFTER COMPARSION
							}
						else
						{
							System.out.println("TestData" + inputrow.get("S.No") + "---flag_for_execution N");
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
	
	private static void disableSslVerification() {
	    try
	    {
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	            @Override
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	            }
	            @Override
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	            }
				
	        }
	        };

	        // Install the all-trusting trust manager
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	        	@Override
	        	public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };

	        // Install the all-trusting host verifier
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (KeyManagementException e) {
	        e.printStackTrace();
	    }
	}
	
	@AfterTest
	public void connectionclose() throws DatabaseException, POIException
	{
		
		if(statuschoice.equals("Y"))
	    {
		this.generateChart();
	    }   
		DatabaseOperation.CloseConn();
	}
	
	
	public void generateChart() throws DatabaseException, POIException
	{
		DatabaseOperation db=new DatabaseOperation();
		LinkedHashMap<Integer, LinkedHashMap<String, String>> table1 = null;
		//DatabaseOperation.ConnectionSetup("com.mysql.jdbc.Driver","jdbc:mysql://192.168.84.225:3700/Starr_DTC_Development_ADMIN","root","redhat");
		table1=db.GetDataObjects("SELECT AnalyserResult, COUNT(*) as NoOfCount FROM OUTPUT_DTC_Rating_SinglePlan  GROUP BY AnalyserResult");
		System.out.println(table1);
		 Iterator<Entry<Integer, LinkedHashMap<String,String>>> inputtableiterator = table1.entrySet().iterator();
		 
		 ExcelOperationsPOI ob=new ExcelOperationsPOI("E:\\Sasirekha1054\\ComparisonReport\\Result Template\\ResultTemplate.xls");
		 ob.getsheets("Sheet3");
		int	row=9;
		int si_no=1;
		 while (inputtableiterator.hasNext()) 
		 {
			 Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
			 LinkedHashMap<String, String> inputrow = inputentry.getValue();
			
			    ob.write_data(row, 2,si_no );
			    ob.write_data(row,3,inputrow.get("AnalyserResult"));
			    ob.write_data(row,4,Integer.parseInt(inputrow.get("NoOfCount")));
				ob.refresh();
				ob.save();
			 row++;
			 si_no++;
			 
		 }
	}
}
