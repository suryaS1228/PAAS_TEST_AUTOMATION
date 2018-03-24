package com.solartis.test.servicetesting.ServiceTestingProgram;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.apiPackage.API;
import com.solartis.test.exception.APIException;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.exception.PropertiesHandleException;
import com.solartis.test.listener.FireEventAPI;
import com.solartis.test.listener.Listener;
import com.solartis.test.listener.LogListener;
import com.solartis.test.util.common.DatabaseOperation;


public class MainClass2 
{	
	public static boolean quote = false;	
	public static LinkedHashMap<String, String> commonMap = new LinkedHashMap<String, String>();
	public static String[] apii;
	public static PropertiesHandle[] ConfigObjectRepository;
	public static DatabaseOperation[] OutputDBObjectRepository;
	public static DatabaseOperation inputTable;
	public static Object[] OutputTableRepository;
	public static String InputtableQuery;
	
	@BeforeTest
	public void beforeTest() 
	{
		try
		{
		System.setProperty("jsse.enableSNIExtension", "false");
		String apis = System.getProperty("Api");
		apii = apis.split("-");
		InputtableQuery="SELECT * FROM INPUT_Quote_GL_V6 a INNER JOIN INPUT_GL_PolicyIssuance_V3 b on a.`S.No` = b.`S.No` INNER JOIN INPUT_GL_Cancel_V2 c on b.`S.No` = c.`S.No`";
		ConfigObjectRepository=new PropertiesHandle[apii.length];
		OutputDBObjectRepository= new DatabaseOperation[apii.length];
		OutputTableRepository = new Object[apii.length];
		for(int i=0;i<apii.length;i++)
		{
			
			System.out.println(apii[i]);
			if(ConfigObjectRepository==null)
			{
				System.out.println("config is null"+i);
			}
			ConfigObjectRepository[i]=new PropertiesHandle(System.getProperty("Project"), apii[i], System.getProperty("Env"), System.getProperty("OutputChioce"), System.getProperty("UserName"), System.getProperty("JDBC_DRIVER"), System.getProperty("DB_URL"), System.getProperty("USER"), System.getProperty("password"), System.getProperty("Priority"));;
			
			OutputDBObjectRepository[i]= new DatabaseOperation();
			OutputDBObjectRepository[0].ConnectionSetup(ConfigObjectRepository[i]);
			//System.out.println(ConfigObjectRepository[i].getProperty("output_query"));
			OutputDBObjectRepository[i].GetDataObjects(ConfigObjectRepository[i].getProperty("output_query"));		
			//System.out.println(ConfigObjectRepository[i].getProperty("output_query"));
			OutputTableRepository[i]=this.outputtable(ConfigObjectRepository[i]);
		}
		
		inputTable = new DatabaseOperation();
		inputTable.GetDataObjects(InputtableQuery);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	@Test(dataProvider="PAASDP")
	public void Api1(Integer RowIterator, Object inputtablerowobj) throws InterruptedException, DatabaseException, PropertiesHandleException, APIException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		for(int i=0;i<apii.length;i++)
		{
			GenericMethod(RowIterator-1, inputtablerowobj, (Object[]) OutputTableRepository[i], apii[i], ConfigObjectRepository[i],inputTable,OutputDBObjectRepository[i]);
		}
		
		commonMap.clear();
	}
	
	@SuppressWarnings({ "unchecked" })
	public void GenericMethod(Integer RowIterator, Object inputtablerowobj, Object[] outputtablerowobject, String apis, PropertiesHandle configuration,DatabaseOperation inputTable, DatabaseOperation OutputTable)throws InterruptedException, DatabaseException, InterruptedException , DatabaseException, PropertiesHandleException, APIException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		try
		{
		//PropertiesHandle config; 
		LinkedHashMap<String, String> inputrow = new LinkedHashMap<String, String> ();
		LinkedHashMap<String, String> outputrow = new LinkedHashMap<String, String> ();
		ObjectMapper inputtableobjectMapper = new ObjectMapper();
		ObjectMapper outputtableobjectMapper = new ObjectMapper();
		Object outputtablerowobj=new Object(); 
		//System.out.println(RowIterator);
		outputtablerowobj = outputtablerowobject[RowIterator];
		String actualchoice = configuration.getProperty("actual");
		String statuschoice = configuration.getProperty("status");
		String outputtablechoice = configuration.getProperty("output_in_same_table");
		String classname = configuration.getProperty("ClassName");		
		
		Class<?> cl = Class.forName("com.solartis.test.apiPackage."+classname);
		Constructor<?> cons = cl.getConstructor(com.solartis.test.Configuration.PropertiesHandle.class);
		API api = (API) cons.newInstance(configuration);
		FireEventAPI fireEventAPI = new FireEventAPI(api);
		Listener listener = new LogListener();
		fireEventAPI.addListener(listener);
		
		if(inputtablerowobj==null||outputtablerowobj==null)
		{
			System.out.println("objects is null.............");
		}
	
		inputrow = inputtableobjectMapper.convertValue(inputtablerowobj, LinkedHashMap.class);
		outputrow = outputtableobjectMapper.convertValue(outputtablerowobj, LinkedHashMap.class);
		if (inputrow==null)
		{
			System.out.println("inputrow is null");
		}
		System.out.println("TestData : " + inputrow.get("S.No"));  	
		if(inputrow.get("Flag_for_execution").equals("Y"))
		{
			System.out.println("TestData" + inputrow.get("S.No") + "flag_for_execution = Y" );					 
							
			fireEventAPI.LoadSampleRequest(inputrow);//LOADING SAMPLE REQUEST
                            
			fireEventAPI.PumpDataToRequest(commonMap,inputrow);//PUMPING TESTDATA TO SAMPLEREQUEST s
						    
			fireEventAPI.RequestToString();//SHOWING REQUEST IN LOG 
						
			fireEventAPI.AddHeaders();//ADDING HEADER || TOKENS || EVENTS FOR HITTING REQUEST
							
			fireEventAPI.SendAndReceiveData();//RECIEVING AND STORING RESPONSE TO THE FILE
							
			fireEventAPI.ResponseToString();//SHOWING RESPONSE IN LOG 
							
			if(actualchoice.equals("Y"))
			{								  
				if(outputtablechoice.equals("Y"))//INPUT AND OUT DB TABLE ARE SAME
				{
					inputrow = fireEventAPI.SendResponseDataToFile(inputrow);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
					commonMap.putAll(inputrow);
					//inputTable.UpdateRow(RowIterator, inputrow);//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA
				}
				else//INPUT AND OUT DB TABLE ARE DIFFERENT
				{
					outputrow = fireEventAPI.SendResponseDataToFile(outputrow);//FETCHING DATA FROM RESPONSE AND STORE THEM INTO THE DATABASE TABLE
					commonMap.putAll(outputrow);
					System.out.println(RowIterator+"-----------"+outputrow);
					OutputTable.UpdateRow(RowIterator+1, outputrow);//UPDATE DB TABLE ROWS AFTER INSERTING RESPONSE DATA		
					System.out.println("Update completed");
				}
			} 
							
			if(statuschoice.equals("Y"))
			{
				if(outputtablechoice.equals("Y"))
				{									
					inputrow = fireEventAPI.CompareFunction(inputrow);//CALLING COMPARING FUNCTION
								     
					inputTable.UpdateRow(RowIterator, inputrow);
				}
				else
				{
					outputrow = fireEventAPI.CompareFunction(outputrow);//CALLING COMPARING FUNCTION
								    
					OutputTable.UpdateRow(RowIterator, outputrow);
					commonMap.putAll(outputrow);									
				}
			} 
							
			//inputrow.put("Flag_for_execution", "Completed");
			inputTable.UpdateRow(RowIterator, inputrow);//UPDATE DB TABLE ROWS AFTER COMPARSION
			}
		else
		{
			System.out.println("TestData" + inputrow.get("S.No") + "---flag_for_execution N");
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		/*if(actualchoice.equals("Y") || statuschoice.equals("Y"))
		{
			output.MoveForward();
		}*/		
    }
	
	@DataProvider(name="PAASDP")
	public Object[][] dpapi1() throws DatabaseException
	{
		LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable;
		ObjectMapper inputtableobjectMapper;		
		DatabaseOperation input = new DatabaseOperation();
		inputtable = input.GetDataObjects(InputtableQuery);
		Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator = inputtable.entrySet().iterator();	
		int rowIterator = 0;
		Object[][] combined = new Object[inputtable.size()][2];
		while (inputtableiterator.hasNext() ) 
		{
			Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();				
			LinkedHashMap<String, String> inputrow = inputentry.getValue();		         
		    inputtableobjectMapper = new ObjectMapper();
			Object inputtablerowobject = inputtableobjectMapper.convertValue(inputrow, Object.class);				 
			combined[rowIterator][0] = rowIterator+1;
			combined[rowIterator][1] = inputtablerowobject;				 
			rowIterator++;
		}  		 
		return combined;
	}
	
	public Object[] outputtable(PropertiesHandle config) throws DatabaseException
	{
		
		ObjectMapper outputtableobjectMapper;
		LinkedHashMap<Integer, LinkedHashMap<String, String>> outputtable;
		DatabaseOperation output = new DatabaseOperation();
		outputtable = output.GetDataObjects(config.getProperty("output_query"));
		Object[] outputableobject = new Object[outputtable.size()];
		Iterator<Entry<Integer, LinkedHashMap<String, String>>> outputtableiterator = outputtable.entrySet().iterator();
		int rowIterator = 0;
		while (outputtableiterator.hasNext()) 
		{
			Entry<Integer, LinkedHashMap<String, String>> outputentry = outputtableiterator.next();
			LinkedHashMap<String, String> outputrow = outputentry.getValue();
			outputtableobjectMapper = new ObjectMapper();
			Object outputtablerowobject = outputtableobjectMapper.convertValue(outputrow, Object.class);
			outputableobject[rowIterator]=outputtablerowobject;
			rowIterator++;
		}
		return outputableobject;		
	}
	
}
