package BankingSystem.View;

import BankingSystem.Controller.Controller;
import BankingSystem.Model.ReportObject;
import BankingSystem.View.Events.CreateAccountEvent;
import BankingSystem.View.Events.DepositEvent;
import BankingSystem.View.Events.TransferEvent;
import BankingSystem.View.Events.WithdrawEvent;
import BankingSystem.View.MenuPanel.MenuPanel;
import BankingSystem.View.WindowPanels.WindowPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Mainframe extends JFrame {
    private MenuPanel menuPanel;
    private WindowPanelContainer windowPanelContainer;
    private Controller controller;

    public Mainframe(){
        menuPanel = new MenuPanel();
        windowPanelContainer = new WindowPanelContainer();

        menuPanel.setMainframe(this);
        windowPanelContainer.setMainframe(this);

        addWidgets();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 600);
        setLocationRelativeTo(null);


    }

    public void changeWindow(String windowName){
        windowPanelContainer.changeWindow(windowName);
        windowPanelContainer.setBorder(BorderFactory.createTitledBorder(windowName));
    }
    public void setController(Controller controller){

        this.controller = controller;
    }

    public ReportObject getReport(String reportType){
        return controller.getReport(reportType);
    }
    public void createAccount(CreateAccountEvent event) {

        controller.createAccount(event);
    }

    public void deposit(DepositEvent event) {
        controller.deposit(event);
    }

    public void transfer(TransferEvent event) {
        controller.transfer(event);

    }

    public void withdraw(WithdrawEvent event) {
        controller.withdraw(event);
    }

    public void setExecutionReport(HashMap<String, String> report){
       windowPanelContainer.setExecutionReport(report);

    }

    private void addWidgets(){
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.weighty = 1;

        add(menuPanel, gc);

        gc.gridx = 1;
        gc.gridy=0;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 4;

        add(windowPanelContainer, gc);

    }

}
