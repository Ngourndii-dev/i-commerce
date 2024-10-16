package org.example.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection connection;

    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                System.getenv("Url"),
                System.getenv("User"),
                System.getenv("Password")
        );
    }

    public static void main(String[] args) {
        new DbConnection();
    }
}
