package pgdp16.blatt08;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SymmetricStackTest extends SymmetricStack {
    private int increaseCounter;
    private int decreaseCounter;

    @Before
    public void setUp() throws Exception {
        increaseCounter = 0;
        decreaseCounter = 0;
    }

    @Test
    public void constructor() throws Exception {
        Assert.assertNotNull("Please be sure to initialize data", getData());
        int length = getData().length;

        Assert.assertNotEquals(0, length);
        assertStack(-1, -1, new int[length]);
        assertIsEmpty(true);
        assertIsFull(false);
    }

    private void assertStack(int expFirst, int expLast, int... expData) {
        assertFirstAndLast(expFirst, expLast);
        Assert.assertArrayEquals(toString(), expData, getData());
    }

    private void assertFirstAndLast(int expFirst, int expLast) {
        Assert.assertEquals(messageVariable("First"), expFirst, getFirst());
        Assert.assertEquals(messageVariable("Last"), expLast, getLast());
    }

    private String messageVariable(String var) {
        return var + " shall be different";
    }

    @Test
    public void isEmpty_givenEmpty_returnsTrue() throws Exception {
        assertIsEmpty(true);
    }

    private void assertIsEmpty(boolean expected) {
        Assert.assertEquals(messageMethod("isEmpty()"), expected, isEmpty());
    }

    private String messageMethod(String method) {
        return "check our " + method + " method";
    }

    @Test
    public void isEmpty_givenFirstNotN1_returnsFalse() throws Exception {
        setFirst(2);
        assertIsEmpty(false);
    }

    @Test
    public void getNumberOfElements_givenEmpty_returns0() throws Exception {
        buildStack(-1, -1, 0);
        assertNumber(0);
    }

    @Test
    public void getNumberOfElements_givenFirst3_givenLast7_returns5() throws Exception {
        buildStack(3, 7, 0, 0, 0, 2, 9, 0, 3, 8, 0, 0);
        assertNumber(5);
    }

    private void assertNumber(int expected) {
        Assert.assertEquals(messageMethod("getNumberOfElements()"), expected, getNumberOfElements());
    }

    @Test
    public void getNumberOfElements_givenFirst3_givenLast0_returns8() throws Exception {
        buildStack(3, 0, 4, 0, 0, 2, 9, 0, 3, 8, 1, 6);
        assertNumber(8);
    }

    @Test
    public void getNumberOfElements_givenFirst4_givenLast1_returns8() throws Exception {
        buildStack(4, 1, 6, 4, 0, 0, 2, 9, 0, 3, 8, 1);
        assertNumber(8);
    }

    @Test
    public void igetNumberOfElements_givenFirst6_givenLast5_returns10() throws Exception {
        buildStack(6, 5, 6, 4, 7, 8, 2, 9, 0, 3, 8, 1);
        assertNumber(10);
    }

    @Test
    public void isFull_givenFirst3_givenLast2_returnsTrue() throws Exception {
        buildStack(3, 2, 6, 4, 7, 8, 2, 9, 0, 3, 8, 1);
        assertIsFull(true);
    }

    private void assertIsFull(boolean expected) {
        Assert.assertEquals(messageMethod("isFull()"), expected, isFull());
    }

    @Test
    public void isFull_givenFirst3_givenLast2_returnsFalse() throws Exception {
        buildStack(4, 2, 6, 4, 7, 0, 2, 9, 0, 3, 8, 1);
        assertIsFull(false);
    }

    private void buildStack(int first, int last, int... data) {
        setFirst(first);
        setLast(last);
        setData(data);
    }

    @Test
    public void increase_givenNotFull_thenNothingHappens() throws Exception {
        int[] expected = new int[5];
        buildStack(2, 4, expected);

        increase();
        assertStack(2, 4, expected);
    }

    @Test
    public void increase_givenFirst0_givenLast0_givenFull_makesSize2() throws Exception {
        buildStack(0, 0, 1);
        int[] expected = {1, 0};

        increase();
        assertStack(0, 0, expected);
    }

    @Test
    public void increase_givenFirst0_givenLast4_givenFull_makesSize10() throws Exception {
        buildStack(0, 4, 2, 9, 0, 3, 5);
        int[] expected = {0, 0, 2, 9, 0, 3, 5, 0, 0, 0};

        increase();
        assertStack(2, 6, expected);
    }

    @Test
    public void increase_givenFirst2_givenLast1_givenFull_makesSize10() throws Exception {
        buildStack(2, 1, 3, 5, 2, 9, 0);
        int[] expected = {0, 0, 2, 9, 0, 3, 5, 0, 0, 0};

        increase();
        assertStack(2, 6, expected);
    }

    @Test
    public void decrease_givenToManyElements_thenNothingHappens() throws Exception {
        buildStack(1, 2, 0, 3, 5, 0);
        int[] expected = {0, 3, 5, 0};

        decrease();
        assertStack(1, 2, expected);
    }

    @Test
    public void decrease_givenFirst5_givenLast6_givenSize9_makesSize4() throws Exception {
        buildStack(5, 6, 0, 0, 0, 0, 0, 3, 5, 0, 0);
        int[] expected = {0, 3, 5, 0};

        decrease();
        assertStack(1, 2, expected);
    }

    @Test
    public void decrease_givenFirst8_givenLast0_givenSize9_makesSize4() throws Exception {
        buildStack(8, 0, 5, 0, 0, 0, 0, 0, 0, 0, 3);
        int[] expected = {0, 3, 5, 0};

        decrease();
        assertStack(1, 2, expected);
    }

    @Test
    public void prepend_givenFull_thenCallIncrease() throws Exception {
        buildStack(0, 0, 0);

        prepend(2);
        assertIncreaseCalls(1);
    }

    private void assertIncreaseCalls(int expected) {
        Assert.assertEquals(messageCalls("increase()"), expected, increaseCounter);
    }

    @Override
    public void increase() {
        increaseCounter++;
        super.increase();
    }

    @Test
    public void prepend_givenEmpty_givenSize1_prependAt0() throws Exception {
        buildStack(-1, -1, 0);

        prepend(2);
        assertIncreaseCalls(0);
        assertStack(0, 0, 2);
    }

    @Test
    public void prepend_givenFirst3_givenLast7_prependAt2() throws Exception {
        buildStack(3, 7, 0, 0, 0, 2, 9, 0, 3, 5, 0, 0);
        int[] expected = {0, 0, 1, 2, 9, 0, 3, 5, 0, 0};

        prepend(1);

        assertIncreaseCalls(0);
        assertStack(2, 7, expected);
    }

    @Test
    public void prepend_givenFirst0_givenLast4_prependAt9() throws Exception {
        buildStack(0, 4, 2, 9, 0, 3, 5, 0, 0, 0, 0, 0);
        int[] expected = {2, 9, 0, 3, 5, 0, 0, 0, 0, 7};

        prepend(7);

        assertIncreaseCalls(0);
        assertStack(9, 4, expected);
    }

    @Test
    public void append_givenEmpty_givenSize1_appendAt0() throws Exception {
        buildStack(-1, -1, 0);

        append(2);
        assertIncreaseCalls(0);
        assertStack(0, 0, 2);
    }

    @Test
    public void append_givenFirst3_givenLast7_appendAt8() throws Exception {
        buildStack(3, 7, 0, 0, 0, 2, 9, 0, 3, 5, 0, 0);
        int[] expected = {0, 0, 0, 2, 9, 0, 3, 5, 1, 0};

        append(1);

        assertIncreaseCalls(0);
        assertStack(3, 8, expected);
    }

    @Test
    public void append_givenFirst3_givenLast9_appendAt0() throws Exception {
        buildStack(3, 9, 0, 0, 0, 2, 9, 0, 3, 5, 1, 6);
        int[] expected = {4, 0, 0, 2, 9, 0, 3, 5, 1, 6};

        append(4);

        assertStack(3, 0, expected);
    }

    @Test
    public void removeFirst_given1Element_thenStackIsEmpty() throws Exception {
        buildStack(0, 0, 2);

        removeFirst();

        assertFirstAndLast(-1, -1);
    }

    @Test
    public void removeFirst_givenFirst2_givenLast7_thenFirstIs3() throws Exception {
        buildStack(2, 7, 0, 0, 1, 2, 9, 0, 3, 5, 0, 0);

        removeFirst();

        assertFirstAndLast(3, 7);
    }

    @Test
    public void removeFirst_givenFirst9_givenLast4_thenFirstIs0() throws Exception {
        buildStack(9, 4, 2, 9, 0, 3, 5, 0, 0, 0, 0, 7);

        removeFirst();

        assertFirstAndLast(0, 4);
    }

    @Test
    public void removeFirst_givenFirst4_givenLast6_givenSize9_makesSize4() throws Exception {
        buildStack(4, 6, 0, 0, 0, 0, 1, 3, 5, 0, 0);

        removeFirst();

        assertDecreaseCalls(1);
        assertFirstAndLast(1, 2);
    }

    private void assertDecreaseCalls(int expected) {
        Assert.assertEquals(messageCalls("decrease()"), expected, decreaseCounter);
    }

    private String messageCalls(String method) {
        return "check your " + method + " calls";
    }

    @Override
    public void decrease() {
        decreaseCounter++;
        super.decrease();
    }


    @Test
    public void removeLast_given1Element_thenStackIsEmpty() throws Exception {
        buildStack(0, 0, 2);

        removeLast();

        assertFirstAndLast(-1, -1);
    }

    @Test
    public void removeLast_givenFirst3_givenLast8_thenLastIs7() throws Exception {
        buildStack(3, 8, 0, 0, 0, 2, 9, 0, 3, 5, 1, 0);

        removeLast();

        assertFirstAndLast(3, 7);
    }

    @Test
    public void removeLast_givenFirst3_givenLast0_thenLastIs9() throws Exception {
        buildStack(3, 0, 4, 0, 0, 2, 9, 0, 3, 5, 1, 6);

        removeLast();

        assertFirstAndLast(3, 9);
    }

    @Test
    public void removeLast_givenFirst5_givenLast7_givenSize9_makesSize4() throws Exception {
        buildStack(5, 7, 0, 0, 0, 0, 0, 3, 5, 1, 0);

        removeLast();

        assertDecreaseCalls(1);
        assertFirstAndLast(1, 2);
    }

    @Test
    public void integration() throws Exception {
        assertStack(-1, -1, 0, 0, 0, 0);
        append(1);
        assertStack(2, 2, 0, 0, 1, 0);
        prepend(2);
        assertStack(1, 2, 0, 2, 1, 0);
        removeFirst();
        assertFirstAndLast(0, 0);
        removeLast();
        assertFirstAndLast(-1, -1);
    }

}