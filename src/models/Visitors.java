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
@Table(name = "visitors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visitors.findAll", query = "SELECT v FROM Visitors v")
    , @NamedQuery(name = "Visitors.findById", query = "SELECT v FROM Visitors v WHERE v.id = :id")
    , @NamedQuery(name = "Visitors.findByDate", query = "SELECT v FROM Visitors v WHERE v.date = :date")
    , @NamedQuery(name = "Visitors.findByName", query = "SELECT v FROM Visitors v WHERE v.name = :name")})
public class Visitors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "appartmentsId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appartments appartmentsId;
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users ownerId;

    public Visitors() {
    }

    public Visitors(Integer id) {
        this.id = id;
    }

    public Visitors(Integer id, Date date, String name) {
        this.id = id;
        this.date = date;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Appartments getAppartmentsId() {
        return appartmentsId;
    }

    public void setAppartmentsId(Appartments appartmentsId) {
        this.appartmentsId = appartmentsId;
    }

    public Users getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Users ownerId) {
        this.ownerId = ownerId;
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
        if (!(object instanceof Visitors)) {
            return false;
        }
        Visitors other = (Visitors) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Visitors[ id=" + id + " ]";
    }
    
}
