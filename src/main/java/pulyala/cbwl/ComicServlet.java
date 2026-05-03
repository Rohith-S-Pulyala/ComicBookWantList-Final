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
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.final_project_cis175.DBConnection;
import pulyala.business.Comic;
import pulyala.dao.ComicDAO;

/**
 *
 * @author rohit
 */
@WebServlet(name = "ComicServlet", urlPatterns = {"/ComicServlet"})
public class ComicServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int userId = (int) session.getAttribute("userId"); // gets user ID
        ComicDAO dao = new ComicDAO();

        String action = request.getParameter("action");
        

        if ("update".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("comicId"));
                String title = request.getParameter("title");
                int issue = Integer.parseInt(request.getParameter("issueNumber"));
                
                String publisher = request.getParameter("publisher");
                String author = request.getParameter("author");
                String illustrator = request.getParameter("illustrator");
                boolean isVariant = Boolean.parseBoolean(request.getParameter("isVariant"));
                
                String store = request.getParameter("storeName");
                String owner = request.getParameter("ownerName");
                String info = request.getParameter("storeInfo");

                boolean updated = dao.updateComic(id, userId, title, issue, publisher, author, illustrator, isVariant, store, owner, info); // UPDATE
                session.setAttribute("feedback", updated ? "Successfully updated " + title : "Database Error");
            } catch (NumberFormatException excpt) {
                session.setAttribute("feedback", "Update Error: ID or Issue Number is invalid.");
            }
        } else {
            try {
                String title = request.getParameter("comicTitle");
                //int issueNumber = Integer.parseInt(request.getParameter("issueNumber"));
                String issueRaw = request.getParameter("issueNumber");
                
                if (issueRaw == null) {
                    session.setAttribute("feedback", "Error: Issue number parameter missing from form.");
                } else {
                    int issueNumber = Integer.parseInt(issueRaw);
                    boolean success = dao.addComicWithStore(userId, title, issueNumber,
                        request.getParameter("publisher"), request.getParameter("author"),
                        request.getParameter("illustrator"),
                        Boolean.parseBoolean(request.getParameter("isVariant")),
                        request.getParameter("storeName"), request.getParameter("ownerName"),
                        request.getParameter("storeInfo"));
                    
                    session.setAttribute("feedback", "Success! Added " + title);
                }
            } catch (NumberFormatException excpt) {
                session.setAttribute("feedback", "Add Error: Issue number must be a number.");
            }
        }
        

        // Captures user input from index.jsp form attributes
        //String title = request.getParameter("comicTitle");
        //String issueString = request.getParameter("issueNumber");
        //int issueNumber = Integer.parseInt(request.getParameter("issueNumber"));
        //String publisher = request.getParameter("publisher");
        //String author = request.getParameter("author");
        //String illustrator = request.getParameter("illustrator");
        //String variant = request.getParameter("variantCover");
        

//        String message = ""; // Sends feedback to the user
//        int issueNumber = 0;
//        boolean isValid = true;
//        
//        if (title == null || title.trim().isEmpty()) {
//            message = "Error: Comic title is null.";
//            isValid = false;
//        } else {
//            try {
//                // Numerical validation
//                issueNumber = Integer.parseInt(issueString);
//                
//                if (isValid) {
//                    String sqlWantList = "INSERT INTO want_list (user_id, title, issue_number, publisher, author, illustrator, is_variant_cover) VALUES (?, ?, ?, ?, ?, ?, ?)";
//                    String sqlStore = "INSERT INTO stores (want_list_id, store_name, owner_name, store_info) VALUES (?, ?, ?, ?)";
//                    
//                    // Database interaction with PreparedStatements to prevent SQL Injection.
//                    try (Connection conn = DBConnection.getConnection()) {
//                        
//                        // Use RETURN_GENERATED_KEYS to get the ID created for the 'want_list' entry.
//                        // We need this ID to link the Store Information in the second table.
//                        try (PreparedStatement psComic = conn.prepareStatement(sqlWantList, Statement.RETURN_GENERATED_KEYS)) {
//                            psComic.setInt(1, userId);
//                            psComic.setString(2, title);
//                            psComic.setInt(3, issueNumber);
//                            psComic.setString(4, publisher);
//                            psComic.setString(5, author);
//                            psComic.setString(6, illustrator);
//                            psComic.setBoolean(7, Boolean.parseBoolean(variant));
//                        
//                            // Sets parameters
//                            psComic.executeUpdate();
//                            
//                            // Retrieve the Auto-Incremented ID from the want_list table.
//                            ResultSet rs = psComic.getGeneratedKeys();
//                            if (rs.next()) {
//                                int newComicId = rs.getInt(1);
//                                // Logic to insert in stores table
//                                
//                                try (PreparedStatement psStore = conn.prepareStatement(sqlStore)) {
//                                    psStore.setInt(1, newComicId);
//                                    psStore.setString(2, request.getParameter("storeName"));
//                                    psStore.setString(3, request.getParameter("ownerName"));
//                                    psStore.setString(4, request.getParameter("storeInfo"));
//                                    psStore.executeUpdate();
//                                }
//                            }
//                        }
//                            
//                        message = "Success! " + title + " #" + issueNumber + " has been added to your vault.";
//                    } catch (Exception excpt){
//                        excpt.printStackTrace();
//                        message = "Database Error: Could not save comic.";
//                        isValid = false;
//                    }
//                }
//                
//                //message = "Success! Added " + title + " #" + issueNumber;
//            } catch(NumberFormatException excpt) {
//                // This triggers ig the user enters text in the number field
//                message = "Error: Issue number must be a whole number (e.g. 61).";
//                isValid = false;
//            }
//        }

        
//        boolean success = dao.addComicWithStore(userId, title, issueNumber, 
//                              publisher, author, illustrator, Boolean.parseBoolean(request.getParameter("variantCover")),
//                              request.getParameter("storeName"), request.getParameter("ownerName"), request.getParameter("storeInfo"));
        
        // Sends result back to the user
        //request.setAttribute("feedback", message);
        
//        if (success) {
//            request.setAttribute("feedback", "Success! Added " + title);
//        } else {
//            request.setAttribute("feedback", "Error: Could not save to database.");
//        }
        
        // Redirects to trigger doGet and refresh the list.
        response.sendRedirect("ComicServlet");
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int userId = (int) session.getAttribute("userId");
//        List<Comic> comicList = new ArrayList<>();
//        
//        // SQL JOIN combines data from 'want_list' table and 'stores' into a single result set
//         String sql = "SELECT wl.title, wl.issue_number, s.store_name, s.store_info " +
//                      "FROM want_list wl " +
//                      "LEFT JOIN stores s ON wl.id = s.want_list_id " +
//                      "WHERE wl.user_id = ?"; // FILTER
//        
//        try (Connection conn = DBConnection.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql)) {
//            
//            ps.setInt(1, userId);
//            try(ResultSet rs = ps.executeQuery()) {
//                // Iterate through the rows returned by MySQL
//                while (rs.next()) {
//                    // Convert each database row into a POJO
//                    Comic comicInfo = new Comic(
//                            rs.getString("title"),
//                            rs.getInt("issue_number"),
//                            rs.getString("store_name"),
//                            rs.getString("store_info")
//                    );
//                    comicList.add(comicInfo);
//                }
//            }
//        } catch (Exception excpt) {
//            excpt.printStackTrace();
//            
//            request.setAttribute("error", "Could not load comic list.");
//        }
//

        ComicDAO dao = new ComicDAO(); // Data Access Object.
        
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteComic(id, userId);
            response.sendRedirect("ComicServlet");
            return;
        }
        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Comic existingComic = dao.getComicById(id, userId); // Data Access Object to work on SQL Logic.
            request.setAttribute("comic", existingComic);
            request.getRequestDispatcher("editComic.jsp").forward(request, response);
            return;
        }
        
        List<Comic> comicList = dao.getComicsByUserId(userId);
        
        // Passes the list to JSP view using a Request attribute.
        request.setAttribute("myComics", comicList);
        request.getRequestDispatcher("wantList.jsp").forward(request, response);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComicServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComicServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
