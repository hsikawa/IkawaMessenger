import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient implements Runnable {
	@Override
	public void run() {
		String server = "127.0.0.1";

		byte[] byteBuffer = null;

		int servPort = 8080;

		Socket socket = null;
		try {
			socket = new Socket(server, servPort);


			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));

			String line;

			while (true) {
				System.out.println("input message:");
				line = reader.readLine();

				if(line.equals("quit")){
					break;
				}

				byteBuffer = line.getBytes();

				out.write(byteBuffer);

				int totalBytesRcvd = 0;
				int bytesRcvd;

				while (totalBytesRcvd < byteBuffer.length) {
					if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd,
							byteBuffer.length - totalBytesRcvd)) == -1) {
						throw new SocketException(
								"Connection closed prematurely");
					}
					totalBytesRcvd += bytesRcvd;

					System.out.println("Receive: " + new String(byteBuffer));
				}
			}

			socket.close();
		} catch (IOException e) {
			System.out.println(e + ":" + e.getMessage());
		}finally{
			try {
				socket.close();
			} catch (IOException e) {

			}
		}
	}
}
