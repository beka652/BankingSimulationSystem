package BankingSystem.View.MenuPanel;

import BankingSystem.View.Mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* this is the menu Panel where users can choose what action the bank to perform
    ------------------
    | create account |
    ------------------
    |    withdraw    |
    ------------------
    |    deposit     |
    ------------------
    |    transfer    |
    ------------------
    This is how the menu panel will look like
 */

public class MenuPanel extends JPanel implements ActionListener {
    private JButton createAccountMenu;
    private JButton withdrawMenu;
    private JButton depositMenu;
    private JButton transferMenu;
    private JButton reportMenu;
    private JButton deleteMenu;
    private Mainframe mainframe;


    public MenuPanel(){
        setBorder(BorderFactory.createTitledBorder("Menu"));

        createAccountMenu = new JButton("create account");
        withdrawMenu = new JButton("     withdraw     ");
        depositMenu = new JButton("      deposit       ");
        transferMenu = new JButton("      transfer      ");
        reportMenu = new JButton("        report       ");
        deleteMenu = new JButton("       delete       ");

        createAccountMenu.addActionListener(this);
        withdrawMenu.addActionListener(this);
        depositMenu.addActionListener(this);
        transferMenu.addActionListener(this);
        reportMenu.addActionListener(this);
        deleteMenu.addActionListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // first row
        gc.gridx = 0;
        gc.gridy =  0;
        gc.insets = new Insets(5,0,10,0);
        gc.anchor = GridBagConstraints.NORTH;


        add(createAccountMenu, gc);

        // Second row

        gc.gridy++;
        add(withdrawMenu, gc);

        // Third row

        gc.gridy++;
        add(depositMenu, gc);

        // Fourth row

        gc.gridy++;
        add(transferMenu, gc);

        // fifth row
        gc.gridy++;
        add(deleteMenu, gc);

        // sixth row

        gc.gridy++;
        gc.weighty = 1;
        add(reportMenu, gc);



    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton buttonClicked = (JButton) e.getSource();

        String menuName = buttonClicked.getText().trim();
        mainframe.changeWindow(menuName);



    }



    public void setMainframe(Mainframe mainframe){
        this.mainframe = mainframe;
    }

}
