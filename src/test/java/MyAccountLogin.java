import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import helperBase.BasePage;
import helperBase.SeleniumFactory;
import utilityLib.Log4jLogger;

public class MyAccountLogin extends SeleniumFactory {

	Log4jLogger testLogger = new Log4jLogger();
	private ThreadLocal<String> testName= new ThreadLocal<String>();
//@BeforeSuite
//
//public void runSetup() {
//	testSetup();
//	
//}
	
	// testname generator for data provider
	
	@BeforeTest
	
	public void beforeMethod(Method method,Object[] testdata,ITestContext ctx) {
		if (testdata.length>0) {
			testName.set(method.getName()+"_"+testdata[0]);
			ctx.setAttribute("testName", testName.get());
		}
		else {
			ctx.setAttribute("testName", method.getName());
		}
				
	}
	
	
@Test
	
	public void myAccLogin() {
		
		
	//	getDriver().get("http://tutorialsninja.com/demo/index.php?");
	testLogger.info("Logging Test sample");
	getDriver().navigate().to("http://tutorialsninja.com/demo/index.php?");
//	getDriver().manage().window().setPosition(new Point(0, -1000));
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		BasePage.homePage().myAccMenu.click();
		BasePage.homePage().loginMenu.click();
		SeleniumFactory.txtBxType(BasePage.loginPage().userEmail, "sachinsankpal1@gmail.com");
		SeleniumFactory.txtBxType(BasePage.loginPage().userPwd, "pass1234");
		BasePage.loginPage().loginBtn.click();
		try {
		BasePage.loginPage().editAccLnk.isDisplayed();
		testLogger.info("Succesfully Logged to My Account");
		System.out.println("Login successful!");
		
		}
		catch (Exception e) {
			testLogger.info("Login Failed! -logger");
			System.out.println("Login Failed!");
			Assert.fail();
		}
		
		
					
	}

@Test (invocationCount=2, threadPoolSize=5)
public void t1() {
	System.out.println("This is T1");
}


@BeforeClass 
public void t2() {
	System.out.println("This is BeforeClass");
}

@BeforeGroups
public void t3() {
	System.out.println("This is BeforeGroups");
}
}
