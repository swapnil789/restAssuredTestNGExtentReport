package com.restassuredtestng.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWriter {

	public FileInputStream fStream;
	public Workbook workbook;
	public Sheet sheet;
	private final String excelFilePath = ConfigFileReader.getInstance().getfilePath();
	private final String sheetName = ConfigFileReader.getInstance().getSheetName();

	public ExcelWriter() {

		try {
			fStream = new FileInputStream(new File(excelFilePath));
			try {
				workbook = WorkbookFactory.create(fStream);
				sheet = workbook.getSheet(sheetName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void writeDataInSheet(int columnNo, int rowNo, String data) {
		Row row = sheet.getRow(rowNo);
		Cell cell = row.createCell(columnNo);
		cell.setCellValue(data);
		try {
			fStream.close();
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
