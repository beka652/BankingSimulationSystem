package BankingSystem.View.WindowPanels.Windows;

import BankingSystem.View.Events.DeleteEvent;
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


public class DeleteWindow extends JPanel {
    private JLabel accountNumberLabel;
    private JTextField accountNumberField;
    private JButton deleteButton;
    private JLabel executionReportField;
    private WindowPanelContainer windowPanelContainer;

    public DeleteWindow(){
        accountNumberLabel = new JLabel("Account Number: ");
        accountNumberField = new JTextField(15);
        deleteButton = new JButton("delete account");
        executionReportField = new JLabel();





        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();

                DeleteEvent event = new DeleteEvent(
                        DeleteWindow.this,
                        accountNumber
                );

                windowPanelContainer.delete(event);

            }
        });

        layWidgets();

    }

    public void setWindowContainer(WindowPanelContainer windowPanelContainer) {
        this.windowPanelContainer = windowPanelContainer;
    }

    public void clear() {
        accountNumberField.setText(null);
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

        // second row

        gc.gridx =1;
        gc.gridy = 1;

        add(deleteButton, gc);


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

