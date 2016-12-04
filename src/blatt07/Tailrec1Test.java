package blatt07;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class Tailrec1Test {

    private static final int DECREASE = randomInt(100000);

    @Test
    public void testFtailrec_given12345_returns23() {
        Assert.assertEquals(23, Tailrec1.ftailrec(12345));
    }

    @Test
    public void testFtailrec_givenAllPossibilities() {
        for (int i = Integer.MAX_VALUE ;i >= 0; i-= DECREASE)
            assertPositiveAndNegativ(i);
    }

    private void assertFtailrec(int input) {
        Assert.assertEquals(getMessage(input, Tailrec1.frec(input)), getMessage(input, Tailrec1.ftailrec(input)));
    }

    private String getMessage(int input, int out) {
        return "in: "+input+"; out: "+ out;
    }

    @Test
    public void testFtailrec_givenRandom() {
        int random = randomInt(Integer.MAX_VALUE);
        assertPositiveAndNegativ(random);
    }

    static private int randomInt(int max) {
        return (int) (Math.random() * max);
    }

    private void assertPositiveAndNegativ(int input) {
        assertFtailrec(input);
        assertFtailrec(-input - 1);
    }
}
