package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Patient {
    private String name, surname, email, CNP, bloodGroup, weight, height, gender;
    private  ArrayList<String> diseases;
    private  ArrayList<String> diseaseType;
    private  ArrayList<String> treatment;
    private  ArrayList<String> symptoms;
    private  SQLConnection SQLCon;
    Patient(String name, String surname, String CNP){
        this.name = name;
        this.surname = surname;
        this.CNP = CNP;
        SQLCon=SQLConnection.getInstance();
    }

    public String toString(){
        return name+" "+surname+" "+CNP;
    }


    public void setCNP(String CNP){
        this.CNP = CNP;
    }

    public void setBloodGroup(String bloodGroup){
        this.bloodGroup = bloodGroup;
    }

    public void setWeight(String weight){
        this.weight = weight;
    }

    public void setHeight(String height){
        this.height = height;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void addDiseases(String disease){this.diseases.add(disease);}

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getBloodGroup(){return this.bloodGroup;}
    public String getWeight(){return this.weight;}
    public String getHeight(){return this.height;}
    public String getGender(){return this.gender;}

    public String getCNP(){
        return this.CNP;
    }
    public void diseaseInfoToArrayList(){
        diseases = new ArrayList<>();
        diseaseType = new ArrayList<>();
        treatment = new ArrayList<>();
        symptoms = new ArrayList<>();
        try{
            Statement s = SQLCon.getStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " +this.getSurname() +this.getName());
            while(rs.next()) {
                diseases.add(rs.getString("Disease"));
                diseaseType.add(rs.getString("DiseaseType"));
                treatment.add(rs.getString("Treatment"));
                symptoms.add(rs.getString("Symptoms"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public  ArrayList<String> getDiseases(){
        return diseases;
    }
    public  ArrayList<String> getDiseaseTypes(){
        return diseaseType;
    }
    public  ArrayList<String> getTreatments(){
        return treatment;
    }
    public  ArrayList<String> getSymptoms(){
        return symptoms;
    }
}


