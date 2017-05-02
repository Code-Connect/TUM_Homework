package pgdp16.blatt08.pgdp; //TODO move to package: pgdp

public class Bank {
    /*private*/ BankAccountList accounts; //TODO make private

    public int newAccount(String firstname, String lastname) {
        //TODO
        return -1;
    }

    public void removeAccount(int accountnumber) {
        //TODO
    }

    public Money getBalance(int accountnumber) {
        //TODO
        return null;
    }

    public boolean depositOrWithdraw(int accountnumber, Money m) {
        //TODO
        return false;
    }

    public boolean transfer(int from, int to, Money m) {
        //TODO
        return false;
    }

    /*private*/ class BankAccountList {//TODO make private
        public BankAccount info;
        public BankAccountList next;
    }
}
