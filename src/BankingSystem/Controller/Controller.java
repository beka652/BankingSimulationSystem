package BankingSystem.Controller;

import BankingSystem.Model.ReportObject;
import BankingSystem.View.Events.CreateAccountEvent;
import BankingSystem.View.Events.DepositEvent;
import BankingSystem.View.Events.TransferEvent;
import BankingSystem.View.Events.WithdrawEvent;
import BankingSystem.View.Mainframe;
import BankingSystem.Model.Bank;
import BankingSystem.Exceptions.*;

import java.util.HashMap;


public class Controller {
    private Mainframe mainframe;
    private Bank bank;

    public Controller(){
        this.mainframe = new Mainframe();
        this.bank = new Bank();

        mainframe.setController(this);

    }

    public void createAccount(CreateAccountEvent event){
        try {
            String name = nameValidator(event.getName());

            double initialDeposit = amountValidator(event.getInitialDeposit());

            var report = bank.createNewAccount(name, initialDeposit);

            mainframe.setExecutionReport(report);

        } catch (Exception e) {

            var failure = new HashMap<String, String>();
            failure.put("source", "create account");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());
            System.out.println(e.getMessage());

            mainframe.setExecutionReport(failure);

        }

    }
    public void deposit(DepositEvent event){
        try {
            String accountNumber = accountNumberValidator(event.getAccountNumber());

            double amount = amountValidator(event.getAmount());

            var report = bank.deposit(accountNumber, amount);

            mainframe.setExecutionReport(report);

        } catch (Exception e) {

            var failure = new HashMap<String, String>();
            failure.put("source", "deposit");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());

            mainframe.setExecutionReport(failure);


        }

    }
    public void transfer(TransferEvent event){
        try {
            String fromAccountNumber = accountNumberValidator(event.getFromAccountNumber());

            String toAccountNumber = accountNumberValidator(event.getToAccountNumber());

            Double amount = amountValidator(event.getAmount());

            var report = bank.transfer(fromAccountNumber, toAccountNumber, amount);

            mainframe.setExecutionReport(report);
        } catch (Exception e) {

            var failure = new HashMap<String, String>();
            failure.put("source", "transfer");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());

            mainframe.setExecutionReport(failure);


        }

    }
    public void withdraw(WithdrawEvent event){
        try {
            String accountNumber = accountNumberValidator(event.getAccountNumber());
            double amount = amountValidator(event.getAmount());

            var report = bank.withdraw(accountNumber, amount);
            mainframe.setExecutionReport(report);
        } catch (Exception e) {

            var failure = new HashMap<String, String>();
            failure.put("source", "withdraw");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());

            mainframe.setExecutionReport(failure);


        }
    }
    public ReportObject getReport(String reportType){
        return bank.getReport(reportType);

    }
    public String nameValidator(String name) throws InvalidNameException{
        if (name == null || name.trim().isEmpty()){
            throw new InvalidNameException();
        }
        if  (!name.matches("[a-zA-Z ]+")) throw new InvalidNameException();

        return name.trim();

    }
    public String accountNumberValidator(String accountNumber) throws InvalidAccountNumberException{
        if (accountNumber == null || accountNumber.trim().isEmpty()){ throw new InvalidAccountNumberException();}

        return accountNumber.trim();

    }
    public double amountValidator(String amount) throws Exception{
        if (amount == null || amount.trim().isEmpty()){ throw new InvalidAccountNumberException();}
        try{
            double amountInDouble = Double.parseDouble(amount.trim());
            return amountInDouble;

        } catch (Exception e){
            throw new InvalidAmountException();
        }



    }
}
