// Luis Cortes
// CS 380
// Project 4


public class Ipv6 {

	private static short dataSize = 1; 

	private byte version; // Version (4 bits)
	private byte trafficClass; // (8 bits)
	private int flowLabel; // (20 bits)
	private short payloadLen; // Length of payload, excluding header(16 bits)
	private byte nextHeader; // (8 bits)
	private byte hopLimit; // (8 bits)
	private byte[] sourAddr; // Source Address (128  bits)
	private byte[] destAddr; // Destination Address (128 bits)

	private byte[] payLoadData; 
	private byte[] packet; // Hold header and payload data in bytes

	/**
	 *	Initialize all assumed information
	 */
	public Ipv6() {
		this.dataSize *= 2;

		this.version = 0x6; // Version number
		this.trafficClass = 0x00; // Not implemented 
		this.flowLabel = 0x0; // Not implemented
		this.payloadLen = this.dataSize; // Start at 2 bytes
		this.nextHeader = 0x11; // Set to UDP value
		this.hopLimit = 0x14; // Set to 20  
		this.sourAddr = new byte[16]; 
		this.destAddr = new byte[16];

		this.payLoadData = new byte[this.payloadLen];

		ipMapping(); // Map ipv4 addresses to ipv6
		populateData();
		makePacket();

	}

	/**
	 *	Return size of payload
	 */
	public int size() {
		return this.payloadLen+40;
	}

	public int loadSize() {
		return this.payloadLen;
	}

	/**
	 *	Return the packet in an array of bytes
	 */
	public byte[] getPacket() {
		return this.packet;
	}

	/**
	 *	Make a packet of bytes
	 */	
	private void makePacket() {
		// Size, 40 bytes (fixed) plus payload
		this.packet = new byte[this.payloadLen + 40];
		int index = 0; 

		// Put together version and first half of traffic class
		this.packet[index++] = (byte) (this.version << 4);

		// Second half of traffic class and first 8 bits from flow label
		this.packet[index++] = 0x00; 

		// Remaining bits [16:31] make up two bytes
		this.packet[index++] = 0x00; 
		this.packet[index++] = 0x00; 

		// Payload len 
		byte firstHalf = (byte) (this.payloadLen >> 8);
		this.packet[index++] = firstHalf;

		byte secondHalf = (byte)  (this.payloadLen & 0xFF);
		this.packet[index++] = secondHalf;

		// System.out.println(Integer.toHexString(firstHalf & 0xFF));
		// System.out.println(Integer.toHexString(secondHalf & 0xFF));

		// next header
		this.packet[index++] = this.nextHeader;

		// hop limit
		this.packet[index++] = this.hopLimit;

		// Source address
		for (byte data : sourAddr) {
			this.packet[index++] = data;
		}

		// Destinatino address
		for (byte data : destAddr) {
			this.packet[index++] = data; 
		}

		// Payload
		for (byte data : payLoadData) {
			this.packet[index++] = data;
		}

	}

	/**
	 *	Ipv4 mapped Ipv6 address 
	 */
	private void ipMapping() {
		// 128 bits --> 16 bytes	

		// Set first 12 bytes to 0x00
		for (int i =0; i < 10; i++) {
			destAddr[i] = (byte) 0x00; 
			sourAddr[i] = (byte) 0x00; 
		}

		// prefix ipv4 address 
		destAddr[10] = (byte) 0xFF; 
		destAddr[11] = (byte) 0xFF;

		sourAddr[10] = (byte) 0xFF; 
		sourAddr[11] = (byte) 0xFF; 

		// 52.37.88.154
		destAddr[12] = (byte) 0x34; // 52
		destAddr[13] = (byte) 0x25; // 37
		destAddr[14] = (byte) 0x58; // 88
		destAddr[15] = (byte) 0x9A; // 154

		// 10.47.150.35
		sourAddr[12] = (byte) 0x0A; // 10
		sourAddr[13] = (byte) 0x2F; // 47
		sourAddr[14] = (byte) 0x96; // 150
		sourAddr[15] = (byte) 0x23; // 35

	}

	/**
	 *	Populate data for payload
	 */
	private void populateData() {

		// Fill payload segment one byte at a time
		for (int i=0; i<this.payloadLen; i++) {
			byte data = 0x00; 
			this.payLoadData[i] = data; 
		}
	}

	public String toString() {
		String x = "";
		int count = 1; 
		for (byte data : packet) {
			x += Integer.toHexString(data & 0xFF);
			x += " ";
		}
		return x;
	}
}