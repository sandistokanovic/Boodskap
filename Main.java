package boodskap;

import java.sql.*;
import java.util.Scanner;
import java.io.IOException;
import java.math.*;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scanner = new Scanner(System.in);
		user reg = new user();

		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		System.out.println("Do you have an account? Type Y if you do and N if you do not!");
		String check = scanner.nextLine(); 
		if(check.equals("Y")) {
			reg.login();}
		
		else if(check.equals("N")){
			reg.registration();
			reg.login();
		}
		
	}			   
}

