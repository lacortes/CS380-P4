
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
			// System.out.println(ip);

			// Connect to server
			Socket socket = new Socket(ip, 38004);
			System.out.println("Connected to server");

			PrintStream outStream = new PrintStream(socket.getOutputStream(), true);
			InputStream is = socket.getInputStream();

			for (int i = 0; i < 12; i++) {
				Ipv6 datagram = new Ipv6();
				byte[] packet = new byte[datagram.size()]; 
				packet = datagram.getPacket();

				byte[] response = new byte[4]; // Hold response 

				System.out.println("data length: "+ (datagram.size()-40));
				outStream.write(packet, 0, packet.length);

				is.read(response);

				// Show response
				System.out.print("Response: 0x");
				for (byte data : response) {
					System.out.print(Integer.toHexString(data & 0xFF));
				}
				System.out.println("\n");

			}

		} catch (Exception e) {e.printStackTrace();}
	}
}