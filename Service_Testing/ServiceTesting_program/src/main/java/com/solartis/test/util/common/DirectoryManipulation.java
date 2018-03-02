package com.solartis.test.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DirectoryManipulation
{
public static String zipFiles(String zipFile, String srcDir)
{
try
{
File srcFile = new File(srcDir);
File[] files = srcFile.listFiles();
FileOutputStream fos = new FileOutputStream(zipFile);

ZipOutputStream zos = new ZipOutputStream(fos);

for (int i = 0; i < files.length; i++) 
{

byte[] buffer = new byte[1024];

FileInputStream fis = new FileInputStream(files[i]);

zos.putNextEntry(new ZipEntry(files[i].getName()));

int length;

while ((length = fis.read(buffer)) > 0)
{
zos.write(buffer, 0, length);
}

zos.closeEntry();

fis.close();

}
zos.close();
}
catch (Exception e)
{
	return e.getMessage();}
    return "Successfully created the zip file"+zipFile;
}


public  void deleteFileFromDirectory(String DirName)
{
	File directory = new File(DirName);

	File[] files = directory.listFiles();

	for (File file : files)

	{
	 
	if (!file.delete())
	{ 
	System.out.println("Failed to delete "+file);
	}
	} 
}


public static void main(String[] args) 
{
	//String result_message=zipFiles("D:/zipped_folder.zip","E:\\RestFullAPIDeliverable\\Devolpement\\admin\\STARR-DTC\\RatingServiceSinglePlan\\Request\\");
	//System.out.println(result_message);
		DirectoryManipulation z=new DirectoryManipulation();
		z.deleteFileFromDirectory("R:\\RestFullAPIDeliverable\\Devolpement\\admin\\STARR-DTC\\RatingServiceSinglePlan\\Results\\Request\\");
	}

}
