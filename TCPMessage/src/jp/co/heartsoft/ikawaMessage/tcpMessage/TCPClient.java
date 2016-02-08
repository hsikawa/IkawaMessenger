package jp.co.heartsoft.ikawaMessage.tcpMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {

	public void send(String server, int servPort, String message) {

		byte[] byteBuffer = null;

		Socket socket = null;
		try {
			socket = new Socket(server, servPort);

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			byteBuffer = message.getBytes();

			out.write(byteBuffer);

			int totalBytesRcvd = 0;
			int bytesRcvd;

			while (totalBytesRcvd < byteBuffer.length) {
				if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd,
						byteBuffer.length - totalBytesRcvd)) == -1) {
					throw new SocketException("Connection closed prematurely");
				}
				totalBytesRcvd += bytesRcvd;

				System.out.println("Receive: " + new String(byteBuffer));
			}

			socket.close();
		} catch (IOException e) {
			System.out.println(e + ":" + e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException e) {

			}
		}
	}
}
