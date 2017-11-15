package com.solartis.test.util.api;

public class Attribute 
{
	private String Atrib;
	private String value;
	public Attribute(String Attrib, String value) 
	{
		this.Atrib = Attrib;
		this.value = value;
	}
	public String getAtrib() 
	{
		return Atrib;
	}
	public void setAtrib(String atrib) 
	{
		Atrib = atrib;
	}
	public String getValue() 
	{
		return value;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}	
}
