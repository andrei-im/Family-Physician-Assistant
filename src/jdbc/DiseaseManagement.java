package jdbc;

import java.sql.*;
import java.util.ArrayList;

public class DiseaseManagement {
    private static SQLConnection SQLCon = SQLConnection.getInstance();

    private static ArrayList<String> diseases;
    private static ArrayList<String> diseaseType;
    private static ArrayList<String> treatment;
    private static ArrayList<String> symptoms;

    public static void diseaseInfoToArrayList(){
        diseases = new ArrayList<>();
        diseaseType = new ArrayList<>();
        treatment = new ArrayList<>();
        symptoms = new ArrayList<>();
        try{
            Statement s = SQLCon.getStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM DiseaseInfoList");
            while(rs.next()) {
                diseases.add(rs.getString("Diseases"));
                diseaseType.add(rs.getString("DiseaseType"));
                treatment.add(rs.getString("Treatment"));
                symptoms.add(rs.getString("Symptoms"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getDiseases(){
        return diseases;
    }
    public static ArrayList<String> getDiseaseTypes(){
        return diseaseType;
    }
    public static ArrayList<String> getTreatments(){
        return treatment;
    }
    public static ArrayList<String> getSymptoms(){
        return symptoms;
    }
    public static String getDisease(int index){return diseases.get(index);}
    public static String getDiseaseType(int index){return diseaseType.get(index);}
    public static String getTreatment(int index){return treatment.get(index);}
    public static String getSymptoms(int index){return symptoms.get(index);}
}
