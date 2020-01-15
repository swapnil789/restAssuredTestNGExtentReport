package com.restassuredtestng.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * @author swapnilk10
 *
 */
public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath = "./config/config.properties";
	private static ConfigFileReader configfilereader = new ConfigFileReader();

	private ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.error("Configuration.properties not found at " + propertyFilePath);
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			
		}
	}

	public static ConfigFileReader getInstance() {
		return configfilereader;
	}

	public String getfilePath() {
		String filepath = properties.getProperty("filepath");
		if (filepath != null)
			return filepath;
		else {
			Log.error("File path does not exists");
			throw new RuntimeException("file path does not exists");
		}
		
	}
	
	public String getSheetName() {
		String sheetname = properties.getProperty("sheetname");
		if (sheetname != null)
			return sheetname;
		else {
			Log.error("Worksheet path does not exists");
			throw new RuntimeException("Worksheet path does not exists");
		}
	}

}
