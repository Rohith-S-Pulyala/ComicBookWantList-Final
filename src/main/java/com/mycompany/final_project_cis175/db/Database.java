/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project_cis175.db;

import java.sql.*;

/**
 *
 * @author rohit
 */
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/comic_app";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "CIS175$$!!";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException excpt) {
            excpt.printStackTrace();
        }
    }
    
    /**
     * Handles the validation for the user in the database.
     *
     * @param String username
     * @param String password
     * 
     * @return userId
     */
    public static int validate(String username, String password) { 
        int userId = -1;
        try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASS)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                userId = rs.getInt("id");
            }
            
        } catch (SQLException excpt) {
            excpt.printStackTrace();
        }
        return userId;
    }
}
