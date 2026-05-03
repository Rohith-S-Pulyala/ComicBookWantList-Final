/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project_cis175.db;

import java.sql.*;

import com.mycompany.final_project_cis175.util.PasswordUtil;
import com.mycompany.final_project_cis175.DBConnection;

/**
 *
 * @author rohit
 */
public class Database {
    
    /**
     * Handles user registration validation.
     *
     * @param String username
     * @param String password
     * @param String email
     * 
     * @return userId
     */
    public static boolean registerUser(String username, String password, String email) {
        //boolean status = false;
        String hashedPassword = PasswordUtil.hashPassword(password);
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.setString(3, email);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException excpt) {
            excpt.printStackTrace();
            return false;
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
        String hashedInput = PasswordUtil.hashPassword(password);
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, username);
            ps.setString(2, hashedInput);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    userId = rs.getInt("id");
                }
            }
        } catch (SQLException excpt) {
            excpt.printStackTrace();
        }
        return userId;
    }
}
