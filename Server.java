package boodskap;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {

		private ServerSocket server;
	
		public Server(ServerSocket serversocket)
		{
			this.server=serversocket;
		}
		
		public void startChat()
		{
		
		try {
			while(!server.isClosed())
			{
				Socket client=server.accept();
				System.out.println("Connection with client established");
				ClientHandler clienthandler= new ClientHandler(client);
				Thread thread=new Thread(clienthandler);
				thread.start();
			}
		}
		catch(IOException e) {
	
		}
		}


		
	public void closeSocket()
	{
		try
		{
			if(server!= null)
			{
				server.close();
			}
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
			
		
		public static void main(String[] args) throws IOException 
		{
			ServerSocket serversocket=new ServerSocket(5454);
			Server server=new Server(serversocket);
			server.startChat();
			
			
		}
		
}
			
			

