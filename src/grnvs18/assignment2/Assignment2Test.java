package grnvs18.assignment2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

@RunWith(Parameterized.class)
public class Assignment2Test {


    public static final byte ff = -0b0000001;
    private final GRNVS_RAW_Mock sock;
    private final int frames;
    private final String expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0 of them were for me\n" +
                        "0 of them were multicast\n" +
                        "IPv4 accounted for 0.0% and IPv6 for 0.0% of traffic\n", new byte[6], new byte[0][]},
                {"0x0000: 1 frames, 1514 bytes\n" +
                        "1 of them were for me\n" +
                        "0 of them were multicast\n" +
                        "IPv4 accounted for 0.0% and IPv6 for 0.0% of traffic\n",
                        new byte[6], new byte[][]{buildFrame(new Byte[6], new Byte[6], new Byte[2], new Byte[1500])}},
                {"0x0000: 2 frames, 3028 bytes\n" +
                        "2 of them were for me\n" +
                        "0 of them were multicast\n" +
                        "IPv4 accounted for 0.0% and IPv6 for 0.0% of traffic\n",
                        new byte[6], new byte[][]{
                        buildFrame(new Byte[6], new Byte[6], new Byte[2], new Byte[1500]),
                        buildFrame(new Byte[6], new Byte[6], new Byte[2], new Byte[1500])}},
                {"0x0000: 2 frames, 3028 bytes\n" +
                        "1 of them were for me\n" +
                        "1 of them were multicast\n" +
                        "IPv4 accounted for 0.0% and IPv6 for 0.0% of traffic\n",
                        new byte[6], new byte[][]{
                        buildFrame(new Byte[6], new Byte[6], new Byte[2], new Byte[1500]),
                        buildFrame(new Byte[]{ff, ff, ff, ff, ff, ff}, new Byte[6], new Byte[2], new Byte[1500])}},
                {"0x0000: 2 frames, 3028 bytes\n" +
                        "1 of them were for me\n" +
                        "1 of them were multicast\n" +
                        "IPv4 accounted for 0.0% and IPv6 for 0.0% of traffic\n",
                        new byte[]{2,2,2,2,2,2}, new byte[][]{
                        buildFrame(new Byte[]{2,2,2,2,2,2}, new Byte[6], new Byte[2], new Byte[1500]),
                        buildFrame(new Byte[]{1,0,0,0,0,0}, new Byte[6], new Byte[2], new Byte[1500])}},
                {"0x0800: 1 frames, 1514 bytes\n" +
                        "1 of them were for me\n" +
                        "0 of them were multicast\n" +
                        "IPv4 accounted for 100.0% and IPv6 for 0.0% of traffic\n",
                        new byte[6], new byte[][]{buildFrame(new Byte[6], new Byte[6], new Byte[]{8, 0}, new Byte[1500])}},
                {"0x86dd: 1 frames, 1514 bytes\n" +
                        "1 of them were for me\n" +
                        "0 of them were multicast\n" +
                        "IPv4 accounted for 0.0% and IPv6 for 100.0% of traffic\n",
                        new byte[6], new byte[][]{buildFrame(new Byte[6], new Byte[6], new Byte[]{-122, -35}, new Byte[1500])}},
                {"0x0800: 3 frames, 192 bytes\n" +
                        "0x0806: 3 frames, 209 bytes\n" +
                        "0x86dd: 4 frames, 256 bytes\n" +
                        "3 of them were for me\n" +
                        "4 of them were multicast\n" +
                        "IPv4 accounted for 29.2% and IPv6 for 39.0% of traffic\n",
                        new byte[6], new byte[][]{
                        buildFrame(new Byte[6], new Byte[6], new Byte[]{8, 0}, new Byte[58]),
                        buildFrame(new Byte[6], new Byte[6], new Byte[]{8, 0}, new Byte[46]),
                        buildFrame(new Byte[6], new Byte[6], new Byte[]{8, 0}, new Byte[46]),
                        buildFrame(new Byte[]{2,2,2,2,2,2}, new Byte[6], new Byte[]{8, 6}, new Byte[50]),
                        buildFrame(new Byte[]{2,2,2,2,2,2}, new Byte[6], new Byte[]{8, 6}, new Byte[50]),
                        buildFrame(new Byte[]{2,2,2,2,2,2}, new Byte[6], new Byte[]{8, 6}, new Byte[67]),
                        buildFrame(new Byte[]{ff, ff, ff, ff, ff, ff}, new Byte[6], new Byte[]{-122, -35}, new Byte[50]),
                        buildFrame(new Byte[]{ff, ff, ff, ff, ff, ff}, new Byte[6], new Byte[]{-122, -35}, new Byte[50]),
                        buildFrame(new Byte[]{ff, ff, ff, ff, ff, ff}, new Byte[6], new Byte[]{-122, -35}, new Byte[50]),
                        buildFrame(new Byte[]{ff, ff, ff, ff, ff, ff}, new Byte[6], new Byte[]{-122, -35}, new Byte[50]),
                }},
        });
    }


    public Assignment2Test(String expected, byte[] mac, byte[]... frames) {
        this.sock = new GRNVS_RAW_Mock(mac, frames);
        this.frames = frames.length;
        this.expected = expected;
    }


    @Test(timeout = 100)
    public void run() {
        String actual = runCaptureSysOut(Assignment2::run, sock, frames);

        Assert.assertEquals("I am ready!\n" + expected, actual);
    }

    private static String runCaptureSysOut(BiConsumer<GRNVS_RAW_Mock, Integer> fun, GRNVS_RAW_Mock sock, int frames) {
        PrintStream original = System.out;
        String captured = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
            System.setOut(ps);
            fun.accept(sock, frames);
            captured = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            System.setOut(original);
        }
        return captured;
    }

    private static byte[] buildFrame(Byte[] destinationMac, Byte[] sourceMac, Byte[] type, Byte[] data) {
        return buildArray(destinationMac, sourceMac, type, data);
    }

    private static byte[] buildArray(Byte[]... bytes) {
        List<Byte> frame = new ArrayList<>();
        for (Byte[] bs : bytes)
            frame.addAll(Arrays.asList(bs));

        byte[] out = new byte[frame.size()];
        for (int i = 0; i < out.length; i++)
            out[i] = frame.get(i) == null ? 0 : frame.get(i);

        return out;
    }
}