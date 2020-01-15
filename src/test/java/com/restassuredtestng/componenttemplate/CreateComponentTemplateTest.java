package com.restassuredtestng.componenttemplate;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restassuredtestng.resource.model.ComponentTemplateResource;
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
public class CreateComponentTemplateTest {

	@Test
	public void createComponentTemplateTest() {

		RestAssured.baseURI = "http://localhost:8443";
		RequestSpecification getRequestSpec = RestAssured.given().contentType(ContentType.JSON);

		Response getBVResponse = getRequestSpec.get("/basevariants");
		String baseVariants = getBVResponse.getBody().asString();
		List<Map<String, String>> bvresponseList = getBVResponse.jsonPath().getList("data");

		// Code for reading excel file's columns and its data
		ExcelReader excelReader = new ExcelReader();

		int rowcount = excelReader.getSheet().getLastRowNum();

		for (int row = 1; row <= rowcount; row++) {
			try {
				RequestSpecification postRequestSpec = RestAssured.given().contentType(ContentType.JSON);

				String gdxShortName = excelReader.getGdxShortName(row);
				String ctname = excelReader.getBaseVariant(row);
				String ecuFamilyModelId = excelReader.getEcuFamilyModellId(row);
				String protocol = excelReader.getProtocol(row);
				String category = excelReader.getVehicleDomain(row);
				String leadvehicleModel = excelReader.getLeadVehicleModel(row);
				String leadvehicleModelYear = excelReader.getLeadVehicleModelYear(row);
				String leadvehicleVariant = excelReader.getLeadVehicleVariant(row);
				String leadvehiclePlatform = excelReader.getLeadVehiclePlatform(row);

				ExcelWriter excelWriter = new ExcelWriter();

				if (baseVariants.contains(ctname)) {
					for (Map<String, String> temp : bvresponseList) {
						String responseBVName = temp.get("longName");
						if (responseBVName.equals(ctname)) {
							System.out.println(responseBVName + " Component Template already exists");
							Log.warn(responseBVName + " Component Template already exists");
							excelWriter.writeDataInSheet(15, row, temp.get("modelId"));
						}
					}
				} else {

					Log.startTestCase("Create BV : " + ctname);

					// Code for getting primary vehicle modelId and executing POST request
					Response getVehicleResponse = postRequestSpec.get("/vehicles");
					List<Map<String, String>> vehiclesResponseList = getVehicleResponse.jsonPath().getList("data");
					for (Map<String, String> temp : vehiclesResponseList) {
						String responseVehicleName = temp.get("vehicleModel") + temp.get("modelYear")
								+ temp.get("variant") + temp.get("platform");
						String excelVehicleName = leadvehicleModel + leadvehicleModelYear + leadvehicleVariant
								+ leadvehiclePlatform;
						if (responseVehicleName.equals(excelVehicleName)) {
							String leadVehicleModelId = temp.get("modelId");
							postRequestSpec
									.body(ComponentTemplateResource.getCTObject(gdxShortName, ctname, ecuFamilyModelId,
											protocol, category.toUpperCase(), leadVehicleModelId).toJSONString());
						}
					}

					Response response = postRequestSpec.post("/basevariants");

					String baseVariantModelId = response.path("newEntity.modelId");
					// Write BV's modelId extracted from response in excel sheet

					if (baseVariantModelId != null) {
						excelWriter.writeDataInSheet(15, row, baseVariantModelId);
					}
					int statusCode = response.getStatusCode();
					SoftAssert softAssert = new SoftAssert();
					if (statusCode == 200) {
						softAssert.assertEquals(statusCode, 200);

						System.out.println("*********************************");
						System.out.println("Component Template " + ctname + " created successfully..");
						System.out.println("*********************************");
						Log.endTestCase("Component Template " + ctname + " created successfully..");

					} else {
						System.out.println("*********************************");
						System.out.println("Failed to create " + ctname + " Component Template!!");
						System.out.println("*********************************");
						Log.info("Failed to create " + ctname + " Component Template!!");
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
