package blatt08.pgdp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    private Money test;

    private static int randomInt(int range) {
        return (int) (Math.random() * range);
    }

    private static int randomInt() {
        return randomInt(Integer.MAX_VALUE);
    }

    @Before
    public void setUp() throws Exception {
        test = new Money();
    }

    @Test
    public void constructor_givenEmpty_thenConstructWith0() throws Exception {
        int expected = 0;
        assertMoney(expected);
    }

    private void assertMoney(int expected) {
        Assert.assertEquals("check the Variable: cent", expected, test.getCent());
    }

    @Test
    public void constructor_given10_thenCentIs10() throws Exception {
        assertConstructor(10);
    }

    private void assertConstructor(int input) {
        test = new Money(input);
        assertMoney(input);
    }

    @Test
    public void constructor_givenRandom() throws Exception {
        assertConstructor(randomInt());
    }

    @Test
    public void addMoney_given10_returns10() throws Exception {
        assertAddMoney(10, 10);
    }

    private void assertAddMoney(int expected, int addition) {
        int oldCent = test.getCent();
        Money output = test.addMoney(new Money(addition));
        String function = "addMoney(" + addition + ")";

        Assert.assertNotNull(function + " shall not return null!", output);
        Assert.assertEquals(messageFunction("addMoney(" + addition + ")" +
                        "; given cent == " + oldCent),
                expected, output.getCent());
        Assert.assertEquals("Money Shell be Immutable!", oldCent, test.getCent());
    }

    @Test
    public void addMoney_given10_given30_returns10() throws Exception {
        test = new Money(10);
        assertAddMoney(40, 30);
    }

    @Test
    public void addMoney_givenRandom_givenRandom() throws Exception {
        int start = randomInt();
        int addition = randomInt();
        test = new Money(start);
        assertAddMoney(start + addition, addition);
    }

    @Test
    public void toString_given10010_returns100_10_Euro() throws Exception {
        assertToString("100,10 Euro", 10010);
    }

    @Test
    public void toString_given12345_returns123_45_Euro() throws Exception {
        assertToString("123,45 Euro", 12345);
    }

    private void assertToString(String expected, int input) {
        Assert.assertEquals(messageFunction("toString"), expected, new Money(input).toString());
    }

    private String messageFunction(String function) {
        return "Check the Function: " + function;
    }
}