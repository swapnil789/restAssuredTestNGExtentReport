package com.restassuredtestng.executor;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class InvokeTestNG {

	public static void main(String[] args) {
		
		System.out.println("******** Execution started ********");
		TestNG  testng = new TestNG ();
		List<String> suites = Lists.newArrayList();
	    suites.add(System.getProperty("user.dir")+"\\config\\testng.xml");
	    testng.setTestSuites(suites);
	    testng.run();
	    System.out.println("******** Execution ended ********");
		
	}

}
