package pgdp16.blatt08;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MatrixMultOptMemoizationTest extends MatrixMultOptMemoization {
    private final int expected;
    private final int[][] input;
    private ArrayList<Integer[]> fInputs;

    public MatrixMultOptMemoizationTest(int expected, int[][] input) {
        this.expected = expected;
        this.input = input;
        fInputs = new ArrayList<>();
    }

    //TODO invalid Input
    @Parameterized.Parameters(name = "expected: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, new int[0][0]},
                {0, new int[][]{{}}},
                {0, new int[][]{{10, 30}}},
                {0, new int[][]{{30, 5}}},
                {0, new int[][]{{5, 60}}},
                {1, new int[][]{{1, 1}, {1, 1}}},
                {8, new int[][]{{2, 2}, {2, 2}}},
                {4500, new int[][]{{10, 30}, {30, 5}, {5, 60}}},
                {286110, new int[][]{{85, 42}, {42, 33}, {33, 60}}},
                {500610, new int[][]{{85, 42}, {42, 33}, {33, 60}, {60, 80}}},
                {13345101, new int[][]{{251, 99}, {99, 666}, {666, 147}}},
                {798200504, new int[][]{{420, 69}, {69, 1337}, {1337, 9001}}},
                {428, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 10}}},
                {3325056, new int[][]{{198,116}, {116,322}, {322,74}, {74,492}, {492,257}, {257,227}, {227,491}, {491,7}, {7,294}}}
        });
    }

    @Test
    public void f() throws Exception {
        Assert.assertEquals(expected, this.f(input));
    }

    @Override
    public int f(int[][] mm, int i, int j) {
        Integer[] array = {i, j};
        assertDuplicates(i, j);
        fInputs.add(array);
        return super.f(mm, i, j);
    }

    private void assertDuplicates(int i, int j) {
        for (Integer[] fInput : fInputs) {
            if (fInput[0] == i && fInput[1] == j)
                Assert.fail("i: " + i + " and j: " + j +
                        " combined were already calculated ");
        }
    }
}
