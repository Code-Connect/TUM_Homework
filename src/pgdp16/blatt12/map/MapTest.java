package pgdp16.blatt12.map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MapTest {
    public static final int WAIT_MS = 100;
    private static final int RANGE = 100;
    private final Object expected;
    private final Fun f;
    private final Object[] a;
    private final Object[] b;
    private final int n;

    public MapTest(Object expected, Fun f, Object[] a, Object[] b, int n) {
        this.expected = expected;
        this.f = f;
        this.a = a;
        this.b = b;
        this.n = n;
    }

    @Parameterized.Parameters
            (name = "n: {4}; exp: {0}")
    public static Collection<Object[]> data() {
        Object[] inputs = buildInputs(randomInt());
        return Arrays.asList(new Object[][]{
                {new IllegalArgumentException(), null, new Object[1], new Object[1], 1},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, null, new Object[1], 1},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, new Object[1], null, 1},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, new Object[0], new Object[0], 1},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, new Object[0], new Object[1], 1},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, new Object[1], new Object[0], 1},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, new Object[10], new Object[3], 1},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, new Object[1], new Object[1], 0},
                {new IllegalArgumentException(), (Fun<Object, Object>) x -> x, new Object[1], new Object[1], -1},

                {new Object[]{1}, (Fun<Object, Object>) x -> x, new Object[]{1}, new Object[1], 3},

                {new Object[]{2}, (Fun<Object, Object>) x -> x, new Object[]{2}, new Object[1], 1},
                {new Object[]{2}, (Fun<Object, Object>) x -> x, new Object[]{2}, new Object[1], 2},

                {new Integer[]{2, 4}, (Fun<Integer, Integer>) x -> x * 2, new Object[]{1, 2}, new Object[2], 2},
                {new Integer[]{2, 4}, (Fun<Integer, Integer>) x -> x * 2, new Object[]{1, 2}, new Object[2], 3},
                {new Integer[]{1, 4}, (Fun<Integer, Integer>) x -> f(x), new Object[]{1, 2}, new Object[2], 3},
                {new Integer[]{1, 4, 9, 16, 25, 36, 49}, (Fun<Integer, Integer>) x -> f(x), new Object[]{1, 2, 3, 4, 5, 6, 7}, new Object[7], 5},

                {buildExpectations(inputs), (Fun<Integer, Integer>) x -> f(x), inputs, new Object[inputs.length], randomInt()},
        });
    }

    private static int randomInt() {
        return (int) (Math.random() * RANGE);
    }

    private static Object[] buildInputs(int size) {
        return Arrays.stream(new Integer[size])
                .map(integer -> randomInt()).toArray();
    }

    private static Object[] buildExpectations(Object[] input) {
        return Arrays.stream(input).
                map(x -> (int) x * (int) x).toArray();
    }

    private static int f(int x) {
        sleepFor(WAIT_MS);
        return x * x;
    }

    public static void sleepFor(long milliseconds) {
        try {
            Thread exe = new Thread(() -> {
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            exe.start();
            exe.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Test
    public void map_givenParameters() throws Exception {
        Exception thrown = null;
        try {
            Map.map(f, a, b, n);
        } catch (Exception e) {
            thrown = e;
        }
        if (thrown != null)
            Assert.assertEquals("\na: " + Arrays.toString(a) +
                            "\nb: " + Arrays.toString(b) +
                            "\nn: " + n,
                    expected.toString(), thrown.toString());
        else {
            Assert.assertTrue("you shall throw an Exception",
                    expected instanceof Object[]);
            Assert.assertArrayEquals("actual: " + Arrays.toString(b) + "\n",
                    (Object[]) expected, b);
        }
    }


}
