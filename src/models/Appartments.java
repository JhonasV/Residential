/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonas
 */
@Entity
@Table(name = "appartments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appartments.findAll", query = "SELECT a FROM Appartments a")
    , @NamedQuery(name = "Appartments.findById", query = "SELECT a FROM Appartments a WHERE a.id = :id")
    , @NamedQuery(name = "Appartments.findByName", query = "SELECT a FROM Appartments a WHERE a.name = :name")
    , @NamedQuery(name = "Appartments.findByNumber", query = "SELECT a FROM Appartments a WHERE a.number = :number")
    , @NamedQuery(name = "Appartments.findByCreatedAt", query = "SELECT a FROM Appartments a WHERE a.createdAt = :createdAt")
    , @NamedQuery(name = "Appartments.findByUpdatedAt", query = "SELECT a FROM Appartments a WHERE a.updatedAt = :updatedAt")})
public class Appartments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "number")
    private int number;
    @Basic(optional = false)
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "buildingsId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Buildings buildingsId;
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users ownerId;
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @JoinColumn(name = "updatedBy", referencedColumnName = "id")
    @ManyToOne
    private Users updatedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appartmentsId")
    private Collection<Visitors> visitorsCollection;

    public Appartments() {
    }

    public Appartments(Integer id) {
        this.id = id;
    }

    public Appartments(Integer id, String name, int number, Date createdAt) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Buildings getBuildingsId() {
        return buildingsId;
    }

    public void setBuildingsId(Buildings buildingsId) {
        this.buildingsId = buildingsId;
    }

    public Users getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Users ownerId) {
        this.ownerId = ownerId;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    @XmlTransient
    public Collection<Visitors> getVisitorsCollection() {
        return visitorsCollection;
    }

    public void setVisitorsCollection(Collection<Visitors> visitorsCollection) {
        this.visitorsCollection = visitorsCollection;
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
        if (!(object instanceof Appartments)) {
            return false;
        }
        Appartments other = (Appartments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Appartments[ id=" + id + " ]";
    }
    
}
