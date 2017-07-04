package com.solartis.test.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Properties;

import com.solartis.test.util.common.DatabaseOperation;

public class PropertiesHandle extends Properties

{
	private static final long serialVersionUID = 1L;
	protected String path = null;
	protected String Project;
	protected String Api;
	protected String Env;
	protected String OutputChioce;
	protected String UserName;
	protected String JDBC_DRIVER;
	protected String DB_URL;
	protected String USER;
	protected String password;
	protected String priority;

	static DatabaseOperation ConfigQuery = new DatabaseOperation();
			
	    public PropertiesHandle(String Project,String Api, String Env ,String OutputChioce, String UserName, String JDBC_DRIVER, String DB_URL, String USER, String password, String priority) throws ClassNotFoundException, SQLException
		{
			this.Project = Project;
			this.Api=Api;
			this.Env=Env;
			this.OutputChioce=OutputChioce;
			this.UserName=UserName;
			this.JDBC_DRIVER=JDBC_DRIVER;
			this.DB_URL=DB_URL;
			this.USER=USER;
			this.password=password;
			this.priority=priority;
			
			WriteProperty(UserName);
			
		}
		
		protected void WriteProperty(String UserName) throws ClassNotFoundException, SQLException
		{
			DatabaseOperation.ConnectionSetup(JDBC_DRIVER, DB_URL, USER, password);

			this.OutputInSameTable();
						
            if(OutputChioce.equalsIgnoreCase("Output_Saved_in_DB"))
            {
				 this.ActualAndStatus("Y", "N");    
            }
		    else if(OutputChioce.equalsIgnoreCase("Get_Response_Only"))
		    {
		    	this.ActualAndStatus("N", "N");    
		    }
			if(OutputChioce.equalsIgnoreCase("Compared_Results"))
			{
				this.ActualAndStatus("Y", "Y");    
			}
			
			this.SampleRequest();
			this.RequestLocation();
			this.ResponseLocation();
		   
			this.SampleRatingModelLocation();
			this.ExpectedRatingModelPath();
			this.MacroMappingQuery();
			this.MacroTranslationQuery();		
			
			this.URL();
			this.ContentType();
			this.Token();
			this.EventName();
			this.EventVersion();

			this.InputQuery();
			this.OutputQuery();
			
			this.InputConditionQuery();
			this.OutputConiditionQuery();
			
			this.ClassName();
			
			this.InputJsonPath();
			this.OutputJsonPath();
			
		    this.InputColumn();
		    this.OutputColumn();
		    		    
			this.InputConditionColumn();
			this.OutputConditionColumn();
			
		    this.ExpectedColumn();
		    this.StatusColumn();
		    		    
		    this.DBdetails();
		    
		    DatabaseOperation.CloseConn();
		 
		}
		
		protected String RdmsQuery(String OutputColoumn) throws SQLException
		{
			ConfigQuery.GetDataObjects("SELECT UserFolder_CONFIG.RootFolder,UserFolder_CONFIG.JDCDriver,UserFolder_CONFIG.DB_URL,UserFolder_CONFIG.DB_UserName,UserFolder_CONFIG.DB_Password,UserFolder_CONFIG.UserDBName,Version_CONFIG.Version,Project_CONFIG.ProjectDBName,Project_CONFIG.ServiceType,Environment_CONFIG.URL,Project_CONFIG.Token,VersionDetail_CONFIG.EventName,VersionDetail_CONFIG.EventVersion,API_CONFIG.OutputInInputTable,VersionDetail_CONFIG.ClassName,VersionDetail_CONFIG.InputConditonTable,VersionDetail_CONFIG.InputTable,VersionDetail_CONFIG.OutputConditionTable,VersionDetail_CONFIG.OutputTable,VersionDetail_CONFIG.MacroMappingTable,VersionDetail_CONFIG.MacroTranslationTable FROM Project_CONFIG INNER JOIN UserFolder_CONFIG INNER JOIN API_CONFIG ON Project_CONFIG.ProjectID = API_CONFIG.ProjectID INNER JOIN Environment_CONFIG ON API_CONFIG.APIID = Environment_CONFIG.APIID INNER JOIN Version_CONFIG ON Environment_CONFIG.Env_ID = Version_CONFIG.Env_ID INNER JOIN VersionDetail_CONFIG ON (VersionDetail_CONFIG.Verision = Version_CONFIG.Version and VersionDetail_CONFIG.APIID = API_CONFIG.APIID)  WHERE Project_CONFIG.ProjectName ='" + Project +"' AND API_CONFIG.APIName = '" + Api + "' AND Environment_CONFIG.Env_Name = '" + Env + "' AND UserFolder_CONFIG.User_ID = '" + UserName + "' ORDER BY Version_CONFIG.Version DESC LIMIT 1");
			return "SELECT * FROM " + ConfigQuery.ReadData(OutputColoumn);
		}
		
		protected String RdmsQueryWithCondition(String OutputColoumn, String priority) throws SQLException
		{
			ConfigQuery.GetDataObjects("SELECT UserFolder_CONFIG.RootFolder,UserFolder_CONFIG.JDCDriver,UserFolder_CONFIG.DB_URL,UserFolder_CONFIG.DB_UserName,UserFolder_CONFIG.DB_Password,UserFolder_CONFIG.UserDBName,Version_CONFIG.Version,Project_CONFIG.ProjectDBName,Project_CONFIG.ServiceType,Environment_CONFIG.URL,Project_CONFIG.Token,VersionDetail_CONFIG.EventName,VersionDetail_CONFIG.EventVersion,API_CONFIG.OutputInInputTable,VersionDetail_CONFIG.ClassName,VersionDetail_CONFIG.InputConditonTable,VersionDetail_CONFIG.InputTable,VersionDetail_CONFIG.OutputConditionTable,VersionDetail_CONFIG.OutputTable,VersionDetail_CONFIG.MacroMappingTable,VersionDetail_CONFIG.MacroTranslationTable FROM Project_CONFIG INNER JOIN UserFolder_CONFIG INNER JOIN API_CONFIG ON Project_CONFIG.ProjectID = API_CONFIG.ProjectID INNER JOIN Environment_CONFIG ON API_CONFIG.APIID = Environment_CONFIG.APIID INNER JOIN Version_CONFIG ON Environment_CONFIG.Env_ID = Version_CONFIG.Env_ID INNER JOIN VersionDetail_CONFIG ON (VersionDetail_CONFIG.Verision = Version_CONFIG.Version and VersionDetail_CONFIG.APIID = API_CONFIG.APIID)  WHERE Project_CONFIG.ProjectName ='" + Project +"' AND API_CONFIG.APIName = '" + Api + "' AND Environment_CONFIG.Env_Name = '" + Env + "' AND UserFolder_CONFIG.User_ID = '" + UserName + "' ORDER BY Version_CONFIG.Version DESC LIMIT 1");
			return "SELECT * FROM " + ConfigQuery.ReadData(OutputColoumn) + " WHERE " + ConfigQuery.ReadData(OutputColoumn) + ".Priority = '" + priority + "'";
		}
		
		protected String RdmsValue(String OutputColoumn) throws SQLException
		{
			ConfigQuery.GetDataObjects("SELECT UserFolder_CONFIG.RootFolder,UserFolder_CONFIG.JDCDriver,UserFolder_CONFIG.DB_URL,UserFolder_CONFIG.DB_UserName,UserFolder_CONFIG.DB_Password,UserFolder_CONFIG.UserDBName,Version_CONFIG.Version,Project_CONFIG.ProjectDBName,Project_CONFIG.ServiceType,Environment_CONFIG.URL,Project_CONFIG.Token,VersionDetail_CONFIG.EventName,VersionDetail_CONFIG.EventVersion,API_CONFIG.OutputInInputTable,VersionDetail_CONFIG.ClassName,VersionDetail_CONFIG.InputConditonTable,VersionDetail_CONFIG.InputTable,VersionDetail_CONFIG.OutputConditionTable,VersionDetail_CONFIG.OutputTable,VersionDetail_CONFIG.MacroMappingTable,VersionDetail_CONFIG.MacroTranslationTable FROM Project_CONFIG INNER JOIN UserFolder_CONFIG INNER JOIN API_CONFIG ON Project_CONFIG.ProjectID = API_CONFIG.ProjectID INNER JOIN Environment_CONFIG ON API_CONFIG.APIID = Environment_CONFIG.APIID INNER JOIN Version_CONFIG ON Environment_CONFIG.Env_ID = Version_CONFIG.Env_ID INNER JOIN VersionDetail_CONFIG ON (VersionDetail_CONFIG.Verision = Version_CONFIG.Version and VersionDetail_CONFIG.APIID = API_CONFIG.APIID)  WHERE Project_CONFIG.ProjectName ='" + Project +"' AND API_CONFIG.APIName = '" + Api + "' AND Environment_CONFIG.Env_Name = '" + Env + "' AND UserFolder_CONFIG.User_ID = '" + UserName + "' ORDER BY Version_CONFIG.Version DESC LIMIT 1");
			return ConfigQuery.ReadData(OutputColoumn);
		}

	
		protected void OutputInSameTable() // FUNCTION CHECK OUTPUT IN SAME TABLE ARE NOT
		{
			try 
			{
				this.put("output_in_same_table", this.RdmsValue("OutputInInputTable"));
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in OutputInSameTable Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}	
		
		protected void ActualAndStatus(String Actual, String Status)// FUNCTION FOR ACTUAL AND STATUS OCCURANCE
		{
			try 
			{
				this.put("actual", Actual);
			    this.put("status", Status);
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in ActualAndStatus Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}	
		
		protected void SampleRequest()// FUNCTION FOR SAMPLEREQUEST PATH
		{
			try 
			{
				this.put("sample_request", this.RdmsValue("RootFolder") + "/" + Project + "/" + Api + "/SampleRequest/SampleRequest" + this.RdmsValue("Version") + "/");
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in SampleRequest Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void RequestLocation()// FUNCTION FOR REQUEST TO SAVE PATH
		{
			try 
			{
				this.put("request_location", this.RdmsValue("RootFolder") + "/" + Project + "/" + Api + "/Request/");
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in RequestLocation Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void ResponseLocation()// FUNCTION FOR RESPONSE TO SAVE PATH
		{
			try 
			{
				 this.put("response_location", this.RdmsValue("RootFolder") + "/" + Project + "/" + Api + "/Response/");
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in ResponseLocation Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void SampleRatingModelLocation()// FUNCTION FOR SAMPLE RATING MODEL PATH
		{
			try 
			{
				this.put("Samplepath", this.RdmsValue("RootFolder") + "/" + Project + "/" + Api + "/SampleRatingModel/SampleRating" + this.RdmsValue("Version") + "/");
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in SampleRatingLocation Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void ExpectedRatingModelPath()// FUNCTION FOR EXPECTED RATING MODEL PATH
		{
			try 
			{
				this.put("TargetPath", this.RdmsValue("RootFolder") + "/" + Project + "/" + Api +  "/RatingModelResult/");
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in ExpectedRatingModelPath Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void MacroMappingQuery()// FUNCTION TO GET RESULT SET FROM MACRO MAPPING TABLE 
		{
			try 
			{
				this.put("config_query", this.RdmsQuery("MacroMappingTable"));
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in MacroMappingQuery Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void MacroTranslationQuery()// FUNCTION TO GET RESULT SET FROM MACRO TRNSLATION TABLE 
		{
			try 
			{
				this.put("lookup_query", this.RdmsQuery("MacroTranslationTable"));
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in MacroTranslationQuery Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}

		protected void URL()// FUNCTION TO GET URL
		{
			try 
			{
				this.put("test_url", this.RdmsValue("URL"));
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in URL Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void ContentType()// FUNCTION TO GET CONTENTTYPE
		{
			try 
			{
				this.put("content_type", "application/"+this.RdmsValue("ServiceType"));
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in ContentType Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		

		protected void Token()// FUNCTION TO GET TOKEN
		{
			try 
			{
				this.put("token", this.RdmsValue("Token"));
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in ContentType Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void EventName()// FUNCTION TO GET EVENT-NAME
		{
			try 
			{
				this.put("EventName", this.RdmsValue("EventName"));
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in EventName Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void EventVersion()// FUNCTION TO GET EVENT-VERSION
		{
			try 
			{
				this.put("EventVersion", this.RdmsValue("EventVersion"));
		    } 
			catch (SQLException e) 
			{
			    System.out.println("Error in EventVersion Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		

		protected void InputQuery()// FUNCTION FOR INPUTQUERY
		{
			try 
			{
				if(priority.equalsIgnoreCase("all"))
				{
					this.put("input_query",  this.RdmsQuery("InputTable"));
				}
				else if(priority.equalsIgnoreCase("high") || priority.equalsIgnoreCase("low"))
				{
					this.put("input_query",  this.RdmsQueryWithCondition("InputTable", priority));
				}		
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in InputQuery Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void OutputQuery()// FUNCTION FOR OUTPUTQUERY
		{
			try 
			{
				this.put("output_query", this.RdmsQuery("OutputTable"));
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in OutputQuery Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void InputConditionQuery()// FUNCTION FOR INPUT-CONDITION-QUERY
		{
			try 
			{
				this.put("InputColQuery",this.RdmsQuery("InputConditonTable"));
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in InputConditionQuery Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void OutputConiditionQuery()// FUNCTION FOR OUTPUT-CONDITION-QUERY
		{
			try 
			{
				this.put("OutputColQuery",this.RdmsQuery("OutputConditionTable"));
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in OutputConiditionQuery Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void ClassName() // FUNCTION TOS GET CLASS-NAME
		{
			try 
			{
				this.put("ClassName", this.RdmsValue("ClassName"));
			} 
			catch (SQLException e) 
			{
			    System.out.println("Error in ClassName Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}	
		

		protected void InputJsonPath() // FUNCTION TO GET INPUT-JSON-PATH
		{
			try 
			{
				this.put("InputJsonPath", "InputJsonPath");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in InputJsonPath Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void OutputJsonPath() // FUNCTION TO GET OUTPUT-JSON-PATH
		{
			try 
			{
				this.put("OutputJsonPath", "OutputJsonPath");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in OutputJsonPath Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		

		protected void InputColumn() // FUNCTION TO GET INPUT-COLUMN
		{
			try 
			{
				this.put("InputColumn", "InputColumn");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in InputColumn Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void OutputColumn() // FUNCTION TO GET OUTPUT-COLUMN
		{
			try 
			{
				this.put("OutputColumn", "OutputColumn");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in OutputColumn Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		

	    protected void InputConditionColumn() // FUNCTION TO GET INPUT-CONDITION-COLUMN
		{
			try 
			{
				this.put("InputCondColumn", "InputColumnCondtn");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in InputConditionColumn Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void OutputConditionColumn() // FUNCTION TO GET OUTPUT-CONDITION-COLUMN
		{
			try 
			{
				this.put("OutputCondColumn", "OutputColumnCondtn");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in OutputConditionColumn Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
	    protected void ExpectedColumn() // FUNCTION TO GET EXPECTED-COLUMN
		{
			try 
			{
				this.put("ExpectedColumn", "ExpectedColumn");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in ExpectedColumn Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		
		protected void StatusColumn() // FUNCTION TO GET STATUS-COLUMN
		{
			try 
			{
				  this.put("StatusColumn", "StatusColumn");
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in StatusColumn Fuction-- PropertiesHandle Class");
				e.printStackTrace();
			}
		}
		

	    protected void DBdetails()// FUNCTION FOR DB-DETAILS
		{
			try 
			{
				this.put("jdbc_driver", this.RdmsValue("JDCDriver"));
			    this.put("db_url", this.RdmsValue("DB_URL") + "/" + this.RdmsValue("ProjectDBName") + "_" + this.RdmsValue("UserDBName"));
			    this.put("db_username", this.RdmsValue("DB_UserName"));
			    this.put("db_password", this.RdmsValue("DB_Password"));
			} 
			catch (Exception e) 
			{
			    System.out.println("Error in DBdetails Function -- PropertiesHandle Class");
				e.printStackTrace();
			}
		}	
		
		public PropertiesHandle(String path)
		{
			this.path = path;
			
			FileInputStream configuration = null;
			
			try 
			{
				configuration = new FileInputStream(path);
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("file not found");
				e.printStackTrace();
			}
			try 
			{
				this.load(configuration);
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void store(String newpath)
		{
			Writer writer = null;
			try 
			{
				 writer = new FileWriter(newpath);
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				this.store(writer, "File saved");
			} catch (IOException e) 
			{
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