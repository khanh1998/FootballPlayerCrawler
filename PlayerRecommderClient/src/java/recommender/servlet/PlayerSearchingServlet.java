/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import recommender.playerinfo.PlayerInfoDAO;
import transfermarkt.jaxb.ObjectFactory;
import transfermarkt.jaxb.PlayerInfoType;
import transfermarkt.jaxb.PlayerInfos;

/**
 *
 * @author KHANHBQSE63463
 */
@WebServlet(name = "PlayerSearchingServlet", urlPatterns = {"/PlayerSearchingServlet"})
public class PlayerSearchingServlet extends HttpServlet {

    private final String INDEX_JSP = "index.jsp";
    private final String ERROR_HTML = "error.html";

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
        String url = INDEX_JSP;
        PlayerInfoDAO infoDAO = new PlayerInfoDAO();
        String searchValue = request.getParameter("searchValue");
        String currentOffset = request.getParameter("offset");
        String currentSize = request.getParameter("pageSize");
        int offset;
        int size;
        if (currentOffset == null || currentOffset.isEmpty()) {
            offset = 0;
        } else {
            offset = Integer.parseInt(currentOffset);
        }
        if (currentSize == null || currentSize.isEmpty()) {
            size = 10;
        } else {
            size = Integer.parseInt(currentSize);
        }
        List<PlayerInfoType> result = null;
        String xml = "";

        try {
            if (searchValue != null || !searchValue.isEmpty()) {

                result = infoDAO.search(searchValue, offset, size);
                PlayerInfos list = new PlayerInfos();
                for (PlayerInfoType player : result) {
                    list.getPlayerInfo().add(player);
                }
                ObjectFactory objectFactory = new ObjectFactory();
                JAXBElement element = objectFactory.createPlayerInfos(list);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JAXBContext context = JAXBContext.newInstance("transfermarkt.jaxb");
                Marshaller m = context.createMarshaller();

                m.marshal(element, baos);
                xml += baos.toString("UTF-8");
                request.setAttribute("XML_RESULT", xml);
                request.setAttribute("RESULT_SIZE", result.size());
                request.setAttribute("FROM_SEARCH", true);
                System.out.println(xml);
            }
        } catch (NamingException | SQLException | JAXBException ex) {
            url = ERROR_HTML;
            Logger.getLogger(PlayerSearchingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

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
        processRequest(request, response);
    }

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
