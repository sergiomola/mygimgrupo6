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
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")
    , @NamedQuery(name = "Actividades.findById", query = "SELECT a FROM Actividades a WHERE a.id = :id")
    , @NamedQuery(name = "Actividades.findByNombre", query = "SELECT a FROM Actividades a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Actividades.findBySala", query = "SELECT a FROM Actividades a WHERE a.sala = :sala")
    , @NamedQuery(name = "Actividades.findByFecha", query = "SELECT a FROM Actividades a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Actividades.findByHoraInicio", query = "SELECT a FROM Actividades a WHERE a.horaInicio = :horaInicio")
    , @NamedQuery(name = "Actividades.findByHoraFinal", query = "SELECT a FROM Actividades a WHERE a.horaFinal = :horaFinal")
    , @NamedQuery(name = "Actividades.findByPrecio", query = "SELECT a FROM Actividades a WHERE a.precio = :precio")
    , @NamedQuery(name = "Actividades.findByDisponibles", query = "SELECT a FROM Actividades a WHERE a.disponibles = :disponibles")
    , @NamedQuery(name = "Actividades.findByDescripcion", query = "SELECT a FROM Actividades a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Actividades.findByCreadaPor", query = "SELECT a FROM Actividades a WHERE a.creadaPor = :creadaPor")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "SALA")
    private String sala;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "FECHA")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "HORA_INICIO")
    private String horaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "HORA_FINAL")
    private String horaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private int precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISPONIBLES")
    private int disponibles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "CREADA_POR")
    private String creadaPor;

    public Actividades() {
    }

    public Actividades(Integer id) {
        this.id = id;
    }

    public Actividades(Integer id, String nombre, String sala, String fecha, String horaInicio, String horaFinal, int precio, int disponibles, String descripcion, String creadaPor) {
        this.id = id;
        this.nombre = nombre;
        this.sala = sala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.precio = precio;
        this.disponibles = disponibles;
        this.descripcion = descripcion;
        this.creadaPor = creadaPor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreadaPor() {
        return creadaPor;
    }

    public void setCreadaPor(String creadaPor) {
        this.creadaPor = creadaPor;
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
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mygim.entities.Actividades[ id=" + id + " ]";
    }
    
}
