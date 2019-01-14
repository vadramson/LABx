/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vadrama
 */
@Entity
@Table(name = "ville")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v WHERE v.deleted = :deleted")
    , @NamedQuery(name = "Ville.next_Id", query = "SELECT max(v.id) FROM Ville v ")       
    , @NamedQuery(name = "Ville.findById", query = "SELECT v FROM Ville v WHERE v.id = :id")
    , @NamedQuery(name = "Ville.findByNom", query = "SELECT v FROM Ville v WHERE v.nom = :nom")
    , @NamedQuery(name = "Ville.findByCreated", query = "SELECT v FROM Ville v WHERE v.created = :created")
    , @NamedQuery(name = "Ville.findByMidified", query = "SELECT v FROM Ville v WHERE v.midified = :midified")
    , @NamedQuery(name = "Ville.findByDeleted", query = "SELECT v FROM Ville v WHERE v.deleted = :deleted")
    , @NamedQuery(name = "Ville.findByRegion", query = "SELECT v FROM Ville v WHERE v.region = :region")})
public class Ville implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom")
    private String nom;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "midified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date midified;
    @Column(name = "deleted")
    private Integer deleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "region")
    private int region;

    public Ville() {
    }

    public Ville(Integer id) {
        this.id = id;
    }

    public Ville(Integer id, String nom, int region) {
        this.id = id;
        this.nom = nom;
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getMidified() {
        return midified;
    }

    public void setMidified(Date midified) {
        this.midified = midified;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
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
        if (!(object instanceof Ville)) {
            return false;
        }
        Ville other = (Ville) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ville[ nom=" + nom + " ]";
    }
    
}
