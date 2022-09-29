package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helperBase.SeleniumFactory;

public class HomePageObjects extends SeleniumFactory {
	
	@FindBy(xpath="//span[text()='My Account']") public WebElement myAccMenu;
	@FindBy(xpath="//a[text()='Login']") public WebElement loginMenu;

}
