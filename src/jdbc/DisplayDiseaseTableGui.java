package jdbc;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static jdbc.PatientManager.*;



public class DisplayDiseaseTableGui extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTableHeader tableHeader;

    private static SQLConnection SQLCon = SQLConnection.getInstance();

    public DisplayDiseaseTableGui(Patient p){
        p.diseaseInfoToArrayList();
        ArrayList<Patient> patients=getPatients();
        setTitle("Disease List");
        model = new DefaultTableModel(patients.size(),4);
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Diseases", "DiseaseType","Treatment","Symptoms"});
        table.setGridColor(Color.RED);
        table.setRowHeight(30);
        table.setShowGrid(true);

        for(int i=0;i<p.getDiseases().size();i++)
        {
            table.setValueAt(p.getDiseases().get(i),i,0);
            table.setValueAt(p.getDiseaseTypes().get(i),i,1);
            table.setValueAt(p.getTreatments().get(i),i,2);
            table.setValueAt(p.getSymptoms().get(i),i,3);
        }
        TableColumnModel tcm = table.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setPreferredWidth(225);
        tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.BLUE);
        tableHeader.setForeground(Color.YELLOW);
        JScrollPane jp = new JScrollPane(table);
        jp.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(jp);
        this.setSize(650,300);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }
    public  ArrayList<Patient> getPatients(){
        ArrayList<Patient> list = new ArrayList<>();
        try{
            Statement s = SQLCon.getStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PatientsData");
            while(rs.next())
            {
                Patient p=new Patient(rs.getString(2),rs.getString(3),rs.getString(4));
                list.add(p);
                p.setEmail(rs.getString("email"));
                p.setCNP(rs.getString("CNP"));
                p.setBloodGroup(rs.getString("blood_group"));
                p.setWeight(rs.getString("weight"));
                p.setHeight(rs.getString("height"));
                p.setGender(rs.getString("gender"));

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
