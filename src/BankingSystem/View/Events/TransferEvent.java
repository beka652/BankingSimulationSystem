package BankingSystem.View.Events;

import java.util.EventObject;

public class TransferEvent extends EventObject {

    private String fromAccountNumber;
    private String toAccountNumber;
    private String amount;

    public TransferEvent(Object source, String fromAccountNumber, String toAccountNumber, String amount){
        super(source);
        this.amount = amount;
        this.toAccountNumber = toAccountNumber;
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
