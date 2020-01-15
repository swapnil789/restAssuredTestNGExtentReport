package com.restassuredtestng.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public FileInputStream fStream;
	public Workbook workbook;
	public Sheet sheet;
	private final String excelFilePath = ConfigFileReader.getInstance().getfilePath();
	private final String sheetName = ConfigFileReader.getInstance().getSheetName();

	public ExcelReader() {

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

	public Sheet getSheet() {
		Sheet sheetNumber = workbook.getSheet(sheetName);
		return sheetNumber;
	}

	public String getVehicleModel(int rownum) {
		String vehiclemodel = workbook.getSheet(sheetName).getRow(rownum).getCell(0).toString();
		return vehiclemodel;
	}

	public String getVehicleModelYear(int rownum) {
		String vehiclemodelYear = workbook.getSheet(sheetName).getRow(rownum).getCell(1).toString();
		return vehiclemodelYear;
	}

	public String getVehiclePlatform(int rownum) {
		String vehicleplatform = workbook.getSheet(sheetName).getRow(rownum).getCell(2).toString();
		return vehicleplatform;
	}

	public String getVehicleVariant(int rownum) {
		String vehiclevariant = workbook.getSheet(sheetName).getRow(rownum).getCell(3).toString();
		return vehiclevariant;
	}

	public String getBaseVariant(int rownum) {
		String baseVariant = workbook.getSheet(sheetName).getRow(rownum).getCell(4).toString();
		return baseVariant;
	}

	public String getGdxShortName(int rownum) {
		String gdxShortName = workbook.getSheet(sheetName).getRow(rownum).getCell(5).toString();
		return gdxShortName;
	}

	public String getProtocol(int rownum) {
		String leadProtocol = workbook.getSheet(sheetName).getRow(rownum).getCell(6).toString();
		return leadProtocol;
	}

	public String getLeadVehicleModel(int rownum) {
		String leadVehiclemodel = workbook.getSheet(sheetName).getRow(rownum).getCell(7).toString();
		return leadVehiclemodel;
	}

	public String getLeadVehicleModelYear(int rownum) {
		String leadVehicleModelYear = workbook.getSheet(sheetName).getRow(rownum).getCell(8).toString();
		return leadVehicleModelYear;
	}

	public String getLeadVehiclePlatform(int rownum) {
		String leadVehiclePlatform = workbook.getSheet(sheetName).getRow(rownum).getCell(9).toString();
		return leadVehiclePlatform;
	}

	public String getLeadVehicleVariant(int rownum) {
		String leadVehicleVariant = workbook.getSheet(sheetName).getRow(rownum).getCell(10).toString();
		return leadVehicleVariant;
	}

	public String getEcuFamilyName(int rownum) {
		String ecuFamilyName = workbook.getSheet(sheetName).getRow(rownum).getCell(11).toString();
		return ecuFamilyName;
	}

	public String getVehicleDomain(int rownum) {
		String vehicleDomain = workbook.getSheet(sheetName).getRow(rownum).getCell(12).toString();
		return vehicleDomain;
	}

	/*
	 * public String getAuthor(int rownum) { String author =
	 * workbook.getSheet(sheetName).getRow(rownum).getCell(13).toString(); return
	 * author; }
	 * 
	 * public String getOwner(int rownum) { String owner =
	 * workbook.getSheet(sheetName).getRow(rownum).getCell(14).toString(); return
	 * owner; }
	 */
	public String getVehicleModelId(int rownum) {
		String vehicleModelId = workbook.getSheet(sheetName).getRow(rownum).getCell(13).toString();
		return vehicleModelId;
	}

	public String getEcuFamilyModellId(int rownum) {
		String ecuFamilyModelId = workbook.getSheet(sheetName).getRow(rownum).getCell(14).toString();
		return ecuFamilyModelId;
	}

	public String getBaseVariantModellId(int rownum) {
		String baseVariantModelId = workbook.getSheet(sheetName).getRow(rownum).getCell(15).toString();
		return baseVariantModelId;
	}

}
