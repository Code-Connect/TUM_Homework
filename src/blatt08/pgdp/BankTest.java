package blatt08.pgdp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
    private static final String FNAME = "First";
    private static final String SNAME = "Last";
    private Bank test;

    @Before
    public void setUp() throws Exception {
        test = new Bank();

    }

    @Test
    public void newAccount_givenFirst_returns0() throws Exception {
        assertNewAccount(0, FNAME, SNAME);
    }

    private void assertNewAccount(int expected, String firstName, String lastName) {
        Assert.assertEquals(messageFunction("newAccount()"), expected,
                test.newAccount(firstName, lastName));
        Assert.assertNotNull("accounts shall not be null!", test.accounts);
        Assert.assertNotNull("accounts.info shall not be null!", test.accounts.info);

        assertAccount(expected, firstName, lastName);
        assertGetBalance(0, expected);
    }

    private void assertAccount(int expected, String firstName, String lastName) {
        int counter = 0;
        Bank.BankAccountList account = test.accounts;
        while (account.next != null) {
            account = account.next;
            counter++;
        }
        Assert.assertEquals("Check the number of Entries in accounts", expected, counter);
        Assert.assertEquals(messageVariable("firstname"), firstName, account.info.getFirstname());
        Assert.assertEquals(messageVariable("surname"), lastName, account.info.getSurname());
        Assert.assertEquals(messageVariable("bankaccount"), expected, account.info.getAccountnumber());
    }

    private String messageVariable(String var) {
        return "Check the Variable: " + var;
    }

    @Test
    public void newAccount_create5_returns01234() throws Exception {
        assertMultipleNewAccounts(0, 1, 2, 3, 4);
    }

    private void assertMultipleNewAccounts(int... expected) {
        for (int i : expected) {
            assertNewAccount(i, FNAME, SNAME);
        }
    }

    @Test
    public void removeAccount_givenEmpty_thenNothingHappens() throws Exception {
        assertRemoveAccount(0);
    }

    private void assertRemoveAccount(int number) {
        String expected = accountsToString(test.accounts).replaceFirst(number + "", "");
        test.removeAccount(number);
        Assert.assertEquals("There is something wrong with your Accounts",
                expected, accountsToString(test.accounts));
    }

    private String accountsToString(Bank.BankAccountList accounts) {
        if (accounts == null) return "null";

        String out = "";
        Bank.BankAccountList tmp = accounts;
        while (tmp.next != null) {
            out += tmp.info.getAccountnumber();
            tmp = tmp.next;
        }
        out += tmp.info.getAccountnumber();

        return out;
    }

    @Test
    public void removeAccount_given01234_givenLastAccount_removesIndex4FormList() throws Exception {
        assertMultipleNewAccounts(0, 1, 2, 3, 4);
        assertRemoveAccount(4);
    }

    @Test
    public void removeAccount_given01234_givenFirstAccount_removesIndex0FormList() throws Exception {
        assertMultipleNewAccounts(0, 1, 2, 3, 4);
        assertRemoveAccount(0);
    }

    @Test
    public void removeAccount_givenNumberNotFound_thenNothingHappens() throws Exception {
        assertMultipleNewAccounts(0, 1, 2, 3, 4);
        assertRemoveAccount(10);
    }

    @Test
    public void getBalance_givenEmpty_returnsNull() throws Exception {
        assertGetBalance(null, -1);
    }

    private void assertGetBalance(Money expected, int accountNumber) {
        Assert.assertEquals("Check the Function: " + "getBalance(" + accountNumber + ")",
                expected + "", test.getBalance(accountNumber) + "");

    }

    private void assertGetBalance(int expected, int accountNumber) {
        assertGetBalance(new Money(expected), accountNumber);
    }

    @Test
    public void getBalance_givenNotFound_returnsNull() throws Exception {
        assertMultipleNewAccounts(0, 1, 2);
        assertGetBalance(null, 10);
    }

    @Test
    public void getBalance_givenNewAccount_givenFirstInList_returns0() throws Exception {
        assertMultipleNewAccounts(0);
        assertGetBalance(new Money(0), 0);
    }

    @Test
    public void getBalance_givenNewAccount_returns0() throws Exception {
        assertMultipleNewAccounts(0, 1, 2, 3);
        assertGetBalance(new Money(), 2);
    }

    @Test
    public void depositOrWithdraw_givenAccountNotFound_returnsFalse() throws Exception {
        assertDepositOrWithdraw(false, -1, 10);
    }

    private void assertDepositOrWithdraw(boolean expected, int number, int amount) {
        String function = "depositOrWithdraw(" + amount + ")";
        Assert.assertEquals(messageFunction(function),
                expected, test.depositOrWithdraw(number, new Money(amount)));
    }

    private String messageFunction(String function) {
        return "Check the Function: " + function;
    }

    @Test
    public void depositOrWithdraw_given10_givenAccountFound_returnsTrue() throws Exception {
        assertMultipleNewAccounts(0);
        assertDepositOrWithdraw(true, 0, 10);
        assertGetBalance(10, 0);
    }

    @Test
    public void transfer_givenFromNotFound_returnsFalse() throws Exception {
        assertMultipleNewAccounts(0);
        assertTransfer(false, 0, -1, 10);
    }

    private void assertTransfer(boolean expected, int from, int to, int amount) {
        Assert.assertEquals(messageFunction("transfer(" + from + ", " + to + ", " + amount + ")"), expected, test.transfer(from, to, new Money(amount)));
    }

    @Test
    public void transfer_givenToNotFound_returnsFalse() throws Exception {
        assertMultipleNewAccounts(0);
        assertTransfer(false, -1, 0, 10);
    }

    @Test
    public void transfer_givenFirstAccounts_givenPositiveAmount_returnsTrue() throws Exception {
        assertMultipleNewAccounts(0, 1);
        assertTransfer(true, 0, 1, 10);
        assertGetBalance(10, 1);
        assertGetBalance(-10, 0);
    }

    @Test
    public void transfer_givenAccountsFound_givenPositiveAmount_returnsTrue() throws Exception {
        assertMultipleNewAccounts(0, 1, 2, 3, 4, 5);
        assertTransfer(true, 4, 2, 110);
        assertGetBalance(110, 2);
        assertGetBalance(-110, 4);
    }

    @Test
    public void transfer_givenFirstAccounts_givenNegativeAmount_returnsTrue() throws Exception {
        assertMultipleNewAccounts(0, 1);
        assertTransfer(true, 0, 1, -10);
        assertGetBalance(10, 0);
        assertGetBalance(-10, 1);
    }
}