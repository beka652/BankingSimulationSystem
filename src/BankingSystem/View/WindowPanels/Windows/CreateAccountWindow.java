package BankingSystem.View.WindowPanels.Windows;

import BankingSystem.View.Events.CreateAccountEvent;
import BankingSystem.View.WindowPanels.WindowPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/*
    ---------------------------------------------
    |                                           |
    |    full name        -----------           |
    |    initial deposit  -----------           |
    |                                           |
    |      ---- execution report field---       |
    |           ___________________             |
    |            |  create account  |           |
    |            -------------------            |
    |-------------------------------------------|
 */

public class CreateAccountWindow extends JPanel{
    private JLabel nameLabel;
    private JLabel initialDepositLabel;
    private JTextField nameField;
    private JTextField initialDepositField;
    private JButton createAccountButton;
    private JLabel executionReportField;
    private WindowPanelContainer windowPanelContainer;


    public CreateAccountWindow(){
        nameLabel = new JLabel("Full name: ");
        nameField = new JTextField(15);
        initialDepositLabel = new JLabel("Initial deposit: ");
        initialDepositField = new JTextField(15);
        createAccountButton = new JButton("create account");
        executionReportField = new JLabel();



        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  name = nameField.getText();
                String deposit = initialDepositField.getText();

                CreateAccountEvent event = new CreateAccountEvent(CreateAccountWindow.this, name, deposit);

                windowPanelContainer.createAccount(event);

            }
        });

        layWidgets();

    }


    public void setWindowContainer(WindowPanelContainer windowPanelContainer) {
        this.windowPanelContainer = windowPanelContainer;
    }

    public void clear() {
        nameField.setText(null);
        initialDepositField.setText(null);
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

        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;

        add(nameField, gc);

        // Second row
        gc.gridx = 0;
        gc.gridy = 1;

        add(initialDepositLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;

        add(initialDepositField, gc);

        // Third row
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,0,0);

        add(createAccountButton, gc);

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
