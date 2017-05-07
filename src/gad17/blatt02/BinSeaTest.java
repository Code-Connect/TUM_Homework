package gad17.blatt02;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BinSeaTest {

    @Test
    public void testSearch() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = BinSea.runSearch(test, 80, true);
        int upper = BinSea.runSearch(test, 80, false);

        assertEquals(2, lower);
        assertEquals(3, upper);
    }

    @Test
    public void testSearchGivenBiggerNumberExpectNotFound() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = BinSea.runSearch(test, 4300, true);
        int upper = BinSea.runSearch(test, 4300, false);

        assertEquals(-1, lower);
        assertEquals(-1, upper);
    }

    @Test
    public void testSearchGivenSmallerNumberExpectNotFound() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        int lower = BinSea.runSearch(test, -100, true);
        int upper = BinSea.runSearch(test, -100, false);

        assertEquals(-1, lower);
        assertEquals(-1, upper);
    }

    @Test
    public void testIntervalSearch() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        Interval testInterval = new NonEmptyInterval(80, 700);
        Interval actual = BinSea.runSearch(test, testInterval);
        Interval expected = new NonEmptyInterval(3, 4);

        assertNotNull(actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testIntervalSearchExpectEmptyInterval() {
        int[] test = {-10, 33, 50, 99, 123, 4242};
        Interval testInterval = new NonEmptyInterval(4500, 5000);
        Interval actual = BinSea.runSearch(test, testInterval);

        assert actual instanceof EmptyInterval;
    }
}