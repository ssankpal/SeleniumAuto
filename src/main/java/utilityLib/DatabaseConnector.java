package utilityLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private static DatabaseConnector instance;
	private Connection conn;
	static Log4jLogger testLogger = new Log4jLogger();
	
	
	//== singleton concept
	
	private DatabaseConnector() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		//	Class.forName("com.micorsoft.sqlserver.jdbc.SQLServerDriver"); for MS Sql server
			String dburl = "jdbc:mysql//xx.xx.xx.xxx:3306/dbname?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(dburl,"user","pwd");
			this.conn=conn;
		}
		catch(Exception e) {
			testLogger.info("Issue in DB connection");
			testLogger.error(e);
		}
		
	}
	
	
	public static DatabaseConnector getInstance() {
		if (instance==null) {
		instance = new DatabaseConnector();	
		}
		return instance;
	}
	
	public static void closeDBConn() {
		try {
			instance.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			testLogger.error(e);
		}
	}
}
