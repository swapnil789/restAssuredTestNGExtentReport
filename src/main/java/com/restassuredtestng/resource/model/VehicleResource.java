package com.restassuredtestng.resource.model;

import org.json.simple.JSONObject;

/**
 * @author swapnilk10
 *
 */
public class VehicleResource {

	public static JSONObject getVehicleObject(String vehicleModel, String modelYear, String variant,
			String platform) {

		JSONObject requestParams = new JSONObject();

		requestParams.put("@class", "com.jlr.ecueditor.entity.pojo.Vehicle");
		requestParams.put("active", "true");
		requestParams.put("authorizationId", "");
		requestParams.put("deleted", "false");

		// Nested JSON Object for passing ElementId information
		JSONObject elementId = new JSONObject();
		elementId.put("description", "Created by automation");

		// Pass Nested JSON Object into Main JSON object
		requestParams.put("elementId", elementId); 

		requestParams.put("modelYear", modelYear);
		requestParams.put("platform", platform);
		requestParams.put("variant", variant);
		requestParams.put("vehicleModel", vehicleModel);
		requestParams.put("type", "Vehicle");

		// Nested JSON Object for passing Admin Information
		//JSONObject adminInfo = new JSONObject();
		//adminInfo.put("author", author);
		//adminInfo.put("owner", owner);

		// Pass Nested JSON Object into Main JSON object
		//requestParams.put("adminInformation", adminInfo);

		return requestParams;
	}

}
