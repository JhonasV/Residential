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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "buildings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buildings.findAll", query = "SELECT b FROM Buildings b")
    , @NamedQuery(name = "Buildings.findById", query = "SELECT b FROM Buildings b WHERE b.id = :id")
    , @NamedQuery(name = "Buildings.findByName", query = "SELECT b FROM Buildings b WHERE b.name = :name")
    , @NamedQuery(name = "Buildings.findByIsDeleted", query = "SELECT b FROM Buildings b WHERE b.isDeleted = :isDeleted")
    , @NamedQuery(name = "Buildings.findByCreatedAt", query = "SELECT b FROM Buildings b WHERE b.createdAt = :createdAt")
    , @NamedQuery(name = "Buildings.findByUpdatedAt", query = "SELECT b FROM Buildings b WHERE b.updatedAt = :updatedAt")})
public class Buildings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "isDeleted")
    private Boolean isDeleted;
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingsId")
    private Collection<Appartments> appartmentsCollection;
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    @ManyToOne
    private Users createdBy;
    @JoinColumn(name = "updatedBy", referencedColumnName = "id")
    @ManyToOne
    private Users updatedBy;

    public Buildings() {
    }

    public Buildings(Integer id) {
        this.id = id;
    }

    public Buildings(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    @XmlTransient
    public Collection<Appartments> getAppartmentsCollection() {
        return appartmentsCollection;
    }

    public void setAppartmentsCollection(Collection<Appartments> appartmentsCollection) {
        this.appartmentsCollection = appartmentsCollection;
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
        if (!(object instanceof Buildings)) {
            return false;
        }
        Buildings other = (Buildings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Buildings[ id=" + id + " ]";
    }
    
}
