/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.initialize;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author KHANHBQSE63463
 */
public class Inititalizer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext servletContext = sce.getServletContext();
            String realPath = servletContext.getRealPath("/");
            
            //xsl which is used in index.jsp
            String xslPlayerTable = realPath + "WEB-INF\\transfermarkt\\playerTable.xsl";
            String fileContent = readFile(xslPlayerTable);
            servletContext.setAttribute("XSL_PLAYER_TABLE", fileContent);
            
            //xsl which is used in playerprofile.jsp
            String xslPlayerDetail = realPath + "WEB-INF\\transfermarkt\\playerDetail.xsl";
            fileContent = readFile(xslPlayerDetail);
            servletContext.setAttribute("XSL_PLAYER_DETAIL", fileContent);
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Inititalizer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inititalizer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Inititalizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String readFile(String filePath) throws FileNotFoundException, IOException {
        File xslFile = new File(filePath);
        FileInputStream fis = new FileInputStream(xslFile);
        byte[] data = new byte[(int) xslFile.length()];
        fis.read(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(data);
        return baos.toString("UTF-8");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
