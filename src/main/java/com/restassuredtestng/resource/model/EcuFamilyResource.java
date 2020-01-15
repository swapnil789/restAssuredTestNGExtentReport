package com.restassuredtestng.resource.model;

import org.json.simple.JSONObject;


/**
 * @author swapnilk10
 *
 */
public class EcuFamilyResource {

	public static JSONObject getEcuFamilyObject(String vehicleDomain, String longName) {

		JSONObject requestParams = new JSONObject();

		requestParams.put("@class", "com.jlr.ecueditor.entity.pojo.ECUFamily");
		requestParams.put("vehicleDomain", vehicleDomain);

		// Nested JSON Object for passing ElementId information
		JSONObject elementId = new JSONObject();
		elementId.put("longName", longName);
		elementId.put("description", "Creatd by automation");

		// Pass Nested JSON Object into Main JSON object
		requestParams.put("elementId", elementId);

		
		// Nested JSON Object for passing Admin Information 
		/*
		 * JSONObject adminInfo = new JSONObject(); adminInfo.put("author", author);
		 * adminInfo.put("owner", owner);
		 * 
		 * // Pass Nested JSON Object into Main JSON object
		 * requestParams.put("adminInformation", adminInfo);
		 */
		 
		return requestParams;
	}

}
