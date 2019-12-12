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
import model.database.AAccount;
import model.managers.AccountManager;

/**
 *
 * @author Yecfly
 */
public class adminhandler extends HttpServlet {

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
        AAccount aa = (AAccount) s.getAttribute("Aaccount");
        if (aa == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        AccountManager am = (AccountManager) this.getServletContext().getAttribute("accountManager");

        String screen = request.getServletPath();
        s.removeAttribute("records");
        s.removeAttribute("recordsyes");
        s.removeAttribute("recordsno");
        s.removeAttribute("invalid");
        s.removeAttribute("detail");
        s.removeAttribute("updateyes");
        s.removeAttribute("updateno");
        s.removeAttribute("nullvalue");
        try {
            if (screen.equals("/admin_search")) {
                String date = request.getParameter("date");
                s.setAttribute("records", 1);
                if (date == null) {
                    s.setAttribute("recordsno", 1);
                } else if (date.equals("")) {
                    s.setAttribute("recordsno", 1);
                } else if (am.getRE(date)) {
                    s.setAttribute("recordsyes", 1);
                } else {
                    s.setAttribute("recordsno", 1);
                }
            }
            if (screen.equals("/admin_list")) {
                s.setAttribute("records", 1);
                if (am.getUnrepaired()) {
                    s.setAttribute("recordsyes", 1);
                } else {
                    s.setAttribute("recordsno", 1);
                }
            }
            if (screen.equals("/admin_list_w")) {
                s.setAttribute("records", 1);
                if (am.getWIP()) {
                    s.setAttribute("recordsyes", 1);
                } else {
                    s.setAttribute("recordsno", 1);
                }
            }
            if (screen.equals("/admin_list_t")) {
                s.setAttribute("records", 1);
                if (am.getTemrepaired()) {
                    s.setAttribute("recordsyes", 1);
                } else {
                    s.setAttribute("recordsno", 1);
                }
            }
            if (screen.equals("/admin_list_r")) {
                s.setAttribute("records", 1);
                if (am.getR()) {
                    s.setAttribute("recordsyes", 1);
                } else {
                    s.setAttribute("recordsno", 1);
                }
            }
            if (screen.equals("/admin_detail")) {
                s.setAttribute("detail", 1);
                String rid = request.getParameter("id");
                if (am.getPo(rid)) {
                    s.setAttribute("searchyes", 1);
                } else {
                    s.setAttribute("searchno", 1);
                }
            }
            if (screen.equals("/admin_update")) {
                String id = request.getParameter("id");
                String crewid = request.getParameter("crewid");
                String hc = request.getParameter("hc");
                String ea = request.getParameter("ea");
                String workhour = request.getParameter("workhour");
                String status = request.getParameter("status");
                String material = request.getParameter("material");
                String cost = request.getParameter("cost");
                String type = request.getParameter("type");
                String dc = request.getParameter("dc");
                if (id == null || crewid == null || hc == null || ea == null || workhour == null ||
                        status == null || material == null || cost == null || type == null || dc == null) {
                    s.setAttribute("nullvalue", 1);
                } else if (id.equals("") || crewid.equals("") || hc.equals("") || ea.equals("") ||
                        workhour.equals("") || status.equals("")
                        || material.equals("") || cost.equals("") || type.equals("") || dc.equals("")) {
                    s.setAttribute("nullvalue", 1);
                } else {
                    try {
                        utx.begin();
                        boolean si = am.upDate(id, crewid, hc, ea, workhour, status, material, cost, type, dc);
                        utx.commit();
                        if (si) {
                            s.setAttribute("updateyes", 1);
                        } else {
                            s.setAttribute("updateno", 1);
                        }
                    } catch (Exception e) {
                        try {
                            System.err.println("Track in adminhandler:try to rollback");
                            utx.rollback();
                            System.err.println("Track in adminhandler:rollback done");
                        } catch (Exception exe) {
                            System.err.println("Rollback failed: " + exe.getMessage());
                        }
                        System.err.println("Track in adminhandler:" + e.toString());
                        s.setAttribute("updateno", 0);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed in handling: " + screen);
        } finally {
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
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
