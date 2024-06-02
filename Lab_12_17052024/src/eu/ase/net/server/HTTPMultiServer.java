package eu.ase.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPMultiServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		boolean listening = true;
		
		try {
			
			
		    int port = Integer.parseInt(args[0]);

			serverSocket = new ServerSocket(port);
			
			System.out.println("Server listens on port: " + port);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(listening) {
			try {
				
				Socket client = serverSocket.accept();
				
				HttpMultiServerThread objClient = new HttpMultiServerThread(client);
				objClient.start();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
