/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project_cis175.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author rohit
 */
public class PasswordUtil {
    
    public static String hashPassword(String password) {
        try {
            MessageDigest  md = MessageDigest.getInstance("SHA-256");
            
            // Convert Password strign to bytes and hash it.
            byte[] hashedBytes = md.digest(password.getBytes());
            
            // Convert bytes to a readable string.
            return Base64.getEncoder().encodeToString(hashedBytes);
            
        } catch (NoSuchAlgorithmException excpt) {
            throw new RuntimeException("Hashing algorithm not found!", excpt);
        }
    }
    
}
