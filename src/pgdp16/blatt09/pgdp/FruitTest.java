package pgdp16.blatt09.pgdp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FruitTest {
    private final String testClass;
    private final Fruit test;
    private final boolean expIsApple;
    private final boolean expIsBanana;
    private final boolean expIsPineapple;
    private final int expShelfLife;

    public FruitTest(String testClass, Fruit test, boolean expIsApple, boolean expIsBanana, boolean expIsPineapple, int expShelfLife) {
        this.testClass = testClass;
        this.test = test;
        this.expIsApple = expIsApple;
        this.expIsBanana = expIsBanana;
        this.expIsPineapple = expIsPineapple;
        this.expShelfLife = expShelfLife;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Fruit", new Fruit(), false, false, false, -1},
                {"Apple", new Apple(), true, false, false, 30},
                {"GrannySmith", new GrannySmith(), true, false, false, 50},
                {"PinkLady", new PinkLady(), true, false, false, 20},
                {"Banana", new Banana(), false, true, false, 7},
                {"Pineapple", new Pineapple(), false, false, true, 20},
        });
    }

    @Test
    public void isApple() throws Exception {
        Assert.assertEquals(messageMethod("isApple()"), expIsApple, test.isApple());
    }

    @Test
    public void isBanana() throws Exception {
        Assert.assertEquals(messageMethod("isBanana()"), expIsBanana, test.isBanana());
    }

    @Test
    public void isPineapple() throws Exception {
        Assert.assertEquals(messageMethod("isPineapple()"), expIsPineapple, test.isPineapple());
    }

    @Test
    public void shelfLife() throws Exception {
        Assert.assertEquals(messageMethod("shelfLife()"), expShelfLife, test.shelfLife());
    }

    private String messageMethod(String method) {
        return "There is something wrong with " + testClass + "." + method + ":";
    }


}