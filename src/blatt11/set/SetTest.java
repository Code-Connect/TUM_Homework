package blatt11.set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SetTest {

    private Set<Integer> s;

    @Before
    public void setUp() throws Exception {
        s = new Set<Integer>();
    }

    @Test(expected = NullPointerException.class)
    public void add_givenNull_throwsNullPointerException() throws Exception {
        s.add(null);
    }

    @Test
    public void toString_givenEmptySet() throws Exception {
        assertList("");
    }

    @Test
    public void toString_givenAdd1_returns1() throws Exception {
        add(1);
        assertList("1");
    }

    private void add(Integer... input) {
        s = add(s, input);
    }

    @Test
    public void toString_givenAdd3Times_returns1() throws Exception {
        add(1, 2, 3);
        assertList("1,2,3");
    }

    private void assertList(String expected) {
        String listString = s.toString();
        char[] listChars = listString.toCharArray();
        char[] expectedChars = ("{" + expected + "}").toCharArray();
        for (char c : expectedChars) {
            boolean charFound = false;
            for (int i = 0; i < listChars.length; i++) {
                if (listChars[i] == c) {
                    listChars[i] = 0;
                    charFound = true;
                    break;
                }
            }
            if (!charFound) {
                Assert.fail("expected: {" + expected + "} found: " + listString + "; Set does not contain (enough): " + c);
            }
        }
    }

    @Test
    public void contains_givenEmptySet() throws Exception {
        assertContains(false, 1);
    }

    @Test
    public void contains_givenNull() throws Exception {
        add(0, 1, 2, 3);
        assertContains(false, null);
    }

    @Test
    public void contains_given1_givenContainsElement_returnTrue() throws Exception {
        Integer first = 1;
        add(first);
        assertContains(true, first);
    }

    @Test
    public void contains_given123_givenContainsLast_returnTrue() throws Exception {
        Integer last = 3;
        add(1, 2, last);
        assertContains(true, last);
    }

    @Test
    public void contains_given123_givenContainsFirst_returnTrue() throws Exception {
        Integer first = 1;
        add(first, 2, 3);
        assertContains(true, first);
    }

    @Test
    public void contains_given12345_givenContainsCenter_returnTrue() throws Exception {
        Integer center = 3;
        add(1, 2, center, 4, 5);
        assertContains(true, center);
    }

    @Test
    public void contains_given12345_given9_returnFalse() throws Exception {
        add(1, 2, 3, 4, 5);
        assertContains(false, 9);
    }

    @Test
    public void contains_given12345_givenWrongType_returnFalse() throws Exception {
        add(1, 2, 3, 4, 5);
        assertContains(false, 5.0);
    }

    @Test
    public void contains_given123_givenArray_returnFalse() throws Exception {
        add(1, 2, 3);
        assertContains(false, new Integer[]{1});
    }

    private void assertContains(boolean expected, Object entry) {
        Assert.assertEquals(expected, s.contains(entry));
    }

    @Test
    public void add_givenSameObjectTwice_returnsThis() throws Exception {
        int same = 1;
        add(same);

        Assert.assertSame(s, s.add(same));
    }

    @Test(expected = NullPointerException.class)
    public void remove_givenNull_throwsNullPointerException() throws Exception {
        remove(null);
    }

    @Test
    public void remove_givenSet123_removeLast_thenSetIs12() throws Exception {
        Integer last = 3;
        add(1, 2, last);

        remove(last);

        assertList("1,2");
    }

    private Set<Integer> remove(Object... integers) {
        Arrays.stream(integers).forEach(integer -> s = s.remove(integer));
        return s;
    }

    @Test
    public void remove_givenSet123_removeFirst_thenSetIs23() throws Exception {
        Integer first = 1;
        add(first, 2, 3);

        remove(first);

        assertList("2,3");
    }

    @Test
    public void remove_givenSet12345_removeCenter_thenSetIs1245() throws Exception {
        Integer center = 3;
        add(1, 2, center, 4, 5);

        remove(center);

        assertList("1,2,4,5");
    }

    @Test
    public void remove_givenSet12345_givenNotFound_thenSetIs12345() throws Exception {
        add(1, 2, 3, 4, 5);

        remove(9);

        assertList("1,2,3,4,5");
    }

    @Test
    public void remove_givenSet12345_givenWrongType_thenSetIs12345() throws Exception {
        add(1, 2, 3, 4, 5);

        remove(3.0);

        assertList("1,2,3,4,5");
    }


    @Test
    public void size_givenEmptySet_returns0() throws Exception {
        assertSize(0);
    }

    @Test
    public void size_given1Entry_returns1() throws Exception {
        add(1);
        assertSize(1);
    }

    @Test
    public void size_given3Entries_returns3() throws Exception {
        add(1, 2, 3);
        assertSize(3);
    }

    private void assertSize(int expected) {
        Assert.assertEquals(expected, s.size());
    }

    @Test
    public void equals_givenNull_returnsFalse() throws Exception {
        assertEquals(false, s, null);
    }

    @Test
    public void equals_givenEmpty_givenNoSetOfIntegers_returnsFalse() throws Exception {
        assertEquals(true, s, new Set<Double>());
    }

    @Test
    public void equals_givenSetOfDoubles_returnsFalse() throws Exception {
        add(1, 2, 3);
        Set<Double> other = new Set<>();
        other = other.add(1.0);
        other = other.add(2.0);
        other = other.add(3.0);
        assertEquals(false, s, other);
    }

    @Test
    public void equals_givenSetOfLongs_returnsFalse() throws Exception {
        add(1, 2, 3);
        Set<Long> other = new Set<>();
        other = other.add(1L);
        other = other.add(2L);
        other = other.add(3L);
        assertEquals(false, s, other);
    }

    @Test
    public void equals_givenEqual_givenEmpty_returnsTrue() throws Exception {
        Set<Integer> other = new Set<>();
        assertEquals(true, s, other);
    }

    @Test
    public void equals_givenEqual_given1Entry_returnsTrue() throws Exception {
        add(1);
        Set<Integer> other = new Set<>();
        other = add(other, 1);
        assertEquals(true, s, other);
    }

    @Test
    public void equals_givenNotEqual_givenSameSize_given1Entry_returnsFalse() throws Exception {
        add(1);
        Set<Integer> other = new Set<>();
        other = add(other, 2);
        assertEquals(false, s, other);
    }

    @Test
    public void equals_givenNotEqual_givenSameSize_given3Entries_returnsFalse() throws Exception {
        add(1, 2, 3);
        Set<Integer> other = new Set<>();
        other = add(other, 1, 0, 3);
        assertEquals(false, s, other);
    }

    @Test
    public void equals_givenEqual_givenSameSize_given3Entries_returnsTrue() throws Exception {
        add(1, 2, 3);
        Set<Integer> other = new Set<>();
        other = add(other, 1, 2, 3);
        assertEquals(true, s, other);
    }

    @Test
    public void equals_givenDifferentSize_returnsFalse() throws Exception {
        add(1, 2, 3);
        Set<Integer> other = new Set<>();
        other = add(other, 1, 2, 3, 4);
        assertEquals(false, s, other);
    }

    @Test
    public void equals_givenDifferentOrder_returnsFalse() throws Exception {
        add(1, 2, 3, 4, 5);
        Set<Integer> other = new Set<>();
        other = add(other, 3, 1, 4, 2, 5);
        assertEquals(true, s, other);
    }

    /**
     * It is reflexive: for any non-null reference value x, x.equals(x) should return true.
     */
    @Test
    public void equals_givenReflexive() throws Exception {
        Set<Integer> x = add(new Set<>(), 1, 2);
        assertEquals(true, x, x);
    }

    private Set<Integer> add(Set<Integer> set, Integer... add) {
        Assert.assertNotNull(set.add(0));
        for (Integer integer : add) set = set.add(integer);
        return set;
    }

    private void assertEquals(boolean expected, Set s, Set other) {
        Assert.assertNotNull(s);
        Assert.assertEquals(expected, s.equals(other));
    }

    /**
     * It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
     */
    @Test
    public void equals_givenSymmetric_givenTrue() throws Exception {
        Integer[] elements = {1, 2, 3, 4, 5};
        Set<Integer> x = add(new Set<>(), elements);
        Set<Integer> y = add(new Set<>(), elements);
        assertEquals(true, x, y);
        assertEquals(true, y, x);
    }

    /**
     * It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
     */
    @Test
    public void equals_givenSymmetric_givenFalse() throws Exception {
        Set<Integer> x = add(new Set<>(), 1, 2, 3, 4, 5);
        Set<Integer> y = add(new Set<>(), 1, 2, 5, 6, 7);
        assertEquals(false, x, y);
        assertEquals(false, y, x);
    }

    /**
     * It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
     */
    @Test
    public void equals_givenTransitive() throws Exception {
        Set<Integer> x = add(new Set<>(), 1, 2, 3, 4, 5);
        Set<Integer> y = add(new Set<>(), 1, 2, 3, 4, 5);
        Set<Integer> z = add(new Set<>(), 1, 2, 3, 4, 5);
        assertEquals(true, x, y);
        assertEquals(true, y, z);
        assertEquals(true, x, z);
    }

    /**
     * It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
     */
    @Test
    public void equals_givenConsistent_givenTrue() throws Exception {
        Set<Integer> x = add(new Set<>(), 1, 2, 3, 4, 5);
        Set<Integer> y = add(new Set<>(), 1, 2, 3, 4, 5);
        assertEquals(true, x, y);
        assertEquals(true, x, y);
        assertEquals(true, x, y);
    }

    /**
     * It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
     */
    @Test
    public void equals_givenConsistent_givenFalse() throws Exception {
        Set<Integer> x = add(new Set<>(), 1, 2, 3, 4, 5);
        Set<Integer> y = add(new Set<>(), 1, 2, 3, 5, 6);
        assertEquals(false, x, y);
        assertEquals(false, x, y);
        assertEquals(false, x, y);
    }

}