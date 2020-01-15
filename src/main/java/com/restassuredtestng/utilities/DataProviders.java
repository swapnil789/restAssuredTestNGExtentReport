package com.restassuredtestng.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author swapnilk10
 *
 */
public class DataProviders {

	private static DataProviders dataprovider = new DataProviders();

	private DataProviders() {
		// TODO Auto-generated constructor stub
	}

	public static DataProviders getInstance() {
		return dataprovider;
	}

	public Object[][] getDataProvider(String workbook, String sheetname) throws IOException {
		File file = new File(workbook);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook Workbook1 = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = Workbook1.getSheet(sheetname);
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		Object[][] Object1 = new Object[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
				Object1[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
			
		Workbook1.close();
		return Object1;
	}

}
