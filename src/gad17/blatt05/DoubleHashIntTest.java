package gad17.blatt05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class DoubleHashIntTest {
    private DoubleHashInt test;

    private int getSize() {
        return getFiled("size");
    }

    private int getA() {
        return getFiled("a");
    }

    private int getB() {
        return getFiled("b");
    }

    private void setA(int a) {
        String name = "a";
        setFiled(name, a);
    }

    private void setB(int b) {
        setFiled("b", b);
    }

    private int getFiled(String name) {
        try {
            Field field = test.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return (int) field.get(test);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
        throw new RuntimeException();
    }

    private void setFiled(String name, int value) {
        try {
            Field field = test.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(test, value);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @Before
    public void setup() {
        test = new DoubleHashInt(3);
    }

    @Test
    public void constructor_input3_thenSizeIs3() {
        test = new DoubleHashInt(3);
        Assert.assertEquals(3, getSize());
    }

    @Test
    public void constructor_inputValid_thenInitRandom_a_b() {
        for (int i = 0; i < 100; i++) {
            test = new DoubleHashInt(3);
            assertABValid();
        }
    }

    private void assertABValid() {
        Assert.assertNotNull(getA());
        Assert.assertNotNull(getB());
        Assert.assertTrue(0 < getA());
        Assert.assertTrue(0 < getB());
        Assert.assertTrue(getA() < getSize());
        Assert.assertTrue(getB() < getSize());
    }

    @Test
    public void hash_input0_returns0() {
        Assert.assertEquals(0, test.hash(0));
    }

    @Test
    public void hash_input1_givenAIs2_returns2() {
        setA(2);
        Assert.assertEquals(2, test.hash(1));
    }

    @Test
    public void hash_input1_givenAIs4_givenSize3_returns1() {
        setA(4);
        Assert.assertEquals(1, test.hash(1));
    }

    @Test
    public void hashTick_input0_returns1() {

        Assert.assertEquals(1, test.hashTick(0));
    }

    @Test
    public void hashTick_input1_givenAIs2_returns1() {
        setB(2);
        Assert.assertEquals(1, test.hashTick(1));
    }

    @Test
    public void hashTick_input1_givenAIs4_givenSize3_returns1() {
        setB(4);
        Assert.assertEquals(1, test.hashTick(1));
    }

    @Test
    public void hashTick_input0_givenSize3_returns1() {
        Assert.assertEquals(1, test.hashTick(0));
    }

    @Test
    public void hashTick_input2_givenSize3_returns1() {
        Assert.assertEquals(1, test.hashTick(2));
    }

    @Test
    public void HashTick_input5_givenBIs1_givenSize3_return2() {
        setB(1);
        Assert.assertEquals(2, test.hashTick(5));
    }
}
