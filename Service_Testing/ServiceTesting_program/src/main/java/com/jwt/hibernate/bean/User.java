package com.jwt.hibernate.bean;

public class User
{
	private String Project;
	private String Api;
	private String Env;
	private String OutputChioce;
	private String UserName;
	private String JDBC_DRIVER;
	private String DB_URL;
	private String USER;
	private String password;
	private String Priority;
	
	public String getProject() {
		return Project;
	}

	public void setProject(String Project) {
		this.Project = Project;
	}

	public String getApi() {
		return Api;
	}

	public void setApi(String Api) {
		this.Api = Api;
	}

	public String getEnv() {
		return Env;
	}

	public void setEnv(String Env) {
		this.Env = Env;
	}

	public String getOutputChioce() {
		return OutputChioce;
	}

	public void setOutputChioce(String OutputChioce) {
		this.OutputChioce = OutputChioce;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}

	public void setJDBC_DRIVER(String JDBC_DRIVER) {
		this.JDBC_DRIVER = JDBC_DRIVER;
	}

	public String getDB_URL() {
		return DB_URL;
	}

	public void setDB_URL(String DB_URL) {
		this.DB_URL = DB_URL;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String USER) {
		this.USER = USER;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getJPriority() {
		return Priority;
	}

	public void setPriority(String Priority) {
		this.Priority = Priority;
	}

}
