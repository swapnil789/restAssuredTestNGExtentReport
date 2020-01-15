package com.restassuredtestng.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * @author swapnilk10
 *
 */
public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("KPIT Technologies");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("DDAT - Data Migration");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		extent.setSystemInfo("Machine", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("DDAT Version", "1.0.4RC02");
		return extent;
	}

}
