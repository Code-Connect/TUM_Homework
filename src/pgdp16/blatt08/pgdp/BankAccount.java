package pgdp16.blatt08.pgdp; //TODO move to package: pgdp

public class BankAccount {
    private int bankaccount;
    private String firstname;
    private String surname;
    private Money balance;

    public BankAccount(int accountnumber, String fname, String sname) {
        this.bankaccount = accountnumber;
        this.firstname = fname;
        this.surname = sname;
        //TODO
    }

    public int getAccountnumber() {
        return bankaccount;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public Money getBalance() {
        return balance;
    }

    public void addMoney(Money m) {
        //TODO
    }

    public String toString() {
        //TODO
        return null;
    }
}
