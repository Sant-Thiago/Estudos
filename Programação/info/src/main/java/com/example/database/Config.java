package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

    public static Connection conexion() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/databaseinfo", "BDuser", "BDuser123");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}