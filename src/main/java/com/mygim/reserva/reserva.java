/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.reserva;

import com.mygim.entities.Actividades;
import java.io.Serializable;
import java.util.List;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sergio 10
 */
@Named
@FlowScoped("reserva")
public class reserva implements Serializable {

    private int actividadId;
    private String tarjeta;
    private String caducidad;
    
    @PersistenceContext
    EntityManager em;


    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public int getDisponibles() {
        try {
            List<Actividades> list = em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", actividadId).getResultList();
            return list.get(0).getDisponibles();
        } catch (NoResultException e) {
            return 0;
        }
    }
    
    public String getNombre() {
        try {
            List<Actividades> list = em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", actividadId).getResultList();
            return list.get(0).getNombre();
        } catch (NoResultException e) {
            return "none";
        }
    }
    
    public String getFecha() {
        try {
            List<Actividades> list = em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", actividadId).getResultList();
            return list.get(0).getFecha();
        } catch (NoResultException e) {
            return "none";
        }
    }
    
    public String getHora() {
        try {
            List<Actividades> list = em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", actividadId).getResultList();
            return list.get(0).getHora();
        } catch (NoResultException e) {
            return "none";
        }
    }
    
    public int getPrecio() {
        try {
            List<Actividades> list = em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", actividadId).getResultList();
            return list.get(0).getPrecio();
        } catch (NoResultException e) {
            return 0;
        }
    }
}
