package helperBase;

import org.openqa.selenium.support.PageFactory;

import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;

public class BasePage extends SeleniumFactory {

	 private static <T> T getPage(Class<T> pageType)  {
	       T page = null;
	        
	            try {
					page = pageType.newInstance();
				
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					System.out.println("Page Not found");
				//	e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	       
	      PageFactory.initElements(getDriver(), page);
	        return page;
	    }
	 
	 //Initialize HomePage
	 
	 public static HomePageObjects  homePage() {
		 return getPage(HomePageObjects.class);
	 }
	 
	
	 // Initialize Login Page
	 
	 public static LoginPageObjects  loginPage() {
		 return getPage(LoginPageObjects.class);
	 }
	 
}
