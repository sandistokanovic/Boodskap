package boodskap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;



public class Message {
	
	private String content;
	private String sender;
	private File file = new  File("messages.txt");
	private FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
    private BufferedWriter bw = new BufferedWriter(fw); 
    private BufferedReader br;
    private Encryption enc;
  
    public create {
    	  public static void main(String[] args) {
    	    try {
    	      File myObj = new File("filename.txt");
    	      if (myObj.createNewFile()) {
    	        System.out.println("File created: " + myObj.getName());
    	      } else {
    	        System.out.println("File already exists.");
    	      }
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    	  }
    	}

	public Message(String username) throws IOException {
		this.sender=username;

	}
	
	public void addMessage(String content) throws IOException
	{
		this.enc=new Encryption(content);
		String encrypted=enc.encrypt();
		this.bw.write(this.sender + ": "+ encrypted);
		this.bw.newLine();
		this.bw.close();
	}
	

	
}
	


  
   