package com.solartis.test.util.common;

import com.solartis.test.exception.POIException;

public interface ExcelOperationsPOIInterface {

	public void openWorkbook() throws POIException;
	public void getsheets(String sheet_name);
	public void getcell(int row_number,int column_number);
	public String read_data() throws POIException;
	public String read_data(int rowNumber, int columnNumber) throws POIException;
	public void write_data(int rownum,int columnnum,Object strData);
	public void refresh();
	public void save() throws POIException;
	public void saveAs(String Targetexpectedpath) throws POIException;
	public void Copy(String Sampleexcelpath, String Targetexpectedpath) throws POIException;
}
