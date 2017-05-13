
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;


// Luis Cortes
// CS 380
// Project 4

public class Ipv6Client {
	public static void main(String[] args) {

		try {
			// Get IP Address
			InetAddress address = InetAddress.getByName(
				new URL("http://codebank.xyz").getHost());
			String ip = address.getHostAddress();
			System.out.println(ip);

			// Connect to server
			Socket socket = new Socket(ip, 38004);
			System.out.println("Connected to server");

			PrintStream outStream = new PrintStream(socket.getOutputStream(), true);

		} catch (Exception e) {e.printStackTrace();}
	}
}