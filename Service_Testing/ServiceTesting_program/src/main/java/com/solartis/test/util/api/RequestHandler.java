package com.solartis.test.util.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import SupportingClasses.propertiesHandle;

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

public class RequestHandler 
{
	public DatabaseOperation requestconfigDB;
	public LinkedHashMap<Integer, LinkedHashMap<String, String>> requestconfig;
	public Template template;
	Map<String, Object> root = new HashMap<String, Object>();
	
	public RequestHandler(PropertiesHandle config) throws ClassNotFoundException, DatabaseException
	{
		requestconfigDB = new DatabaseOperation();
		//DatabaseOperation.ConnectionSetup(config);
		requestconfig=requestconfigDB.GetDataObjects(config.getProperty("request_query"));
	}
	
	@SuppressWarnings("deprecation")
	public void openTemplate() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException
	{
		System.setProperty("org.freemarker.loggerLibrary", "none");
		String path="src/main/java/com/solartis/test/apiPackage/StarrGL/StarrGLRateRequest.ftl";
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		//cfg.setTemplateLoader(templateLoader);
		template = cfg.getTemplate("src/main/java/com/solartis/test/apiPackage/StarrGL/StarrGLRateRequest.ftl");
	}
	
	public void LoadData(LinkedHashMap<Integer, LinkedHashMap<String, String>> requestaddconfig)
	{
		
		List <String> parentlist = new ArrayList<String>();
		for (Entry<Integer, LinkedHashMap<String, String>> entry : requestaddconfig.entrySet())	
		{
			LinkedHashMap<String, String> rowInputColVerify = entry.getValue();
			System.out.println(rowInputColVerify);
			if(rowInputColVerify.get("flagforexecution").equals("Y"))
			{
				String parentName = rowInputColVerify.get("Parent");
				boolean flag=false;
				System.out.println(parentName);
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
					root.put(parentName, atribParent);
					//System.out.println(parentName);
				}
			}
		}	
	}
	@SuppressWarnings("unchecked")
	public void PumpinDatatoRequest(LinkedHashMap<Integer, LinkedHashMap<String, String>> requestaddconfig, LinkedHashMap<String, String> InputData)
	{
		for (Entry<Integer, LinkedHashMap<String, String>> entry : requestaddconfig.entrySet())	
		{
			LinkedHashMap<String, String> rowInputColVerify = entry.getValue();
			if(rowInputColVerify.get("flagforexecution").equals("Y"))
			{
				String parentName=rowInputColVerify.get("Parent");
				String atributeName=rowInputColVerify.get("AtributeName");
				String atributeStaticValue=rowInputColVerify.get("AttributeStaticValue");
				String atributeDynamicValue=InputData.get(rowInputColVerify.get("DBColumnName"));
				System.out.println(parentName+"---------"+atributeName+"---------"+atributeStaticValue);
				if(rowInputColVerify.get("AttributeNature").equals("static"))
					((List<Object>) root.get(parentName)).add(new Attribute(atributeName,atributeStaticValue));
				else
					((List<Object>) root.get(parentName)).add(new Attribute(atributeName,atributeDynamicValue));
			}
		}
	}

	public void saveJsontoPath(String filepath) throws TemplateException, IOException
	{
		/*Writer out = new OutputStreamWriter(System.out);
		template.process(root, out);
		out.flush();*/
		File file= new File(filepath+".json");
		Writer writer = new FileWriter (file);
		template.process(root, writer);
		System.out.println(file.toString());
		writer.flush();
		writer.close();
		
	}
	
	/*public static void main(String args[]) throws ClassNotFoundException, SQLException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, DatabaseException
	{
		propertiesHandle config= new propertiesHandle("A:/1 Projects/19 StarrGL/config/config - Copy.properties");
		
		DatabaseOperation requestconfigDB = new DatabaseOperation();
		requestconfigDB.ConnectionSetup(config);
		//DatabaseOperation.ConnectionSetup(config);
		System.out.println(config.getProperty("config_query"));
		LinkedHashMap<Integer, LinkedHashMap<String, String>> tableInputColVerify =  requestconfigDB.GetDataObjects(config.getProperty("config_query"));
		
	//	RequestHandler constructreq = new RequestHandler(config);
		constructreq.openTemplate();
		//System.out.println(rowInputColVerify);
		constructreq.LoadData(tableInputColVerify);					
		constructreq.PumpinDatatoRequest(tableInputColVerify);
		constructreq.saveJsontoPath("D:/ftl/trialjson");
	}*/
}
