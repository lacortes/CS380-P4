// Luis Cortes
// CS 380
// Project 4

public class Ipv6 {

	private byte version; // Version (4 bits)
	private byte trafficClass; // (8 bits)
	private int flowLabel; // (20 bits)
	private short payloadLen = 1; // Length of payload, excluding header(16 bits)
	private byte nextHeader; // (8 bits)
	private byte hopLimit; // (8 bits)
	private byte[] sourAddr; // Source Address (128  bits)
	private byte[] destAddr; // Destination Address (128 bits)

	private byte[] payLoadData; 

	/**
	 *	Initialize all assumed information
	 */
	public Ipv6() {
		this.version = 0x6; // Version number
		this.trafficClass = 0x00; // Not implemented 
		this.flowLabel = 0x0; // Not implemented
		this.payloadLen *= 2; // Start at 2 bytes
		this.nextHeader = 0x11; // Set to UDP value
		this.hopLimit = 0x14; // Set to 20  
		this.sourAddr = new byte[16]; 
		this.destAddr = new byte[16];

		this.payLoadData = new byte[this.payloadLen];

	}

	/**
	 *	Set ipv4 address to ipv6
	 */
	public void setDestIp() {
		// 52.37.88.154	
		
	}

	/**
	 *	Populate data for payload
	 */
	private void populateData() {

		// Fill payload segment one byte at a time
		for (int i=0; i<this.payloadSize; i++) {
			byte data = 0x00; 
			this.payLoadData[i] = data; 
		}
	}
}