/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.playersimilarity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import recommender.utils.ConnectionUtils;

/**
 *
 * @author KHANHBQSE63463
 */
public class PlayerSimilarityDAO {

    public static final int RECOMMENDATION_QUANITTY = 10;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public PlayerSimilarityDAO() {
    }

    public float[][][] getSimilarityPlayerIdList(int id) throws SQLException {
        float[][][] ids = new float[2][RECOMMENDATION_QUANITTY][2];
        try {
            connection = ConnectionUtils.getConnection();
            String query = "SELECT cosine, euclidean FROM playersimilarity WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cosine = resultSet.getString("cosine");
                String euclidean = resultSet.getString("euclidean");
                String[] cosineTokens = cosine.split(";");
                int i = -1;
                for (String token : cosineTokens) {
                    i++;
                    String tmp = token.substring(1, token.length() - 1); // remove ( and )
                    String[] id_distance = tmp.split(",");
                    ids[0][i][0] = Integer.parseInt(id_distance[0]);
                    ids[0][i][1] = Float.parseFloat(id_distance[1]);
                }
                String[] euclideanTokens = euclidean.split(";");
                i = -1;
                for (String token : euclideanTokens) {
                    i++;
                    String tmp = token.substring(1, token.length() - 1); // remove ( and )
                    String[] id_distance = tmp.split(",");
                    ids[1][i][0] = Integer.parseInt(id_distance[0]);
                    ids[1][i][1] = Float.parseFloat(id_distance[1]);
                }
            }

        } catch (NamingException ex) {
            Logger.getLogger(PlayerSimilarityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return ids;
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
