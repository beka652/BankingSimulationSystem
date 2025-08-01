package BankingSystem.View.WindowPanels.ViewModels;



import BankingSystem.Model.Transaction;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class TransactionsTableModel extends AbstractTableModel {

    private List<Transaction> transactions;
    private final String[] colNames = {"accountNumber", "transactionID", "transaction Amount", "date", "transaction type"};

    public void setData( List<Transaction> transactions){
        this.transactions = transactions;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getColumnCount(){
        return colNames.length;
    }

    @Override
    public int getRowCount(){
        return transactions.size();
    }
    @Override
    public Object getValueAt(int arg0, int arg1){
        Transaction transaction = transactions.get(arg0);

        return switch (arg1){
            case 0 -> transaction.getAccountNumber();
            case 1 -> transaction.getTransactionID();
            case 2 -> transaction.getTransactionAmount();
            case 3 -> transaction.getDate();
            case 4 -> transaction.getTransactionType();
            default -> null;
        };
    }

    public void print() {
        for (Transaction transaction: transactions){
            System.out.println(transaction);
        }
        for (String colName: colNames){
            System.out.println(colName);
        }
    }
}
