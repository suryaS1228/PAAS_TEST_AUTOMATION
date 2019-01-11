package com.solartis.test.apiPackage;

import java.util.LinkedHashMap;
import java.util.List;

import com.solartis.test.Configuration.PropertiesHandle;
import com.solartis.test.exception.APIException;

public interface API2 
{
	public String tokenGenerator(PropertiesHandle config) throws APIException;
	public void LoadSampleRequest(LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException;
	public void PumpDataToRequest(LinkedHashMap<String, String> commonmap,LinkedHashMap<Integer,LinkedHashMap<String, String>> InputData) throws APIException;
	public void AddHeaders(String Token) throws APIException;
	public void SendAndReceiveData() throws APIException;
	public List<String> SendResponseDataToFile(LinkedHashMap<String, String> output) throws APIException;
	public String RequestToString(String Token) throws APIException;
	public String ResponseToString() throws APIException;
	public LinkedHashMap<Integer,LinkedHashMap<String, String>> CompareFunction(LinkedHashMap<Integer,LinkedHashMap<String, String>> inputrow, LinkedHashMap<String, String> output) throws APIException;
}
