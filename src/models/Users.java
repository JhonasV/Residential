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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
    , @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name")
    , @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt")
    , @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt")
    , @NamedQuery(name = "Users.findByCreatedBy", query = "SELECT u FROM Users u WHERE u.createdBy = :createdBy")
    , @NamedQuery(name = "Users.findByUpdatedBy", query = "SELECT u FROM Users u WHERE u.updatedBy = :updatedBy")})
public class Users implements Serializable {

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "createdBy")
    private Integer createdBy;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "updatedBy")
    private Integer updatedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private Collection<Appartments> appartmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Appartments> appartmentsCollection1;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<Appartments> appartmentsCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<MaintenanceLines> maintenanceLinesCollection;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<MaintenanceLines> maintenanceLinesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
    private Collection<MaintenancePays> maintenancePaysCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private Collection<Visitors> visitorsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
    private Collection<UsersRoles> usersRolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<UsersRoles> usersRolesCollection1;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<UsersRoles> usersRolesCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Buildings> buildingsCollection;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<Buildings> buildingsCollection1;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String userName, String email, String name, String lastName, Date createdAt, int createdBy) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public Collection<Appartments> getAppartmentsCollection() {
        return appartmentsCollection;
    }

    public void setAppartmentsCollection(Collection<Appartments> appartmentsCollection) {
        this.appartmentsCollection = appartmentsCollection;
    }

    @XmlTransient
    public Collection<Appartments> getAppartmentsCollection1() {
        return appartmentsCollection1;
    }

    public void setAppartmentsCollection1(Collection<Appartments> appartmentsCollection1) {
        this.appartmentsCollection1 = appartmentsCollection1;
    }

    @XmlTransient
    public Collection<Appartments> getAppartmentsCollection2() {
        return appartmentsCollection2;
    }

    public void setAppartmentsCollection2(Collection<Appartments> appartmentsCollection2) {
        this.appartmentsCollection2 = appartmentsCollection2;
    }

    @XmlTransient
    public Collection<MaintenanceLines> getMaintenanceLinesCollection() {
        return maintenanceLinesCollection;
    }

    public void setMaintenanceLinesCollection(Collection<MaintenanceLines> maintenanceLinesCollection) {
        this.maintenanceLinesCollection = maintenanceLinesCollection;
    }

    @XmlTransient
    public Collection<MaintenanceLines> getMaintenanceLinesCollection1() {
        return maintenanceLinesCollection1;
    }

    public void setMaintenanceLinesCollection1(Collection<MaintenanceLines> maintenanceLinesCollection1) {
        this.maintenanceLinesCollection1 = maintenanceLinesCollection1;
    }

    @XmlTransient
    public Collection<MaintenancePays> getMaintenancePaysCollection() {
        return maintenancePaysCollection;
    }

    public void setMaintenancePaysCollection(Collection<MaintenancePays> maintenancePaysCollection) {
        this.maintenancePaysCollection = maintenancePaysCollection;
    }

    @XmlTransient
    public Collection<Visitors> getVisitorsCollection() {
        return visitorsCollection;
    }

    public void setVisitorsCollection(Collection<Visitors> visitorsCollection) {
        this.visitorsCollection = visitorsCollection;
    }

    @XmlTransient
    public Collection<UsersRoles> getUsersRolesCollection() {
        return usersRolesCollection;
    }

    public void setUsersRolesCollection(Collection<UsersRoles> usersRolesCollection) {
        this.usersRolesCollection = usersRolesCollection;
    }

    @XmlTransient
    public Collection<UsersRoles> getUsersRolesCollection1() {
        return usersRolesCollection1;
    }

    public void setUsersRolesCollection1(Collection<UsersRoles> usersRolesCollection1) {
        this.usersRolesCollection1 = usersRolesCollection1;
    }

    @XmlTransient
    public Collection<UsersRoles> getUsersRolesCollection2() {
        return usersRolesCollection2;
    }

    public void setUsersRolesCollection2(Collection<UsersRoles> usersRolesCollection2) {
        this.usersRolesCollection2 = usersRolesCollection2;
    }

    @XmlTransient
    public Collection<Buildings> getBuildingsCollection() {
        return buildingsCollection;
    }

    public void setBuildingsCollection(Collection<Buildings> buildingsCollection) {
        this.buildingsCollection = buildingsCollection;
    }

    @XmlTransient
    public Collection<Buildings> getBuildingsCollection1() {
        return buildingsCollection1;
    }

    public void setBuildingsCollection1(Collection<Buildings> buildingsCollection1) {
        this.buildingsCollection1 = buildingsCollection1;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Users[ id=" + id + " ]";
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}
