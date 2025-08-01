package BankingSystem.View.WindowPanels.Windows;

import BankingSystem.View.Events.TransferEvent;
import BankingSystem.View.WindowPanels.WindowPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/*
    ---------------------------------------------
    |                                           |
    |    to account number    -----------       |
    |    from account number  -----------       |
    |    amount               -----------       |
    |                                           |
    |            ______________                 |
    |            |  transfer  |                 |
    |            --------------                 |
    |-------------------------------------------|
 */

public class TransferWindow extends JPanel {
    private JLabel toAccountNumberLabel;
    private JLabel fromAccountNumberLabel;
    private JLabel amountLabel;
    private JTextField toAccountNumberField;
    private JTextField fromAccountNumberField;
    private JTextField amountField;
    private JButton transferButton;
    private JLabel executionReportField;

    private WindowPanelContainer windowPanelContainer;

    public TransferWindow(){
        toAccountNumberLabel = new JLabel("To account number: ");
        toAccountNumberField = new JTextField(20);
        fromAccountNumberLabel = new JLabel("From account number: ");
        fromAccountNumberField = new JTextField(20);
        amountLabel = new JLabel("Amount: ");
        amountField = new JTextField(20);
        transferButton = new JButton("transfer");
        executionReportField = new JLabel();



        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toAccountNumber = toAccountNumberField.getText();
                String fromAccountNumber = fromAccountNumberField.getText();
                String  amount = amountField.getText();

                TransferEvent event = new TransferEvent(
                        TransferWindow.this,
                        fromAccountNumber,
                        toAccountNumber,
                        amount
                );

                windowPanelContainer.transfer(event);


            }
        });

        layWidgets();

    }

    public void setWindowContainer(WindowPanelContainer windowPanelContainer) {
        this.windowPanelContainer = windowPanelContainer;
    }

    public void clear() {
        toAccountNumberField.setText(null);
        fromAccountNumberField.setText(null);
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
        add(toAccountNumberLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;

        add(toAccountNumberField, gc);

        // Second row

        gc.gridx = 0;
        gc.gridy = 1;
        add(fromAccountNumberLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(fromAccountNumberField, gc);

        // third row

        gc.gridx = 0;
        gc.gridy = 2;
        add(amountLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(amountField, gc);

        // fourth row
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.insets = new Insets(10,0,0,0);
        add(transferButton, gc);

        // fifth row
        gc.gridx = 0;
        gc.gridy = 4;
        gc.weighty = 1;
        gc.gridwidth = 3;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(20, 0,20,0);
        add(executionReportField, gc);
    }

}
