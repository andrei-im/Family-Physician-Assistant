package jdbc;

import static jdbc.PatientManager.*;
import static jdbc.DatabaseManager.*;
import static jdbc.DiseaseManagement.*;
import java.sql.*;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args)
    {
        Thread GUIThread = new Thread(new StartGui());
        GUIThread.start();

    }
}
