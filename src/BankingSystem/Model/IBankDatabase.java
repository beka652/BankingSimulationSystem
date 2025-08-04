package BankingSystem.Model;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class IBankDatabase {


    public abstract void createAccountTable() throws SQLException;

    public  abstract void addAccount(Account account) throws SQLException;

    public   abstract void  recordTransaction(Transaction transaction) throws SQLException;
}
