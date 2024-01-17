package de.srh.holtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class MySQLConnection {

    private String host = "127.0.0.1";
    private String username = "root";
    private String password = "";
    private int port = 3306;
    private String dbname = "java_csv2sql";
    private Connection connection = null;

    public MySQLConnection(Properties dbProperties) {
        if (dbProperties != null) {
            System.out.println("INFO: reading database config...");

            host = dbProperties.getProperty("db.host");
            username = dbProperties.getProperty("db.username");
            password = dbProperties.getProperty("db.password");
            port = Integer.valueOf(dbProperties.getProperty("db.port"));
            dbname = dbProperties.getProperty("db.dbname");
        }
    }


    public Connection getConnection() {
        if (this.connection == null) {

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("ERROR: cannot connect to the database. " + ex.getMessage());
            }
        }

        return connection;
    }






    public boolean addCity(String country, String city, String function) {
        try {

            String sql = "INSERT INTO cities"
                    + "(land, city, function )"
                    + "VALUES(?, ?, ?)";

            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, country);
            ps.setString(2, city);
            ps.setString(3, function);


            int result = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR: cannot execute statement. " + ex.getMessage());
            return false;
        }

        return true;
    }


}
