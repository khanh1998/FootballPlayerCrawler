/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author KHANHBQSE63463
 */
public class ConnectionUtils {
    public static Connection getConnection() 
            throws SQLException, NamingException {
        Context appContext = new InitialContext();
        Context tomcaContext = (Context) appContext.lookup("java:comp/env");
        DataSource dataSource = (DataSource) tomcaContext.lookup("PlayerDatasource");
        return dataSource.getConnection();
    }
}
