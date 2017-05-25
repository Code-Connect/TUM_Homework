package gad17.blatt04;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class HashStringTest {
    private static final int RANGE = 100;
    ArrayList[] as;
    private HashString test;

    private int getSize() {
        return test.size;
    }

    private ArrayList<Integer> getVectors() {
        Assert.assertNotNull(test.vectors);
        return test.vectors;
    }

    @Before
    public void setup() {
        try {
            test = new HashString(307);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    public void constructor_input0_throwsException() {
        new HashString(0);
    }

    @Test(expected = Exception.class)
    public void constructor_inputNegative_throwsException() {
        new HashString(-1);
    }

    @Test(expected = Exception.class)
    public void constructor_inputNotPrime_throwsException() {
        new HashString(10);
    }

    @Test
    public void constructor_input1_saveMIs1() {
        test = new HashString(2);
        assertM();
    }

    private void assertM() {
        Assert.assertEquals(2, getSize());
    }

    @Test
    public void constructor_givenValid_thenCreateEmptyList() {
        assertA(0);
    }

    @Test(expected = Exception.class)
    public void hash_inputNull_throwsException() throws Exception {
        test.hash(null);
    }

    @Test(expected = Exception.class)
    public void hash_inputEmptyString_throwsException() throws Exception {
        test.hash("");
    }

    @Test
    public void hash_input1_givenA1_returns1() throws Exception {
        add(1);
        assertHash('1', "1");
    }

    private void assertHash(int expected, String key) {
        Assert.assertEquals(expected, test.hash(key));
    }

    @Test
    public void hash_givenAIsEmpty_thenASizeIs1() throws Exception {
        test.hash("1");
        assertA(1);
    }

    private void assertA(int expected) {
        Assert.assertNotNull(getVectors());
        Assert.assertEquals(expected, getVectors().size());
    }

    @Test
    public void hash_givenAIsEmpty_thenA1IsLessThanM() throws Exception {
        test.hash("1");
        Assert.assertEquals(true, getVectors().get(0) < getSize());
    }

    @Test
    public void hash_input1_2_3_givenAIsEmpty_thenASizeIs3() throws Exception {
        test.hash("123");
        assertA(3);
    }

    @Test
    public void hash_input123_givenAIs3_2_1_returns298() throws Exception {
        add(3, 2, 1);
        assertHash(298, "123");
    }


    @Test
    public void hash_input1234_givenAIs4_3_2_1_returns193() {
        add(4, 3, 2, 1);
        Assert.assertEquals(193, test.hash("1234"));
    }

    private void add(int... i) {
        for (int j : i) getVectors().add(j);
    }

    @Test
    public void hash_calledWithSameKey2Times_returnsSameValue() throws Exception {
        int actual1 = test.hash("123");
        int actual2 = test.hash("123");
        Assert.assertEquals(actual1, actual2);
    }

    @Test
    public void hash_calledWithDifferentKey2Times_returnsDifferentValues() throws Exception {
        int actual1 = test.hash("124");
        int actual2 = test.hash("123");
        Assert.assertNotEquals(actual1, actual2);
    }

    @Test
    @Ignore//TODO
    public void hash_calledBy2Threads_returnsGenerateSameA() throws Exception {
        as = new ArrayList[RANGE];
        Thread[] ts = new Thread[as.length];
        for (int i = 0; i < as.length; i++)
            ts[i] = buildThread(buildKey(), i);

        startAndJoin(ts);

        assertAsAreDifferent();
    }

    private String buildKey() {
        String key = "";
        for (int j = 1; j < RANGE; j++) {
            key += j;
        }
        return key;
    }

    private void startAndJoin(Thread[] ts) throws InterruptedException {
        for (Thread thread : ts) {
            thread.start();
        }
        for (Thread thread : ts) {
            thread.join();
        }
    }

    private void assertAsAreDifferent() {
        for (ArrayList a : as) {
            Assert.assertArrayEquals(as[0].toArray(), a.toArray());
        }
    }

    private Thread buildThread(String key, int index) {
        return new Thread(() -> {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(index);
            test.hash(key);
            as[index] = getVectors();
        });
    }

    @Test
    public void hash_input2Keys_givenSameLength_generatesKeysOnce() throws Exception {
        test.hash("124");
        test.hash("123");
        assertA(3);
    }

    @Test
    public void hash_input2TimesSameKeys_thenGeneratesDifferentAs() throws Exception {
        test.hash("123");
        List a1 = getVectors();

        test.vectors = new ArrayList<>();
        test.hash("123");
        List a2 = getVectors();

        Assert.assertNotEquals(a1, a2);
    }
}
