package BankingSystem.View.Events;



import java.util.EventObject;

public class CreateAccountEvent extends EventObject {

    private String name;
    private String initialDeposit;

    public CreateAccountEvent(Object source, String name, String initialDeposit){
        super(source);
        this.name = name;
        this.initialDeposit = initialDeposit;

    }

    public String getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(String initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
