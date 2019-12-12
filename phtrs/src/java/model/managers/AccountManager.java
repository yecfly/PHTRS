/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import model.database.PotholeRecord;

/**
 *
 * @author Yecfly
 */
public class AccountManager {
    private List pos;
    private PotholeRecord cur;
    private EntityManager em;

    public AccountManager(EntityManagerFactory emf) throws IOException {
        try {
            em = emf.createEntityManager();
        } catch (Exception ex) {
            throw new IOException(
                    "Couldn't open connection to database: " + ex.getMessage());
        }
    }

    public boolean Clogin(String user, String password) throws IOException {
        if (!user.equals("") && !password.equals("")) {
            System.err.println("Clogin" + user + ":" + password);
            Object x = null;
            try {
                Query query = em.createQuery("SELECT ac FROM CAccount ac WHERE ac.cAccountPK.name = :username and ac.cAccountPK.password = :pw");
                query.setParameter("username", user);
                query.setParameter("pw", password);
                x = query.getSingleResult();
            } catch (Exception ex) {
                System.err.println(ex.toString());
                return false;
            }
            System.err.println("success to get account" + user);

            if (x == null) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean Alogin(String user, String password) throws IOException {
        if (!user.equals("") && !password.equals("")) {
            System.err.println("Alogin " + user + ":" + password);
            Object x = null;
            try {
                Query q = em.createQuery("SELECT ad from AAccount ad where ad.name = :un and ad.password = :pw");
                q.setParameter("un", user);
                q.setParameter("pw", password);
                x = q.getSingleResult();
                if (x != null) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                System.err.println(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean logout(String user) {
        return true;
    }

    public boolean insert(String streetAddr, String location, String size, String district, String CName, String CAddr, String CPhone) {
        boolean s = false;
        try {
            System.err.println("Track:insert() called");
            Date d = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS");//
            PotholeRecord po = new PotholeRecord();
            po.setId(dateFormat.format(d));
            System.err.println("Track:new record finished");
            po.setStreetaddr(streetAddr);
            po.setLocation(location);
            po.setPsize(Integer.parseInt(size));
            po.setDistrict(district);
            po.setCname(CName);
            po.setCaddr(CAddr);
            po.setCphone(CPhone);
            if (location.length() > 4) {
                po.setRepri(Integer.parseInt(size) * 2);
            } else {
                po.setRepri(Integer.parseInt(size) * 2 - 1);
            }
            po.setStatus("not repaired");
            po.setCrewid(0);
            po.setHc(0);
            po.setEa("None");
            po.setWorkhour(0.0);
            po.setMaterial(0.0);
            po.setCost(0.0);
            po.setType("None");
            po.setDc(0.0);
            em.persist(po);
            em.flush();
            s = true;
            System.err.println("Track:insert() finished successfully");
        } catch (Exception e) {
            System.err.println("Track:insert() failed");
            System.err.println("Couldn't open execute insert sql when insert record: " + e.getMessage());
        }
        return s;
    }

    public boolean upDate(String id,String crewid,String hc,String ea,String workhour,String status,String material,String cost,String type,String dc){
        cur=null;
        try{
            cur=em.find(PotholeRecord.class, id);
            if(cur==null) return false;
            cur.setCrewid(Integer.parseInt(crewid));
            cur.setHc(Integer.parseInt(hc));
            cur.setEa(ea);
            cur.setWorkhour(Double.parseDouble(workhour));
            cur.setStatus(status);
            cur.setMaterial(Double.parseDouble(material));
            cur.setCost(Double.parseDouble(cost));
            cur.setType(type);
            cur.setDc(Double.parseDouble(dc));
            em.persist(cur);
            em.flush();
        }catch(Exception e){
            System.err.println("Failed in AM update: "+e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean getPo(String date){
        cur=null;
        try{
            cur=em.find(PotholeRecord.class, date);
            if(cur!=null) return true;
            else return false;
        }catch(Exception e){
            System.err.println("Failed in AM getPo(String date): "+e.getMessage());
            return false;
        }
    }
    public PotholeRecord getPo(){
        return cur;
    }
    
    public boolean getRE(String date){
        pos=null;
        try{
            String sql="SELECT pr FROM PotholeRecord pr WHERE pr.id LIKE '%"+date+"%'";
            Query q=em.createQuery(sql);
            pos=q.getResultList();
            if(pos==null) return false;
            if(pos.isEmpty()) return false;
            else return true;
        }catch(Exception e){
            System.err.println("Exception in AM getPo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean getUnrepaired(){
        pos=null;
        try{
            Query q=em.createQuery("SELECT pr FROM PotholeRecord pr WHERE pr.status = :s");
            q.setParameter("s", "not repaired");
            pos=q.getResultList();
            if(pos==null) return false;
            if(pos.isEmpty()) return false;
            else return true;
        }catch(Exception e){
            System.err.println("Exception in AM getUnrepaired: "+e.getMessage());
            return false;
        }
    }
    
    public boolean getTemrepaired(){
        pos=null;
        try{
            Query q=em.createQuery("SELECT pr FROM PotholeRecord pr WHERE pr.status = :s");
            q.setParameter("s", "temporary repaired");
            pos=q.getResultList();
            if(pos==null) return false;
            if(pos.isEmpty()) return false;
            else return true;
        }catch(Exception e){
            System.err.println("Exception in AM getTemrepaired: "+e.getMessage());
            return false;
        }
    }
    
    public boolean getWIP(){
        pos=null;
        try{
            Query q=em.createQuery("SELECT pr FROM PotholeRecord pr WHERE pr.status = :s");
            q.setParameter("s", "work in progress");
            pos=q.getResultList();
            if(pos==null) return false;
            if(pos.isEmpty()) return false;
            else return true;
        }catch(Exception e){
            System.err.println("Exception in AM getWIP: "+e.getMessage());
            return false;
        }
    }
    
    public boolean getR(){
        pos=null;
        try{
            Query q=em.createQuery("SELECT pr FROM PotholeRecord pr WHERE pr.status = :s");
            q.setParameter("s", "repaired");
            pos=q.getResultList();
            if(pos==null) return false;
            if(pos.isEmpty()) return false;
            else return true;
        }catch(Exception e){
            System.err.println("Exception in AM getWIP: "+e.getMessage());
            return false;
        }
    }
    
    public List getUn(){
        return pos;
    }
    
    
}
