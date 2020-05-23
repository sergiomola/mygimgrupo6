/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.reserva;

import com.mygim.entities.Actividades;
import com.mygim.entities.ActividadesUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sergio 10
 */
@Stateless
public class ActividadesUsuarioEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    public void crearActividadUsuario(ActividadesUsuario a){
        em.persist(a);
        Actividades act=em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", a.getActividadId()).getSingleResult();
        act.setDisponibles(act.getDisponibles()-1);
        em.persist(act);
    }
    
    public Actividades findById(int id) {
        Actividades act = em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", id).getSingleResult();
        return act;
    }
}
