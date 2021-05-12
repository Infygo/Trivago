package stepDefinitions;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReportNG;

public class Listeners extends Base implements ITestListener {
	ExtentTest test;
	ExtentReports exrep = ExtentReportNG.reportObject();
	WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = exrep.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test failed");
		test.fail(result.getThrowable());
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		/*
		try {
			//test.addScreenCaptureFromPath(getScreenShot(scenarioName, driver),result.getMethod().getMethodName());
			getScreenShot(scenarioName, driver);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		exrep.flush();
	}

}
