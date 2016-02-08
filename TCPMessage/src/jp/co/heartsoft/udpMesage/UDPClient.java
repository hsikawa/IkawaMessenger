package jp.co.heartsoft.udpMesage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class UDPClient{

	public static final String SERVER = "192.168.0.255";
	public static final int SERVER_PORT = 8080;

	public static final int PACKET_SIZE = 1024;

	public void get() {
		DatagramSocket socket = null;

		InetSocketAddress remoteAddress =

				new InetSocketAddress(SERVER, SERVER_PORT);



		try {
			socket = new DatagramSocket();

			String message = "hello";

			byte[] buf = message.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, remoteAddress);
			socket.send(sendPacket);

			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			socket.receive(receivePacket);

	        String receveMessage = new String(buf, 0, receivePacket.getLength());


			System.out.println("receveMessage : " + receveMessage);
			System.out.println("receveAddress : " + receivePacket.getAddress());

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (socket != null) {

				socket.close();

			}

		}
	}
}
