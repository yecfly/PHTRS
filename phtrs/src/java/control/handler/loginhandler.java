/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.database.AAccount;
import model.database.CAccountPK;
import model.managers.AccountManager;

/**
 *
 * @author Yecfly
 */
public class loginhandler extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        String logout = request.getParameter("logout");

        System.out.println("name:" + username + " password:" + password + " type:" + type);
        if (logout != null) {
            HttpSession session = request.getSession();
            session.removeAttribute("Caccount");
            session.removeAttribute("Aaccount");
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
        if (type != null) {
            AccountManager manager = (AccountManager) this.getServletContext().getAttribute("accountManager");
            boolean success = false;
            if (type.equals("Citizen")) {
                success = manager.Clogin(username, password);
                if (success) {
                    HttpSession session = request.getSession();
                    CAccountPK account = (CAccountPK) session.getAttribute("Caccount");
                    if (account == null) {
                        account = new CAccountPK();
                        account.setName(username);
                        account.setPassword(password);
                        request.getSession().setAttribute("Caccount", account);
                    } else {
                        account.setName(username);
                        account.setPassword(password);
                    }
                    request.getRequestDispatcher("/report")
                            .forward(request, response);
                } else {
                    request.getRequestDispatcher("/login.jsp?login=0")
                            .forward(request, response);
                }
            } else {
                success = manager.Alogin(username, password);
                if (success) {
                    HttpSession session = request.getSession();
                    AAccount account = (AAccount) session.getAttribute("Aaccount");
                    if (account == null) {
                        account = new AAccount();
                        account.setName(username);
                        account.setPassword(password);
                        request.getSession().setAttribute("Aaccount", account);
                    } else {
                        account.setName(username);
                        account.setPassword(password);
                    }
                    request.getRequestDispatcher("/admin")
                            .forward(request, response);
                } else {
                    request.getRequestDispatcher("/login.jsp?login=0")
                            .forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("/login.jsp?login=0")
                    .forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
