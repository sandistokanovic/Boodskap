package boodskap;

import java.io.*;
import java.util.Scanner;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {	
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String username;
	private Message message;
	
	
	public Client(Socket socket, String username)
	{
		
		try {
		this.socket=socket;
		this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		this.username=username;
		this.message=new Message(this.username);
		
	}
		catch(IOException e)
		{
			close(socket,reader,writer);
		}
	
		
	}
	
	
	public  void sendMessage()
	{
		try {
			writer.write(username);
			writer.newLine();
			writer.flush();
			
			Scanner input=new Scanner(System.in);
		    while(socket.isConnected())
		    {
		    
		    	String usermessage=input.nextLine();
		    	writer.write(username +" : " + usermessage);
		    	writer.newLine();
		    	writer.flush();
		   
		    	this.message.addMessage(usermessage);
		    }
		    input.close();
		    
			
		} catch(IOException e) {
			close(socket,reader,writer);
			
			
		}
	}
	
	public void listener()
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				String chatmessage;
				
				while(socket.isConnected())
				{
					try
					{
						chatmessage=reader.readLine();
						System.out.println(chatmessage);
					}
					catch(IOException e)
					{
						close(socket,reader,writer);
					}
				}
				
			}
			
		}).start();
		
	}
	public void close(Socket socket, BufferedReader reader, BufferedWriter writer)
	{


		try
		{
			if(reader!=null)
			{
				reader.close();
			}
			if(writer!=null)
			{
				writer.close();
			}
			if(socket!=null)
			{
				socket.close();
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	}

