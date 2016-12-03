package blatt07;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class Tailrec1Test {

    @Test
    public void testFtailrec() {
        Random r = new Random();
        for (int i = -12345; i <= 12345; i++) {
            Assert.assertEquals(Tailrec1.frec(i), Tailrec1.ftailrec(i));
        }
    }
}
