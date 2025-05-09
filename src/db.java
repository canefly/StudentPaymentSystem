/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author canef
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String URL = "jdbc:mysql://localhost:3306/student_payment_db?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "root";        // or your own
    private static final String PASSWORD = "";        // your MySQL password

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found ):< ", e);
        }
    }
}

