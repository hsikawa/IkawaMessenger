package jp.co.heartsoft.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.co.heartsoft.ikawaMessage.tcpMessage.TCPClient;
import jp.co.heartsoft.ikawaMessage.tcpMessage.TCPServer;
import jp.co.heartsoft.udpMesage.UDPClient;
import jp.co.heartsoft.udpMesage.UDPServer;

public class ikawaMessage {

	public static void main(String[] args) {
		System.out.println("UDPサーバ起動");
		Runnable udpServerRunnable = new UDPServer();
		new Thread(udpServerRunnable).start();

		System.out.println("TCPサーバ起動");
		Runnable tcpServerRunnable = new TCPServer();

		try {
			new Thread(tcpServerRunnable).start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));

			String line;

			while (true) {
				line = reader.readLine();

				if (line.equals("quit")) {
					break;
				}

				String[] comand = line.split(" ", 0);
				if (comand[0].equals("get")) {
					UDPClient uClient = new UDPClient();
					uClient.get();
				} else if (comand[0].equals("send")) {
					TCPClient tClient = new TCPClient();
					tClient.send(comand[1], 8081, comand[2]);
				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
