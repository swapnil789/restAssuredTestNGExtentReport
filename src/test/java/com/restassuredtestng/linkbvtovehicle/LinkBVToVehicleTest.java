package com.restassuredtestng.linkbvtovehicle;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restassuredtestng.resource.model.LinkBaseVariantToVehicleResource;
import com.restassuredtestng.utilities.ExcelReader;
import com.restassuredtestng.utilities.Log;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LinkBVToVehicleTest {

	@Test
	public void linkBVToVehicle() {
		RestAssured.baseURI = "http://localhost:8443";
		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);

		ExcelReader excelReader = new ExcelReader();

		int rowcount = excelReader.getSheet().getLastRowNum();
		for (int row = 1; row <= rowcount; row++) {
			try {
				String vehileModelId = excelReader.getVehicleModelId(row);
				String baseVariantModelId = excelReader.getBaseVariantModellId(row);

					Log.startTestCase("Link BV : " + baseVariantModelId + " To Vehicle :" + vehileModelId);

					request.body(LinkBaseVariantToVehicleResource
							.getLinkBaseVariantToVehicleResource(vehileModelId, baseVariantModelId).toJSONString());

					Response response = request.pathParams("vehicleId", vehileModelId)
							.queryParam("comment", "BV linked using automation")
							.post("vehicles/createLinks/{vehicleId}");

					int statusCode = response.getStatusCode();
					SoftAssert softAssert = new SoftAssert();
					if (statusCode == 200) {
						softAssert.assertEquals(statusCode, 200);

						System.out.println("*********************************");
						System.out.println("BV : " + baseVariantModelId + " linked with vehicle : " + vehileModelId
								+ " successfully..");
						System.out.println("*********************************");
						Log.endTestCase("BV : " + baseVariantModelId + " linked with vehicle : " + vehileModelId
								+ " successfully..");

					} else {
						System.out.println("*********************************");
						System.out.println(
								"Failed to link BV : " + baseVariantModelId + " with vehicle : " + vehileModelId);
						System.out.println("*********************************");
						softAssert.fail();
					}
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.getClass().getSimpleName() + ":" + e.getMessage());
			}

		}
	}
}
