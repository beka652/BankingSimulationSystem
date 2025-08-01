package BankingSystem.View.Events;

import java.util.EventObject;

public class WithdrawEvent extends EventObject {

    private String accountNumber;
    private String amount;

    public WithdrawEvent(Object source, String accountNumber, String amount) {
        super(source);
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
