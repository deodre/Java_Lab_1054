package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	public static void main(String[] args) {
		
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			
			
			byte[] buffer = new byte[256];
			buffer[0] = 'H';
			buffer[1] = 'e';
			buffer[2] = 'l';
			buffer[3] = 'l';
			buffer[4] = 'l';
			buffer[5] = 'o';
			
			InetAddress address = InetAddress.getByName(args[0]);
			
			int port = Integer.parseInt(args[1]);
			
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
			clientSocket.send(packet);
			
			byte[] bufferResp = new byte[256];
			packet = new DatagramPacket(bufferResp, bufferResp.length);
			clientSocket.receive(packet);
			
			String received = new String(packet.getData());
			System.out.println("Client received from the server: " + received);
			
			clientSocket.close();
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
