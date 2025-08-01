package BankingSystem.Model;


import BankingSystem.Exceptions.*;
import BankingSystem.Interfaces.Bankable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;


public class Bank implements Bankable {

    private  HashMap<String, Account> accountsHashMap;
    Database database;

    public Bank(){
        try {

            accountsHashMap = new HashMap<>();
            database = new Database();
            database.fetchAccounts(accountsHashMap);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public HashMap<String, String> createNewAccount(String name, double initial_deposit){

        try {
            // check if the initial deposit is acceptable
            if (initial_deposit <= 0) throw new InvalidInitialDeposit();

            // create an account object
            Account account = new Account(name, generateAccountNumber(), initial_deposit);

            // Add the account to the account's table
            database.addAccount(account);

            // Add it to the accounts hashmap for easy access
            accountsHashMap.put(account.getAccountNumber(), account);


            var success = new HashMap<String, String>();
            success.put("source", "create account");
            success.put("type", "Success");
            success.put("report","Account created successfully" );
            return success;

        } catch (Exception e) {
            var failure = new HashMap<String, String>();
            failure.put("source", "create account");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());
            return failure;
        }


    }

    @Override
    public HashMap<String, String> withdraw(String accountNumber, double withdrawAmount){

        String transactionType = "withdraw";

        try{

            // get the account object using its account number
            Account account = accountsHashMap.get(accountNumber);
            if (account == null) throw new AccountNotFoundException();

            // check the withdrawal amount is valid
            if (account.getBalance() < withdrawAmount) throw new InvalidWithdrawAmount();
            // check that the withdrawal amount is non-negative
            if (withdrawAmount <= 0) throw new InvalidWithdrawAmount();

            // withdraw
            account.withdraw(withdrawAmount);

            // update the accounts table
            database.updateAccountsTable(account);

            // record the transaction
            database.recordTransaction(new Transaction(
                    generateTransactionID(),
                    account.getAccountNumber(),
                    transactionType,
                    withdrawAmount,
                    LocalDate.now().toString()));


            var success = new HashMap<String, String>();
            success.put("source", "withdraw");
            success.put("type", "Success");
            success.put("report","Withdrawal completed successfully" );
            return success;

        } catch (Exception e) {
            var failure = new HashMap<String, String>();
            failure.put("source", "withdraw");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());
            System.out.println(e.getMessage());
            return failure;
        }

    }


    @Override
    public HashMap<String, String> deposit(String accountNumber, double amount){

        String transactionType = "deposit";

        try{

            // get the account object using its account number
            Account account = accountsHashMap.get(accountNumber);
            if (account == null) throw new AccountNotFoundException();

            // check the deposit amount is valid
            if (amount <= 0) throw new InvalidDepositAmount();

            // deposit the money
            account.deposit(amount);

            // update the accounts table
            database.updateAccountsTable(account);

            // record the transaction
            database.recordTransaction(new Transaction(
                    generateTransactionID(),
                    account.getAccountNumber(),
                    transactionType,
                    amount,
                    LocalDate.now().toString()));


            var success = new HashMap<String, String>();
            success.put("type", "Success");
            success.put("source", "deposit");
            success.put("report","Deposit completed successfully" );
            return success;

        } catch (Exception e) {
            var failure = new HashMap<String, String>();
            failure.put("source", "deposit");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());
            return failure;
        }

    }

    @Override
    public HashMap<String, String> transfer(String fromAccountNumber, String toAccountNumber, double amount) {

        try{
            Account fromAccount;
            Account toAccount;
            // check for the existence of both accounts
            if ((fromAccount= accountsHashMap.get(fromAccountNumber)) == null) throw new AccountNotFoundException();
            if ((toAccount= accountsHashMap.get(toAccountNumber)) == null) throw new AccountNotFoundException();

            /* transfer: is the same as withdrawing from one account and depositing into the other therefore we use
             the already defined deposit and withdraw function to achieve the same goal*/

            if (amount <=0 ) throw new InvalidAmountException();
            if (fromAccount.getBalance() < amount) throw new InvalidAmountException();

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            database.updateAccountsTable(fromAccount);
            database.updateAccountsTable(toAccount);

            database.recordTransaction(new Transaction(
                    generateTransactionID(),
                    fromAccount.getAccountNumber(),
                    "transfer + ",
                    amount,
                    LocalDate.now().toString()
                    ));

            database.recordTransaction(new Transaction(
                    generateTransactionID(),
                    toAccount.getAccountNumber(),
                    "transfer - ",
                    amount,
                    LocalDate.now().toString()
            ));




            var success = new HashMap<String, String>();
            success.put("type", "Success");
            success.put("source", "transfer");
            success.put("report","Transfer completed successfully" );
            return success;

        } catch (Exception e) {
            var failure = new HashMap<String, String>();
            failure.put("source", "transfer");
            failure.put("type", "Failure");
            failure.put("report", e.getMessage());
            return failure;
        }


    }

    public HashMap<String, String> delete(String accountNumber) {
        try{
            Account account;
            if ((account= accountsHashMap.get(accountNumber)) == null) throw new AccountNotFoundException();

            database.delete(account);
            accountsHashMap.remove(accountNumber);

            var success = new HashMap<String, String>();
            success.put("type", "Success");
            success.put("source", "delete");
            success.put("report","Deletion completed successfully" );
            return success;

        } catch (Exception e){
            var failure = new HashMap<String, String>();
            failure.put("type", "Failure");
            failure.put("source", "delete");
            failure.put("report", e.getMessage() );
            return failure;



        }
    }


    public ReportObject getReport(String reportType) {
        try{
             return database.report(reportType);

        } catch( Exception e) {
            return null;
        }


    }

    private static String generateAccountNumber(){
        return UUID.randomUUID().toString().substring(0, 8);
    }
    private  String generateTransactionID(){
        return UUID.randomUUID().toString();
    }


}
