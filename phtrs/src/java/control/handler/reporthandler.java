/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.handler;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.database.CAccountPK;
import model.managers.AccountManager;

/**
 *
 * @author Yecfly
 */
public class reporthandler extends HttpServlet {

    @Resource
    private UserTransaction utx;

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
        HttpSession s = request.getSession();
        CAccountPK ca=(CAccountPK)s.getAttribute("Caccount");
        if(ca==null){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        s.setAttribute("ReportSuinfo", "Please fill the following form.");
        String screen = request.getServletPath();
        String streetAddr = request.getParameter("streetAddr");
        System.err.println("TEST on streetAddr:" + streetAddr);
        screen = "/report.jsp";
        if (streetAddr != null) {
            System.err.println("Track:starting handling report request.");
            String location = request.getParameter("location");
            String size = request.getParameter("size");
            String district = request.getParameter("district");
            String CName = request.getParameter("CName");
            String CPhone = request.getParameter("CPhone");
            String CAddr = request.getParameter("CAddr");
            try {
                if (!streetAddr.equals("") && !district.equals("")) {
                    AccountManager m=(AccountManager)this.getServletContext().getAttribute("accountManager");
                    utx.begin();
                    System.err.println("Track:utx begin");
                    boolean su = m.insert(streetAddr, location, size, district, CName, CAddr, CPhone);
                    utx.commit();
                    System.err.println("Track:utx commit");
                    if (su) {
                        s.setAttribute("ReportSuinfo", "Report success! Thancks for your work.");
                    }
                } else {
                    System.err.println("Track in reporthandler:invalid input");
                    s.setAttribute("ReportSuinfo", "Please fill the following form.");
                }
            } catch (Exception e) {
                try {
                    System.err.println("Track:try to rollback");
                    utx.rollback();
                    System.err.println("Track:rollback done");
                } catch (Exception exe) {
                    System.err.println("Rollback failed: " + exe.getMessage());
                }
                System.err.println(e.toString());
                s.setAttribute("ReportSuinfo", "Fail in reporting.Please try again.");
            }
        }
        try {
            request.getRequestDispatcher(screen).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
