package BankingSystem.Model;


import BankingSystem.Model.Abstract.AbstractDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class DatabaseManager extends AbstractDatabase {

    private static String url = "jdbc:sqlite:Bank.db";

    public DatabaseManager() throws SQLException{
        createTransactionsTable();
        createAccountTable();
    }

    public  Connection connect() throws SQLException{

        return DriverManager.getConnection(url);

    }

    @Override
    public  void createAccountTable() throws SQLException{

        String sqlQuery = "CREATE TABLE IF NOT EXISTS accounts(" +
                "accountNumber TEXT PRIMARY KEY  NOT NULL," +
                "name TEXT NOT NULL," +
                "balance REAL NOT NULL" +
                ");";

        try(Connection connection = connect();
            Statement statement = connection.createStatement())
        {
            statement.execute(sqlQuery);
        }



    }

    public  void createTransactionsTable() throws SQLException{
        String sqlQuery = "CREATE TABLE IF NOT EXISTS transactions(" +
                "transactionID TEXT PRIMARY KEY NOT NULL," +
                "accountNumber TEXT NOT NULL," +
                "transactionType TEXT NOT NULL," +
                "transactionAmount REAL NOT NULL," +
                "date TEXT NOT NULL" +
                ");";

        try (Connection connection = connect();
             Statement statement = connection.createStatement())
        {
            statement.execute(sqlQuery);
        }

    }


    @Override
    public   void  recordTransaction(Transaction transaction) throws SQLException
    {
        String sqlQuery = "INSERT INTO transactions" +
                "(transactionId, accountNumber, transactionType, transactionAmount, date)" +
                " VALUES(?, ? , ? ,?, ?) ";

        try(Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){

            preparedStatement.setString(1, transaction.getTransactionID());
            preparedStatement.setString(2, transaction.getAccountNumber());
            preparedStatement.setString(3, transaction.getTransactionType());
            preparedStatement.setDouble(4, transaction.getTransactionAmount());
            preparedStatement.setString(5, transaction.getDate());
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public  void addAccount(Account account) throws SQLException{
        // For adding new Account to Accounts table

        String sqlQuery = "INSERT INTO accounts(accountNumber, name, balance) VALUES(? , ? , ?);";

        try(Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery))
        {
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        }

    }

    public  void updateAccountsTable(Account account) throws SQLException{

        String sqlQuery = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";

        try(Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery))
        {
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getAccountNumber());

            preparedStatement.executeUpdate();
        }



    }
    public void delete(Account account) throws SQLException{

        String sqlQuery = "DELETE FROM accounts WHERE accountNumber = ?";

        try(Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.executeUpdate();
        }
    }


    public  void fetchAccounts(HashMap<String, Account> accountHashMap) throws SQLException{

        String sqlQuery = "SELECT accountNumber, name, balance FROM accounts";

        try(Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery()
        ){
            while (resultSet.next()){
                String accountNumber = resultSet.getString("accountNumber");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");

                accountHashMap.put(accountNumber, new Account(name, accountNumber, balance));
            }
        }

    }

    public ReportObject report(String reportType) throws SQLException{

        List<Account> accountList = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM %s".formatted(reportType);

        try(Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery())
        {
            switch (reportType) {
                case "transactions" -> {
                    while (resultSet.next()) {
                        transactionList.add(new Transaction(
                                resultSet.getString("transactionID"),
                                resultSet.getString("accountNumber"),
                                resultSet.getString("transactionType"),
                                resultSet.getDouble("transactionAmount"),
                                resultSet.getString("date")));

                    }
                }
                case "accounts" -> {
                    while (resultSet.next()) {
                        accountList.add(new Account(
                                resultSet.getString("name"),
                                resultSet.getString("accountNumber"),
                                resultSet.getDouble("balance")
                        ));
                    }
                }
            }
            if (reportType.equals("transactions")) {
                var report= new ReportObject();
                report.setTransactionList(transactionList);
                return report;
            } else {
                var report= new ReportObject();
                report.setAccountList(accountList);
                return report; }


        }
    }




}
