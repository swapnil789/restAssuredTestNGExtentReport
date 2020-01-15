package com.restassuredtestng.ecufamily;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restassuredtestng.resource.model.EcuFamilyResource;
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
public class CreateEcuFamilyTest {

	@Test()
	public void createEcuFamilyInDDAT() {

		RestAssured.baseURI = "http://localhost:8443";
		RequestSpecification getRequestSpec = RestAssured.given().contentType(ContentType.JSON);

		// Code for reading excel file's columns and its data
		ExcelReader excelReader = new ExcelReader();

		Response getVehicle = getRequestSpec.get("/ecufamilies");
		String ecuFamilies = getVehicle.getBody().asString();
		// Store the list of all existing ECU families
		List<Map<String, String>> responseList = getVehicle.jsonPath().getList("data");

		int rowCount = excelReader.getSheet().getLastRowNum();
		for (int row = 1; row <= rowCount; row++) {
			try {
				RequestSpecification postRequestSpec = RestAssured.given().contentType(ContentType.JSON);

				String ecuFamilyName = excelReader.getEcuFamilyName(row);
				String vehicleDomain = excelReader.getVehicleDomain(row);

				ExcelWriter excelWriter = new ExcelWriter();
				if (ecuFamilies.contains(ecuFamilyName)) {
					for (Map<String, String> temp : responseList) {
						String responseFamilyName = temp.get("longName");
						if (responseFamilyName.equals(ecuFamilyName)) {
							System.out.println(responseFamilyName + " ECU Family already exists");
							Log.warn(responseFamilyName + " ECU Family already exists");
							String modelId = temp.get("modelId");
							excelWriter.writeDataInSheet(14, row, modelId);
						}
					}
				} else {

					Log.startTestCase("Create ECU Family : " + ecuFamilyName);

					postRequestSpec
							.body(EcuFamilyResource.getEcuFamilyObject(vehicleDomain, ecuFamilyName).toJSONString());

					Response response = postRequestSpec.post("/ecufamilies");

					String familyModelId = response.path("newEntity.modelId");

					// Write ECU Family's modelId extracted from response in excel sheet
					if (familyModelId != null) {
						excelWriter.writeDataInSheet(14, row, familyModelId);
					}

					int statusCode = response.getStatusCode();
					SoftAssert softAssert = new SoftAssert();
					if (statusCode == 200) {
						softAssert.assertEquals(statusCode, 200);

						System.out.println("*********************************");
						System.out.println("ECU Family " + ecuFamilyName + " created successfully..");
						System.out.println("*********************************");
						Log.endTestCase("ECU Family " + ecuFamilyName + " created successfully..");

					} else {
						System.out.println("*********************************");
						System.out.println("Failed to create " + ecuFamilyName + " ECU Family!!");
						System.out.println("*********************************");
						Log.info("Failed to create " + ecuFamilyName + " ECU Family!!");
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
