package jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static jdbc.DatabaseManager.*;
import static jdbc.PatientManager.*;
import static jdbc.DiseaseManagement.*;

public class EditPatient  extends JFrame implements ActionListener {
    private JTextField weightField, heightField, bloodgroupField, genderField, emailField;
    private JLabel weightLabel, heightLabel, bloodgroupLabel, genderLabel, emailLabel, diseaseLabel, diseaseTypeLabel, treatmentLabel, symptomsLabel;
    private JButton patientButton, diseasedButton;
    private JList diseaseList, diseaseTypeList, treatmentList, symptomsList;
    private JComboBox diseaseBox;

    EditPatient(Patient p) {
        patient = p;
        p.diseaseInfoToArrayList();

        {
            weightLabel = new JLabel();
            weightLabel.setVisible(true);
            weightLabel.setForeground(Color.BLACK);
            weightLabel.setText("Patient weight:");
            weightLabel.setBounds(0, 60, 200, 40);


            weightField = new JTextField();

            weightField.setVisible(true);
            weightField.setBounds(210, 60, 250, 40);
        }
        {
            heightLabel = new JLabel();
            heightLabel.setVisible(true);
            heightLabel.setForeground(Color.BLACK);
            heightLabel.setText("Patient height:");
            heightLabel.setBounds(0, 110, 200, 40);


            heightField = new JTextField();

            heightField.setVisible(true);
            heightField.setBounds(210, 110, 250, 40);
        }
        {
            bloodgroupLabel = new JLabel();
            bloodgroupLabel.setVisible(true);
            bloodgroupLabel.setForeground(Color.BLACK);
            bloodgroupLabel.setText("Patient Blood Group:");
            bloodgroupLabel.setBounds(0, 160, 200, 40);


            bloodgroupField = new JTextField();

            bloodgroupField.setVisible(true);
            bloodgroupField.setBounds(210, 160, 250, 40);
        }
        {
            genderLabel = new JLabel();
            genderLabel.setVisible(true);
            genderLabel.setForeground(Color.BLACK);
            genderLabel.setText("Patient Gender:");
            genderLabel.setBounds(0, 210, 200, 40);
            System.out.println(p.getGender());


            genderField = new JTextField();

            genderField.setVisible(true);
            genderField.setBounds(210, 210, 250, 40);
        }
        {
            emailLabel = new JLabel();
            emailLabel.setVisible(true);
            emailLabel.setForeground(Color.BLACK);
            emailLabel.setText("Patient email:");
            emailLabel.setBounds(0, 260, 200, 40);


            emailField = new JTextField();

            emailField.setVisible(true);
            emailField.setBounds(210, 260, 250, 40);
        }
        {
            diseaseLabel = new JLabel();
            diseaseLabel.setVisible(true);
            diseaseLabel.setForeground(Color.BLACK);
            diseaseLabel.setText("Patient disease:");
            diseaseLabel.setBounds(0, 310, 200, 40);

            diseaseInfoToArrayList();
            String[] listingDisease=getDiseases().toArray(new String[getDiseases().size()]);
            diseaseBox=new JComboBox(listingDisease);
            diseaseBox.setBounds(210,310,200,40);
        }

        {

            diseasedButton = new JButton("Update Diseases");
            diseasedButton.addActionListener(this);
            diseasedButton.setFocusable(false);
            diseasedButton.setVisible(true);
            diseasedButton.setBounds(50, 510, 150, 60);

        }


        {

            patientButton = new JButton("Commmit changes");
            patientButton.addActionListener(this);
            patientButton.setFocusable(false);
            patientButton.setVisible(true);
            patientButton.setBounds(260, 510, 150, 60);

        }

        {
            this.setTitle("Patient Editor v1.4.2");
            this.add(weightField);
            this.add(heightField);
            this.add(emailField);
            this.add(bloodgroupField);
            this.add(genderField);
            this.add(weightLabel);
            this.add(heightLabel);
            this.add(emailLabel);
            this.add(bloodgroupLabel);
            this.add(genderLabel);
            this.add(diseaseLabel);
            this.add(diseaseBox);
            this.add(patientButton);
            
            this.add(diseasedButton);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setResizable(true);
            this.setSize(500, 1000);
            this.setLayout(null);
            this.setVisible(true);
            this.getContentPane().setBackground(Color.LIGHT_GRAY);
        }

    }

    Patient patient;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == patientButton) {
            if (!genderField.getText().equals("")) {
                patient.setGender(genderField.getText());
                System.out.println(patient.getGender());
                modifyGender(patient);
            }
            if (!emailField.getText().equals("")) {
                patient.setEmail(emailField.getText());
                modifyEmail(patient);
            }
            if (!weightField.getText().equals("")) {
                patient.setWeight(weightField.getText());
                modifyWeight(patient);
            }
            if (!bloodgroupField.getText().equals("")) {
                patient.setBloodGroup(bloodgroupField.getText());
                modifyBloodGroup(patient);
            }
            if (!heightField.getText().equals("")) {
                patient.setHeight(heightField.getText());
                modifyHeight(patient);
            }
            DisplayTableGui displayTable= new DisplayTableGui();
        }
        if(e.getSource()==diseasedButton)
        {


                String disease = (String)diseaseBox.getSelectedItem();
                patient.addDiseases(disease);
                diseaseInfoToArrayList();
                ArrayList<String> diseases=getDiseases();
                addNewDiseaseInfo(patient,diseases.indexOf(disease));
                DisplayDiseaseTableGui sickness= new DisplayDiseaseTableGui(patient);


        }


    }
}

