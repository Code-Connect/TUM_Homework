package pgdp16.blatt07;

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
        assertAdd(0);
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
        int[] input = new int[randomInt(RANGE)];
        for (int i = 0; i < input.length; i++) input[i] = randomInt(RANGE);
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
        assertRemoveError(-1, -5, -8, 0, 1);
    }

    private void assertRemoveError(int... index) {
        for (int i : index) Assert.assertEquals(ERROR, test.remove(i));
    }

    @Test
    public void remove_givenInvalidIndex_returnsIntegerMIN_VALUE() throws Exception {
        assertAdd(0, 1, 2, 3, 4, 5);
        assertFirstElement(0, test.head);
        assertRemoveError(-1, 7);
    }

    @Test
    public void remove_givenIndex1_returns2() throws Exception {
        int[] input = {1, 2, 3, 4, 5};
        assertAdd(input);
        assertFirstElement(1, test.head);
        assertRemove(1, input);
        assertFirstElement(1, test.head);
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
            if (i > 0)
                assertFirstElement(input[0], test.head);
            assertHead(test.head);
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
            assertFirstElement(list.get(0), test.head);
            assertHead(test.head.first);
        }
        Assert.assertEquals(Arrays.toString(listToArray(list))
                .replace(", ", ","), test.toString());
    }

    private void assertFirstElement(Object expected, HeadList.Entry entry) {
        try {
            Assert.assertEquals(expected, entry.first.elem);
        } catch (NullPointerException e) {
            Assert.fail("Please be sure that Head includes the first element ;)");
        }
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

    @Test
    public void testConstructor() {
        HeadList list = new HeadList();
        assertList(list, "[]");
    }

    @Test
    public void testAdd() {
        HeadList list = new HeadList();
        list.add(1337);
        assertList(list, "[1337]");
        list.add(42);
        assertList(list, "[1337,42]");
        list.add(0);
        assertList(list, "[1337,42,0]");
        list.add(-88);
        assertList(list, "[1337,42,0,-88]");

        HeadList list2 = new HeadList();
        list2.add(-19);
        assertList(list2, "[-19]");
    }

    @Test
    public void testRemove() {
        HeadList list = new HeadList();
        list.add(0);
        list.add(-18);
        list.add(1337);
        list.add(17);
        list.add(42);

        assertListRemove(list, -1, Integer.MIN_VALUE);
        assertList(list, "[0,-18,1337,17,42]");

        assertListRemove(list, 5, Integer.MIN_VALUE);
        assertList(list, "[0,-18,1337,17,42]");

        assertListRemove(list, 0, 0);
        assertList(list, "[-18,1337,17,42]");

        assertListRemove(list, 4, Integer.MIN_VALUE);
        assertList(list, "[-18,1337,17,42]");

        assertListRemove(list, 3, 42);
        assertList(list, "[-18,1337,17]");

        assertListRemove(list, 1, 1337);
        assertList(list, "[-18,17]");
    }

    @Test
    public void testReverse() {
        HeadList list = new HeadList();
        list.add(17);
        list.add(42);
        list.add(1337);
        list.add(-90);
        list.add(0);

        assertList(list, "[17,42,1337,-90,0]");
        list.reverse();
        assertList(list, "[0,-90,1337,42,17]");

        list = new HeadList();
        list.reverse();
        assertList(list, "[]");

        list = new HeadList();
        list.add(0);
        list.reverse();
        assertList(list, "[0]");

        list = new HeadList();
        list.add(1);
        list.add(2);
        list.reverse();
        assertList(list, "[2,1]");
    }

    private void assertListRemove(HeadList list, int index, int expected) {
        Assert.assertEquals(expected, list.remove(index));
    }

    private void assertList(HeadList list, String expected) {
        Assert.assertEquals(expected, list.toString());
    }
}
