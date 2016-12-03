package blatt07;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HeadListTest {
    private static final int RANGE = 100;
    private static final int ERROR = Integer.MIN_VALUE;
    private HeadList test;

    @Before
    public void setUp() throws Exception {
        test = new HeadList();
    }

    @Test
    public void constructor_toString_returnsEmptyList() throws Exception {
        Assert.assertEquals("[]", test.toString());
    }

    @Test
    public void add_given0_includes0() throws Exception {
        int info = 0;
        assertAdd(info);
    }

    @Test
    public void add_given01_includes01() throws Exception {
        assertAdd(0, 1);
    }

    @Test
    public void add_given012345_includes012345() throws Exception {
        assertAdd(0, 1, 2, 3, 4, 5);
    }

    @Test
    public void add_givenRandom() throws Exception {
        assertAdd(buildRandomInput());
    }

    private int[] buildRandomInput() {
        int[] input = new int[randomInt(HeadListTest.RANGE)];
        for (int i = 0; i < input.length; i++) input[i] = randomInt(HeadListTest.RANGE);
        return input;
    }

    private int randomInt(int max) {
        return (int) (Math.random() * max);
    }

    private void assertAdd(int... info) {
        for (int i : info) test.add(i);
        assertToString(info);
    }

    @Test
    public void referenceToHead() throws Exception {
        assertAdd(0, 1, 2, 3);
        HeadList.Entry head = test.head;
        assertHead(head);
    }

    private void assertHead(HeadList.Entry head) {
        if (head == null) return;

        HeadList.Entry tmp = head.next;
        while (tmp != null) {
            Assert.assertEquals(head, tmp.first);
            tmp = tmp.next;
        }
    }

    @Test
    public void remove_givenInvalidIndex_givenEmptyList_returnsIntegerMIN_VALUE() throws Exception {
        Assert.assertEquals(ERROR, test.remove(-1));
        Assert.assertEquals(ERROR, test.remove(-5));
        Assert.assertEquals(ERROR, test.remove(-8));
        Assert.assertEquals(ERROR, test.remove(0));
        Assert.assertEquals(ERROR, test.remove(1));
    }

    @Test
    public void remove_givenInvalidIndex_returnsIntegerMIN_VALUE() throws Exception {
        assertAdd(0, 1, 2, 3, 4, 5);
        Assert.assertEquals(ERROR, test.remove(-1));
        Assert.assertEquals(ERROR, test.remove(7));
    }

    @Test
    public void remove_given1_returns1() throws Exception {
        int[] input = {1, 2, 3, 4, 5};
        assertAdd(input);
        assertRemove(1, input);
    }

    @Test
    public void remove_givenHead_returnsHead() throws Exception {
        int[] input = {0, 1, 2, 3, 4, 5};
        assertAdd(input);
        assertRemove(0, input);
    }

    @Test
    public void remove_givenLastElement() throws Exception {
        int[] input = {0};
        assertAdd(input);
        assertRemove(0, input);
    }

    @Test
    public void remove_givenRandom() throws Exception {
        int[] input = buildRandomInput();
        assertAdd(input);
        for (int i = input.length - 1; i >= 0; i--) {
            int index = randomInt(i);
            assertRemove(index, input);
            input = removeElement(input, index);
        }
    }

    private int[] removeElement(int[] array, int index) {
        int[] out = new int[array.length - 1];

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == index) continue;
            out[count++] = array[i];
        }

        return out;
    }

    private void assertRemove(int index, int... input) {
        HeadList.Entry head = index == 0 ? test.head.next : test.head;

        Assert.assertEquals(input[index], test.remove(index));
        int[] array = removeElement(input, index);
        assertToString(array);
        assertHead(head);
    }

    private void assertToString(int[] array) {
        Assert.assertEquals(Arrays.toString(array)
                .replace(", ", ","), test.toString());
    }

    @Test
    public void reverse_givenEmpty_includesEmpty() throws Exception {
        assertReverse();
    }

    @Test
    public void reverse_given1_includes1() throws Exception {
        assertReverse(1);
    }

    private void assertReverse(int... input) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i : input) list.add(i);
        Collections.reverse(list);

        assertAdd(input);
        test.reverse();

        if (input.length > 0) {
            Assert.assertEquals(list.get(0), test.head.first.elem);
            assertHead(test.head.first);
        }
        Assert.assertEquals(Arrays.toString(listToArray(list))
                .replace(", ", ","), test.toString());
    }

    private Object[] listToArray(List list) {
        Object[] out = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) out[i] = list.get(i);
        return out;
    }

    @Test
    public void reverse_given12345_includes54321() throws Exception {
        assertReverse(1, 2, 3, 4, 5);
    }
    @Test
    public void reverse_givenRandom() throws Exception {
        assertReverse(buildRandomInput());
    }
}