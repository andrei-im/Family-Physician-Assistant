package jdbc;

import java.sql.*;
import java.util.ArrayList;

public class PatientManager {
    private static SQLConnection SQLCon = SQLConnection.getInstance();
    private static Connection connection = SQLCon.getConnection();

    public static void addNewPatient(Patient p){
        if (!isPatient(p.getCNP())) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO PatientsData(p_surname,p_name, CNP) VALUES (?,?,?)");

                ps.setString(1, p.getSurname());
                ps.setString(2, p.getName());
                ps.setString(3, p.getCNP());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("This patient already exists");
        }
    }

    public static void modifyEmail(Patient p){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE PatientsData SET email = ? WHERE CNP = ?");
            ps.setString(1,p.getEmail());
            ps.setString(2,p.getCNP());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void modifyWeight(Patient p){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE PatientsData SET weight = ? WHERE CNP = ?");
            ps.setString(1,p.getWeight());
            ps.setString(2,p.getCNP());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void modifyHeight(Patient p){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE PatientsData SET height = ? WHERE CNP = ?");
            ps.setString(1,p.getHeight());
            ps.setString(2,p.getCNP());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void modifyBloodGroup(Patient p){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE PatientsData SET blood_group = ? WHERE CNP = ?");
            ps.setString(1,p.getBloodGroup());
            ps.setString(2,p.getCNP());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void modifyGender(Patient p){
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE PatientsData SET gender = ? WHERE CNP = ?");
            ps.setString(1,p.getGender());
            ps.setString(2,p.getCNP());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void deletePatient(Patient p){
        if(isPatient(p.getCNP())) {
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE from PatientsData WHERE CNP = ?");
                ps.setString(1, p.getCNP());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Can't delete patient that doesn't exist.");
        }
    }

    public static boolean isPatient(String CNP){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PatientsData WHERE CNP = ?");
            ps.setString(1,CNP);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e){
            return false;
        }
    }

    public static Patient searchPatient(String CNP){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PatientsData WHERE CNP = ?");
            ps.setString(1,CNP);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String patientSurname = rs.getString("p_surname");
            String patientName = rs.getString("p_name");
            String patientCNP = rs.getString("CNP");
            return new Patient(patientName, patientSurname, patientCNP);
        } catch (SQLException e){
            return null;
        }
    }


    public static ArrayList<Patient> getPatients(){
        ArrayList<Patient> list = new ArrayList<>();
        try{
            Statement s = SQLCon.getStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PatientsData");
            while(rs.next())
                list.add(new Patient(rs.getString(2),rs.getString(3),rs.getString(4)));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
