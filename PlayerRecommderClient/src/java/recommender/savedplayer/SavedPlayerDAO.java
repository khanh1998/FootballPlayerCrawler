/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.savedplayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import recommender.utils.ConnectionUtils;

/**
 *
 * @author KHANHBQSE63463
 */
public class SavedPlayerDAO {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public SavedPlayerDAO() {
    }
    public List<String> getListSavedPlayer(String username) 
            throws SQLException, NamingException {
        List<String> fbref = new ArrayList<>();
        
        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT fbref_url FROM savedplayer WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fbref.add(resultSet.getString("fbref_url"));
            }
        } finally {
            closeConnection();
        }
        return fbref;
    }
    public boolean delete(String username, String fbref_url) {
        try {
            connection = ConnectionUtils.getConnection();
            String query = "DELETE FROM savedplayer WHERE username = ? AND fbref_url = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, fbref_url);

            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavedPlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SavedPlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean insert(String username, String fbref_url) {
        try {
            connection = ConnectionUtils.getConnection();
            String query = "INSERT INTO savedplayer VALUES(?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, fbref_url);

            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavedPlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SavedPlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    private void closeConnection() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
