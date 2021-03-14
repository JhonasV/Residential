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
@Table(name = "maintenancePays")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaintenancePays.findAll", query = "SELECT m FROM MaintenancePays m")
    , @NamedQuery(name = "MaintenancePays.findById", query = "SELECT m FROM MaintenancePays m WHERE m.id = :id")
    , @NamedQuery(name = "MaintenancePays.findByCreatedAt", query = "SELECT m FROM MaintenancePays m WHERE m.createdAt = :createdAt")
    , @NamedQuery(name = "MaintenancePays.findByUpdatedAt", query = "SELECT m FROM MaintenancePays m WHERE m.updatedAt = :updatedAt")
    , @NamedQuery(name = "MaintenancePays.findByCreatedBy", query = "SELECT m FROM MaintenancePays m WHERE m.createdBy = :createdBy")
    , @NamedQuery(name = "MaintenancePays.findByUpdatedBy", query = "SELECT m FROM MaintenancePays m WHERE m.updatedBy = :updatedBy")})
public class MaintenancePays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "createdBy")
    private int createdBy;
    @Column(name = "updatedBy")
    private Integer updatedBy;
    @JoinColumn(name = "maintenanceHeadersId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MaintenanceHeaders maintenanceHeadersId;
    @JoinColumn(name = "usersId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users usersId;

    public MaintenancePays() {
    }

    public MaintenancePays(Integer id) {
        this.id = id;
    }

    public MaintenancePays(Integer id, Date createdAt, int createdBy) {
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

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public MaintenanceHeaders getMaintenanceHeadersId() {
        return maintenanceHeadersId;
    }

    public void setMaintenanceHeadersId(MaintenanceHeaders maintenanceHeadersId) {
        this.maintenanceHeadersId = maintenanceHeadersId;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
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
        if (!(object instanceof MaintenancePays)) {
            return false;
        }
        MaintenancePays other = (MaintenancePays) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.MaintenancePays[ id=" + id + " ]";
    }
    
}
