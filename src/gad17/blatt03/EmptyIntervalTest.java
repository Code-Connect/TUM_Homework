package gad17.blatt03;

import org.junit.Assert;
import org.junit.Test;

public class EmptyIntervalTest {
    @Test
    public void isEmpty_returnsFalse() throws Exception {
        EmptyInterval test = new EmptyInterval();
        Assert.assertTrue(test.isEmpty());
    }

    @Test
    public void getSize_returns0() throws Exception {
        EmptyInterval test = new EmptyInterval();
        Assert.assertEquals(0, test.getSize(10));
    }
}