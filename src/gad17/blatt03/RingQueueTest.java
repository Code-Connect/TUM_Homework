package gad17.blatt03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class RingQueueTest {
    RingQueue test;

    @Before
    public void setUp() throws Exception {
        test = new RingQueue(2, 4);
    }

    private void setField(String key, int f) throws Exception {
        Field field = test.getClass().getDeclaredField(key);
        field.setAccessible(true);
        field.set(test, f);
    }

    private Object getField(String key) throws Exception {
        Field field = test.getClass().getDeclaredField(key);
        field.setAccessible(true);
        return field.get(test);
    }

    private void assertEmpty(boolean expected) {
        Assert.assertEquals(expected, test.isEmpty());
    }

    @Test
    public void isEmpty_givenInitialState_returnsTrue() throws Exception {
        assertEmpty(true);
    }

    @Test
    public void isEmpty_givenSize1_returnsFalse() throws Exception {
        setField("size", 1);
        assertEmpty(false);
    }

    @Test(expected = Exception.class)
    public void dequeue_givenInitialState_throwsException() throws Exception {
        test.dequeue();
    }

    private void assertSize(int expected) {
        Assert.assertEquals(expected, test.getSize());
    }

    @Test
    public void enqueue_givenInitialState_thenSizeIs1() throws Exception {
        enqueue(10);
        assertSize(1);
    }

    @Test
    public void dequeue_givenSize1_thenSizeIs0() throws Exception {
        enqueue(0);
        setField("size", 1);

        test.dequeue();
        assertSize(0);
    }

    private void assertTo(int expected) throws Exception {
        Assert.assertEquals(expected, getField("to"));
    }

    @Test
    public void enqueue_givenInitialState_thenToIs0() throws Exception {
        enqueue(10);
        assertTo(0);
    }

    @Test
    public void enqueue_givenSize1_thenToIs1() throws Exception {
        enqueue(0);

        enqueue(10);
        assertTo(1);
    }

    private void assertFrom(int expected) throws Exception {
        Assert.assertEquals(expected, getField("from"));
    }

    @Test
    public void dequeue_givenSizeIs2_givenFromIs1_thenFromIs0() throws Exception {
        enqueue(1);
        enqueue(2);
        setField("size", 2);
        setField("from", 0);

        test.dequeue();
        assertFrom(1);
        test.dequeue();
        assertFrom(0);
    }

    @Test
    public void enqueue_givenInitialState_thenPutInArray() throws Exception {
        enqueue(10);
        assertArray(10);
    }

    @Test
    public void enqueue_input12345_givenInitialState_thenPutInArray() throws Exception {
        enqueue(1, 2, 3, 4, 5);
        assertArray(1, 2, 3, 4, 5);
    }

    private void assertArray(int... expecteds) throws Exception {
        for (int i = 0; i < expecteds.length; i++)
            Assert.assertEquals(expecteds[i],
                    ((DynamicArray) getField("dynArr")).get(i));
    }

    private void enqueue(int... values) {
        for (int value : values) test.enqueue(value);
    }

    private void assertDequeue(int... ints) {
        for (int i : ints) Assert.assertEquals(i, test.dequeue());
    }

    @Test
    public void dequeue_given12345_thenDequeue12345() throws Exception {
        enqueue(1, 2, 3, 4, 5);

        assertDequeue(1, 2, 3, 4, 5);
    }

    private void assertFromTo(int form, int to) throws Exception {
        assertFrom(form);
        assertTo(to);
    }

    @Test
    public void integration() throws Exception {
        test.enqueue(0);
        assertFromTo(0, 0);
        test.enqueue(1);
        assertFromTo(0, 1);
        test.dequeue();
        assertFromTo(1, 1);
        test.enqueue(2);
        assertFromTo(1, 0);
        test.dequeue();
        assertFromTo(0, 0);
        test.enqueue(3);
        assertFromTo(0, 1);
        test.dequeue();
        assertFromTo(1, 1);
        test.enqueue(4);
        assertFromTo(1, 0);
        test.dequeue();
        assertFromTo(0, 0);
        test.enqueue(5);
        assertFromTo(0, 1);
        test.dequeue();
        assertFromTo(1, 1);
    }

    @Test
    public void integration2() throws Exception {
        test.enqueue(1);
        assertFromTo(0, 0);
        test.dequeue();
        assertFromTo(1, 0);
        test.enqueue(2);
        assertFromTo(0, 0);
        test.dequeue();
        assertFromTo(1, 0);
        test.enqueue(3);
        assertFromTo(0, 0);
        test.dequeue();
        assertFromTo(1, 0);
        test.enqueue(4);
        assertFromTo(0, 0);
        test.dequeue();
        assertFromTo(1, 0);
        test.enqueue(5);
        assertFromTo(0, 0);
        test.dequeue();
        assertFromTo(1, 0);
    }
}