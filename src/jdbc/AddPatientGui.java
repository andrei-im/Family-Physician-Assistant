package jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static jdbc.DatabaseManager.createDiseaseTable;
import static jdbc.PatientManager.addNewPatient;
import static jdbc.PatientManager.getPatients;

public class AddPatientGui extends JFrame implements ActionListener, Runnable {
    private JTextField fieldName, fieldSurname, fieldCnp;
    private String inputName, inputSurname, inputCnp;
    private JButton commit;
    private JLabel labelName, labelSurname, labelCnp;

    public void run() {
        {
            labelName = new JLabel();
            labelName.setVisible(true);
            labelName.setForeground(Color.BLACK);
            labelName.setText("Write patient name:");
            labelName.setBounds(0, 60, 200, 40);


            fieldName = new JTextField();

            fieldName.setVisible(true);
            fieldName.setBounds(210, 60, 250, 40);
        }
        {
            labelSurname = new JLabel();
            labelSurname.setVisible(true);
            labelSurname.setForeground(Color.BLACK);
            labelSurname.setText("Write patient surname:");
            labelSurname.setBounds(0, 110, 200, 40);


            fieldSurname = new JTextField();

            fieldSurname.setVisible(true);
            fieldSurname.setBounds(210, 110, 250, 40);
        }
        {
            labelCnp = new JLabel();
            labelCnp.setVisible(true);
            labelCnp.setForeground(Color.BLACK);
            labelCnp.setText("Write patient CNP:");
            labelCnp.setBounds(0, 160, 200, 40);


            fieldCnp = new JTextField();

            fieldCnp.setVisible(true);
            fieldCnp.setBounds(210, 160, 250, 40);
        }
        {

            commit = new JButton("Add patient");
            commit.addActionListener(this);
            commit.setFocusable(false);
            commit.setVisible(true);
            commit.setBounds(100, 210, 400, 60);

        }

        {
            this.setTitle("Add Patient");
            this.add(labelName);
            this.add(labelSurname);
            this.add(labelCnp);
            this.add(fieldName);
            this.add(fieldSurname);
            this.add(fieldCnp);
            this.add(commit);
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
        if (e.getSource() == commit) {
            if ((!fieldName.getText().equals("")) && (!fieldSurname.getText().equals("")) && (!fieldCnp.getText().equals(""))) {
                this.inputName = fieldName.getText();
                this.inputSurname = fieldSurname.getText();
                this.inputCnp = fieldCnp.getText();
                Patient p = new Patient(inputName, inputSurname, inputCnp);
                addNewPatient(p);
                createDiseaseTable(p,p.getSurname(),p.getName());
                DisplayTableGui displayTable= new DisplayTableGui();
                DisplayDiseaseTableGui displayDiseaseTableGui=new DisplayDiseaseTableGui(p);

            }
        }

    }
}
