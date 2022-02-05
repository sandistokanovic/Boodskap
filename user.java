package boodskap;


import java.util.Scanner;
import java.sql.*;
import java.io.IOException;
import java.math.*; //ovo navodno treba za jdbc jer java natively ne podrzava big int 
import java.net.Socket;
import java.net.UnknownHostException;

public class user {
	
	public static Connection getConnection() throws SQLException { 			
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/boodskap","root","admin");
		
		return connection;
	}

	public void registration() {
		Connection connection = null;
		try {
		connection = user.getConnection();
	    Scanner scanner = new Scanner(System.in);
	    

	    System.out.println("Enter username"); 
	    String userName = scanner.nextLine(); 	 
	    
	    System.out.println("Enter password");
	    String password = scanner.nextLine();
	    
		int checkPass = 0;
	    while (checkPass == 0) {  
		    System.out.println("Confirm password");
		    String passRepeat = scanner.nextLine();
	    
	    		if (password.equals(passRepeat)) {
	    			checkPass = 1;
	    		}
	    		else {
	    			System.out.println("Passwords didn't match, try again!");
	    		}
	    }

	    
	    System.out.println("Enter firstname");
	    String firstName = scanner.nextLine();
	    
	    System.out.println("Enter lastname");
	    String lastName = scanner.nextLine();
	    
	    System.out.println("Enter email");
	    String email = scanner.nextLine();		
	    
	    PreparedStatement stmt = connection.prepareStatement("Insert into user values(?, ?, ?, ?, ?, ?)"); 
	    
	    int nextID = 0;
	    Statement idAuto = connection.createStatement();
	    ResultSet rs = idAuto.executeQuery("select idUser from user order by idUser desc limit 1");
	    if (rs.next()) {
	    	nextID = rs.getInt(1)+1;
	    }
	   
	    stmt.setInt(1, nextID);
	    stmt.setString(2, userName);
	    stmt.setString(3, password);	    
	    stmt.setString(4, firstName);	    
	    stmt.setString(5, lastName);	    
	    stmt.setString(6, email);		    
	    stmt.execute();
	    stmt.close();
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
		finally {
			
			System.out.println("Succesfully registered!");
		}
	}
	
	public void login() throws UnknownHostException, IOException{
		Connection connection = null;
		try {
		connection = user.getConnection();
	    Scanner scanner = new Scanner(System.in);

        while(true) {
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select Username, Password from user where Username=? and Password=?");
	        System.out.println("Please enter your username");
			String userName = scanner.nextLine(); 
			System.out.println("Please enter your password:");
			String password = scanner.nextLine(); 
			st.setString(1, userName);
            st.setString(2, password);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            
	           System.out.println("You have successfully logged in. Welcome to the Boodskap chat");
	           Socket socket=new Socket("localhost",5454);
	   		   Client client=new Client(socket,userName);
	   		   client.listener(); 
	   		   client.sendMessage();
	           
	           
	        } else {
	           System.out.println("Wrong Username & Password");
        }}
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
    }}}