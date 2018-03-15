<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REST API TESTING</title>
</head>
<body>
	<h1>REST API TESTING</h1>
	<form action="register" method="post">
		<table cellpadding="3pt">
			<tr>
				<td>Project :</td>
				<td><select id="Project" name="Project">
  <option value="STARR-DTC">STARR-DTC</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select></td>
			</tr>
			<tr>
				<td>Api :</td>
				<td><select id="Api" name="Api">
  <option value="PreviewPDF">PreviewPDF</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select></td>
			</tr>

			<tr>
				<td>Env :</td>
				<td><select id="Env" name="Env">
  <option value="QA">QA</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select></td>
			</tr>
			<tr>
				<td>OutputChioce :</td>
				<td><select id="OutputChioce" name="OutputChioce">
  <option value="Output_Saved_in_DB">Output_Saved_in_DB</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select></td>
			</tr>
			<tr> 
				<td>UserName :</td>
				<td><select id="UserName" name="UserName">
  <option value="ADMIN">ADMIN</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select></td>
			</tr>
			
			<tr>
				<td>JDBC_DRIVER :</td>
				<td><input type="text" name="JDBC_DRIVER" value="com.mysql.jdbc.Driver" size="30" /></td>
			</tr>
			<tr>
				<td>DB_URL :</td>
				<td><input type="text" name="DB_URL" value="jdbc:mysql://192.168.84.225:3700/Starr_Config_Development" size="30" /></td>
			</tr>
			<tr>
				<td>USER :</td>
				<td><input type="text" name="USER" value="root" size="30" /></td>
			</tr>
			<tr>
				<td>password :</td>
				<td><input type="text" name="password" value="redhat"  size="30" /></td>
			</tr>
			<tr>
				<td>Priority :</td>
				<td><select id="Priority" name="Priority">
  <option value="All">All</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select></td>
			</tr>
			
		</table>
		<p />
		<input type="submit" value="Run" />
	</form>
</body>
</html>