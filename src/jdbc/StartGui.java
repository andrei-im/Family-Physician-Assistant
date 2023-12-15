package jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGui extends JFrame implements ActionListener, Runnable {
    private JButton newPatient;
    private JButton editPatient;

    public void run() {

            {

                newPatient = new JButton("Add new patient");
                newPatient.addActionListener(this);
                newPatient.setFocusable(false);
                newPatient.setVisible(true);
                newPatient.setBounds(10, 10, 400, 60);
                newPatient.addActionListener(e -> {Thread GUIThread = new Thread(new AddPatientGui());
                                                    GUIThread.start();});
            }
            {
                editPatient = new JButton("Edit or search existing patient");
                editPatient.addActionListener(this);
                editPatient.setFocusable(false);
                editPatient.setVisible(true);
                editPatient.setBounds(425, 10, 400, 60);
                editPatient.addActionListener(e -> {Thread GUIThread2 = new Thread(new SearchPatient());
                                                            GUIThread2.start();});
            }
            {
                this.add(newPatient);
                this.add(editPatient);
                this.setTitle("Family Physician Assistant ");
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setResizable(false);
                this.setSize(855, 130);
                this.setLayout(null);
                this.setVisible(true);
                this.getContentPane().setBackground(Color.LIGHT_GRAY);
            }

        }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
