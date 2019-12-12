/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.managers.AccountManager;

/**
 * Web application lifecycle listener.
 *
 * @author Yecfly
 */
public class CL implements ServletContextListener {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    private ServletContext context = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
        try {
            AccountManager manager = new AccountManager(emf);
            context.setAttribute("accountManager", manager);
            System.err.println("AccountManager has been set!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
