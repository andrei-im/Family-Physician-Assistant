package jdbc;

import java.sql.*;

public class SQLConnection {
    private static SQLConnection instance;
    private Connection connection;

    private Statement statement;

    private SQLConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:ucanaccess://.\\EHR.accdb"); // establish connection
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Connected Successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SQLConnection getInstance(){
        if(instance == null){
            instance = new SQLConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

    public Statement getStatement(){
        return statement;
    }

    public DatabaseMetaData getMetaData(){
        try{
            return connection.getMetaData();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void close(){
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
