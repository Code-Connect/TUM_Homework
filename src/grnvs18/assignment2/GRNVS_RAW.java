package grnvs18.assignment2;

public class GRNVS_RAW extends GRNVS_RAW_Mock{
	static {
		System.loadLibrary("GRNVS");
	}

	private final int handle;

	private final native int getSocket(String dev, int level);
	private final native int write_(int handle, byte[] buffer, int size);
	private final native int read_(int handle, byte[] buffer, Timeout time);
	private final native int close_(int handle);
	private final native byte[] mac_(int handle);
	private final native byte[] ip_(int handle);
	private final native byte[] ip6_(int handle);

	private static final native void hexdump_(byte[] buffer, int size);
	private static final native byte[] checksum_(byte[] hdr, int hdrOffset,
			byte[] payload, int payloadOffset, int payloadLength);
	private static final int SOCK_RAW = 3;
	private static final int SOCK_DGRAM = 2;

	public GRNVS_RAW(String dev, int level) {
		handle = getSocket(dev, level);
	}

	/* Write to the network device
	 * @param buffer A bytebuffer containing the frame/packet
	 * @size the first size byte will be sent
	 *
	 * @result The number of bytes sent
	 */
	public final int write(byte[] buffer, int size) {
		return write_(handle, buffer, size);
	}

	/* Read from the network device
	 * @param buffer The buffer to read into
	 * @param Timeout The timeout that should be used for the read
	 *        it will be updated
	 *
	 * @result The number of bytes sent
	 */
	public final int read(byte[] buffer, Timeout time) {
		return read_(handle, buffer, time);
	}

	/* Get the ip address of the device for this socket */
	public final byte[] getIP() {
		return ip_(handle);
	}

	/* Get an ipv6 address of the device for this socket */
	public final byte[] getIPv6() {
		return ip6_(handle);
	}

	/* Get the mac address of the device for this socket */
	public final byte[] getMac() {
		return mac_(handle);
	}

	public final int close() {
		return close_(handle);
	}

	/* Dump a hex representation of the buffers content to stderr */
	public static final void hexdump(byte[] buffer, int size) {
		GRNVS_RAW.hexdump_(buffer, size);
	}

	/* Compute the icmpv6 checksum for the packet.
	 * @param hdr The buffer containing the ipv6 header
	 * @param hdrOffset the offset of the header in the buffer
	 * @param payload The buffer containing the icmp data (ip payload)
	 * @param payloadOffset the offset of the payload in the buffer
	 * @param payloadLength The length of the ip payload
	 *
	 * @return A byte array containing the checksum, in the same order it
	 *         should be in the output buffer
	 */
	public static final byte[] checksum(byte[] hdr, int hdrOffset,
			byte[] payload, int payloadOffset, int payloadLength) {
		return GRNVS_RAW.checksum_(hdr, hdrOffset, payload,
						payloadOffset, payloadLength);
	}

	protected void finalize() {
		close_(handle);
	}



}
