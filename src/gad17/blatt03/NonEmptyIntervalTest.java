package gad17.blatt03;

import org.junit.Assert;
import org.junit.Test;

public class NonEmptyIntervalTest {
    @Test
    public void isEmpty_returnsFalse() throws Exception {
        NonEmptyInterval test = new NonEmptyInterval(0, 0);
        Assert.assertFalse(test.isEmpty());
    }

    @Test
    public void getSize_input10_givenFrom1To4_returns4() throws Exception {
        NonEmptyInterval test = new NonEmptyInterval(1, 4);
        Assert.assertEquals(4, test.getSize(10));
    }

    @Test
    public void getSize_input10_givenFrom4To1_returns4() throws Exception {
        NonEmptyInterval test = new NonEmptyInterval(4, 1);
        Assert.assertEquals(8, test.getSize(10));
    }
}