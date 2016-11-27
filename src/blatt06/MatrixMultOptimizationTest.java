package blatt06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MatrixMultOptimizationTest {
    private final int expected;
    private final int[][] input;

    public MatrixMultOptimizationTest(int expected, int[][] input) {
        this.expected = expected;
        this.input = input;
    }

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
        });
    }

    @Test
    public void f() throws Exception {
        Assert.assertEquals(expected, MatrixMultOptimization.f(input));
    }
}