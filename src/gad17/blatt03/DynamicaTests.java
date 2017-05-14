package gad17.blatt03;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class DynamicaTests {
    private void testQueue(Consumer<Integer> enqueue, Function<Integer, Integer> dequeue, Function<Integer, Integer> size) {
        int enqueueCount = 55;
        int dequeueCount = 55;

//    System.out.println("Up...");
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);

//    System.out.println("Down...");
        for (int i = 0; i < 6; i++)
            assertEquals(dequeueCount++, (int) dequeue.apply(0));

//    System.out.println("Up...");
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);

//    System.out.println("Down...");
        for (int i = 0; i < 4; i++)
            assertEquals(dequeueCount++, (int) dequeue.apply(0));

//    System.out.println("Up...");
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);
        enqueue.accept(enqueueCount++);

//    System.out.println("Down...");
        while (size.apply(0) > 0)
            assertEquals(dequeueCount++, (int) dequeue.apply(0));

//    System.out.println("Up...");
        for (int i = 0; i < 1024; i++)
            enqueue.accept(enqueueCount++);
//    System.out.println("Down...");
        for (int i = 0; i < 1024; i++)
            assertEquals(dequeueCount++, (int) dequeue.apply(0));
    }

    @Test
    public void testRingQueue() {
        RingQueue rq = new RingQueue(2, 4);
        testQueue(x -> rq.enqueue(x), __ -> rq.dequeue(), __ -> rq.getSize());
    }

    @Test
    public void testStackyQueue() {
        StackyQueue sq = new StackyQueue(2, 4);
        testQueue(x -> sq.enqueue(x), __ -> sq.dequeue(), __ -> sq.getLength());
    }

    @Test
    public void testDynamicStack() {
        DynamicStack stack = new DynamicStack(2, 4);

        int count = 0;
        for (int i = 0; i < 20; i++)
            stack.pushBack(count++);

        for (int i = 0; i < 10; i++)
            assertEquals(--count, stack.popBack());

        for (int i = 0; i < 30; i++)
            stack.pushBack(count++);

        for (int i = 0; i < 30 + 10; i++)
            assertEquals(--count, stack.popBack());
    }

}
