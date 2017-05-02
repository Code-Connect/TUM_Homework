package pgdp16.blatt06;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ToolboxTest {

    private static final int TEST_RANGE = 10000;
    private static final int TEST_INCREMENT = randomInt(1000);

    private static int randomInt(int range) {
        return (int) (Math.random() * range);
    }

    @Test
    public void evenSum_given0_returns0() throws Exception {
        assertEvenSum(0, 0);
    }

    @Test
    public void evenSum_given1_returns0() throws Exception {
        int expected = 0;
        int input = 1;
        assertEvenSum(expected, input);
    }

    private void assertEvenSum(int expected, int input) {
        printAssertMessage(expected, Toolbox.evenSum(input),input);
        printAssertMessage(-expected, Toolbox.evenSum(-input),input);
    }

    private void printAssertMessage(int expected, int actual, int... inputs) {
        Assert.assertEquals(
                inputToString(expected, inputs),
                inputToString(actual, inputs));
    }

    private String inputToString(int expected, int[] inputs) {
        return "given: " + Arrays.toString(inputs) + "; returns: " + expected;
    }

    @Test
    public void evenSum_given8_returns20() throws Exception {
        assertEvenSum(20, 8);
    }

    @Test
    public void evenSum_given9_returns20() throws Exception {
        assertEvenSum(20, 9);
    }

    @Test
    public void evenSum_givenAllPossibilities() throws Exception {
        for (int n = 0; n <= TEST_RANGE; n += TEST_INCREMENT) assertEvenSum(evenSum(n), n);
    }

    private int evenSum(int n) {
        return n / 2 * (n / 2 + 1);
    }

    @Test
    public void multiplication_given0() throws Exception {
        assertMultiplication(0, 33);
    }

    @Test
    public void multiplication_givenAllPossibilities() throws Exception {
        for (int x = 0; x <= TEST_RANGE; x += TEST_INCREMENT)
            for (int y = 0; y <= TEST_RANGE; y += TEST_INCREMENT)
                assertMultiplication(x, y);
    }

    private void assertMultiplication(int x, int y) {
        printAssertMessage(x * y, Toolbox.multiplication(x, y),x,y);
        printAssertMessage(y * x, Toolbox.multiplication(y, x),x,y);
    }

    @Test
    public void reverse_given01_thenMakes10() throws Exception {
        int[] arr = {0, 1};
        assertReverse(arr);
    }

    @Test
    public void reverse_given012_thenMakes210() throws Exception {
        int[] arr = {0, 1, 2};
        assertReverse(arr);
    }

    @Test
    public void reverse_givenRandom_thenReverse() throws Exception {
        int[] arr = new int[randomInt(ToolboxTest.TEST_RANGE)];
        for (int i = 0; i < arr.length; i++) arr[i] = randomInt(ToolboxTest.TEST_RANGE);
        assertReverse(arr);
    }

    private void assertReverse(int[] arr) {
        String expected = (reverse(arr)).toString();
        Toolbox.reverse(arr);
        Assert.assertEquals(expected, Arrays.toString(arr));
    }

    private ArrayList<Integer> reverse(int[] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int anArr : arr) out.add(anArr);
        Collections.reverse(out);
        return out;
    }

    @Test
    public void numberOfOddIntegers_givenEmptyArray_returns0() throws Exception {
        assertNumberOfOddIntegers(new int[]{}, 0);
    }

    private void assertNumberOfOddIntegers(int[] m, int expected) {
        printAssertMessage(expected, Toolbox.numberOfOddIntegers(m),m);
    }

    @Test
    public void numberOfOddIntegers_given474251N50N4N3_returns5() throws Exception {
        assertNumberOfOddIntegers(new int[]{4, 7, 42, 5, 1, -5, 0, -4, -3}, 5);
    }

    @Test
    public void filterOdd_given474251N50N4N3_returns751N5N3() throws Exception {
        assertFilterOdd(new int[]{4, 7, 42, 5, 1, -5, 0, -4, -3},
                new int[]{7, 5, 1, -5, -3});
    }

    private void assertFilterOdd(int[] m, int[] expected) {
        Assert.assertArrayEquals(expected, Toolbox.filterOdd(m));
    }


}