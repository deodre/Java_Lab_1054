package eu.ase.net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpMultiServerThread extends Thread {

	private Socket socket = null;
	
	public HttpMultiServerThread(Socket client) {
		this.socket = client;
	}

	@Override
	public void run() {
		
		OutputStream os = null;
		PrintWriter out = null;
		
		InputStream is = null;
		BufferedReader in = null;
		
		try {
			
			is = this.socket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			
			os = this.socket.getOutputStream();
			out = new PrintWriter(os, true);
			
			String inputLine = "";
			String outputLine = "";
			String processLine = "";
			
			while (((inputLine = in.readLine()) != null) && (inputLine.length() > 1)) {
				processLine += inputLine;
			}
			
			HttpMyProtocol objProtocol = new HttpMyProtocol();
			
			outputLine = objProtocol.processInput(processLine);
			
			out.println(outputLine);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	
}
