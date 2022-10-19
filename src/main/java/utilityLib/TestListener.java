package utilityLib;



import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
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
		Log4jLogger.info("Starting New Test Execution");
		// For test name generator when data provider used
		//ExtentTestManager.startTest(result.getTestContext().getAttribute("testName").toString());
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(Status.PASS,"Test Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String excepMsg = Arrays.toString(result.getThrowable().getStackTrace());
		ExtentTestManager.getTest().log(Status.FAIL,"Test Failed");
		ExtentTestManager.getTest().fail("<details><summary><b><font color=red>" + "Exception occured, click to view details:" + "</font></b></summary>"+
		 excepMsg.replaceAll(",","<br>")+"</details> \n");
		String screenshotPath = SeleniumFactory.captureSnap(result.getMethod().getMethodName());
		//	testLogger.error(result.getThrowable());
		//attach snap o report
		try {
			ExtentTestManager.getTest().fail("Screenshot",
				MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		catch (Exception e) {
	//		testLogger.ino("Somewthing went worng while attaching snap to report");
	//		testLogger.error(e);
		}
//		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
//		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
//		Date date =new Date();
//		String dateFolder = dateFormat.format(date);
//		String currTime = timeFormat.format(date);
//		String fs = System.getProperty("user.dir")+"//screenshots//"+dateFolder+"//"+result.getMethod().getMethodName()+"_"+currTime+".png";
//		File outputFile = new File(System.getProperty("user.dir")+"//screenshots//"+dateFolder+"//"+result.getMethod().getMethodName()+"_"+currTime+".png");
//		outputFile.getParentFile().mkdirs();
//		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(BrowserFactory.getDriver());
//		
//		try {
//			ImageIO.write(fpScreenshot.getImage(),"PNG",outputFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//		ExtentTestManager.getTest().fail("<b><font color=red>"+"Screenshot"+"</font></b>",
//			MediaEntityBuilder.createScreenCaptureFromPath(fs).build());
//	}
//	catch (Exception e) {
//		ExtentTestManager.getTest().fail("Somewthing went worng while attaching snap to report");
//		testLogger.info("Somewthing went worng while attaching snap to report");
//		testLogger.error(e);
//	}
		
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
		Log4jLogger.info("Starting Suite Execution");
//		String fileSeperator = System.getProperty("file.separator");
//		File destDir = new File(System.getProperty("user.dir")+fileSeperator+"jReport");
//		try {
//			FileUtils.cleanDirectory(destDir);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		String fileSeperator = System.getProperty("file.separator");
		File destDir = new File(System.getProperty("user.dir")+fileSeperator+"jReport");
		File srcFile = new File(System.getProperty("user.dir")+fileSeperator+"testReports"+fileSeperator+System.getProperty("current.date")
		+fileSeperator+"report-Name_"+System.getProperty("current.date.time")+".html");
		System.out.println(srcFile.toString());
	//	String fileSeperator = System.getProperty("file.separator");
		File deDir = new File(System.getProperty("user.dir")+fileSeperator+"jReport");
		try {
			FileUtils.cleanDirectory(deDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileUtils.copyFileToDirectory(srcFile,destDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
