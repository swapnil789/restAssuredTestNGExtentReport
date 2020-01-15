package com.restassuredtestng.resource.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author swapnilk10
 *
 */
public class LinkBaseVariantToVehicleResource {

	public static JSONArray getLinkBaseVariantToVehicleResource(String parentModelId, String baseVariantModelId) {

		JSONObject requestParams = new JSONObject();

		requestParams.put("@class", "com.jlr.ecueditor.entity.pojo.VehicleBaseVariantLink");
		requestParams.put("unlink", "false");
		requestParams.put("parentModelId", parentModelId); // This should be the modelId of Existing vehicle
		requestParams.put("baseVariantModelId", baseVariantModelId);
		requestParams.put("active", "true");
		requestParams.put("deleted", "false");

		// Nested JSON Object for passing Admin Information
		/*
		 * JSONObject adminInfo = new JSONObject(); adminInfo.put("author", author);
		 * adminInfo.put("owner", owner);
		 * 
		 * // Pass Nested JSON Object into Main JSON object
		 * requestParams.put("adminInformation", adminInfo);
		 */

		requestParams.put("type", "VehicleBaseVariantLink");

		// Convert JSON object into JSON array as per API syntax
		JSONArray requestArray = new JSONArray();
		requestArray.add(requestParams);

		return requestArray;

	}
}
