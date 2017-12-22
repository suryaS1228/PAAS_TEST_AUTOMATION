package com.solartis.test.servicetesting.ServiceTestingProgram;

import org.testng.annotations.Test;

import com.solartis.test.exception.DatabaseException;

public class MainClass2 
{
static MainClass main = new MainClass();
	
	@Test(dataProvider="PaaSTest1")
	public static void api1(Integer RowIterator, Object inputtablerowobj, Object outputtablerowobj) throws InterruptedException, DatabaseException
	{
		MainClass.apiTest(RowIterator, inputtablerowobj, outputtablerowobj);
		
	}
	
	@Test(dataProvider="PaaSTest2",dependsOnMethods={"api1"})
	public static void api2(Integer RowIterator, Object inputtablerowobj, Object outputtablerowobj) throws InterruptedException, DatabaseException
	{
		MainClass.apiTest(RowIterator, inputtablerowobj, outputtablerowobj);
		
	}
	
	@Test(dataProvider="PaaSTest3",dependsOnMethods={"api2"})
	public static void api3(Integer RowIterator, Object inputtablerowobj, Object outputtablerowobj) throws InterruptedException, DatabaseException
	{
		MainClass.apiTest(RowIterator, inputtablerowobj, outputtablerowobj);
		
	}
}
