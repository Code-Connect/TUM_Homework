package  gad17.blatt07;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RadixSortTest {
    private RadixSort test;

    @Before
    public void setUp() throws Exception {
        test = new RadixSort();
    }

    private int key(Integer element, int digit) throws Exception {
        Method method = test.getClass().getDeclaredMethod("key", Integer.class, int.class);
        method.setAccessible(true);
        return (int) method.invoke(test, element, digit);
    }

    private void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) throws Exception {
        Method method = test.getClass().getDeclaredMethod("concatenate", ArrayList[].class, Integer[].class);
        method.setAccessible(true);
        method.invoke(test, buckets, elements);
    }

    private void kSort(Integer[] elements, int digit) throws Exception {
        Method method = test.getClass().getDeclaredMethod("kSort", Integer[].class, int.class);
        method.setAccessible(true);
        method.invoke(test, elements, digit);
    }


    @Test(expected = Exception.class)
    public void key_input_eleneg_throws_Exception() throws Exception {
        key(-1, 1);
    }

    @Test(expected = Exception.class)
    public void key_input_digneg_throws_Exception() throws Exception {
        key(1, -1);
    }

    @Test
    public void key_input0_0_return0() throws Exception {
        int actual = key(0, 0);
        Assert.assertEquals(0, actual);
    }

    @Test
    public void key_input10_1_return1() throws Exception {
        int actual = key(10, 1);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void key_input123456_2_return4() throws Exception {
        int actual = key(123456, 2);
        Assert.assertEquals(4, actual);
    }

    @Test
    public void key_input123456_3_return3() throws Exception {
        int actual = key(123456, 3);
        Assert.assertEquals(3, actual);
    }

    @Test
    public void key_input123456_4_return2() throws Exception {
        int actual = key(123456, 4);
        Assert.assertEquals(2, actual);
    }

    @Test
    public void key_input003_2_return0() throws Exception {
        int actual = key(003, 2);
        Assert.assertEquals(0, actual);
    }

    @Test(expected = Exception.class)
    public void concatenate_inputNull_null_throws_Exception() throws Exception {
        concatenate(null, null);
    }

    @Test(expected = Exception.class)
    public void concatenate_inputNull_elementsValid_throws_Exception() throws Exception {
        concatenate(null, new Integer[0]);
    }

    @Test(expected = Exception.class)
    public void concatenate_bucketsValid_elementsNull_throws_Exception() throws Exception {
        concatenate(new ArrayList[0], null);
    }

    @Test(expected = Exception.class)
    public void concatenate_bucketsWithOneElement_elementsEmpty_throws_Exception() throws Exception {
        concatenate(buildBuckets(new Integer[]{1}), new Integer[0]);
    }

    private ArrayList[] buildBuckets(Integer[]... buckets) {
        List<Integer>[] out = (List<Integer>[])
                Array.newInstance(ArrayList.class, buckets.length);
        for (int i = 0; i < buckets.length; i++)
            out[i] = new ArrayList<>(Arrays.asList(buckets[i]));
        return (ArrayList[]) out;
    }

    @Test(expected = Exception.class)
    public void concatenate_bucketsWithTwoElement_elementsSizeOne_throws_Exception() throws Exception {
        concatenate(buildBuckets(new Integer[]{1, 2}), new Integer[1]);
    }

    @Test(expected = Exception.class)
    public void concatenate_bucketsWithOneElement_elementsSizeTwo_throws_Exception() throws Exception {
        concatenate(buildBuckets(new Integer[]{1}), new Integer[2]);
    }

    @Test
    public void concatenate_input_bucketsEmpty_elementsEmpty_then_nothingHappens() throws Exception {
        concatenate(new ArrayList[0], new Integer[0]);
    }

    @Test
    public void concatenate_input_bucketsOne_elementsOne_then_nothingHappens() throws Exception {
        assertConcatenate(new Integer[]{1}, new Integer[]{1});
    }

    private void assertConcatenate(Integer[] expected, Integer[]... input) throws Exception {
        Integer[] elements = new Integer[expected.length];
        concatenate(buildBuckets(input), elements);
        Assert.assertArrayEquals(expected, elements);
    }

    @Test
    public void concatenate_input_sortedBucketsOneWithTwoElements_elementsTwo_then_nothingHappens() throws Exception {
        assertConcatenate(new Integer[]{1, 2}, new Integer[]{1, 2});
    }

    @Test
    public void concatenate_input_bucketsTwo_elementsTwo() throws Exception {
        assertConcatenate(new Integer[]{1, 2}, new Integer[]{1}, new Integer[]{2});
    }

    @Test
    public void concatenate_input_bucketsFour_elementsSeven() throws Exception {
        assertConcatenate(new Integer[]{012, 112, 203, 003, 074, 024, 017},
                new Integer[][]{{012, 112}, {203, 003}, {074, 024}, {017}});
    }

    private void assertKSort(Integer[] elements, int digit, Integer[] expecteds) throws Exception {
        kSort(elements, digit);
        Assert.assertArrayEquals(expecteds, elements);
    }

    @Test(expected = Exception.class)
    public void kSort_input_null_0_throws_Exception() throws Exception {
        kSort(null, 0);
    }

    @Test(expected = Exception.class)
    public void kSort_input_elementsvalid_digitneg_throws_Exception() throws Exception {
        kSort(new Integer[0], -1);
    }

    @Test
    public void kSort_input_elementsEmpty_digit1_then_nothingHappens() throws Exception {
        assertKSort(new Integer[0], 1, new Integer[0]);
    }

    @Test
    public void kSort_input_elements1_digit0_then_nothingHappens() throws Exception {
        assertKSort(new Integer[]{1}, 0, new Integer[]{1});
    }

    @Test
    public void kSort_input_elements21_digit0_then_elementsIs12() throws Exception {
        assertKSort(new Integer[]{2, 1}, 0, new Integer[]{1, 2});
    }

    @Test
    public void kSort_input_elements20_10_30_digit1_elementsIs102030() throws Exception {
        assertKSort(new Integer[]{20, 10, 30}, 1, new Integer[]{10, 20, 30});
    }

    @Test
    public void kSort_integration() throws Exception {
        assertKSort(new Integer[]{12, 203, 3, 74, 24, 17, 112},
                0, new Integer[]{12, 112, 203, 3, 74, 24, 17});
    }

    @Test(expected = Exception.class)
    public void sort_inputnull_throws_Exception() throws Exception {
        test.sort(null);
    }

    @Test
    public void sort_input_elementsEmpty_then_nothingHappens() throws Exception {
        assertSort(new Integer[0], new Integer[0]);
    }

    @Test
    public void sort_input_elements1_then_nothingHappens() throws Exception {
        assertSort(new Integer[]{1}, new Integer[]{1});
    }

    @Test
    public void sort_input_elements21_then_ElementIs12() throws Exception {

        assertSort(new Integer[]{2, 1}, new Integer[]{1, 2});
    }

    @Test
    public void sort_input_elements20_10_30_then_ElementIs10_20_30() throws Exception {
        assertSort(new Integer[]{20, 10, 30}, new Integer[]{10, 20, 30});
    }


    @Test
    public void sort_integration() throws Exception {
        assertSort(new Integer[]{12, 203, 3, 74, 24, 17, 112},
                new Integer[]{3, 12, 17, 24, 74, 112, 203});
    }

    private void assertSort(Integer[] elements, Integer[] expecteds) {
        test.sort(elements);
        Assert.assertArrayEquals(expecteds, elements);
    }

}