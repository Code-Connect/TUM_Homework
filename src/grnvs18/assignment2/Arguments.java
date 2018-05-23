package grnvs18.assignment2;

class Arguments {
	public int frames = 10;
	public String iface = "eth0";
	public int timeout = 10;

	private void printHelp() {
		System.out.println("Analyze and count raw Ethernet frames");
		System.out.println("Usage: ethstats [-i|--interface interface] NUM");
		System.out.println("-i/--interface: The interface on which the frames should be read from");
		System.out.println("NUM:            The number of frames to read before printing summary");
		System.out.println("-?/-h/--help    Print this help message");
	}

	Arguments(String[] argv) {
		//For_each would be nice, but we may have to skip/access next
		int i, j = 0;
		String[] fargs = new String[1];
		for(i = 0; i < argv.length; ++i) {
			String arg = argv[i];
			switch(arg) {
				case "-h":
				case "-?":
				case "--help":
					printHelp();
					System.exit(0);
					break;
				case "-i":
				case "--interface":
					iface = argv[++i];
					break;
				default:
					if(j == fargs.length) {
						System.out.println("Encountered an unexpected number of positional arguments");
						System.exit(1);
					}
					fargs[j++] = arg;
					break;
			}
		}
		if(fargs[0] != null) {
			try {
				this.frames = Integer.parseInt(fargs[0]);
			} catch (NumberFormatException e) {
				System.err.format("\"%s\" is not a valid number.\n", fargs[0]);
				this.printHelp();
				System.exit(1);
			}
		}
	}
}
