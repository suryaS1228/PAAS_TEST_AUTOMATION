package com.solartis.test.util.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.exception.DatabaseException;
import com.solartis.test.util.common.DatabaseOperation;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class ExtendedRequestHandler 
{
	public DatabaseOperation requestconfigDB;
	public LinkedHashMap<Integer, LinkedHashMap<String, String>> requestconfig;
	public Template template;
	public DBColoumnVerify condition = new DBColoumnVerify();
	Map<String, Object> root = new HashMap<String, Object>();
	protected String Requesttemplatepath="";
	
	public ExtendedRequestHandler(PropertiesHandle config) throws ClassNotFoundException, DatabaseException
	{
		requestconfigDB = new DatabaseOperation();
		requestconfig=requestconfigDB.GetDataObjects(config.getProperty("InputColQuery"));
		Requesttemplatepath="src/main/java/com/solartis/test/apiPackage/"+config.getProperty("ClassName").replace(".", "/")+"Request.ftl";
	}
	
	@SuppressWarnings("deprecation")
	public void openTemplate() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException
	{
		System.setProperty("org.freemarker.loggerLibrary", "none");
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setNumberFormat("0.######");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		//System.out.println(Requesttemplatepath);
		template = cfg.getTemplate(Requesttemplatepath);
	}
	
	public void LoadData(LinkedHashMap<Integer, LinkedHashMap<String, String>> requestaddconfig, LinkedHashMap<String, String> InputData) throws DatabaseException
	{
		
		List <String> parentlist = new ArrayList<String>();
		for (Entry<Integer, LinkedHashMap<String, String>> entry : requestaddconfig.entrySet())	
		{
			LinkedHashMap<String, String> rowInputColVerify = entry.getValue();
			if(rowInputColVerify.get("flagforexecution").equals("Y") && condition.ConditionReading(rowInputColVerify.get("Condition"),InputData) )
			{
				String parentName = rowInputColVerify.get("Parent");
				boolean flag=false;
				//System.out.println(parentName);
				for(String str: parentlist) 
				{
				    if(str.trim().contains(parentName))
				       flag=true;
				    //System.out.println(parentName);
				}
				if(flag==false)
				{
					parentlist.add(parentName);				
					List <Object> atribParent = new ArrayList<Object>();
					if(rowInputColVerify.get("AttributeNature").equals("dynamic"))
					{
						//if(!InputData.get(rowInputColVerify.get("DBColumnName")).equals(""))
							root.put(parentName, atribParent);
					}
					else
					{
						root.put(parentName, atribParent);
					}
					//System.out.println(parentName);
				}
			}
		}	
	}
	@SuppressWarnings("unchecked")
	public void PumpinDatatoRequest(LinkedHashMap<Integer, LinkedHashMap<String, String>> requestaddconfig, LinkedHashMap<String, String> InputData) throws DatabaseException
	{
		for (Entry<Integer, LinkedHashMap<String, String>> entry : requestaddconfig.entrySet())	
		{
			LinkedHashMap<String, String> rowInputColVerify = entry.getValue();
			if(rowInputColVerify.get("flagforexecution").equals("Y") && condition.ConditionReading(rowInputColVerify.get("Condition"),InputData))
			{
				String parentName = rowInputColVerify.get("Parent");
				String atributeName = rowInputColVerify.get("AtributeName");
				//System.out.println(parentName+"---------"+atributeName+"----------"+rowInputColVerify.get("DBColumnName")+"---------"+InputData.get(rowInputColVerify.get("DBColumnName"))+"---------"+rowInputColVerify.get("AttributeStaticValue"));
				String atributeStaticValue = rowInputColVerify.get("AttributeStaticValue");
				System.out.println(rowInputColVerify.get("DBColumnName"));
				Object atributeDynamicValue = InputData.get(rowInputColVerify.get("DBColumnName"));

				if(rowInputColVerify.get("AttributeNature").equals("static"))
				{
					((List<Object>) root.get(parentName)).add(new Attribute(atributeName,atributeStaticValue));
					//System.out.println(atributeName+"-----------"+atributeStaticValue);
				}
				else
				{
					if(rowInputColVerify.get("iteration").equals("loop"))
					{
						atributeDynamicValue =Integer.parseInt((String) atributeDynamicValue);
					}
					else if(rowInputColVerify.get("iteration").equals("spl"))
					{
						String TableName = rowInputColVerify.get("AtributeName");
						
						DatabaseOperation input = new DatabaseOperation();
						LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable = input.GetDataObjects("Select * from "+TableName+" Where `S.NO` <= "+atributeDynamicValue);
						Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator = inputtable.entrySet().iterator();
						while (inputtableiterator.hasNext()) 
						{
							Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
							LinkedHashMap<String, String> inputrow = inputentry.getValue();
							for (Map.Entry<String,String> entryset : inputrow.entrySet()) 
							{
								((List<Object>) root.get(parentName)).add(new Attribute(entryset.getKey(),entryset.getValue()));
								System.out.println(entryset.getKey()+"-------------------------"+entryset.getValue());
							}
						}
					    
					}
					System.out.println(atributeName+"-----------"+atributeDynamicValue);
					((List<Object>) root.get(parentName)).add(new Attribute(atributeName,atributeDynamicValue));
					//
				}
			}
		}
	}

	public void saveJsontoPath(String filepath) throws TemplateException, IOException
	{
		File file= new File(filepath);
		Writer writer = new FileWriter (file);
		template.process(root, writer);
		//System.out.println(writer.toString());
		writer.flush();
		writer.close();
		
	}
	
	protected boolean isInteger(Object s) 
	{
	    try 
	    { 
	    	
	        Integer.parseInt((String) s); 
	    } 
	    catch(NumberFormatException e) 
	    { 
	        return false; 
	    } 
	    catch(NullPointerException e) 
	    {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	/*public static void main(String args[]) throws ClassNotFoundException, SQLException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, DatabaseException
	{
		propertiesHandle config= new propertiesHandle("A:/1 Projects/19 StarrGL/config/config - Copy.properties");
		
		DatabaseOperation requestconfigDB = new DatabaseOperation();
		requestconfigDB.ConnectionSetup(config);
		//DatabaseOperation.ConnectionSetup(config);
		System.out.println(config.getProperty("config_query"));
		LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  requestconfigDB.GetDataObjects(config.getProperty("config_query"));
		
		RequestHandler constructreq = new RequestHandler(config);
		constructreq.openTemplate();
		//System.out.println(rowInputColVerify);
		constructreq.LoadData(tableInputColVerify);					
		constructreq.PumpinDatatoRequest(tableInputColVerify);
		constructreq.saveJsontoPath("D:/ftl/trialjson");
	}*/
}
