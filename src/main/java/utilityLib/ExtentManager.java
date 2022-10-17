package utilityLib;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	
	private static ExtentReports extent;
	private static String reportFileName = "report-Name_"+System.getProperty("current.date.time")+".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath =System.getProperty("user.dir")+fileSeperator+"testReports"+fileSeperator+System.getProperty("current.date");
	private static String reportFileLocation = reportFilepath+fileSeperator+reportFileName;
	
	
	public static ExtentReports getInstance() {
		if(extent==null) {
			createInstance();
		}
		return extent;
	}
	
	
	//create extent report instance
	public static ExtentReports createInstance() {
		
		String fileName= getReportPath(reportFilepath);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setReportName("Automation Test Result");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Results");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //set env
        extent.setSystemInfo("OS","Windows");
        extent.setSystemInfo("AUT","App Under test");
        
        return extent;
	}
	
	
	//create report path
	 private static String getReportPath (String path) {
	        File testDirectory = new File(path);
	        if (!testDirectory.exists()) {
	            if (testDirectory.mkdir()) {
	                System.out.println("Directory: " + path + " is created!" );
	                return reportFileLocation;
	            } else {
	                System.out.println("Failed to create directory: " + path);
	                return System.getProperty("user.dir");
	            }
	        } else {
	            System.out.println("Directory already exists: " + path);
	                   }
	        return reportFileLocation;
	    }
}
