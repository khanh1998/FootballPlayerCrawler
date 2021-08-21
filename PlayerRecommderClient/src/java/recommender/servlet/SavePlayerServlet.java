/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import recommender.savedplayer.SavedPlayerDAO;

/**
 *
 * @author KHANHBQSE63463
 */
@WebServlet(name = "SavePlayerServlet", urlPatterns = {"/SavePlayerServlet"})
public class SavePlayerServlet extends HttpServlet {
    private final String ERROR_HTML = "error.html";
    private final String DISPATCHER_SERVLET = "DispatcherServlet";
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
        String playerId = request.getParameter("playerId");
        String url = ERROR_HTML;
        String fbref = request.getParameter("link");
        boolean isSaved = false;
        
        try {
            HttpSession session = request.getSession(false);
            if (session != null && fbref != null) {
                List<String> savedFbrefs = (List<String>) 
                        request.getSession().getAttribute("FBREF");
                for (String item: savedFbrefs) {
                    if (item.equalsIgnoreCase(fbref)) {
                        isSaved = true;
                    }
                }
                if (!isSaved) {
                    SavedPlayerDAO savedPlayerDAO = new SavedPlayerDAO();
                    String username = (String) session.getAttribute("USERNAME");
                    savedPlayerDAO.insert(username, fbref);
                    
                    //add to session
                    savedFbrefs.add(fbref);
                    session.setAttribute("FBREF", savedFbrefs);
                    url = DISPATCHER_SERVLET + "?action=viewPlayerProfile&playerId=" + playerId;
                    
                }
            }
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
