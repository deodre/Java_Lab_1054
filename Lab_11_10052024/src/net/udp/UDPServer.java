package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
	public static void main(String[] args) {
		
		byte[] bufferRecv = null;
		byte[] bufferResp = null;
		
		try (DatagramSocket socket = new DatagramSocket(7778)) {
			
			System.out.println("UDP Server bind on 7778 port");
			
			while (true) {
				
				bufferRecv = new byte[256];
				DatagramPacket packet = new DatagramPacket(bufferRecv, bufferRecv.length);
				socket.receive(packet);
				System.out.println("Client: " + new String(packet.getData(), 0, packet.getLength()));
				
				String responseString = new String("Ok");
				bufferResp = responseString.getBytes();
				
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				
				DatagramPacket packetResp = new DatagramPacket(bufferResp, bufferResp.length, address, port);
				
				socket.send(packetResp);
				
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
