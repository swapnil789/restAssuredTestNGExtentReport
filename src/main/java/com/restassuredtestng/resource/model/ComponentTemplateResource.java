package com.restassuredtestng.resource.model;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author swapnilk10
 *
 */
public class ComponentTemplateResource {

	public static JSONObject getCTObject(String gdxShortName, String longName, String parentECUFamilyId,
			String protocol, String category, String primaryVehicleModelId) {

		Random rd = new Random();
		// creating random integers
		int randInt = rd.nextInt(9000000) + 1000000;

		JSONObject requestParams = new JSONObject();

		requestParams.put("@class", "com.jlr.ecueditor.entity.pojo.BaseVariant");
		requestParams.put("gdxShortName", gdxShortName);
		// Parameter passed in bleow line is used to generate 7 digit random number
		requestParams.put("baseVariantExternalSystemID", randInt);

		// Nested JSON Object for passing ElementId information
		JSONObject elementId = new JSONObject();
		elementId.put("longName", longName);
		elementId.put("description", "Created by automation");

		requestParams.put("elementId", elementId);
		requestParams.put("parentECUFamilyId", parentECUFamilyId); // This should be the modelId
																	// of ECU Family
		requestParams.put("protocol", protocol);
		requestParams.put("category", category); // This should be same as defined for ECU Family

		// Nested JSON Object for passing Admin Information
		/*
		 * JSONObject adminInfo = new JSONObject(); adminInfo.put("author", author);
		 * adminInfo.put("owner", owner);
		 * 
		 * // Pass Nested JSON Object into Main JSON object
		 * requestParams.put("adminInformation", adminInfo);
		 */

		// Passing ECU Properties
		JSONArray ecuPropertiesArray = new JSONArray();

		// ============= Object of MAX DID REQ_Application ==============/
		JSONObject req_Application = new JSONObject();
		req_Application.put("name", "MAX DID REQ_Application");
		req_Application.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray = new JSONArray();
		detailsArray.add("1");

		req_Application.put("details", detailsArray);
		req_Application.put("description", "");

		ecuPropertiesArray.add(req_Application);

		// ======== Object of MAX DID REQ_Bootloader ===============/
		JSONObject req_Bootloader = new JSONObject();
		req_Bootloader.put("name", "MAX DID REQ_Bootloader");
		req_Bootloader.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray1 = new JSONArray();
		detailsArray1.add("1");

		req_Bootloader.put("details", detailsArray1);
		req_Bootloader.put("description", "");

		ecuPropertiesArray.add(req_Bootloader);

		// == Object of Max Message Size(Buffer Size) Default Extended(Decimal)==/
		JSONObject req_DefaultExt = new JSONObject();
		req_DefaultExt.put("name", "Max Message Size(Buffer Size) Default Extended(Decimal)");
		req_DefaultExt.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray2 = new JSONArray();
		detailsArray2.add("1024");

		req_DefaultExt.put("details", detailsArray2);
		req_DefaultExt.put("description", "");

		ecuPropertiesArray.add(req_DefaultExt);

		// == Object of Max Message Size(Buffer Size) Programming(Decimal) ==/
		JSONObject req_Programming = new JSONObject();
		req_Programming.put("name", "Max Message Size(Buffer Size) Programming(Decimal)");
		req_Programming.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray3 = new JSONArray();
		detailsArray3.add("1024");

		req_Programming.put("details", detailsArray3);
		req_Programming.put("description", "");

		ecuPropertiesArray.add(req_Programming);

		// ======= Gateway ========/
		JSONObject gateway = new JSONObject();
		gateway.put("name", "Gateway");
		gateway.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray4 = new JSONArray();
		detailsArray4.add("");

		gateway.put("details", detailsArray4);
		gateway.put("description", "");

		ecuPropertiesArray.add(gateway);

		// ======= OBD II ========/
		JSONObject obd2 = new JSONObject();
		obd2.put("name", "OBD II");
		obd2.put("propertyType", "APPLICABLE_FUNCTION");

		JSONArray detailsArray5 = new JSONArray();
		detailsArray5.add("true");

		obd2.put("details", detailsArray5);
		obd2.put("description", "");

		ecuPropertiesArray.add(obd2);

		// ======= Programmable ECU ========/
		JSONObject programmableEcu = new JSONObject();
		programmableEcu.put("name", "Programmable ECU");
		programmableEcu.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray6 = new JSONArray();
		detailsArray6.add("true");

		programmableEcu.put("details", detailsArray6);
		programmableEcu.put("description", "");

		ecuPropertiesArray.add(programmableEcu);

		// ======= Bootloader ========/
		JSONObject bootloader = new JSONObject();
		bootloader.put("name", "Bootloader");
		bootloader.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray7 = new JSONArray();
		detailsArray7.add("Two Level");

		bootloader.put("details", detailsArray7);
		bootloader.put("description", "");

		ecuPropertiesArray.add(bootloader);

		// ======= CCF Type ========/
		JSONObject ccfType = new JSONObject();
		ccfType.put("name", "CCF Type");
		ccfType.put("propertyType", "ECU_SPECIFIC_PROPERTY");

		JSONArray detailsArray8 = new JSONArray();
		detailsArray8.add("CCVDS");

		ccfType.put("details", detailsArray8);
		ccfType.put("description", "");

		ecuPropertiesArray.add(ccfType);

		requestParams.put("ecuProperties", ecuPropertiesArray);

		JSONArray supportedProtocols = new JSONArray();
		supportedProtocols.add("UDS ON CAN");

		requestParams.put("supportedProtocols", supportedProtocols);

		requestParams.put("templateForDataMigration", "true");
		requestParams.put("type", "BaseVariant");

		// Below property links the primary vehicle to Component template
		requestParams.put("primaryVehicleModelId", primaryVehicleModelId);

		return requestParams;

	}
}
