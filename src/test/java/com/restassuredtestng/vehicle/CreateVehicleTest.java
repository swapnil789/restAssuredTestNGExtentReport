package com.restassuredtestng.vehicle;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restassuredtestng.resource.model.VehicleResource;
import com.restassuredtestng.utilities.ExcelReader;
import com.restassuredtestng.utilities.ExcelWriter;
import com.restassuredtestng.utilities.Log;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author swapnilk10
 *
 */

public class CreateVehicleTest {

	@Test()
	public void createVehicleInDDAT() {

		RestAssured.baseURI = "http://localhost:8443";
		RequestSpecification getRequestSpec = RestAssured.given().contentType(ContentType.JSON);

		// Code for reading excel file's columns and its data
		ExcelReader excelReader = new ExcelReader();

		Response getVehicle = getRequestSpec.get("/vehicles");
		String vehicles = getVehicle.getBody().asString();
		// Store the list of all existing vehicles
		List<Map<String, String>> responseList = getVehicle.jsonPath().getList("data");

		int rowCount = excelReader.getSheet().getLastRowNum();
		for (int row = 1; row <= rowCount; row++) {
			try {
				RequestSpecification postRequestSpec = RestAssured.given().contentType(ContentType.JSON);

				String vehicleModel = excelReader.getVehicleModel(row);
				String vehicleModelYear = excelReader.getVehicleModelYear(row);
				String vehicleVariant = excelReader.getVehicleVariant(row);
				String vehiclePlatform = excelReader.getVehiclePlatform(row);

				String excelVehicleName = vehicleModel + vehicleModelYear + vehicleVariant;

				ExcelWriter excelWriter = new ExcelWriter();
				if (vehicles.contains(excelVehicleName)) {

					for (Map<String, String> temp : responseList) {

						String responseVehicleName = temp.get("shortName");
						if (responseVehicleName.contains(excelVehicleName)) {
							System.out.println(responseVehicleName + " Vehicle already exists");
							Log.warn(responseVehicleName + " Vehicle already exists");
							excelWriter.writeDataInSheet(13, row, temp.get("modelId"));
						}
					}
				} else {
					Log.startTestCase("Create Vehicle : " + vehicleModel + vehicleModelYear + vehicleVariant);
					postRequestSpec.body(VehicleResource
							.getVehicleObject(vehicleModel, vehicleModelYear, vehicleVariant, vehiclePlatform)
							.toJSONString());
					Response response = postRequestSpec.post("/vehicles");
					String vehicleModelId = response.path("newEntity.modelId");

					// Write Vehicle's modelId extracted from response in excel sheet
					if (vehicleModelId != null) {
						excelWriter.writeDataInSheet(13, row, vehicleModelId);
					}

					int statusCode = response.getStatusCode();
					SoftAssert softAssert = new SoftAssert();
					if (statusCode == 200) {
						softAssert.assertEquals(statusCode, 200);
						System.out.println("*********************************");
						System.out.println("Vehicle : " + vehicleModel + vehicleModelYear + vehicleVariant
								+ " created successfully");
						System.out.println("*********************************");
						Log.endTestCase("Vehicle : " + vehicleModel + vehicleModelYear + vehicleVariant
								+ " created successfully");

					} else {
						System.out.println("*********************************");
						System.out.println(
								"Failed to create " + vehicleModel + vehicleModelYear + vehicleVariant + " vehicle!!");
						System.out.println("*********************************");
						Log.info("Failed to create : " + vehicleModel + vehicleModelYear + vehicleVariant);
						softAssert.fail();
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.getClass().getSimpleName() + ":" + e.getMessage());
			}
		}

	}
}
