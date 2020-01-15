package com.restassuredtestng.listeners;

import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.restassuredtestng.utilities.ExtentManager;

/**
 * @author swapnilk10
 *
 */
public class ExtentListeners implements ITestListener {

	static Date date = new Date();
	static String fileName = "Report_" + date.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "SCENARIO:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(markup);
	}

	public void onTestFailure(ITestResult result) {

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		String methodName = result.getMethod().getMethodName();
		String failureLogg = "<b>" + "SCENARIO:-" + methodName.toUpperCase() + " FAILED" + "</b>";
		Markup markup = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, markup);
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "SCENARIO:- " + methodName.toUpperCase() + " Skipped" + "</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
		testReport.get().skip(markup);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}

	}

}
