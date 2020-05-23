/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.schedule;

import com.mygim.entities.Actividades;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sergio 10
 */
@Stateless
public class ActividadesEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Actividades> getActividadesPersona(){
         
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        List<Integer> activ = em.createQuery("SELECT a.actividadId FROM ActividadesUsuario a WHERE a.email = :email").setParameter("email",request.getUserPrincipal().getName()).getResultList();
        if(activ.isEmpty()){
            return null;
        }
        return em.createQuery("SELECT a FROM Actividades a WHERE a.id IN :list").setParameter("list",activ).getResultList();
        
    }
    
    public Actividades getActividad(int id){
        return em.createNamedQuery("Actividades.findById", Actividades.class).setParameter("id", id).getSingleResult();
    }
}
