package BankingSystem.Interfaces;

import java.util.HashMap;

public interface Bankable {

    HashMap<String, String> createNewAccount(String name, double initial_deposit);

    HashMap<String, String> withdraw(String accountNumber, double amount);

    HashMap<String, String> deposit(String accountNumber, double amount);

    HashMap<String, String> transfer(String fromAccountNumber, String toAccountNumber, double amount);
}
