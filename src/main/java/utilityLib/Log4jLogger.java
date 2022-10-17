package utilityLib;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;



public class Log4jLogger {
	
	// static block to set env varibale to pick it up for appending with report, snapshot etc. 
	static {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("ddMMyyyy");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		System.setProperty("current_time", timeFormat.format(new Date()));
		System.setProperty("current.date", dateFormat1.format(new Date()));
	}
	
	public static void getName(ITestContext ctx) {
		String textName=ctx.getName();
		System.setProperty("testName", textName);
	}
	
	private static Logger logger = LogManager.getLogger();
	
	public static void info(String info) {
	//	System.out.println(info);
		logger.info(info);
		}
	
	public static void error(Throwable T) {
		logger.error(T);
	}
	

}
