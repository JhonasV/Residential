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
@Table(name = "maintenanceHeaders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaintenanceHeaders.findAll", query = "SELECT m FROM MaintenanceHeaders m")
    , @NamedQuery(name = "MaintenanceHeaders.findById", query = "SELECT m FROM MaintenanceHeaders m WHERE m.id = :id")
    , @NamedQuery(name = "MaintenanceHeaders.findByTotalCost", query = "SELECT m FROM MaintenanceHeaders m WHERE m.totalCost = :totalCost")
    , @NamedQuery(name = "MaintenanceHeaders.findByCreatedAt", query = "SELECT m FROM MaintenanceHeaders m WHERE m.createdAt = :createdAt")
    , @NamedQuery(name = "MaintenanceHeaders.findByUpdatedAt", query = "SELECT m FROM MaintenanceHeaders m WHERE m.updatedAt = :updatedAt")
    , @NamedQuery(name = "MaintenanceHeaders.findByCreatedBy", query = "SELECT m FROM MaintenanceHeaders m WHERE m.createdBy = :createdBy")
    , @NamedQuery(name = "MaintenanceHeaders.findByUpdatedBy", query = "SELECT m FROM MaintenanceHeaders m WHERE m.updatedBy = :updatedBy")})
public class MaintenanceHeaders implements Serializable {

    @Column(name = "createdBy")
    private Integer createdBy;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalCost")
    private Double totalCost;
    @Basic(optional = false)
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "updatedBy")
    private Integer updatedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maintenanceHeadersId")
    private Collection<MaintenanceLines> maintenanceLinesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maintenanceHeadersId")
    private Collection<MaintenancePays> maintenancePaysCollection;

    public MaintenanceHeaders() {
    }

    public MaintenanceHeaders(Integer id) {
        this.id = id;
    }

    public MaintenanceHeaders(Integer id, Date createdAt, int createdBy) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
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


    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @XmlTransient
    public Collection<MaintenanceLines> getMaintenanceLinesCollection() {
        return maintenanceLinesCollection;
    }

    public void setMaintenanceLinesCollection(Collection<MaintenanceLines> maintenanceLinesCollection) {
        this.maintenanceLinesCollection = maintenanceLinesCollection;
    }

    @XmlTransient
    public Collection<MaintenancePays> getMaintenancePaysCollection() {
        return maintenancePaysCollection;
    }

    public void setMaintenancePaysCollection(Collection<MaintenancePays> maintenancePaysCollection) {
        this.maintenancePaysCollection = maintenancePaysCollection;
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
        if (!(object instanceof MaintenanceHeaders)) {
            return false;
        }
        MaintenanceHeaders other = (MaintenanceHeaders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.MaintenanceHeaders[ id=" + id + " ]";
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    
}
