/*
 * Promineo Tech BESD Bootcamp
 * MySQL Week 6 Coding Assignment
 * Group Project:  John, Kendall, & Lisa
 * 
 * DBConnection Class using JavaSE-1.8
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {

	private static Connection connection;
	private final static String URL = "jdbc:mysql://localhost:3306/recording_artists";
	private final static String USER = "root";
	private static String password = "";
	private static Scanner scanner = new Scanner(System.in);
	private static DBConnection instance;

	/*
	 * Constructor
	 */
	
	private DBConnection (Connection connection) {
		this.connection = connection;
	}
	
	
	public static Connection getConnection() {
		boolean success = false;
		do {
			if (instance == null) {
				try {				
					System.out.println("UserName: " + USER);
					System.out.print("Enter your password: ");
					password = scanner.nextLine();
					connection = DriverManager.getConnection(URL,USER,password);
					instance = new DBConnection(connection);
					System.out.println("\n\n\n\n");	
					success = true;
				} catch (SQLException e) {
					System.out.println("\n\nERROR:  Please try again!\n\n");				
				}
		    } else {
		    	success = true;
		    }
		} while (success == false);
		
		return DBConnection.connection;		
	}
}
