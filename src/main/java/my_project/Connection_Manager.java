package my_project;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_Manager {
	public static Connection connectWithDb() throws Exception {
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
		String user = "root";
		String password = "Username123";
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
	
}
