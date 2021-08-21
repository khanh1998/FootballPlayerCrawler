/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KHANHBQSE63463
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = {"/DispatcherServlet"})
public class DispatcherServlet extends HttpServlet {
    private final String INDEX_JSP = "index.jsp";
    private final String VIEW_INDEX_SERVLET = "ViewIndexServlet";
    private final String LOGIN_HTML = "login.html";
    private final String SEARCH_SERVLET = "PlayerSearchingServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String REGISTER_SERVLET = "RegisterServlet";
    private final String SAVE_SERVLET = "SavePlayerServlet";
    private final String REMOVE_SAVED_SERVLET = "RemoveSavedPlayer";
    private final String PLAYER_PROFILE_SERVLET = "PlayerProfileServlet";
    
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
        String action = request.getParameter("action");
        String url = "error.html";
        
        try {
            if (action == null) {
                url = VIEW_INDEX_SERVLET;
            } else {
                switch(action) {
                    case "login":
                        url = LOGIN_SERVLET;
                        break;
                    case "logout":
                        url = LOGOUT_SERVLET;
                        break;
                    case "search": 
                        url = SEARCH_SERVLET;
                        break;
                    case "viewPlayerProfile": 
                        url = PLAYER_PROFILE_SERVLET;
                        break;
                    case "save":
                        url = SAVE_SERVLET;
                        break;
                    case "remove":
                        url = REMOVE_SAVED_SERVLET;
                        break;
                    case "register":
                        url = REGISTER_SERVLET;
                        break;
                }
            }
        } catch (Exception e) {
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
