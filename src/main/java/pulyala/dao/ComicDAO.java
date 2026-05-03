/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulyala.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.final_project_cis175.DBConnection;
import pulyala.business.Comic;

/**
 * Data Access Object to handle SQL Logic
 * 
 * @author rohit
 */
public class ComicDAO {
    
    public List<Comic> getComicsByUserId(int userId) {
        List<Comic> comicList = new ArrayList<>();
        String sql = "SELECT wl.id, wl.title, wl.issue_number, wl.publisher, wl.author, " +
                     "wl.illustrator, wl.is_variant_cover, s.store_name, s.owner_name, s.store_info " +
                      "FROM want_list wl " +
                      "LEFT JOIN stores s ON wl.id = s.want_list_id " +
                      "WHERE wl.user_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    comicList.add(new Comic(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("issue_number"),
                        rs.getString("publisher"),
                        rs.getString("author"),
                        rs.getString("illustrator"),
                        rs.getBoolean("is_variant_cover"),
                        rs.getString("store_name"),
                        rs.getString("owner_name"),
                        rs.getString("store_info")
                    ));
                }
            }
        } catch (SQLException excpt) {
            excpt.printStackTrace();
        }
        return comicList;
    }
    
    public Comic getComicById(int comicId, int userId) {
        String sql = "SELECT wl.id, wl.title, wl.issue_number, wl.publisher, wl.author, " +
                     "wl.illustrator, wl.is_variant_cover, s.store_name, s.owner_name, s.store_info " +
                      "FROM want_list wl " +
                      "LEFT JOIN stores s ON wl.id = s.want_list_id " +
                      "WHERE wl.id = ? AND wl.user_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comicId);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Comic(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("issue_number"),
                        rs.getString("publisher"),
                        rs.getString("author"),
                        rs.getString("illustrator"),
                        rs.getBoolean("is_variant_cover"),
                        rs.getString("store_name"),
                        rs.getString("owner_name"),
                        rs.getString("store_info")
                    );
                }
            }
        } catch (SQLException excpt) { 
            excpt.printStackTrace(); 
        }
        return null;
    }
    
    public boolean updateComic(int comicId, int userId, String title, int issue,
                               String publisher, String author, String illustrator,
                               boolean isVariant, String storeName, String ownerName, String storeInfo) {
        String sqlWantList = "UPDATE want_list SET title = ?, issue_number = ?, publisher = ?, " + 
                             "author = ?, illustrator = ?, is_variant_cover = ? " + 
                             "WHERE id = ? AND user_id = ?";
        
        
        String sqlStore = "UPDATE stores SET store_name = ?, owner_name = ?, store_info = ? WHERE want_list_id = ?";
        
        try (Connection conn = DBConnection.getConnection();) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(sqlWantList);
                 PreparedStatement ps2 = conn.prepareStatement(sqlStore)) {
                
                ps1.setString(1, title);
                ps1.setInt(2, issue);
                ps1.setString(3, publisher);
                ps1.setString(4, author);
                ps1.setString(5, illustrator);
                ps1.setBoolean(6, isVariant);
                ps1.setInt(7, comicId);
                ps1.setInt(8, userId);
                ps1.executeUpdate();
                
                ps2.setString(1, storeName);
                ps2.setString(2, ownerName);
                ps2.setString(3, storeInfo);
                ps2.setInt(4, comicId);
                ps2.executeUpdate();
                
                conn.commit();
                return true;
            } catch (SQLException excpt) {
                conn.rollback();
                excpt.printStackTrace();
            }
        } catch (SQLException excpt) {
            excpt.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deleteComic(int comicId, int userId) {
        String sqlStore = "DELETE FROM stores WHERE want_list_id = ?";
        String sqlWantList = "DELETE FROM want_list WHERE id = ? AND user_id = ?";
        
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps1 = conn.prepareStatement(sqlStore)) {
                    ps1.setInt(1, comicId);
                    ps1.executeUpdate();
                }
                try (PreparedStatement ps2 = conn.prepareStatement(sqlWantList)) {
                    ps2.setInt(1, comicId);
                    ps2.setInt(2, userId);
                    ps2.executeUpdate();
                }
                conn.commit();
                return true;
            } catch (SQLException excpt) {
                conn.rollback();
                excpt.printStackTrace();
            }
        } catch (SQLException excpt) {
            excpt.printStackTrace();
        }
        return false;
    }
    
    public boolean addComicWithStore(int userId, String title, int issue, String publisher,
                                    String author, String illustrator, boolean isVariant,
                                    String storeName, String ownerName, String storeInfo) {
        
        String sqlWantList = "INSERT INTO want_list (user_id, title, issue_number, publisher, author, illustrator, is_variant_cover) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlStore = "INSERT INTO stores (want_list_id, store_name, owner_name, store_info) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            
            try (PreparedStatement psComic = conn.prepareStatement(sqlWantList, Statement.RETURN_GENERATED_KEYS)) {
                psComic.setInt(1, userId);
                psComic.setString(2, title);
                psComic.setInt(3, issue);
                psComic.setString(4, publisher);
                psComic.setString(5, author);
                psComic.setString(6, illustrator);
                psComic.setBoolean(7, isVariant);
                psComic.executeUpdate();
                            
                ResultSet rs = psComic.getGeneratedKeys();
                if (rs.next()) {
                    int newId = rs.getInt(1);
                    try (PreparedStatement psStore = conn.prepareStatement(sqlStore)) {
                        psStore.setInt(1, newId);
                        psStore.setString(2, storeName);
                        psStore.setString(3, ownerName);
                        psStore.setString(4, storeInfo);
                        psStore.executeUpdate();
                    }
                }
                conn.commit();
                return true;
            } catch (SQLException excpt) {
                conn.rollback();
                excpt.printStackTrace();
                return false;
            }
        } catch (SQLException excpt) {
            excpt.printStackTrace();
            return false;
        }
    }
    
}
