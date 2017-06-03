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
	protected String Project;
	protected String Api;
	protected String Env;
	protected String AutualFlag;
	protected String ComparisonFlag;
	
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
		
		protected void WriteProperty() throws ClassNotFoundException, SQLException
		{
			DatabaseOperation.ConnectionSetup();
		
			this.put("output_in_same_table", this.RdmsValue("OutputInInputTable"));
			
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
			else if(ComparisonFlag.equalsIgnoreCase("comparisionnotneed"))
			{
				this.put("status", "N");  
			}
			
			this.put("sample_request", "C:/RestFullAPIDeliverable/" + Project + "/" + Api + "/SampleRequest/");
			this.put("request_location", "C:/RestFullAPIDeliverable/" + Project + "/" + Api + "/Request/");
			this.put("response_location", "C:/RestFullAPIDeliverable/" + Project + "/" + Api + "/Response/");
			
			this.put("test_url", this.RdmsValue("URL"));
			this.put("type", this.RdmsValue("ServiceType"));
			this.put("content_type", "application/"+this.RdmsValue("ServiceType"));
			this.put("token", this.RdmsValue("Token"));

			this.put("input_query",  this.RdmsQuery("InputTable"));
			this.put("output_query", this.RdmsQuery("OutputTable"));
			
			this.put("InputColQuery",this.RdmsQuery("InputConditonTable"));
			this.put("OutputColQuery",this.RdmsQuery("OutputConditionTable"));
			
			this.put("InputJsonPath", "InputJsonPath");
			this.put("OutputJsonPath", "OutputJsonPath");
			
			this.put("InputColumn", "InputColumn");
		    this.put("OutputColumn", "OutputColumn");
			
			this.put("InputCondColumn", "InputColumnCondtn");
		    this.put("OutputCondColumn", "OutputColumnCondtn");
		    
		    this.put("jdbc_driver", "com.mysql.jdbc.Driver");
		    this.put("db_url", "jdbc:mysql://192.168.35.2:3391/" + this.RdmsValue("DBName"));
		    this.put("db_username", "root");
		    this.put("db_password", "password");
		    
		    DatabaseOperation.CloseConn();
		    
		}
		
		protected String RdmsQuery(String OutputColoumn) throws SQLException
		{
			ConfigQuery.GetDataObjects("SELECT CredentialTable_CONFIG.Verision,Project_CONFIG.DBName,Project_CONFIG.ServiceType,Environment_CONFIG.URL,Project_CONFIG.Token,API_CONFIG.OutputInInputTable,CredentialTable_CONFIG.InputConditonTable,CredentialTable_CONFIG.InputTable,CredentialTable_CONFIG.OutputConditionTable,CredentialTable_CONFIG.OutputTable FROM Project_CONFIG INNER JOIN API_CONFIG ON Project_CONFIG.ProjectID = API_CONFIG.ProjectID INNER JOIN Environment_CONFIG ON API_CONFIG.APIID = Environment_CONFIG.APIID INNER JOIN CredentialTable_CONFIG ON CredentialTable_CONFIG.Env_ID = Environment_CONFIG.Env_ID WHERE Project_CONFIG.ProjectName ='" + Project + "' AND API_CONFIG.APIName = '" + Api + "' AND Environment_CONFIG.Env_Name = '" + Env + "' ORDER BY CredentialTable_CONFIG.Verision DESC LIMIT 1");
			return "SELECT * FROM " + ConfigQuery.ReadData(OutputColoumn);
		}
		
		protected String RdmsValue(String OutputColoumn) throws SQLException
		{
			ConfigQuery.GetDataObjects("SELECT CredentialTable_CONFIG.Verision,Project_CONFIG.DBName,Project_CONFIG.ServiceType,Environment_CONFIG.URL,Project_CONFIG.Token,API_CONFIG.OutputInInputTable,CredentialTable_CONFIG.InputConditonTable,CredentialTable_CONFIG.InputTable,CredentialTable_CONFIG.OutputConditionTable,CredentialTable_CONFIG.OutputTable FROM Project_CONFIG INNER JOIN API_CONFIG ON Project_CONFIG.ProjectID = API_CONFIG.ProjectID INNER JOIN Environment_CONFIG ON API_CONFIG.APIID = Environment_CONFIG.APIID INNER JOIN CredentialTable_CONFIG ON CredentialTable_CONFIG.Env_ID = Environment_CONFIG.Env_ID WHERE Project_CONFIG.ProjectName ='" + Project + "' AND API_CONFIG.APIName = '" + Api + "' AND Environment_CONFIG.Env_Name = '" + Env + "' ORDER BY CredentialTable_CONFIG.Verision DESC LIMIT 1");
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
