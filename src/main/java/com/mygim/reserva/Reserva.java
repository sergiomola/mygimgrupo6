/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.reserva;

import com.mygim.entities.Actividades;
import com.mygim.entities.ActividadesUsuario;
import com.mygim.jaas.LoginView;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sergio 10
 */
@Named
@FlowScoped("reserva")
public class Reserva implements Serializable {

    int actividadId;
    String tarjeta="";
    Date caducidad= new Date();
    @Inject
    ActividadesUsuarioEJB act;
    
    @PersistenceContext
    EntityManager em;


    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
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
    
    public String getSala() {
        try {
            List<Actividades> list = em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", actividadId).getResultList();
            return list.get(0).getSala();
        } catch (NoResultException e) {
            return "none";
        }
    }
    
    public String apuntarCliente(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        ActividadesUsuario a = new ActividadesUsuario(1,request.getUserPrincipal().getName(),actividadId);
        act.crearActividadUsuario(a);
        return "print";
    }
    
    public boolean estaApuntado(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        List<ActividadesUsuario> a = em.createNamedQuery("ActividadesUsuario.findByEmailActividadId", ActividadesUsuario.class).setParameter("email",request.getUserPrincipal().getName()).setParameter("actividadId", actividadId).getResultList();
        return a.size()>0;
    }
}
