package helperBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public static Properties PR;
	public static File f1;
	public FileInputStream file;
//	public static WebDriver driver;
	protected static ThreadLocal<WebDriver> ThreadDriver = new ThreadLocal<WebDriver>();

	public BrowserFactory() {
		PR = new Properties();
		File f1 = new File(System.getProperty("user.dir") + "\\config\\config.properties");
		FileInputStream file = null;

		try {
			file = new FileInputStream(f1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			PR.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// read browser name from config

	public static void testSetup(String bName) {

	//	String bName = PR.getProperty("BrowserName");

		if (bName.equalsIgnoreCase("Firefox")) {
			FirefoxProfile myprofile = new FirefoxProfile();
			myprofile.setPreference("browser.download.dir",
					System.getProperty("user.dir") + "\\UIAutomation_Selenium\\download");
			myprofile.setPreference("browser.download.folderList", 2);
			myprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
			myprofile.setPreference("browser.helperApps.neverAsk.openFile", "application/vnd.ms-excel");
			myprofile.setPreference("browser.download.manager.showWhenStarting", false);
			myprofile.setPreference("pdfjs.disabled", true);

			FirefoxOptions option = new FirefoxOptions();
			option.setProfile(myprofile);
			ThreadDriver.set(WebDriverManager.firefoxdriver().capabilities(option).create());
	//		ThreadDriver.set(new FirefoxDriver(option));
			System.out.println(Thread.currentThread().getId());
		}

		else {
		//	ThreadDriver.set(new ChromeDriver());
			ThreadDriver.set(WebDriverManager.chromedriver().create());
			System.out.println(Thread.currentThread().getId());
		}
	}

	// get browser name from testNG
	public static void testSetup() {
		String bName = PR.getProperty("BrowserName");
		if (bName.equalsIgnoreCase("Firefox")) {
			FirefoxProfile myprofile = new FirefoxProfile();
			myprofile.setPreference("browser.download.dir",
					System.getProperty("user.dir") + "\\UIAutomation_Selenium\\download");
			myprofile.setPreference("browser.download.folderList", 2);
			myprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
			myprofile.setPreference("browser.helperApps.neverAsk.openFile", "application/vnd.ms-excel");
			myprofile.setPreference("browser.download.manager.showWhenStarting", false);
			myprofile.setPreference("pdfjs.disabled", true);

			FirefoxOptions option = new FirefoxOptions();
			option.setProfile(myprofile);
			ThreadDriver.set(WebDriverManager.firefoxdriver().capabilities(option).create());
	//		ThreadDriver.set(new FirefoxDriver(option));
			System.out.println(Thread.currentThread().getId());
		}

		else if(bName.equalsIgnoreCase("Chrome")) {
		//	ThreadDriver.set(new ChromeDriver());
			ThreadDriver.set(WebDriverManager.chromedriver().create());
			System.out.println(Thread.currentThread().getId());
		}
	}

	public static WebDriver getDriver() {
		return ThreadDriver.get();
	}

}
