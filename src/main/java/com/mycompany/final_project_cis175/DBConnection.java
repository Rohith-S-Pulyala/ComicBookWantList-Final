/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.final_project_cis175;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Utility class to manage database connections using Tomcat's JNDI Resource Pool.
 * Ensures efficient connection sharing and keeps credentials out of the source code
 * @author rohit
 */
public class DBConnection {
    private static DataSource dataSource;
    
    //
    static {
        try {
            Context initContext = new InitialContext();
            // Searches for the resource name in context.xml to connect 
            //to comic_app.sql
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/ComicDB");
        } catch (NamingException excpt) {
            // Log the error properly and throw a RuntimeException to stop the app
            //if the DB cannot be reached.
            System.out.println(excpt);
            throw new ExceptionInInitializerError("Failed to initialize JNDI DataSource: " + excpt.getMessage());
        }
    }
    
    /**
     * @return A connection from the Tomcat connection pool.
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource was not properly initialized.");
        }
        return dataSource.getConnection();
    }
}
