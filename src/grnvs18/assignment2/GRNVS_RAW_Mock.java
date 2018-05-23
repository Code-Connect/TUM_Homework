package grnvs18.assignment2;

import java.util.ArrayList;

public class GRNVS_RAW_Mock {
    private byte[] mac;
    private ArrayList<byte[]> frames;

    public GRNVS_RAW_Mock(byte[] mac, byte[]... frames) {
        this.frames = new ArrayList<>();
        for (byte[] frame : frames)
            this.frames.add(frame);
        this.mac = mac;
    }

    public GRNVS_RAW_Mock() {
        this.mac = new byte[6];
        this.frames = new ArrayList<>();
    }

    public byte[] getMac() {
        return mac;
    }

    /**
     * @param recbuffer writes into
     * @param timeout
     * @return length of recbuffer (1514); nothing to receive: returns 0
     */
    public int read(byte[] recbuffer, Timeout timeout) {
        try {
            byte[] f = frames.remove(0);
            for (int i = 0; i < f.length; i++)
                recbuffer[i] = f[i];

            return f.length;
        } catch (Exception e) {
            return 1234;
        }
    }
}
