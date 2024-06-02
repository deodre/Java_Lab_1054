package eu.ase.net.server;

import java.io.FileInputStream;
import java.io.IOException;

public class HttpMyProtocol {

	public String processInput(String input) {
		
		String output = "";
		byte[] bufferResp = new byte[4096];
		
		if (input.indexOf("GET") != 0) {
			output = "HTTP/1.1 200 OK\r\nContent-Length: 19\r\nNO SUCH COMMAND\r\n\r\n";
		} else {
			
			String fileName = input.substring(input.indexOf("/") + 1, input.indexOf("HTTP/"));
			String fileExt = fileName.substring(fileName.indexOf(".") + 1);
			
			String contentType = "";
			String fileContent = "";
			
			if (fileExt.compareToIgnoreCase("txt") == 0) {
				contentType = "text/plain";
			}
			
			if (fileExt.compareToIgnoreCase("html") == 0) {
				contentType = "text/html";
			}
			
			if (fileExt.compareToIgnoreCase("htm") == 0) {
				contentType = "text/html";
			}
			
			if (fileExt.compareToIgnoreCase("gif") == 0) {
				contentType = "image/gif";
			}
			
			
			
			try {
				int bytesRead = 0;
				FileInputStream fis = new FileInputStream(fileName);
				
				while ((bytesRead = fis.read(bufferResp)) != -1) {
					fileContent += new String(bufferResp, 0, bytesRead);
				}
				
				fis.close();
				
				output = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContentLength" + (fileContent.length() + 2) + "\r\n\r\n" + fileContent + "\r\n";
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				ioe.printStackTrace();
				output = "HTTP/1.1 404\r\n\r\n";
			}

		}
		
		return output;
	}
}
