/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.servlet;

import fbref.jaxb.PlayerType;
import fbref.jaxb.PlayerTypes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.xml.namespace.QName;
import recommender.playerinfo.PlayerInfoDAO;
import recommender.playersimilarity.PlayerSimilarityDAO;
import recommender.playerstats.PlayerStatsDAO;
import recommender.utils.ArrayUtils;
import transfermarkt.jaxb.ObjectFactory;
import transfermarkt.jaxb.PlayerInfoType;
import transfermarkt.jaxb.PlayerInfos;

/**
 *
 * @author KHANHBQSE63463
 */
@WebServlet(name = "PlayerProfileServlet", urlPatterns = {"/PlayerProfileServlet"})
public class PlayerProfileServlet extends HttpServlet {
    private final String PLAYER_PROFILE_JSP = "playerprofile.jsp";
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
        String url = "error.html";
        String idStr = request.getParameter("playerId");
        
        try {
            if (idStr != null && !idStr.isEmpty()) {
                PlayerInfoDAO infoDAO = new PlayerInfoDAO();
                PlayerSimilarityDAO similarityDAO = new PlayerSimilarityDAO();
                
                int id = Integer.parseInt(idStr);
                //get detail info of player
                PlayerInfoType info = infoDAO.searchById(id);
                JAXBElement<PlayerInfoType> jAXBElement = 
                        new JAXBElement<>(new QName("", "player_infoType"), PlayerInfoType.class, info);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JAXBContext context = JAXBContext.newInstance(PlayerInfoType.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.marshal(jAXBElement, baos);
                String xmlPlayerDetailt = baos.toString("UTF-8");
                request.setAttribute("XML_PLAYER_DETAIL", xmlPlayerDetailt);
                request.setAttribute("FBREF_URL", info.getFbrefUrl());
                
                //get list of similarity player ids and distance corresponding with two metric
                float[][][] ids_distances = similarityDAO.getSimilarityPlayerIdList(id);
                float[][] cosine_distance = ids_distances[0];
                float[][] euclidean_distance = ids_distances[1];
                
                request.setAttribute("IDS_DISTANCES", ids_distances);
                request.setAttribute("COSINE_DISTANCES", cosine_distance);
                request.setAttribute("EUCLIDEAN_DISTANCES", euclidean_distance);
                
                int[] uniqueIds = ArrayUtils.getUniqueIdList(
                        ids_distances, PlayerSimilarityDAO.RECOMMENDATION_QUANITTY);
                //list similar player info
                List<PlayerInfoType> similarPlayers = infoDAO.getList(uniqueIds);
                similarPlayers.add(info);
                System.out.println("similar player size: " + similarPlayers.size());
                
                ObjectFactory fbrefFactory = new ObjectFactory();
                PlayerInfos infos = new PlayerInfos();
                for (PlayerInfoType infoType: similarPlayers) {
                    infos.getPlayerInfo().add(infoType);
                    System.out.println(infoType.getName());
                }
                JAXBElement jaxbElem = fbrefFactory.createPlayerInfos(infos);
                JAXBContext jAXBContext = JAXBContext.newInstance("transfermarkt.jaxb");
                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                Marshaller m = jAXBContext.createMarshaller();
                m.marshal(jaxbElem, baos2);
                String xmlSimilarPlayers = baos2.toString("UTF-8");
                request.setAttribute("XML_SIMILAR_PLAYERS", xmlSimilarPlayers);
                
                //list of similar player stats
                PlayerStatsDAO statsDAO = new PlayerStatsDAO();
                List<PlayerType> list = statsDAO.getList(uniqueIds);
                list.add(statsDAO.getPlayerStats(id)); //include stats of this player
                System.out.println("player stat size: " + list.size());
                PlayerTypes playerTypes = new PlayerTypes();
                for (PlayerType player: list) {
                    playerTypes.getPlayer().add(player);
                }
                //get list of stats corresponding with id
                fbref.jaxb.ObjectFactory factory = new fbref.jaxb.ObjectFactory();
                JAXBElement playerStats = factory.createPlayers(playerTypes);
                
                jAXBContext = JAXBContext.newInstance("fbref.jaxb");
                Marshaller m1 = jAXBContext.createMarshaller();
                ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                m1.marshal(playerStats, baos1);
                String xmlPlayersStats = baos1.toString("UTF-8");
                request.setAttribute("XML_PLAYERS_STATS", xmlPlayersStats);
                
                //set recommendation quantity for each metric
                request.setAttribute("RECOMMEND_QUANTITY", PlayerSimilarityDAO.RECOMMENDATION_QUANITTY);
                
                url = PLAYER_PROFILE_JSP;
            }
        } catch (JAXBException ex) {
            Logger.getLogger(PlayerProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(PlayerProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
