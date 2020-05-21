/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergio 10
 */
@Entity
@Table(name = "actividades_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadesUsuario.findAll", query = "SELECT a FROM ActividadesUsuario a")
    , @NamedQuery(name = "ActividadesUsuario.findById", query = "SELECT a FROM ActividadesUsuario a WHERE a.id = :id")
    , @NamedQuery(name = "ActividadesUsuario.findByEmail", query = "SELECT a FROM ActividadesUsuario a WHERE a.email = :email")
    , @NamedQuery(name = "ActividadesUsuario.findByEmailActividadId", query = "SELECT a FROM ActividadesUsuario a WHERE a.email = :email AND a.actividadId = :actividadId")
    , @NamedQuery(name = "ActividadesUsuario.findByActividadId", query = "SELECT a FROM ActividadesUsuario a WHERE a.actividadId = :actividadId")})
public class ActividadesUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVIDAD_ID")
    private int actividadId;

    public ActividadesUsuario() {
    }

    public ActividadesUsuario(Integer id) {
        this.id = id;
    }

    public ActividadesUsuario(Integer id, String email, int actividadId) {
        this.id = id;
        this.email = email;
        this.actividadId = actividadId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
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
        if (!(object instanceof ActividadesUsuario)) {
            return false;
        }
        ActividadesUsuario other = (ActividadesUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mygim.entities.ActividadesUsuario[ id=" + id + " ]";
    }
    
}
