package jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static jdbc.PatientManager.*;

public class SearchPatient extends JFrame implements ActionListener,Runnable {
    private JTextField inputCnp;
    private JLabel insertCnp;
    private JButton clickCnp;

    public void run(){

        insertCnp = new JLabel();
        insertCnp.setVisible(true);
        insertCnp.setForeground(Color.BLACK);
        insertCnp.setText("Write patient CNP:");
        insertCnp.setBounds(0, 60, 200, 40);


        inputCnp = new JTextField();

        inputCnp.setVisible(true);
        inputCnp.setBounds(210, 60, 250, 40);


    {

        clickCnp = new JButton("Search");
        clickCnp.addActionListener(this);
        clickCnp.setFocusable(false);
        clickCnp.setVisible(true);
        clickCnp.setBounds(100, 210, 400, 60);

    }

    {
        this.setTitle("Add Patient");
        this.add(inputCnp);
        this.add(insertCnp);
        this.add(clickCnp);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setSize(570, 400);
        this.setLayout(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clickCnp) {
            Patient p=searchPatient(inputCnp.getText());
            EditPatient editPatient=new EditPatient(p);
            }
        }

    }

