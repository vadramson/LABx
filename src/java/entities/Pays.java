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
@Table(name = "pays")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p WHERE p.deleted = :deleted")
//     @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")
    , @NamedQuery(name = "Pays.findById", query = "SELECT p FROM Pays p WHERE p.id = :id")
    , @NamedQuery(name = "Pays.nextId", query = "SELECT max(p.id) FROM Pays p ")   
    , @NamedQuery(name = "Pays.findByNom", query = "SELECT p FROM Pays p WHERE p.nom = :nom")
    , @NamedQuery(name = "Pays.findByIso2", query = "SELECT p FROM Pays p WHERE p.iso2 = :iso2")
    , @NamedQuery(name = "Pays.findByIso3", query = "SELECT p FROM Pays p WHERE p.iso3 = :iso3")
    , @NamedQuery(name = "Pays.findByDevise", query = "SELECT p FROM Pays p WHERE p.devise = :devise")
    , @NamedQuery(name = "Pays.findByCreated", query = "SELECT p FROM Pays p WHERE p.created = :created")
    , @NamedQuery(name = "Pays.findByModified", query = "SELECT p FROM Pays p WHERE p.modified = :modified")
    , @NamedQuery(name = "Pays.findByDeleted", query = "SELECT p FROM Pays p WHERE p.deleted = :deleted")})
public class Pays implements Serializable {

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
    @Size(min = 1, max = 2)
    @Column(name = "iso2")
    private String iso2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "iso3")
    private String iso3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "devise")
    private int devise;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Column(name = "deleted")
    private Integer deleted;

    public Pays() {
    }

    public Pays(Integer id) {
        this.id = id;
    }

    public Pays(Integer id, String nom, String iso2, String iso3, int devise) {
        this.id = id;
        this.nom = nom;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.devise = devise;
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

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public int getDevise() {
        return devise;
    }

    public void setDevise(int devise) {
        this.devise = devise;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "entities.Pays[ id=" + id + " ]";
        return "entities.Pays[ nom=" + nom + " ]";
    }
    
}
