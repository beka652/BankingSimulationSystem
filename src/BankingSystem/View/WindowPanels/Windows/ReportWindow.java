package BankingSystem.View.WindowPanels.Windows;

import BankingSystem.Model.ReportObject;
import BankingSystem.View.WindowPanels.ViewModels.AccountsTableModel;
import BankingSystem.View.WindowPanels.ViewModels.TransactionsTableModel;
import BankingSystem.View.WindowPanels.WindowPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportWindow extends JPanel {

    private AccountsTableModel accountsTableModel;
    private TransactionsTableModel transactionsTableModel;
    private JTable table;
    private JComboBox comboBox;
    private JButton printButton;
    private JLabel statusbar;
    private JLabel statusLabel;


    private WindowPanelContainer windowPanelContainer;

    public ReportWindow() {

        table = new JTable();
        comboBox = new JComboBox();
        printButton = new JButton("print");

        statusbar = new JLabel();
        statusLabel = new JLabel("Status");

        printButton.setEnabled(false);

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                statusbar.setText(null);
                statusbar.setForeground(Color.WHITE);

                print();

            }
        });


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBox.getSelectedItem());
                switch (comboBox.getSelectedItem().toString()) {
                    case "transactions" -> setTransactionsData("transactions");
                    case "accounts" -> setAccountsData("accounts");
                }
                printButton.setEnabled(true);
                statusbar.setText(null);
            }
        });

        layWidgets();



    }

    public void setWindowContainer(WindowPanelContainer windowPanelContainer) {
        this.windowPanelContainer = windowPanelContainer;
    }

    public void setAccountsData(String dataType) {
        ReportObject data = windowPanelContainer.getData(dataType);
        accountsTableModel = new AccountsTableModel();
        accountsTableModel.setData(data.getAccountList());
        accountsTableModel.print();
        table.setModel(accountsTableModel);

    }

    public void setTransactionsData(String dataType) {
        ReportObject data = windowPanelContainer.getData(dataType);
        transactionsTableModel = new TransactionsTableModel();
        transactionsTableModel.setData(data.getTransactionList());
        transactionsTableModel.print();
        table.setModel(transactionsTableModel);
    }

    public void print() {
        var model = table.getModel();
        switch (comboBox.getSelectedItem().toString()) {
            case "transactions" -> {
                try (BufferedWriter bufferedWriter =
                             new BufferedWriter(new FileWriter("transactions.txt"))) {
                    bufferedWriter.write("| accountNumber  | transactionID | transactionAmount | date | transactionType |\n");


                    statusbar.setText("Printing to transactions.txt ...");

                    for (int row = 0; row < model.getRowCount(); row++) {
                        bufferedWriter.write("| %s | %s | %s | %s | %s |\n".formatted(
                                model.getValueAt(row, 0).toString(),
                                model.getValueAt(row, 1).toString(),
                                model.getValueAt(row, 2).toString(),
                                model.getValueAt(row, 3).toString(),
                                model.getValueAt(row, 4).toString()
                        ));
                    }
                    statusbar.setForeground(Color.RED);
                    statusbar.setText("Finished printing");

                } catch (IOException e) {
                    System.out.println( e.getMessage());
                }

            }
            case "accounts" -> {
                try (BufferedWriter bufferedWriter =
                             new BufferedWriter(new FileWriter("accounts.txt"))) {

                    statusbar.setText("Printing to accounts.txt ...");

                    bufferedWriter.write("| accountNumber  | account Name  | Balance |\n");
                    for (int row = 0; row < model.getRowCount(); row++) {
                        bufferedWriter.write("| %s | %s | %s |\n".formatted(
                                model.getValueAt(row, 0).toString(),
                                model.getValueAt(row, 1).toString(),
                                model.getValueAt(row, 2).toString()
                        ));
                    }

                    statusbar.setForeground(Color.RED);
                    statusbar.setText("Finished printing");

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        }


    }

    public void clear() {
        statusbar.setText(null);
        statusbar.setForeground(Color.WHITE);
    }
    public void layWidgets(){


        setLayout(new GridBagLayout());

        // set combo box
        DefaultComboBoxModel reportModel = new DefaultComboBoxModel();
        reportModel.addElement("accounts");
        reportModel.addElement("transactions");
        comboBox.setModel(reportModel);

        GridBagConstraints gc = new GridBagConstraints();

        // first row
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(0, 10, 0, 10);
        add(new JLabel("Table "), gc);

        gc.gridy = 0;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 10);
        add(comboBox, gc);

        gc.gridy = 0;
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.EAST;
        add(printButton, gc);


        // Second row
        gc.gridy = 1;
        gc.gridx = 0;
        gc.weighty = 1;
        gc.weightx = 1;
        gc.gridwidth = 3;
        gc.fill = GridBagConstraints.BOTH;

        add(new JScrollPane(table), gc);

        // third row

        gc.gridy = 2;
        gc.gridx = 1;
        gc.weighty = 0;
        gc.weightx = 1;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.NONE;

        add(statusLabel, gc);

        gc.gridy = 2;
        gc.gridx = 2;
        gc.weighty = 0;
        gc.weightx = 0;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;

        add(statusbar, gc);

    }
}


