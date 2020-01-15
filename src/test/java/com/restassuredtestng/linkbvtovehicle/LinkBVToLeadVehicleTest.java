package com.restassuredtestng.linkbvtovehicle;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restassuredtestng.resource.model.LinkBaseVariantToVehicleResource;
import com.restassuredtestng.utilities.ExcelReader;
import com.restassuredtestng.utilities.Log;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LinkBVToLeadVehicleTest {

	@Test
	public void linkBvToLeadVehicle() {
		RestAssured.baseURI = "http://localhost:8443";

		// Code for getting primary vehicle modelId and executing POST request
		RequestSpecification getVehicleRequestSpec = RestAssured.given().contentType(ContentType.JSON);
		Response getVehicleResponse = getVehicleRequestSpec.get("/vehicles");
		List<Map<String, String>> vehiclesResponseList = getVehicleResponse.jsonPath().getList("data");

		ExcelReader excelReader = new ExcelReader();

		int rowcount = excelReader.getSheet().getLastRowNum();

		for (int row = 1; row <= rowcount; row++) {
			RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
			try {

				String baseVariantModelId = excelReader.getBaseVariantModellId(row);
				String leadvehicleModel = excelReader.getLeadVehicleModel(row);
				String leadvehicleModelYear = excelReader.getLeadVehicleModelYear(row);
				String leadvehicleVariant = excelReader.getLeadVehicleVariant(row);
				String leadvehiclePlatform = excelReader.getLeadVehiclePlatform(row);

				for (Map<String, String> temp : vehiclesResponseList) {
					String responseVehicleName = temp.get("vehicleModel") + temp.get("modelYear") + temp.get("variant")
							+ temp.get("platform");
					String excelVehicleName = leadvehicleModel + leadvehicleModelYear + leadvehicleVariant
							+ leadvehiclePlatform;
					if (responseVehicleName.equals(excelVehicleName)) {
						String leadVehicleModelId = temp.get("modelId");

						Log.startTestCase(
								"Link BV : " + baseVariantModelId + " To Lead Vehicle :" + leadVehicleModelId);

						request.body(LinkBaseVariantToVehicleResource
								.getLinkBaseVariantToVehicleResource(leadVehicleModelId, baseVariantModelId)
								.toJSONString());

						Response response = request.pathParams("vehicleId", leadVehicleModelId)
								.queryParam("comment", "BV linked using automation")
								.post("vehicles/createLinks/{vehicleId}");

						int statusCode = response.getStatusCode();
						System.out.println(response.getBody().asString());
						SoftAssert softAssert = new SoftAssert();
						if (statusCode == 200) {
							softAssert.assertEquals(statusCode, 200);

							System.out.println("*********************************");
							System.out.println("BV : " + baseVariantModelId + " linked with lead vehicle : "
									+ leadVehicleModelId + "successfully..");
							System.out.println("*********************************");
							Log.endTestCase("BV : " + baseVariantModelId + " linked with lead vehicle : "
									+ leadVehicleModelId + "successfully..");

						} else {
							System.out.println("*********************************");
							System.out.println("Failed to link BV : " + baseVariantModelId + " with lead vehicle : "
									+ leadvehicleModel);
							System.out.println("*********************************");
							Log.info("Failed to link BV : " + baseVariantModelId + " with lead vehicle : "
									+ leadvehicleModel);

							softAssert.fail();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.getClass().getSimpleName() + ":" + e.getMessage());
			}

		}
	}

}
