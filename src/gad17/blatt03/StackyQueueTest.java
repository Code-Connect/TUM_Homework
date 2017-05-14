package gad17.blatt03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackyQueueTest {
    StackyQueue test;

    @Before
    public void setUp() throws Exception {
        test = new StackyQueue(2, 4);
        System.out.println(test.getClass().getFields());
    }

    private void assertLength(int expected) {
        Assert.assertEquals(expected, test.getLength());
    }

    @Test
    public void getLength_givenInitialState_returns0() throws Exception {
        assertLength(0);
    }

    @Test(expected = Exception.class)
    public void dequeue_givenInitialState_throwsException() throws Exception {
        test.dequeue();
    }

    @Test
    public void enqueue_givenInitialState_thenLengthIs1() throws Exception {
        test.enqueue(10);
        assertLength(1);
    }

    @Test
    public void dequeue_given12345_thenDequeue12345() throws Exception {
        enqueue(1, 2, 3, 4, 5);

        assertDequeue(1, 2, 3, 4, 5);
    }

    private void enqueue(int... values) {
        for (int value : values) test.enqueue(value);
    }

    private void assertDequeue(int... ints) {
        for (int i : ints) Assert.assertEquals(i, test.dequeue());
    }

}