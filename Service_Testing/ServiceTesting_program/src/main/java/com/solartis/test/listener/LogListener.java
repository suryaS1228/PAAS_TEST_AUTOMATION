package com.solartis.test.listener;

import org.apache.log4j.Logger;


public class LogListener implements Listener
{
	static Logger logError = Logger.getLogger("ERRORlog");
	static Logger logInfo = Logger.getLogger("INFOlog");
	
	
	@Override
	public void beforeLoadSampleRequest()
	{
		// TODO Auto-generated method stub
		//this.InputData=InputData;
		//logInfo.info("TestData" + InputData.ReadData("S.No") + "flag_for_execution = Y" );		    
		//logInfo.info("Loading Sample Request for Testdata--" + InputData.ReadData("S.No"));
	}

	@Override
	public void afterLoadSampleRequest() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePumpDataToRequest()
	{
		// TODO Auto-generated method stub
		//logInfo.info("Pumping Testdata--" + InputData.ReadData("S.No") + "To Sample Request");
	}

	@Override
	public void afterPumpDataToRequest() 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeRequestToString()
	{
		// TODO Auto-generated method stub
		//logInfo.info("REQUEST For Testdata--" + InputData.ReadData("S.No"));
	}

	@Override
	public void afterRequestToString() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAddHeaders()
	{
		// TODO Auto-generated method stub
		//logInfo.info("Adding Header For Testdata--" + InputData.ReadData("S.No"));
	}

	@Override
	public void afterAddHeaders() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSendAndReceiveData()
	{
		// TODO Auto-generated method stub
		//logInfo.info("Respone For Testdata--" + InputData.ReadData("S.No") + "is Received");
	}

	@Override
	public void afterSendAndReceiveData() 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void beforeResponseToString()
	{
		// TODO Auto-generated method stub
		//logInfo.info("REQUEST For Testdata--" + InputData.ReadData("S.No"));
	}

	@Override
	public void afterResponseToString() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSendResponseDataToFile()
	{
		// TODO Auto-generated method stub
		//logInfo.info("Storing Response--" + InputData.ReadData("S.No") + "Data into DB");
	}

	@Override
	public void afterSendResponseDataToFile() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeCompareFunction() 
	{
		// TODO Auto-generated method stub
		//logInfo.info("Comparison is selected");
		 //logInfo.info("Starts Copmaring Expected and atual");
	}

	@Override
	public void afterCompareFunction() 
	{
		// TODO Auto-generated method stub
		 //logInfo.info("Updating DB For Comparison");
	}

	@Override
	public void onError(Throwable e) {
		// TODO Auto-generated method stub
		
	}

	

	

}
