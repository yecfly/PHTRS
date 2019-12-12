/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.util.List;
import model.database.PotholeRecord;

/**
 *
 * @author Yecfly
 */
public class PR {
    private AccountManager db=null;
    private String id="0";
    
    public PR(){
        
    }
    
    public void setID(String i){
        id=i;
    }
    public String getID(){
        return id;
    }
    
    public void setdb(AccountManager a){
        db=a;
    }
    public AccountManager getdb(){
        return db;
    }
    
    public PotholeRecord getPo(){
        return db.getPo();
    }
    
    public List getUn(){
        return db.getUn();
    }
}
