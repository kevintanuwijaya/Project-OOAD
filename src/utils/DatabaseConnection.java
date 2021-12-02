package utils;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {

	private static DatabaseConnection instance = null;
	private Connection conn;
	
	public static DatabaseConnection getInstance() {
		
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
	
	private DatabaseConnection() {
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_lab_ooad","root","");
			System.out.println("Success");
		} catch (Exception e) {
			System.out.println("Failed");
		}
	}
	
}
