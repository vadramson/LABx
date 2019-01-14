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
@Table(name = "region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r WHERE r.deleted = :deleted")
    , @NamedQuery(name = "Region.findById", query = "SELECT r FROM Region r WHERE r.id = :id")
    , @NamedQuery(name = "Region.nextId", query = "SELECT max(r.id) FROM Region r ")   
    , @NamedQuery(name = "Region.findByNom", query = "SELECT r FROM Region r WHERE r.nom = :nom")
    , @NamedQuery(name = "Region.findByPays", query = "SELECT r FROM Region r WHERE r.pays = :pays")
    , @NamedQuery(name = "Region.findByDeleted", query = "SELECT r FROM Region r WHERE r.deleted = :deleted")
    , @NamedQuery(name = "Region.findByCreated", query = "SELECT r FROM Region r WHERE r.created = :created")
    , @NamedQuery(name = "Region.findByModified", query = "SELECT r FROM Region r WHERE r.modified = :modified")})
public class Region implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "pays")
    private int pays;
    @Column(name = "deleted")
    private Integer deleted;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public Region() {
    }

    public Region(Integer id) {
        this.id = id;
    }

    public Region(Integer id, String nom, int pays) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
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

    public int getPays() {
        return pays;
    }

    public void setPays(int pays) {
        this.pays = pays;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
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
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Region[ nom=" + nom + " ]";
    }
    
}
