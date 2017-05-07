package gad17.blatt2;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinSeaTest {

    @Test
    public void testSearch() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = BinSea.search(test, 80, true);
        int upper = BinSea.search(test, 80, false);

        assertEquals(2, lower);
        assertEquals(3, upper);
    }

    @Test
    public void testSearchGivenBiggerNumberExpectNotFound() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = BinSea.search(test, 4300, true);
        int upper = BinSea.search(test, 4300, false);

        assertEquals(-1, lower);
        assertEquals(-1, upper);
    }

    @Test
    public void testSearchGivenSmallerNumberExpectNotFound() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = BinSea.search(test, -100, true);
        int upper = BinSea.search(test, -100, false);

        assertEquals(-1, lower);
        assertEquals(-1, upper);
    }

    @Test
    public void testIntervalSearch() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        Interval testInterval = new NonEmptyInterval(80, 700);
        Interval actual = BinSea.search(test, testInterval);
        Interval expected = new NonEmptyInterval(3, 4);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testIntervalSearchExpectEmptyInterval() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        Interval testInterval = new NonEmptyInterval(4500, 5000);
        Interval actual = BinSea.search(test, testInterval);

        assert actual instanceof EmptyInterval;
    }
}