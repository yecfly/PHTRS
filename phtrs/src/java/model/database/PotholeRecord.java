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
@Table(name = "POTHOLE_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PotholeRecord.findAll", query = "SELECT p FROM PotholeRecord p"),
    @NamedQuery(name = "PotholeRecord.findById", query = "SELECT p FROM PotholeRecord p WHERE p.id = :id"),
    @NamedQuery(name = "PotholeRecord.findByStreetaddr", query = "SELECT p FROM PotholeRecord p WHERE p.streetaddr = :streetaddr"),
    @NamedQuery(name = "PotholeRecord.findByPsize", query = "SELECT p FROM PotholeRecord p WHERE p.psize = :psize"),
    @NamedQuery(name = "PotholeRecord.findByLocation", query = "SELECT p FROM PotholeRecord p WHERE p.location = :location"),
    @NamedQuery(name = "PotholeRecord.findByDistrict", query = "SELECT p FROM PotholeRecord p WHERE p.district = :district"),
    @NamedQuery(name = "PotholeRecord.findByRepri", query = "SELECT p FROM PotholeRecord p WHERE p.repri = :repri"),
    @NamedQuery(name = "PotholeRecord.findByCname", query = "SELECT p FROM PotholeRecord p WHERE p.cname = :cname"),
    @NamedQuery(name = "PotholeRecord.findByCaddr", query = "SELECT p FROM PotholeRecord p WHERE p.caddr = :caddr"),
    @NamedQuery(name = "PotholeRecord.findByCphone", query = "SELECT p FROM PotholeRecord p WHERE p.cphone = :cphone"),
    @NamedQuery(name = "PotholeRecord.findByCrewid", query = "SELECT p FROM PotholeRecord p WHERE p.crewid = :crewid"),
    @NamedQuery(name = "PotholeRecord.findByHc", query = "SELECT p FROM PotholeRecord p WHERE p.hc = :hc"),
    @NamedQuery(name = "PotholeRecord.findByEa", query = "SELECT p FROM PotholeRecord p WHERE p.ea = :ea"),
    @NamedQuery(name = "PotholeRecord.findByWorkhour", query = "SELECT p FROM PotholeRecord p WHERE p.workhour = :workhour"),
    @NamedQuery(name = "PotholeRecord.findByStatus", query = "SELECT p FROM PotholeRecord p WHERE p.status = :status"),
    @NamedQuery(name = "PotholeRecord.findByMaterial", query = "SELECT p FROM PotholeRecord p WHERE p.material = :material"),
    @NamedQuery(name = "PotholeRecord.findByCost", query = "SELECT p FROM PotholeRecord p WHERE p.cost = :cost"),
    @NamedQuery(name = "PotholeRecord.findByType", query = "SELECT p FROM PotholeRecord p WHERE p.type = :type"),
    @NamedQuery(name = "PotholeRecord.findByDc", query = "SELECT p FROM PotholeRecord p WHERE p.dc = :dc")})
public class PotholeRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "STREETADDR")
    private String streetaddr;
    @Column(name = "PSIZE")
    private Integer psize;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "LOCATION")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DISTRICT")
    private String district;
    @Column(name = "REPRI")
    private Integer repri;
    @Size(max = 20)
    @Column(name = "CNAME")
    private String cname;
    @Size(max = 50)
    @Column(name = "CADDR")
    private String caddr;
    @Size(max = 15)
    @Column(name = "CPHONE")
    private String cphone;
    @Column(name = "CREWID")
    private Integer crewid;
    @Column(name = "HC")
    private Integer hc;
    @Size(max = 50)
    @Column(name = "EA")
    private String ea;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WORKHOUR")
    private Double workhour;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "MATERIAL")
    private Double material;
    @Column(name = "COST")
    private Double cost;
    @Size(max = 20)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DC")
    private Double dc;

    public PotholeRecord() {
    }

    public PotholeRecord(String id) {
        this.id = id;
    }

    public PotholeRecord(String id, String streetaddr, String location, String district, String status) {
        this.id = id;
        this.streetaddr = streetaddr;
        this.location = location;
        this.district = district;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreetaddr() {
        return streetaddr;
    }

    public void setStreetaddr(String streetaddr) {
        this.streetaddr = streetaddr;
    }

    public Integer getPsize() {
        return psize;
    }

    public void setPsize(Integer psize) {
        this.psize = psize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getRepri() {
        return repri;
    }

    public void setRepri(Integer repri) {
        this.repri = repri;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCaddr() {
        return caddr;
    }

    public void setCaddr(String caddr) {
        this.caddr = caddr;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public Integer getCrewid() {
        return crewid;
    }

    public void setCrewid(Integer crewid) {
        this.crewid = crewid;
    }

    public Integer getHc() {
        return hc;
    }

    public void setHc(Integer hc) {
        this.hc = hc;
    }

    public String getEa() {
        return ea;
    }

    public void setEa(String ea) {
        this.ea = ea;
    }

    public Double getWorkhour() {
        return workhour;
    }

    public void setWorkhour(Double workhour) {
        this.workhour = workhour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getMaterial() {
        return material;
    }

    public void setMaterial(Double material) {
        this.material = material;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDc() {
        return dc;
    }

    public void setDc(Double dc) {
        this.dc = dc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PotholeRecord)) {
            return false;
        }
        PotholeRecord other = (PotholeRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.database.PotholeRecord[ id=" + id + " ]";
    }
    
}
