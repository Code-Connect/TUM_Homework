package gad17.blatt06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.LongStream;

@RunWith(Parameterized.class)
public class SortTest {
    private static final long RANGE = 10000;
    private SortingBase test;

    public SortTest(String name, SortingBase test) {
        this.test = test;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"MergeSort", new Mergesort()},
                {"QuickSort", new Quicksort()},
        });
    }

    @Test(expected = Exception.class)
    public void sort_inputNull_throwsException() throws Exception {
        test.sort(null);
    }

    private void assertSort(int[] input, int[] expected) {
        test.sort(input);
        Assert.assertArrayEquals(expected, input);
    }

    @Test
    public void sort_inputEmptyArray_thenNothingHappens() throws Exception {
        assertSort(new int[]{}, new int[]{});
    }

    @Test
    public void sort_input1_thenNothingHappens() throws Exception {
        assertSort(new int[]{1}, new int[]{1});
    }

    @Test
    public void sort_input21_thenArrayIs12() throws Exception {
        assertSort(new int[]{2, 1}, new int[]{1, 2});
    }

    @Test
    public void sort_input132_thenArrayIs123() throws Exception {
        assertSort(new int[]{1, 3, 2}, new int[]{1, 2, 3});
    }

    @Test
    public void sort_input312_thenArrayIs123() throws Exception {
        assertSort(new int[]{3, 1, 2}, new int[]{1, 2, 3});
    }

    @Test
    public void sort_input2143_thenArrayIs1234() throws Exception {
        assertSort(new int[]{2, 1, 4, 3}, new int[]{1, 2, 3, 4});
    }

    @Test
    public void sort_input21435_thenArrayIs12345() throws Exception {
        assertSort(new int[]{2, 1, 4, 3, 5}, new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void sort_input54321_thenArrayIs12345() throws Exception {
        assertSort(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void sort_inputWithNegative_thenSortArray() throws Exception {
        assertSort(new int[]{-5, 4, -3, 2, -1}, new int[]{-5, -3, -1, 2, 4});
    }

    @Test
    public void sort_inputRandom() throws Exception {
        int[] input = LongStream.range(0, RANGE).mapToInt(l
                -> (int) (Math.random() * Integer.MAX_VALUE)).toArray();
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        assertSort(input, expected);
    }

    @Test
    public void sort_inputSorted() throws Exception {
        int[] input = LongStream.range(0, RANGE).mapToInt(l
                -> (int) (l)).toArray();
        assertSort(input, input);
    }

    @Test
    public void sort_inputReverse() throws Exception {
        int[] input = LongStream.range(0, RANGE).mapToInt(l
                -> (int) (Integer.MAX_VALUE - l)).toArray();
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        assertSort(input, expected);
    }
}