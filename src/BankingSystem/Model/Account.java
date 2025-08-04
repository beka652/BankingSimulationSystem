package BankingSystem.Model;

import BankingSystem.Interfaces.IAccount;


public class Account implements IAccount {

    private String name;
    private String accountNumber;
    private double balance;




    public Account(String name, String accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public Account(Person person, String AccountNumber, double balance){
        // This was created on to show method overloading -- not used within the project
        this.name = person.getName();
        this.accountNumber = getAccountNumber();
        this.balance = balance;

    }


    public void withdraw(double withdrawAmount) throws Exception{
        balance -= withdrawAmount;
    }
    public void deposit(double amount) throws Exception{
        balance += amount;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
