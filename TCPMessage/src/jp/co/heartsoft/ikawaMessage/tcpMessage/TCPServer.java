package jp.co.heartsoft.ikawaMessage.tcpMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {

	private static final int BUFSIZE = 32;

	@Override
	public void run() {

		int servPort = 8081;
		int recvMsgSize;
		byte[] byteBuffer = new byte[BUFSIZE];


		ServerSocket servSock;
		try {
			servSock = new ServerSocket(servPort);

			for (;;) {
				Socket clntSock = servSock.accept();
				System.out.println("Handing client at"
						+ clntSock.getInetAddress().getHostAddress()
						+ "on port " + clntSock.getPort());

				InputStream in = clntSock.getInputStream();
				OutputStream out = clntSock.getOutputStream();
				while ((recvMsgSize = in.read(byteBuffer)) != -1) {
					System.out.println("server:" + new String(byteBuffer));
					out.write(byteBuffer, 0, recvMsgSize);
					byteBuffer = new byte[BUFSIZE];
				}
				clntSock.close();

			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
