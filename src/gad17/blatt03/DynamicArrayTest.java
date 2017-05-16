package gad17.blatt03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DynamicArrayTest {
    DynamicArray test;

    @Before
    public void setUp() throws Exception {
        test = new DynamicArray(2, 4);
    }

    private void reallocate(Interval usage, int lengthNew) throws Exception {
        Method reallocate = test.getClass().getDeclaredMethod("reallocate",
                Interval.class, int.class);
        reallocate.setAccessible(true);
        reallocate.invoke(test, usage, lengthNew);
    }

    private int[] getElements() throws Exception {
        Field elements = test.getClass().getDeclaredField("elements");
        elements.setAccessible(true);
        return (int[]) elements.get(test);
    }

    private void setElements(int... ints) throws Exception {
        Field elements = test.getClass().getDeclaredField("elements");
        elements.setAccessible(true);
        elements.set(test, ints);
    }

    private void assertLength(int expected) {
        Assert.assertEquals(expected, test.getInnerLength());
    }

    @Test
    public void getInnerLength_givenInitialState_returns0() throws Exception {
        assertLength(0);
    }

    @Test
    public void reallocate_givenInitialState_thenLengthIs2() throws Exception {
        reallocate(new EmptyInterval(), 1);
        assertLength(2);
    }

    @Test
    public void set_input0_1_givenInitialState_thenLengthIs2() throws Exception {
        test.reportUsage(new EmptyInterval(), 1);
        test.set(0, 1);
        assertLength(2);
    }

    @Test
    public void set_input0_1_thenElementsIs1_0() throws Exception {
        test.reportUsage(new EmptyInterval(), 1);
        test.set(0, 1);
        assertElements(1, 0);
    }

    private void assertElements(int... expectedElements) throws Exception {
        Assert.assertArrayEquals(Arrays.toString(getElements()), expectedElements, getElements());
    }

    @Test(expected = Exception.class)
    public void get_givenInitialState_throwsException() throws Exception {
        test.get(0);
    }

    @Test
    public void reportUsage_givenInitialState() throws Exception {
        Interval expected = new EmptyInterval();
        Interval actual = test.reportUsage(new EmptyInterval(), 1);

        assertLength(2);
        assertInterval(expected, actual);
    }

    private void assertInterval(Interval expected, Interval actual) {
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void set_givenNotEmptyArray_thenOverrides() throws Exception {
        setElements(1, 2, 3, 4, 5);
        test.set(2, -3);
        assertElements(1, 2, -3, 4, 5);
    }

    @Test(expected = Exception.class)
    public void get_givenArrayOutOfBounds_throwsException() throws Exception {
        setElements(1, 2, 3, 4, 5);
        test.get(5);
    }

    @Test(expected = Exception.class)
    public void get_inputNegative_throwsException() throws Exception {
        setElements(1, 2, 3, 4, 5);
        test.get(-1);
    }

    @Test
    public void get_input2_givenNotEmptyArray_returns3() throws Exception {
        setElements(1, 2, 3, 4, 5);
        Assert.assertEquals(3, test.get(2));
    }

    @Test //could fail, because of implementation Differences
    public void reallocate_givenBigEnough_thenNothingHappens() throws Exception {
        int[] elements = {1, 2, 3, 4, 0};
        setElements(elements);

        reallocate(new NonEmptyInterval(0, 3), 5);

        assertElements(elements);
    }

    @Test
    public void reallocate_givenToSmall_thenIncreasesElements() throws Exception {
        int[] elements = {1, 2, 3, 4};
        setElements(elements);

        reallocate(new NonEmptyInterval(0, 3), 5);

        assertElements(1, 2, 3, 4, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void reallocate_givenToBig_thenDecreasesElements() throws Exception {
        int[] elements = {1, 2, 3, 0, 0, 0, 7, 8, 9, 10, 11, 12, 13};
        setElements(elements);

        reallocate(new NonEmptyInterval(0, 2), 3);

        assertElements(1, 2, 3, 0, 0, 0);
    }

    @Test(expected = Exception.class)
    @Ignore
    public void reallocate_givenToBig_givenUsageIndexToHigh_throwsException() throws Exception {
        int[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        setElements(elements);

        reallocate(new NonEmptyInterval(6, 8), 3);

        assertElements(elements);
    }

    @Test
    public void reportUsage_givenRightSize_returnsUsage() throws Exception {
        NonEmptyInterval expected = new NonEmptyInterval(4, 8);
        int[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        setElements(elements);

        Interval actual = test.reportUsage(expected, 5);

        assertElements(elements);
        assertInterval(expected, actual);
    }

    @Test
    public void reportUsage_givenToBig_thenDecreasesElements() throws Exception {
        setElements(0, 0, 0, 0, 0, 0, 0, 0, 9, 10, 0, 0, 0);

        Interval actual = test.reportUsage(new NonEmptyInterval(8, 9), 3);

        assertInterval(new NonEmptyInterval(0, 1), actual);
        assertElements(9, 10, 0, 0, 0, 0);
    }

    @Test
    public void reportUsage_givenToBig_givenOutOfOrder_thenDecreasesElements() throws Exception {
        setElements(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13);

        Interval actual = test.reportUsage(new NonEmptyInterval(12, 0), 3);

        assertInterval(new NonEmptyInterval(0, 1), actual);
        assertElements(13, 1, 0, 0, 0, 0);
    }


    @Test
    public void reportUsage_givenToSmall_thenIncreasesElements() throws Exception {
        int[] elements = {1, 2, 3, 4};
        setElements(elements);

        test.reportUsage(new NonEmptyInterval(0, 3), 5);

        assertElements(1, 2, 3, 4, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void reportUsage_givenToSmall_givenOutOfOrder_thenIncreasesElements() throws Exception {
        int[] elements = {1, 2, 3, 4};
        setElements(elements);

        test.reportUsage(new NonEmptyInterval(2, 1), 5);

        assertElements(3, 4, 1, 2, 0, 0, 0, 0, 0, 0);
    }
}