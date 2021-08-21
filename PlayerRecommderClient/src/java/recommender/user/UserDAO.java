/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import recommender.utils.ConnectionUtils;

/**
 *
 * @author KHANHBQSE63463
 */
public class UserDAO {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    
    public boolean isDuplicateUsername(String username) 
            throws SQLException, NamingException {
        try {
            connection = ConnectionUtils.getConnection();
            if (connection != null) {
                String query = "SELECT * FROM user WHERE username = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                
                resultSet = statement.executeQuery();
                if (resultSet != null && resultSet.next())
                    return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean insert(UserDTO dto) 
            throws SQLException, NamingException {
        try {
             connection = ConnectionUtils.getConnection();
            if (connection != null) {
                String query = "INSERT INTO user VALUES(?,?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, dto.getUsername());
                statement.setString(2, dto.getPassword());
                statement.setString(3, dto.getName());
                statement.setBoolean(4, dto.isIsAdmin());
                
                int result = statement.executeUpdate();
                if (result > 0)
                    return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    public UserDTO getUser(String username) 
            throws SQLException, NamingException {
        try {
            connection = ConnectionUtils.getConnection();
            if (connection != null) {
                String query = "SELECT * FROM user WHERE username = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                
                resultSet = statement.executeQuery();
                UserDTO dTO = new UserDTO();
                if (resultSet != null && resultSet.next()) {
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    boolean isAdmin = resultSet.getBoolean("isAdmin");
                    dTO.setUsername(username);
                    dTO.setName(name);
                    dTO.setPassword(password);
                    dTO.setIsAdmin(isAdmin);
                    return dTO;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
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
