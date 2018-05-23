package grnvs18.assignment2;

/* DO NOT!!! put a package statement here, that would break the build system */
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Assignment2 {

	/*====================================TODO===================================*/
	/* Put your required state variables here */
	static byte[] mymac;

	/*===========================================================================*/

	public static void run(GRNVS_RAW_Mock sock, int frames) {
		byte[] recbuffer = new byte[1514];
		int length;
		Timeout timeout = new Timeout(10000);

		/*====================================TODO===================================*/
		/* If you want to set up any data/counters before the receive loop,
		 * this is the right location
		 */
		mymac = sock.getMac();

		/*===========================================================================*/

		/* keep this AS IS! the tester uses this to avoid races */
		System.out.format("I am ready!\n");

		/*====================================TODO===================================*/
		/* Update loop condition */
		for (; true; ) {
			/*===========================================================================*/
			length = sock.read(recbuffer, timeout);
			if (length == 0) {
				System.err.format("Timed out, this means there was nothing to receive. Do you have a sender set up?\n");
				break;
			}
			/*====================================TODO===================================*/
			/* This is the receive loop, 'recbuffer' will contain the received
			 * frame. 'length' tells you the length of what you received.
			 * Anything that should be done with every frame that's received
			 * should be done here.
			 */

			/*===========================================================================*/
		}
		/*====================================TODO===================================*/
		/* Print your summary here */

		/*===========================================================================*/
	}


	public static void main(String[] argv) {
		Arguments args = new Arguments(argv);
		GRNVS_RAW sock = null;
		try{
			sock = new GRNVS_RAW(args.iface, 3);
			run(sock, args.frames);
			sock.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
