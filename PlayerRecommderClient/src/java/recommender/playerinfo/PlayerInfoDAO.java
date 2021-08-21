/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.playerinfo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import recommender.utils.ConnectionUtils;
import transfermarkt.jaxb.CurrentValueType;
import transfermarkt.jaxb.PlayerInfoType;

/**
 *
 * @author KHANHBQSE63463
 */
public class PlayerInfoDAO {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public PlayerInfoDAO() {
    }
    public List<PlayerInfoType> getListByFbrefUrl(List<String> fbrefs) 
            throws SQLException, NamingException {
        List<PlayerInfoType> list = new ArrayList<>();
        for (String fbref: fbrefs) {
            PlayerInfoType infoType = searchByFbref(fbref);
            list.add(infoType);
        }
        return list;
    }
    public List<PlayerInfoType> getList(int [] ids) 
            throws SQLException, NamingException {
        List<PlayerInfoType> infoTypes = new ArrayList<>();
        for (int id: ids) {
            infoTypes.add(searchById(id));
        }
        return infoTypes;
    }
    public PlayerInfoType searchByFbref(String fbref) 
            throws SQLException, NamingException {
        try {
            connection = ConnectionUtils.getConnection();
            if (connection != null) {
                String query = "SELECT * FROM playerinfo WHERE fbref_url = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, fbref);

                resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    PlayerInfoType infoType = convertResultToPlayerInfo(resultSet);
                    return infoType;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }
    public PlayerInfoType searchById(int id) 
            throws SQLException, NamingException {
        try {
            connection = ConnectionUtils.getConnection();
            if (connection != null) {
                String query = "SELECT * FROM playerinfo WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);

                resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    PlayerInfoType infoType = convertResultToPlayerInfo(resultSet);
                    return infoType;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public List<PlayerInfoType> search(String playerName, int offset, int size)
            throws SQLException, NamingException {
        List<PlayerInfoType> infoTypes = new ArrayList<>();
        try {
            connection = ConnectionUtils.getConnection();
            if (connection != null) {
                String query = "SELECT * FROM playerinfo WHERE name LIKE ? LIMIT ?, ?";
                statement = connection.prepareStatement(query);
                playerName = "%" + playerName + "%";
                statement.setString(1, playerName);
                statement.setInt(2, offset);
                statement.setInt(3, size);

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    PlayerInfoType infoType = convertResultToPlayerInfo(resultSet);
                    infoTypes.add(infoType);
                }
            }
        } finally {
            closeConnection();
        }
        return infoTypes;
    }

    private PlayerInfoType convertResultToPlayerInfo(ResultSet resultSet) throws SQLException {
        PlayerInfoType infoType = new PlayerInfoType();
        int id = resultSet.getInt("id");
        infoType.setId(BigInteger.valueOf(id));
        String transfermarkt_avartar = resultSet.getString("transfermarkt_avartar");
        infoType.setTransfermarktAvartar(transfermarkt_avartar);
        String fbref_avartar = resultSet.getString("fbref_avartar");
        infoType.setFbrefAvartar(fbref_avartar);
        String transfermarkt_url = resultSet.getString("transfermarkt_url");
        infoType.setTransfermarktUrl(transfermarkt_url);
        String fbref_url = resultSet.getString("fbref_url");
        infoType.setFbrefUrl(fbref_url);
        String club = resultSet.getString("club");
        infoType.setClub(club);
        String nation = resultSet.getString("nation");
        infoType.setNation(nation);
        String place_of_birth = resultSet.getString("place_of_birth");
        infoType.setPlaceOfBirth(place_of_birth);
        String name = resultSet.getString("name");
        infoType.setName(name);
        String birth_date = resultSet.getString("birth_date");
        infoType.setBirthDate(birth_date);
        float height = resultSet.getFloat("height");
        infoType.setHeight(BigDecimal.valueOf(height));
        String contact_expires = resultSet.getString("contact_expires");
        infoType.setContactExpires(contact_expires);
        float value = resultSet.getFloat("value");

        String unit = resultSet.getString("unit");
        String lasted_update = resultSet.getString("lasted_update");
        CurrentValueType valueType = new CurrentValueType();
        valueType.setLastedUpdate(lasted_update);
        valueType.setUnit(unit);
        valueType.setValue(BigDecimal.valueOf(value));

        infoType.setCurrentValue(valueType);
        return infoType;
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
