package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import helperBase.SeleniumFactory;

public class LoginPageObjects extends SeleniumFactory {
	
	@FindBy(id="input-email") public WebElement userEmail;
	@FindBy(id="input-password") public WebElement userPwd;
	@FindBy(xpath="//input[@value='Login']") public WebElement loginBtn;
	@FindBy(xpath="//a[text()='Edit Account']") public WebElement editAccLnk;
}
