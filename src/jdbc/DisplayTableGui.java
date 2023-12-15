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



public class DisplayTableGui extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTableHeader tableHeader;

    private static SQLConnection SQLCon = SQLConnection.getInstance();

    public DisplayTableGui(){
        ArrayList<Patient> patients=getPatients();
        setTitle("Patient List");
        model = new DefaultTableModel(patients.size(),8);
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Name", "Surname","CNP","Email","Bloodgroup","Weight","Height","Gender"});
        table.setGridColor(Color.RED);
        table.setRowHeight(30);
        table.setShowGrid(true);
        int i=0;
        for(Patient p:patients)
        {
            table.setValueAt(p.getName(),i,0);
            table.setValueAt(p.getSurname(),i,1);
            table.setValueAt(p.getCNP(),i,2);
            table.setValueAt(p.getEmail(),i,3);
            table.setValueAt(p.getBloodGroup(),i,4);
            table.setValueAt(p.getWeight(),i,5);
            table.setValueAt(p.getHeight(),i,6);
            table.setValueAt(p.getGender(),i,7);
            i++;
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