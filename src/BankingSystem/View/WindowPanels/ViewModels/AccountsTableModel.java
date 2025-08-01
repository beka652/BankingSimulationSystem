package BankingSystem.View.WindowPanels.ViewModels;

import BankingSystem.Model.Account;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class AccountsTableModel extends AbstractTableModel {
    private List<Account> accounts;
    private final String[] colNames = {"accountNumber","name", "balance"};

    public void setData(List<Account> accounts){
        this.accounts =  accounts;

    }
    @Override
    public String getColumnName(int column){
        return colNames[column];
    }
    @Override
    public int getColumnCount(){
        return colNames.length;
    }
    @Override
    public int getRowCount(){
        return accounts.size();
    }
    @Override
    public Object getValueAt(int arg0, int arg1){
        Account account = accounts.get(arg0);

        return switch (arg1){
            case 0 -> account.getAccountNumber();
            case 1 -> account.getName();
            case 2 -> account.getBalance();
            default -> null;
        };

    }

    public void print() {
        for (Account account: accounts){
            System.out.println(account);
        }
        for (String colName: colNames){
            System.out.println(colName);
        }
    }
}
