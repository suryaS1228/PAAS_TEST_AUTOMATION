package com.solartis.test.listener;

import java.util.LinkedList;

import com.solartis.test.apiPackage.API;
import com.solartis.test.exception.APIException;
import com.solartis.test.util.common.DatabaseOperation;

public class FireEventAPI implements API
{
	protected API api= null;
	
	
	public FireEventAPI(API api)
	{
		this.api=api;
		
	}
	
	protected LinkedList<Listener> listeners1 = new LinkedList<Listener>();

	public void addListener(Listener listener){
	    listeners1.add((Listener) listener);
	}

	public boolean removeListener(Listener listener){
	    return listeners1.remove(listener);
	}
	
	@Override
	public void LoadSampleRequest(DatabaseOperation InputData) throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforeLoadSampleRequest();
		api.LoadSampleRequest(InputData);
		for (Listener listen : listeners1)
			listen.afterLoadSampleRequest();
	}

	@Override
	public void PumpDataToRequest() throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforePumpDataToRequest();
		api.PumpDataToRequest();
		for (Listener listen : listeners1)
			listen.afterPumpDataToRequest();
	}

	@Override
	public void AddHeaders() throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforeAddHeaders();
		api.AddHeaders();
		for (Listener listen : listeners1)
			listen.afterAddHeaders();
	}

	@Override
	public void SendAndReceiveData() throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforeSendAndReceiveData();
		api.SendAndReceiveData();
		for (Listener listen : listeners1)
			listen.afterSendAndReceiveData();
	}

	@Override
	public DatabaseOperation SendResponseDataToFile(DatabaseOperation output) throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforeSendResponseDataToFile();
		output =  api.SendResponseDataToFile(output);
		for (Listener listen : listeners1)
			listen.afterSendResponseDataToFile();
		return output;
	}

	@Override
	public DatabaseOperation CompareFunction(DatabaseOperation output) throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforeCompareFunction();
		output= api.CompareFunction(output);
		for (Listener listen : listeners1)
			listen.afterCompareFunction();
		return output;
	}

	@Override
	public String RequestToString() throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforeRequestToString();
		String requesttoString= api.RequestToString();
		for (Listener listen : listeners1)
			listen.afterRequestToString();
		return requesttoString;
	}

	@Override
	public String ResponseToString() throws APIException 
	{
		// TODO Auto-generated method stub
		for (Listener listen : listeners1)
			listen.beforeResponseToString();
		String ResponseToString = api.ResponseToString();
		for (Listener listen : listeners1)
			listen.afterResponseToString();
		return ResponseToString;
	}
	
	
}
