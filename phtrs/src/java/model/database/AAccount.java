/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yecfly
 */
@Entity
@Table(name = "ADMIN_ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AAccount.findAll", query = "SELECT a FROM AAccount a"),
    @NamedQuery(name = "AAccount.findByName", query = "SELECT a FROM AAccount a WHERE a.name = :name"),
    @NamedQuery(name = "AAccount.findByPassword", query = "SELECT a FROM AAccount a WHERE a.password = :password")})
public class AAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAME")
    private String name;
    @Size(max = 6)
    @Column(name = "PASSWORD")
    private String password;

    public AAccount() {
    }

    public AAccount(String name) {
        this.name = name;
    }
    
    public AAccount(String n, String p){
        this.name=n;
        this.password=p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AAccount)) {
            return false;
        }
        AAccount other = (AAccount) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))||!this.password.equals(other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.database.AAccount[ name=" + name + " ]";
    }
    
}
