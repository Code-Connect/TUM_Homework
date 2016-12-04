package blatt07;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Tailrec1Test {
    private static final int Random = randomInt(Integer.MAX_VALUE);
    private int expected;
    private int input;

    public Tailrec1Test(int expected, int input) {
        this.expected = expected;
        this.input = input;
    }

    @Parameterized.Parameters (name = "in: {1}; out: {0}")
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {23, 12345},
                {Tailrec1.frec(Random), Random},
                {Tailrec1.frec(-Random-1), -Random-1},
        });
    }

    private static int randomInt(int max) {
        return (int) (Math.random() * max);
    }

    @Test
    public void ftailrec() throws Exception {
        Assert.assertEquals(expected, Tailrec1.ftailrec(input));
    }
}