package helperBase;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class testNGConfig extends SeleniumFactory {
	
	
	@BeforeTest
	@Parameters({"browser","env"})
	public void runEnvSetup(String browser, String env) {
		if(browser.isEmpty()) {
			testSetup();	
		}
		
		testSetup(browser);
	//	String url = "";
		getDriver().manage().window().maximize();
	//	getDriver().get(url);
	}

	@BeforeMethod
	public void gotoHomePage() {
		
	}
	
	@AfterTest
	public void closeDriver() {
		getDriver().quit();
	}
	
	
	
}
