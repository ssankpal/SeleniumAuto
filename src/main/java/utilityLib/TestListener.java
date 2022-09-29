package utilityLib;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import helperBase.BrowserFactory;
import helperBase.SeleniumFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestListener implements ITestListener {

	Log4jLogger testLogger = new Log4jLogger();
	
	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		testLogger.info("Starting Suite Execution");
		// For test name generator when data provider used
		//ExtentTestManager.startTest(result.getTestContext().getAttribute("testName").toString());
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(Status.PASS,"Test Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(Status.FAIL,"Test Failed");
//		String screenshotPath = SeleniumFactory.captureSnap(result.getMethod().getMethodName());
//		testLogger.error(result.getThrowable());
//		//attach snap o report
//		try {
//			ExtentTestManager.getTest().fail("Screenshot",
//				MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//		}
//		catch (IOException e) {
//	//		testLogger.ino("Somewthing went worng while attaching snap to report");
//			testLogger.error(e);
//		}
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
		Date date =new Date();
		String dateFolder = dateFormat.format(date);
		String currTime = timeFormat.format(date);
		File outputFile = new File(System.getProperty("user.dir")+"//screenshots//"+dateFolder+"//"+result.getMethod().getMethodName()+"_"+currTime+".png");
		outputFile.getParentFile().mkdirs();
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(BrowserFactory.getDriver());
		try {
			ImageIO.write(fpScreenshot.getImage(),"PNG",outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(Status.SKIP,"Test Skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		
	}

}
