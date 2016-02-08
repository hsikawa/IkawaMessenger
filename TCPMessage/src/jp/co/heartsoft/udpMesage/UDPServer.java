package jp.co.heartsoft.udpMesage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer implements Runnable {

	public static final int SERVER_PORT = 8080;

	public static final int PACKET_SIZE = 1024;

	public static final String userName = "ikawa";

	@Override
	public void run() {
		DatagramSocket socket = null;

		byte[] buf = new byte[PACKET_SIZE];

		DatagramPacket packet = new DatagramPacket(buf, buf.length);

		try {

			socket = new DatagramSocket(SERVER_PORT);

			System.out.println("DatagramReceiverが起動しました(port="

			+ socket.getLocalPort() + ")");

			while (true) {

				socket.receive(packet);

				String message = new String(buf, 0, packet.getLength());

				if (message.equals("hello")) {
					buf = userName.getBytes();
					packet.setData(buf);
					socket.send(packet);

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (socket != null) {

				socket.close();

			}

		}
	}
}
