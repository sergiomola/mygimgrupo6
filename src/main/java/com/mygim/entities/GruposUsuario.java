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
@Table(name = "grupos_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruposUsuario.findAll", query = "SELECT g FROM GruposUsuario g")
    , @NamedQuery(name = "GruposUsuario.findByEmail", query = "SELECT g FROM GruposUsuario g WHERE g.email = :email")
    , @NamedQuery(name = "GruposUsuario.findByNombregrupo", query = "SELECT g FROM GruposUsuario g WHERE g.nombregrupo = :nombregrupo")
    , @NamedQuery(name = "GruposUsuario.emailsByNombregrupo", query = "SELECT g.email FROM GruposUsuario g WHERE g.nombregrupo = :nombregrupo")})
public class GruposUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NOMBREGRUPO")
    private String nombregrupo;

    public GruposUsuario() {
    }

    public GruposUsuario(String email) {
        this.email = email;
    }

    public GruposUsuario(String email, String nombregrupo) {
        this.email = email;
        this.nombregrupo = nombregrupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombregrupo() {
        return nombregrupo;
    }

    public void setNombregrupo(String nombregrupo) {
        this.nombregrupo = nombregrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruposUsuario)) {
            return false;
        }
        GruposUsuario other = (GruposUsuario) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mygim.entities.GruposUsuario[ email=" + email + " ]";
    }

}
