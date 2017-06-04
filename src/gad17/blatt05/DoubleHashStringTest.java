package gad17.blatt05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DoubleHashStringTest {

    private DoubleHashString test;

    private int getSize() {
        return (int) getFiled("size");
    }

    private ArrayList getAVector() {
        return (ArrayList) getFiled("a");
    }

    private ArrayList getBVector() {
        return (ArrayList) getFiled("b");
    }

    private Object getFiled(String name) {
        try {
            Field field = test.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(test);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
        throw new RuntimeException();
    }

    @Before
    public void setup() {
        test = new DoubleHashString(307);
    }

    @Test
    public void constructor_input2_saveSize2() {
        test = new DoubleHashString(2);
        Assert.assertEquals(2, getSize());
    }

    @Test
    public void constructor_givenValid_thenInitA() {
        Assert.assertNotNull(getAVector());
        Assert.assertEquals(true, getAVector().isEmpty());
    }

    @Test
    public void constructor_givenValid_thenInitB() {
        Assert.assertNotNull(getBVector());
        Assert.assertEquals(true, getBVector().isEmpty());
    }


    @Test
    public void hash_givenAIsEmpty_thenASizeIs1() {
        test.hash("1");
        assertSize(1, getAVector());
    }

    private void assertSize(int expected, ArrayList vector) {
        Assert.assertNotNull(getAVector());
        Assert.assertEquals(expected, vector.size());
    }

    @Test
    @Ignore //Optional
    public void hash_givenAIsEmpty_thenA1IsLessThanSize() {
        test.hash("1");
        Assert.assertNotNull(getAVector());
        Assert.assertEquals(true, (long) getAVector().get(0) < getSize());
    }

    @Test
    public void hash_input123_givenAIsEmpty_thenASizeIs3() {
        test.hash("123");
        assertSize(3, getAVector());
    }

    @Test
    public void hashTick_givenAIsEmpty_thenASizeIs1() {
        test.hashTick("1");
        assertSize(1, getBVector());
    }

    @Test
    @Ignore //Optional
    public void hashTick_givenBIsEmpty_thenA1IsLessThanSize() {
        test.hashTick("1");
        Assert.assertNotNull(getBVector());
        Assert.assertEquals(true, (long) getBVector().get(0) < getSize());
    }

    @Test
    public void hashTick_input123_givenAIsEmpty_thenASizeIs3() {
        test.hashTick("123");
        assertSize(3, getBVector());
    }

    private void addA(long... i) {
        Assert.assertNotNull(getAVector());
        for (long j : i)
            getAVector().add(j);
    }

    private void addB(long... i) {
        Assert.assertNotNull(getBVector());
        for (long j : i)
            getBVector().add(j);
    }

    @Test
    public void hash_input1_givenA1_returns1() {
        addA(1);
        Assert.assertEquals('1', test.hash("1"));
    }

    @Test
    public void hash_input123_givenAIs3_2_1_returns298() {
        addA(3, 2, 1);
        Assert.assertEquals(298, test.hash("123"));
    }

    @Test
    public void hash_input1234_givenAIs4_3_2_1_returns193() {
        addA(4, 3, 2, 1);
        Assert.assertEquals(193, test.hash("1234"));
    }

    @Test
    public void hashTick_input1_givenA1_returns2() {
        addB(1);
        Assert.assertEquals('2', test.hashTick("1"));
    }

    @Test
    public void hashTick_input123_givenAIs3_2_1_returns299() {
        addB(3, 2, 1);
        Assert.assertEquals(299, test.hashTick("123"));
    }

    @Test
    public void hashTick_input1234_givenAIs4_3_2_1_returns195() {
        addB(4, 3, 2, 1);
        Assert.assertEquals(195, test.hashTick("1234"));
    }

}
