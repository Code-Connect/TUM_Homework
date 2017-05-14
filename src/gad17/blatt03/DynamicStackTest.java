package gad17.blatt03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class DynamicStackTest {
    DynamicStack test;

    @Before
    public void setUp() throws Exception {
        test = new DynamicStack(2, 4);
    }

    private void setLength(int l) throws Exception {
        Field length = test.getClass().getDeclaredField("length");
        length.setAccessible(true);
        length.set(test, l);
    }

    private DynamicArray getArray() throws Exception {
        Field array = test.getClass().getDeclaredField("dynArr");
        array.setAccessible(true);
        return (DynamicArray) array.get(test);
    }

    @Test(expected = Exception.class)
    public void popBack_givenInitialState_throwsException() throws Exception {
        test.popBack();
    }

    @Test
    public void getLength_returns0() throws Exception {
        assertLength(0);
    }

    private void assertLength(int expected) {
        Assert.assertEquals(expected, test.getLength());
    }

    @Test
    public void pushBack_givenInitialState_thenLengthIs1() throws Exception {
        test.pushBack(10);
        assertLength(1);
    }

    @Test
    public void popBack_givenLengthIs10_thenLengthIs9() throws Exception {
        push(1, 2, 3);
        test.popBack();
        assertLength(2);
    }

    @Test
    public void pushBack_givenInitialState_addsToArray() throws Exception {
        test.pushBack(10);
        Assert.assertEquals(10, getArray().get(0));
    }

    @Test
    public void pushBack_push3Times_addsToArray() throws Exception {
        push(10, 11, 12);
        assertPush(10, 11, 12);
    }

    private void push(int... ints) {
        for (int anInt : ints) test.pushBack(anInt);
    }

    private void assertPush(int... ints) throws Exception {
        Assert.assertEquals(3, test.getLength());
        for (int i = 0; i < ints.length; i++)
            Assert.assertEquals(ints[i], getArray().get(i));
    }

    @Test
    public void popBack_givenPushed12345_popAll() throws Exception {
        push(1, 2, 3, 4, 5);
        assertPop(5, 4, 3, 2, 1);
    }

    private void assertPop(int... ints) {
        for (int anInt : ints)
            Assert.assertEquals(anInt, test.popBack());
    }

    @Test
    public void integration() throws Exception {
        //TODO

    }
}