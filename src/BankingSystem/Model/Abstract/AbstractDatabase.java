package BankingSystem.Model.Abstract;

import BankingSystem.Model.Account;
import BankingSystem.Model.Transaction;

import java.sql.SQLException;

public abstract class AbstractDatabase {


    public abstract void createAccountTable() throws SQLException;

    public  abstract void addAccount(Account account) throws SQLException;

    public   abstract void  recordTransaction(Transaction transaction) throws SQLException;
}
