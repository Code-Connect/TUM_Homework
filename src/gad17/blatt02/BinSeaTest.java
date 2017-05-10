package gad17.blatt02;

import org.junit.Test;

import java.lang.reflect.Method;

import static gad17.blatt02.BinSea.search;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BinSeaTest {
    private int searchA(int[] sortedData, int value, boolean lower) {
        try {
            BinSea test = new BinSea();
            Method search = test.getClass().getDeclaredMethod("search",
                    int[].class, int.class, boolean.class);
            search.setAccessible(true);
            return (int) search.invoke(test, sortedData, value, lower);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Test
    public void testSearch() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = searchA(test, 80, true);
        int upper = searchA(test, 80, false);

        assertEquals(2, lower);
        assertEquals(3, upper);
    }

    @Test
    public void testSearchGivenBiggerNumberExpectNotFound() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = searchA(test, 4300, true);
        int upper = searchA(test, 4300, false);

        assertEquals(5, lower);
        assertEquals(-1, upper);
    }

    @Test
    public void testSearchGivenSmallerNumberExpectNotFound() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = searchA(test, -100, true);
        int upper = searchA(test, -100, false);

        assertEquals(-1, lower);
        assertEquals(0, upper);
    }

    @Test
    public void testIntervalSearch() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        Interval testInterval = new NonEmptyInterval(80, 700);
        Interval actual = search(test, testInterval);
        Interval expected = new NonEmptyInterval(3, 4);

        assertNotNull(actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testIntervalSearchExpectEmptyInterval() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        Interval testInterval = new NonEmptyInterval(4500, 5000);
        Interval actual = search(test, testInterval);

        assert actual instanceof EmptyInterval;
    }

    @Test
    public void searchA_givenEmptyArray_returnsErrorValue() {
        assertSearchA(-1, new int[0], 0, true);
        assertSearchA(-1, new int[0], 0, false);
    }

    @Test
    public void searchA_givenValueInArray_givenSize1_givenLowerTrue_returnsIndex() throws Exception {
        int[] sortedData = {1};
        int value = 1;

        assertSearchA(0, sortedData, value, true);
    }

    @Test
    public void searchA_givenValueInArray_givenSize1_givenLowerFalse_returnsIndex() throws Exception {
        int[] sortedData = {1};
        int value = 1;

        assertSearchA(0, sortedData, value, false);
    }

    @Test
    public void searchA_givenValueHigherThanArray_givenSize1_givenLowerTrue_returnsIndex() throws Exception {
        int[] sortedData = {1};
        int value = 2;

        assertSearchA(0, sortedData, value, true);
    }

    @Test
    public void searchA_givenValueHigherThanArray_givenSize1_givenLowerFalse_returnsErrorValue() throws Exception {
        int[] sortedData = {1};
        int value = 2;

        assertSearchA(-1, sortedData, value, false);
    }

    @Test
    public void searchA_givenValueLowerThanArray_givenSize1_givenLowerTrue_returnsIndex() throws Exception {
        int[] sortedData = {1};
        int value = -2;

        assertSearchA(-1, sortedData, value, true);
    }

    @Test
    public void searchA_givenValueLowerThanArray_givenSize1_givenLowerFalse_returnsErrorValue() throws Exception {
        int[] sortedData = {1};
        int value = -2;

        assertSearchA(0, sortedData, value, false);
    }

    @Test
    public void searchA_givenValueInArray_givenSize5_givenLowerTrue_returnsIndex() throws Exception {
        int[] sortedData = {1, 2, 3, 4, 5};
        int value = 2;

        assertSearchA(1, sortedData, value, true);
    }

    @Test
    public void searchA_givenValueInArray_givenSize5_givenLowerFalse_returnsIndex() throws Exception {
        int[] sortedData = {1, 2, 3, 4, 5};
        int value = 2;

        assertSearchA(1, sortedData, value, false);
    }

    @Test
    public void searchA_givenValueHigherThanArray_givenSize5_givenLowerTrue_returnsIndex() throws Exception {
        int[] sortedData = {1, 2, 3, 4, 5};
        int value = 6;

        assertSearchA(4, sortedData, value, true);
    }

    @Test
    public void searchA_givenValueHigherThanArray_givenSize5_givenLowerFalse_returnsErrorValue() throws Exception {
        int[] sortedData = {1, 2, 3, 4, 5};
        int value = 7;

        assertSearchA(-1, sortedData, value, false);
    }

    @Test
    public void searchA_givenValueLowerThanArray_givenSize5_givenLowerTrue_returnsIndex() throws Exception {
        int[] sortedData = {1, 2, 3, 4, 5};
        int value = -2;

        assertSearchA(-1, sortedData, value, true);
    }

    @Test
    public void searchA_givenValueLowerThanArray_givenSize5_givenLowerFalse_returnsErrorValue() throws Exception {
        int[] sortedData = {1, 2, 3, 4, 5};
        int value = -2;

        assertSearchA(0, sortedData, value, false);
    }

    @Test
    public void searchA_givenValueNotInArray_givenValueInMiddle_givenLowerTrue() throws Exception {
        int[] sortedData = {1, 2, 4, 5};
        int value = 3;

        assertSearchA(2, sortedData, value, false);
    }


    @Test
    public void searchA_givenValueNotInArray_givenValueInMiddle_givenLowerFalse() throws Exception {
        int[] sortedData = {1, 2, 4, 5};
        int value = 3;

        assertSearchA(1, sortedData, value, true);
    }

    private void assertSearchA(int expected, int[] sortedData, int value, boolean lower) {
        assertEquals(expected, searchA(sortedData, value, lower));
    }

    private void assertSearchB(Interval expected, int[] sortedData, Interval valueRange) {
        Interval actual = search(sortedData, valueRange);
        assertNotNull(actual);
        assertEquals(expected.toString(),
                actual.toString());
    }

    @Test
    public void searchB_givenRangeNull_returnsEmptyInterval() {
        int[] sortedData = {1, 2, 3};
        Interval valueRange = null;
        assertSearchB(new EmptyInterval(), sortedData, valueRange);
    }

    @Test
    public void searchB_givenEmptyArray_returnsEmptyInterval() {
        int[] sortedData = new int[0];
        Interval valueRange = new NonEmptyInterval(0, 0);
        assertSearchB(new EmptyInterval(), sortedData, valueRange);
    }

    @Test
    public void searchB_givenRangeLowerThanArray_returnsEmptyInterval() {
        int[] sortedData = {1, 2, 3};
        Interval valueRange = new NonEmptyInterval(0, 0);
        assertSearchB(new EmptyInterval(), sortedData, valueRange);
    }

    @Test
    public void searchB_givenRangeHigherThanArray_returnsEmptyInterval() {
        int[] sortedData = {1, 2, 3};
        Interval valueRange = new NonEmptyInterval(4, 5);
        assertSearchB(new EmptyInterval(), sortedData, valueRange);
    }

    @Test
    public void searchB_givenRangeNotInArray_returnsEmptyInterval() {
        int[] sortedData = {1, 2, 5, 6};
        Interval valueRange = new NonEmptyInterval(3, 4);
        assertSearchB(new EmptyInterval(), sortedData, valueRange);
    }

    @Test
    public void searchB_givenRange1InArray_returnsNonEmptyInterval() {
        int[] sortedData = {1, 2, 3};
        Interval valueRange = new NonEmptyInterval(2, 2);
        assertSearchB(new NonEmptyInterval(1, 1), sortedData, valueRange);
    }

    @Test
    public void searchB_givenRange2InArray_returnsNonEmptyInterval() {
        int[] sortedData = {1, 2, 3, 4};
        Interval valueRange = new NonEmptyInterval(2, 3);
        assertSearchB(new NonEmptyInterval(1, 2), sortedData, valueRange);
    }

    @Test
    public void searchB_givenRangeOverArray_returnsNonEmptyInterval() {
        int[] sortedData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Interval valueRange = new NonEmptyInterval(7, 30);
        assertSearchB(new NonEmptyInterval(6, 9), sortedData, valueRange);
    }

    @Test
    public void searchB_givenRangeUnderArray_returnsNonEmptyInterval() {
        int[] sortedData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Interval valueRange = new NonEmptyInterval(-7, 4);
        assertSearchB(new NonEmptyInterval(0, 3), sortedData, valueRange);
    }
}