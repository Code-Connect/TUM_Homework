package pgdp16.blatt08.pgdp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
    private static final int NUMBER = 007;
    private static final String FNAME = "James B.";
    private static final String SNAME = "Ond";
    private BankAccount test;
    private String underTest;

    @Before
    public void setUp() throws Exception {
        test = new BankAccount(NUMBER, FNAME, SNAME);
    }

    @Test
    public void constructor() throws Exception {
        assertCent(0);
    }

    private void assertCent(int expected) {
        assertAccount(expected, NUMBER, FNAME, SNAME);
    }

    private void assertAccount(int cent, int number, String fname, String sname) {
        Money balance = test.getBalance();
        Assert.assertEquals(messageFunction("getAccountnumber()"), number, test.getAccountnumber());
        Assert.assertEquals(messageFunction("getFirstname()"), fname, test.getFirstname());
        Assert.assertEquals(messageFunction("getSurname()"), sname, test.getSurname());
        Assert.assertNotNull("getBalance() shall not return null!", balance);
        Assert.assertEquals(messageFunction(underTest != null ? underTest : "getCent()"), cent, balance.getCent());
    }

    private String messageFunction(String function) {
        return "Check the Function: " + function;
    }

    @Test
    public void addMoney_givenEmpty_given10_thenCentIs10() throws Exception {
        assertAddMoney(10);
    }

    @Test
    public void addMoney_given10_given30_thenCentIs40() throws Exception {
        assertAddMoney(10, 30);
    }

    private void assertAddMoney(int... input) {
        underTest = "addMoney()";
        int sum = 0;
        for (int i : input) {
            sum += i;
            test.addMoney(new Money(i));
        }
        assertCent(sum);
    }

    @Test
    public void toString_returnsSomething() throws Exception {
        Assert.assertNotNull("toString() shall return something!",test.toString());

        assertToStringContains(NUMBER);
        assertToStringContains(FNAME);
        assertToStringContains(SNAME);
        assertToStringContains(new Money(0));
    }

    private void assertToStringContains(Object contains) {
        Assert.assertTrue("toString() shall contain: " + contains,test.toString().contains(contains +""));
    }
}