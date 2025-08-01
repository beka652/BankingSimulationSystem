package BankingSystem.View.Events;

import java.util.EventObject;

public class DeleteEvent extends EventObject {

    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public DeleteEvent(Object source, String accountNumber) {
        super(source);
        this.accountNumber = accountNumber;
    }
}
