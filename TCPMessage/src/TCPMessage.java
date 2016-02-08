
public class TCPMessage {
	public static void main(String[] args) {
		System.out.println("TCPメッセージ開始");

		Runnable tcpServerRunnable = new TCPServer();
		new Thread(tcpServerRunnable).start();


		Runnable tcpClientRunnable = new TCPClient();
		new Thread(tcpClientRunnable).start();


	}
}
