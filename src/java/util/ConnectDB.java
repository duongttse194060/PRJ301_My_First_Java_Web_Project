package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class ConnectDB {

    private String host;
    private String port;
    private String dbName;
    private String user;
    private String pass;

    public ConnectDB() {
        this.host = "localhost";
        this.port = "1433";
        this.dbName = "human";
        this.user = "sa";
        this.pass = "12345";
    }

    private String getStringConnect() {
        return String.format(
                "jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s;",
                this.host, this.port, this.dbName, this.user, this.pass
        );
    }

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(getStringConnect());
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy JDBC Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Kết nối SQL thất bại!");
            e.printStackTrace();
        }
        return null;
    }
    
    
}
