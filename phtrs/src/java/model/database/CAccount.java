/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yecfly
 */
@Entity
@Table(name = "CITIZEN_ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CAccount.findAll", query = "SELECT c FROM CAccount c"),
    @NamedQuery(name = "CAccount.findByName", query = "SELECT c FROM CAccount c WHERE c.cAccountPK.name = :name"),
    @NamedQuery(name = "CAccount.findByPassword", query = "SELECT c FROM CAccount c WHERE c.cAccountPK.password = :password")})
public class CAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CAccountPK cAccountPK;

    public CAccount() {
    }

    public CAccount(CAccountPK cAccountPK) {
        this.cAccountPK = cAccountPK;
    }

    public CAccount(String name, String password) {
        this.cAccountPK = new CAccountPK(name, password);
    }

    public CAccountPK getCAccountPK() {
        return cAccountPK;
    }

    public void setCAccountPK(CAccountPK cAccountPK) {
        this.cAccountPK = cAccountPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cAccountPK != null ? cAccountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CAccount)) {
            return false;
        }
        CAccount other = (CAccount) object;
        if ((this.cAccountPK == null && other.cAccountPK != null) || (this.cAccountPK != null && !this.cAccountPK.equals(other.cAccountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.database.CAccount[ cAccountPK=" + cAccountPK + " ]";
    }
    
}
