package BankingSystem.View.WindowPanels;


import BankingSystem.Model.ReportObject;
import BankingSystem.View.Events.*;
import BankingSystem.View.Mainframe;
import BankingSystem.View.WindowPanels.Windows.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class WindowPanelContainer extends JPanel{
    private TransferWindow transferWindow;
    private WithdrawWindow withdrawWindow;
    private DepositWindow depositWindow;
    private CreateAccountWindow createAccountWindow;
    private CardLayout cardLayout;
    private Mainframe mainframe;
    private ReportWindow reportWindow;
    private DeleteWindow deleteWindow;

    private String currentWindow = "";


    public WindowPanelContainer(){
        setBorder(BorderFactory.createTitledBorder("create account"));
        transferWindow = new TransferWindow();
        withdrawWindow = new WithdrawWindow();
        depositWindow = new DepositWindow();
        createAccountWindow = new CreateAccountWindow();
        reportWindow = new ReportWindow();
        deleteWindow = new DeleteWindow();

        transferWindow.setWindowContainer(this);
        withdrawWindow.setWindowContainer(this);
        depositWindow.setWindowContainer(this);
        createAccountWindow.setWindowContainer(this);
        reportWindow.setWindowContainer(this);
        deleteWindow.setWindowContainer(this);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        add(transferWindow, "transfer");
        add(withdrawWindow, "withdraw");
        add(depositWindow, "deposit");
        add(createAccountWindow, "create account");
        add(reportWindow, "report");
        add(deleteWindow, "delete");

        cardLayout.show(this, "create account");
        currentWindow = "create account";
    }

    public void changeWindow(String windowName){
        clearTextBoxes(currentWindow);
        currentWindow = windowName;
        cardLayout.show(this, windowName);
        System.out.println("changed window to: "+ windowName);

    }
    public void setMainframe(Mainframe mainframe){
        this.mainframe = mainframe;
    }

    public void createAccount(CreateAccountEvent event){
        mainframe.createAccount(event);

    }
    public void deposit(DepositEvent event){
        mainframe.deposit(event);
    }
    public void transfer(TransferEvent event){
        mainframe.transfer(event);
    }

    public void withdraw(WithdrawEvent event) {
        mainframe.withdraw(event);
    }
    public void delete(DeleteEvent event) {
        mainframe.delete(event);
    }
    public ReportObject getData(String dataType){
        return mainframe.getReport(dataType);
    }
    public void clearTextBoxes(String windowName){
        switch(windowName){
            case "transfer" -> transferWindow.clear() ;
            case "withdraw" -> withdrawWindow.clear() ;
            case "deposit" -> depositWindow.clear();
            case "create account" -> createAccountWindow.clear() ;
            case "report" -> reportWindow.clear();
            case "delete" -> deleteWindow.clear();
        }

    }
    public void setExecutionReport(HashMap<String, String> report){
        var source = report.get("source");
        switch (source){
            case "transfer" -> transferWindow.setExecutionReport(report);
            case "withdraw" -> withdrawWindow.setExecutionReport(report);
            case "deposit" -> depositWindow.setExecutionReport(report);
            case "create account" -> createAccountWindow.setExecutionReport(report);
            case "delete" -> deleteWindow.setExecutionReport(report);

        }

    }

}
