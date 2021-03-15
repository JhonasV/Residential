/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonas
 */
@Entity
@Table(name = "maintenanceLines")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaintenanceLines.findAll", query = "SELECT m FROM MaintenanceLines m")
    , @NamedQuery(name = "MaintenanceLines.findById", query = "SELECT m FROM MaintenanceLines m WHERE m.id = :id")
    , @NamedQuery(name = "MaintenanceLines.findByDescription", query = "SELECT m FROM MaintenanceLines m WHERE m.description = :description")
    , @NamedQuery(name = "MaintenanceLines.findByCost", query = "SELECT m FROM MaintenanceLines m WHERE m.cost = :cost")
    , @NamedQuery(name = "MaintenanceLines.findByCreatedAt", query = "SELECT m FROM MaintenanceLines m WHERE m.createdAt = :createdAt")
    , @NamedQuery(name = "MaintenanceLines.findByUpdatedAt", query = "SELECT m FROM MaintenanceLines m WHERE m.updatedAt = :updatedAt")})
public class MaintenanceLines implements Serializable {

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private Double cost;
    @Basic(optional = false)
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "maintenanceHeadersId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MaintenanceHeaders maintenanceHeadersId;
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @JoinColumn(name = "updatedBy", referencedColumnName = "id")
    @ManyToOne
    private Users updatedBy;

    public MaintenanceLines() {
    }

    public MaintenanceLines(Integer id) {
        this.id = id;
    }

    public MaintenanceLines(Integer id, String description, Date createdAt) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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

    public MaintenanceHeaders getMaintenanceHeadersId() {
        return maintenanceHeadersId;
    }

    public void setMaintenanceHeadersId(MaintenanceHeaders maintenanceHeadersId) {
        this.maintenanceHeadersId = maintenanceHeadersId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaintenanceLines)) {
            return false;
        }
        MaintenanceLines other = (MaintenanceLines) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.MaintenanceLines[ id=" + id + " ]";
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}
