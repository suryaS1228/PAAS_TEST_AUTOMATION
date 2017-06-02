package Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Properties;
import util.common.DatabaseOperation;

public class PropertiesHandle extends Properties

{
	private static final long serialVersionUID = 1L;
	protected String path = null;
	protected static String Project;
	protected static String Api;
	protected static String Env;
	protected static String AutualFlag;
	protected static String ComparisonFlag;
	static DatabaseOperation DB = new DatabaseOperation();
	static DatabaseOperation ConfigQuery = new DatabaseOperation();
			
	    public PropertiesHandle(String Project,String Api, String Env ,String AutualFlag, String ComparisonFlag) throws ClassNotFoundException, SQLException
		{
			this.Project = Project;
			this.Api=Api;
			this.Env=Env;
			this.AutualFlag=AutualFlag;
			this.ComparisonFlag=ComparisonFlag;
			
			WriteProperty();
			
		}
		
		protected PropertiesHandle WriteProperty() throws ClassNotFoundException, SQLException
		{
			DB.ConnectionSetup();
			
			this.put("output_in_same_table", this.QueryValue("SameTable","CONFIG_SameOutputTable"));
			
            if(AutualFlag.equalsIgnoreCase("responseneed"))
            {
				      this.put("actual", "Y");
            }
		    else if(AutualFlag.equalsIgnoreCase("responsenotneed"))
		    {
				      this.put("actual", "N");
		    }
			if(ComparisonFlag.equalsIgnoreCase("comparisonneed"))
			{
				this.put("status", "Y");
			}
			else if(ComparisonFlag.equalsIgnoreCase("comparisionneed"))
			{
				this.put("status", "N");  
			}
			
			this.put("sample_request", "Q:/RestFullAPIDeliverable/" + Project + "/" + Api + "/SampleRequest/");
			this.put("request_location", "Q:/RestFullAPIDeliverable/" + Project + "/" + Api + "/Request/");
			this.put("response_location", "Q:/RestFullAPIDeliverable/" + Project + "/" + Api + "/Response/");
			
			this.put("test_url", this.QueryValue(Env,"CONFIG_URL"));
			this.put("type", this.QueryValue("Type","CONFIG_ServiceType"));
			this.put("content_type", "application/"+this.QueryValue("Type","CONFIG_ServiceType"));
			this.put("token", this.QueryValue("Token","CONFIG_Token"));
			
			this.put("json_query",   this.Query("CNFTable","CONFIG_Cnf"));
			this.put("input_query",  this.Query("InputTable","CONFIG_Input"));
			this.put("output_query", this.Query("OutputTable","CONFIG_Output"));
			
			this.put("InputColQuery",this.Query("ConditionInputTable","CONFIG_ConditionInput"));
			this.put("OutputColQuery",this.Query("ConditionOutputTable","CONFIG_ConditionOutput"));
			
			this.put("InputColumn", "InputColumn");
		    this.put("OutputColumn", "OutputColumn");
			
			this.put("InputCondColumn", "InputColumnCondtn");
		    this.put("OutputCondColumn", "OutputColumnCondtn");
		    
		    this.put("jdbc_driver", "com.mysql.jdbc.Driver");
		    this.put("db_url", "jdbc:mysql://192.168.35.2:3391/" + this.QuerySingleValue("DB","CONFIG_DB"));
		    this.put("db_username", "root");
		    this.put("db_password", "password");
		    return this;
		}
		
		protected String Query(String OutputColoumn, String TableName) throws SQLException 
		{
			ConfigQuery.GetDataObjects("Select " + OutputColoumn + " from " + TableName + " WHERE ProjectType = '" + Project + "'AND APiType = '" + Api + "'");
			return "SELECT * FROM " + ConfigQuery.ReadData(OutputColoumn);
		}
		
		protected String QueryValue(String OutputColoumn, String TableName) throws SQLException 
		{
			ConfigQuery.GetDataObjects("Select " + OutputColoumn + " from " + TableName + " WHERE ProjectType = '" + Project + "'AND APiType = '" + Api + "'");
			return ConfigQuery.ReadData(OutputColoumn);
		}
		
		protected String QuerySingleValue(String OutputColoumn, String TableName) throws SQLException 
		{
			ConfigQuery.GetDataObjects("Select " + OutputColoumn + " from " + TableName + " WHERE ProjectType = '" + Project + "'");
			return ConfigQuery.ReadData(OutputColoumn);
		}
			
		public PropertiesHandle(String path)
		{
			this.path = path;
			FileInputStream configuration = null;
			try 
			{
				
				configuration = new FileInputStream(path);
			} catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("file not found");
				e.printStackTrace();
			}
			try 
			{
				this.load(configuration);
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void store(String newpath)
		{
			Writer writer = null;
			try {
				 writer = new FileWriter(newpath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.store(writer, "File saved");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
		public void store()
		{
			Writer writer = null;
			try 
			{
				 writer = new FileWriter(this.path);
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try 
			{
				this.store(writer, "File saved");
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
}
