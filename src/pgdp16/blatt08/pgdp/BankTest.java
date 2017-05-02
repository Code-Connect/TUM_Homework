package pgdp16.blatt08.pgdp;

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

    private int assertNewAccount(int lastIndex, String firstName, String lastName) {
        int actual = test.newAccount(firstName, lastName);

        Assert.assertNotNull("accounts shall not be null!", test.accounts);
        Assert.assertNotNull("accounts.info shall not be null!", test.accounts.info);

        assertAccountNumber();
        assertAccount(lastIndex, firstName, lastName, actual);
        assertGetBalance(0, actual);
        return actual;
    }

    private void assertAccountNumber() {
        String list = accountsToString(test.accounts);

        String[] split = list.split(",");
        if (split.length > 1)
            for (int i = 0; i < split.length - 1; i++) {
                list = list.substring(split[i].length() + 1);
                Assert.assertFalse("Every AccountNumber shall exist only once!"
                        + "\nAccounts: " + accountsToString(test.accounts), list.contains(split[i] + ""));
            }
    }

    private void assertAccount(int lastIndex, String firstName, String lastName, int number) {
        int counter = 0;
        Bank.BankAccountList account = test.accounts;
        while (account.next != null) {
            account = account.next;
            counter++;
        }
        Assert.assertEquals("Check the number of Entries in accounts", lastIndex, counter);
        Assert.assertEquals(messageVariable("firstname"), firstName, account.info.getFirstname());
        Assert.assertEquals(messageVariable("surname"), lastName, account.info.getSurname());
        Assert.assertEquals(messageVariable("bankaccount"), number, account.info.getAccountnumber());
    }

    private String messageVariable(String var) {
        return "Check the Variable: " + var;
    }

    @Test
    public void newAccount_create5_returns01234() throws Exception {
        assertMultipleNewAccounts(0, 1, 2, 3, 4);
    }

    private int[] assertMultipleNewAccounts(int... expected) {
        int[] out = new int[expected.length];
        for (int i = 0; i < expected.length; i++) {
            out[i] = assertNewAccount(expected[i], FNAME, SNAME);
        }
        return out;
    }

    @Test
    public void removeAccount_givenEmpty_thenNothingHappens() throws Exception {
        assertRemoveAccountNotNull(0);
    }

    private void assertRemoveAccount(int number, String expected) {
        test.removeAccount(number);
        Assert.assertEquals("There is something wrong with your Accounts",
                expected, accountsToString(test.accounts));
    }

    private String accountsToString(Bank.BankAccountList accounts) {
        if (accounts == null) return "null";

        String out = "";
        Bank.BankAccountList tmp = accounts;
        while (tmp.next != null) {
            out += tmp.info.getAccountnumber() + ",";
            tmp = tmp.next;
        }
        out += tmp.info.getAccountnumber();

        return out;
    }

    @Test
    public void removeAccount_givenOneAccount_thenAccountsIsNull() throws Exception {
        int number = assertNewAccount(0);
        assertRemoveAccountNull(number);
    }

    @Test
    public void integration_givenRemove_givenNewAccount() throws Exception {
        int number = assertNewAccount(0);
        assertRemoveAccountNull(number);
        assertNewAccount(0);
    }

    private void assertRemoveAccountNull(int number) {
        assertRemoveAccount(number, "null");
    }

    @Test
    public void removeAccount_given01234_givenLastAccount_removesIndex4FormList() throws Exception {
        int[] accounts = assertMultipleNewAccounts(0, 1, 2, 3, 4);
        assertRemoveAccountNotNull(accounts[4]);
    }

    private void assertRemoveAccountNotNull(int number) {
        assertRemoveAccount(number, accountsToString(test.accounts).replace(number + ",", "").replace("," + number, ""));
    }

    @Test
    public void removeAccount_given01234_givenFirstAccount_removesIndex0FormList() throws Exception {
        int number = assertNewAccount(0);
        assertMultipleNewAccounts(1, 2, 3, 4);
        assertRemoveAccountNotNull(number);
    }

    @Test
    public void removeAccount_givenNumberNotFound_thenNothingHappens() throws Exception {
        int[] numbers = assertMultipleNewAccounts(0, 1, 2, 3, 4);
        assertRemoveAccountNotNull(numbers[4] + 1);
    }

    @Test
    public void removeAccount_givenAllAccounts_givenLastToFirst_thenListIsNull() throws Exception {
        int[] numbers = assertMultipleNewAccounts(0, 1, 2, 3, 4);
        for (int i = 4; i > 0; i--) assertRemoveAccountNotNull(numbers[i]);
        assertRemoveAccountNull(numbers[0]);
    }

    @Test
    public void removeAccount_givenAllAccounts_givenFirstToLast_thenListIsNull() throws Exception {
        int[] numbers = assertMultipleNewAccounts(0, 1, 2, 3, 4);
        for (int i = 0; i < 4; i++) assertRemoveAccountNotNull(numbers[i]);
        assertRemoveAccountNull(numbers[4]);
    }

    @Test
    public void removeAccount_givenAllAccountsExceptTheFirst_givenFirstToLast_thenListHasFirstElement() throws Exception {
        int[] numbers = assertMultipleNewAccounts(0, 1, 2, 3, 4);
        for (int i = 1; i <= 4; i++) assertRemoveAccountNotNull(numbers[i]);
    }

    @Test
    public void removeAccount_given5Accounts_remove2ThenRemove1_Returns034() throws Exception {
        int[] numbers = assertMultipleNewAccounts(0, 1, 2, 3, 4);
        assertRemoveAccountNotNull(numbers[2]);
        assertRemoveAccountNotNull(numbers[1]);
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
        int[] accounts = assertMultipleNewAccounts(0, 1, 2);
        assertGetBalance(null, accounts[2] + 1);
    }

    @Test
    public void getBalance_givenNewAccount_givenFirstInList_returns0() throws Exception {
        assertGetBalance(new Money(0), assertNewAccount(0));
    }

    private int assertNewAccount(int lastIndex) {
        return assertNewAccount(lastIndex, FNAME, SNAME);
    }

    @Test
    public void getBalance_givenNewAccount_returns0() throws Exception {
        int[] numbers = assertMultipleNewAccounts(0, 1, 2, 3);
        assertGetBalance(new Money(0), numbers[2]);
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
        int number = assertNewAccount(0);
        assertDepositOrWithdraw(true, number, 10);
        assertGetBalance(10, number);
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
        assertTransfer(false, -1, assertNewAccount(0), 10);
    }

    @Test
    public void transfer_givenFirstAccounts_givenPositiveAmount_returnsTrue() throws Exception {
        assertTransfer(true, 0, 1, 10, 0, 1);
    }

    @Test
    public void transfer_givenAccountsFound_givenPositiveAmount_returnsTrue() throws Exception {
        assertTransfer(true, 4, 2, 110, 0, 1, 2, 3, 4, 5);
    }

    @Test
    public void transfer_givenFirstAccounts_givenNegativeAmount_returnsTrue() throws Exception {
        assertTransfer(true, 0, 1, -10, 0, 1);
    }


    private void assertTransfer(boolean expected, int from, int to, int amount, int... accounts) {
        int[] numbers = assertMultipleNewAccounts(accounts);
        assertTransfer(expected, numbers[from], numbers[to], amount);
        assertGetBalance(-amount, numbers[from]);
        assertGetBalance(amount, numbers[to]);
    }
}