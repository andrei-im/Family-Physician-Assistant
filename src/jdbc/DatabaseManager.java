package jdbc;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import static jdbc.PatientManager.*;
import static jdbc.DiseaseManagement.*;

public class DatabaseManager {

    private static SQLConnection SQLCon = SQLConnection.getInstance();
    private static Connection connection = SQLCon.getConnection();

    public static void createDiseaseTable(Patient p, String surname, String name) {
        boolean canCreateTable = true;
        try(ResultSet resultSet = SQLCon.getMetaData().getTables(null, null, null, new String[]{"TABLE"})){
            while(resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                if (tableName.equals(surname+name)){
                    canCreateTable = false;
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }

        if (canCreateTable) {
            try {
                PreparedStatement ps = connection.prepareStatement("CREATE TABLE " + surname + name + "(id AUTOINCREMENT PRIMARY KEY, Disease Text, DiseaseType Text, Treatment Text, Symptoms Text);");

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addNewDiseaseInfo(Patient p, int index){
        try{
            String disease,treatment,diseaseType,symptoms;
            disease=getDisease(index);
            treatment=getTreatment(index);
            diseaseType=getDiseaseType(index);
            symptoms=getSymptoms(index);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " + p.getSurname() + p.getName()+ "(Disease,Treatment,DiseaseType,Symptoms) VALUES (?,?,?,?)");

            ps.setString(1, disease);
            ps.setString(2, treatment);
            ps.setString(3, diseaseType);
            ps.setString(4, symptoms);

            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
