package helperBase;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class SeleniumFactory extends BrowserFactory {

	// textbox type
	
	public static void txtBxType(WebElement element, String input) {
		element.clear();
		element.sendKeys(input);
	}
	
	// Menu Travel
	public void menuTravel(List<WebElement> MT) {
		Actions action = new Actions(getDriver());
		int mlen = MT.size();
		
		for(int ML=0; ML<mlen; ML++) {
			if(ML== mlen-1) {
				action.pause(5);
				action.click(MT.get(ML)).perform();
			}
			else {
				action.moveToElement(MT.get(ML)).perform();
			}
		}
		
	}
	
	// Select radio button
	
	public static boolean selectradioBtn(WebElement option) {
		if(option.isEnabled()) {
			if(!option.isSelected()) {
				option.click();
			}
			return true;
		}
		return false;
	}
	
	// dropdown selction
	
	public static void drpdwnSelect(WebElement dd, String value) {
		
		Select s = new Select(dd);
		s.selectByValue(value);
		
	}
	
	
// capture screenshot
	
	public static String captureSnap(String methodname) {
		File src =((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String reportDir=null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
			DateFormat timeFormat = new SimpleDateFormat("HHmmss");
			Date date =new Date();
			String dateFolder = dateFormat.format(date);
			String currTime = timeFormat.format(date);
			reportDir = System.getProperty("user.dir")+"//screenshots//"+dateFolder+"//"+methodname+"_"+currTime+".png";
			File destination =new File(reportDir);
			FileUtils.copyFile(src, destination);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return reportDir;
	}
	
	
	// Fluent wait method
	
		public static boolean chkImportStatus() {
			
			FluentWait<WebDriver> wait = new FluentWait<WebDriver> (getDriver()).withTimeout(Duration.ofSeconds(600))
					.pollingEvery(Duration.ofSeconds(60)).ignoring(NoSuchElementException.class);
			Function<WebDriver, Boolean> function= new Function<WebDriver, Boolean>(){
				public Boolean apply(WebDriver driver) {
					String impStatus = "Done" ;//BasePage.importpage.impstatus.getText();
					if(impStatus.equals("Done") || impStatus.equals("Done with Warning")
							|| impStatus.equals("Failed")) {
						return true;
					}
					else {
						return false;
					}
				}
			};
			return wait.until(function);
		}
}
