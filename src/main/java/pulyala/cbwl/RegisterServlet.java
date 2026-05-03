/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pulyala.cbwl;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.mycompany.final_project_cis175.db.Database;
import com.mycompany.final_project_cis175.util.PasswordUtil;

/**
 *
 * @author rohit
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("password"); // Spaces can be part of passwords.
        
        if (user == null || email == null || pass == null) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Trims whitespace to prevent "hero " vs "hero" bugs.
        String cleanUser = user.trim();
        String cleanEmail = email.trim();
        
        // Basic Validation
        String error = null;
        
        if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            error = "Fields must not be blank or whitespace.";
        }
        else if (user.length() < 3 || user.length() > 20) {
            error = "Username must be between 3 and 20 characters.";
        }
        else if (!email.contains("@") || !email.contains(".")) {
            error = "Please enter a valid email format.";
        }
        else if (pass.length() < 8) {
            error = "Password must be at least 8 characters long.";
        }
        
        // 3. If there is an error, kick back immediately
        if (error != null) {
            request.setAttribute("errorMessage", error);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        //String hashedPass = PasswordUtil.hashPassword(pass);
        
        if (Database.registerUser(cleanUser, pass, cleanEmail)) {
            // Success! Sends to login.
            response.sendRedirect("login.jsp?msg=Welcome, New Hero! Please Log In.");
        } else {
            request.setAttribute("errorMessage", "Registration failed, try a different Hero Alias.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}
