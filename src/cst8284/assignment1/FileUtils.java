package cst8284.assignment1;
import  cst8284.assignment1.ToDo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.OpenDataException;

import java.io.RandomAccessFile;

// TODO: import required library files here


public class FileUtils {

	private final static int NUMBER_OF_TODOS = 4;
	private static final Object lines = null;
	private static String relPath = "/Users/shiva/Desktop/Assignment01/Assignment01/"; ///CHANGE THE PATH *************************************
	private ToDo[] stringArray1;

	
	public ToDo[] getToDoArray(String fileName) throws IOException, ClassNotFoundException{
	    // TODO: insert required code here, as described in the
 	    // Assignment 1 document
		ToDo toDos[] =
				 new ToDo[NUMBER_OF_TODOS];
	
	    List<Object> results = new ArrayList<Object>();
	    FileInputStream fis = new FileInputStream(relPath+fileName);
	    ObjectInputStream ois = new ObjectInputStream(fis);

	    try {
	        while (true) {
	            results.add(ois.readObject());
	        }
	    } catch (IOException e) {
	    	e.getMessage();
	    	ois.close();
	    }
	       
	       for(int i=0;i<NUMBER_OF_TODOS;i++){
           	toDos[i]=(ToDo) results.get(i);
           }
	       System.out.println("fromutils"+results);
         
           ois.close();
		  return toDos;
	
				
	}
		
	public static String getAbsPath() {
		return relPath;
	}

	public static String getAbsPath(File f) {
		return f.getAbsolutePath();
	}

	public static void setAbsPath(File f) { 
		relPath = (fileExists(f))? f.getAbsolutePath():""; 
	}
	
	public static Boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead());
	}

}
