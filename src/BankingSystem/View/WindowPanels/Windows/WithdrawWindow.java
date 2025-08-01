package BankingSystem.View.WindowPanels.Windows;

import BankingSystem.View.Events.WithdrawEvent;
import BankingSystem.View.WindowPanels.WindowPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/*
    ---------------------------------------------
    |                                           |
    |    Account Number   -----------           |
    |    amount           -----------           |
    |                                           |
    |            ___________________            |
    |            |     withdraw     |           |
    |            -------------------            |
    |-------------------------------------------|
 */


public class WithdrawWindow extends JPanel {
    private JLabel accountNumberLabel;
    private JLabel amountLabel;
    private JTextField accountNumberField;
    private JTextField amountField;
    private JButton withdrawButton;
    private JLabel executionReportField;
    private WindowPanelContainer windowPanelContainer;

    public WithdrawWindow(){
        accountNumberLabel = new JLabel("Account Number: ");
        accountNumberField = new JTextField(15);
        amountLabel = new JLabel("Amount: ");
        amountField = new JTextField(15);
        withdrawButton = new JButton("withdraw");
        executionReportField = new JLabel();





        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                String amount = amountField.getText();

                WithdrawEvent event = new WithdrawEvent(
                        WithdrawWindow.this,
                        accountNumber,
                        amount);

                windowPanelContainer.withdraw(event);

            }
        });

        layWidgets();

    }

    public void setWindowContainer(WindowPanelContainer windowPanelContainer) {
        this.windowPanelContainer = windowPanelContainer;
    }

    public void clear() {
        accountNumberField.setText(null);
        amountField.setText(null);
        executionReportField.setText(null);
    }
    public void setExecutionReport(HashMap<String, String> report) {
        String message = "%s: %s".formatted(report.get("type"), report.get("report"));
        switch (report.get("type")){
            case "Success" -> executionReportField.setForeground(Color.green);
            case "Failure" -> executionReportField.setForeground(Color.red);
        }
        executionReportField.setText(message);
    }
    public void layWidgets(){

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // first row
        gc.gridx = 0;
        gc.gridy = 0;

        add(accountNumberLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;

        add(accountNumberField, gc);

        // Second row
        gc.gridx = 0;
        gc.gridy = 1;

        add(amountLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;

        add(amountField, gc);

        // Third row
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.insets = new Insets(10,0,0,0);
        gc.anchor = GridBagConstraints.CENTER;

        add(withdrawButton, gc);

        // fourth row
        gc.gridx = 0;
        gc.gridy = 3;
        gc.weighty = 1;
        gc.gridwidth = 3;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(20, 0,20,0);
        add(executionReportField, gc);

    }

}
