package com.solartis.test.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.solartis.test.servicetesting.ServiceTestingProgram.MainClass;


public class LogListener implements Listener
{
	private static Logger Log;
	static
	{
		String log4jConfigFile = "src/main/java/log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		Log = Logger.getLogger(MainClass.class.getName());
	}
	
	@Override
	public void beforeLoadSampleRequest()
	{
		//System.out.println("beforeLoadSampleRequest");
		Log.debug("beforeLoadSampleRequest");
	}

	@Override
	public void afterLoadSampleRequest() 
	{
		//System.out.println("afterLoadSampleRequest");
		Log.debug("afterLoadSampleRequest");
	}

	@Override
	public void beforePumpDataToRequest()
	{
		//System.out.println("beforePumpDataToRequest");
		Log.debug("beforePumpDataToRequest");
	}

	@Override
	public void afterPumpDataToRequest() 
	{
		//System.out.println("afterPumpDataToRequest");
		Log.debug("afterPumpDataToRequest");
	}
	@Override
	public void beforeRequestToString()
	{
		//System.out.println("beforeRequestToString");
		Log.debug("beforeRequestToString");
	}

	@Override
	public void afterRequestToString() 
	{
		//System.out.println("afterRequestToString");
		Log.debug("afterRequestToString");
	}

	@Override
	public void beforeAddHeaders()
	{
		//System.out.println("beforeAddHeaders");
		Log.debug("beforeAddHeaders");
	}

	@Override
	public void afterAddHeaders() 
	{
		//System.out.println("afterAddHeaders");
		Log.debug("afterAddHeaders");
	}

	@Override
	public void beforeSendAndReceiveData()
	{
		//System.out.println("beforeSendAndReceiveData");
		Log.debug("beforeSendAndReceiveData");
	}

	@Override
	public void afterSendAndReceiveData() 
	{
		//System.out.println("afterSendAndReceiveData");
		Log.debug("afterSendAndReceiveData");
	}
	
	@Override
	public void beforeResponseToString()
	{
		//System.out.println("beforeResponseToString");
		Log.debug("beforeResponseToString");
	}

	@Override
	public void afterResponseToString() 
	{
		//System.out.println("afterResponseToString");
		Log.debug("afterResponseToString");
	}

	@Override
	public void beforeSendResponseDataToFile()
	{
		//System.out.println("beforeSendResponseDataToFile");
		Log.debug("beforeSendResponseDataToFile");
	}

	@Override
	public void afterSendResponseDataToFile() 
	{
		//System.out.println("afterSendResponseDataToFile");
		Log.debug("afterSendResponseDataToFile");
	}

	@Override
	public void beforeCompareFunction() 
	{
		//System.out.println("beforeCompareFunction");
		Log.debug("beforeCompareFunction");
	}

	@Override
	public void afterCompareFunction() 
	{
		//System.out.println("afterCompareFunction");
		Log.debug("afterCompareFunction");
	}

	@Override
	public void onError(Throwable e)
	{
		//System.out.println("Printed by Listener");
		e.printStackTrace();
		System.out.println(e.getMessage()+"--"+e.getCause().getMessage()+"--"+e.getCause().getCause().getMessage());
		Log.error(e.getMessage()+"--"+e.getCause().getMessage()+"--"+e.getCause().getCause().getMessage());
		
	}

	

	

}
