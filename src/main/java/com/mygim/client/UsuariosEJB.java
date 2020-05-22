/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.client;

import com.mygim.entities.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sergio 10
 */
@Stateless
public class UsuariosEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Usuarios> getActivityUsers(int actividadId){
        List<String> a = em.createQuery("SELECT a.email FROM ActividadesUsuario a WHERE a.actividadId = :actividadId").setParameter("actividadId", actividadId).getResultList();
        if(a==null || a.isEmpty()){
            return null;
        }
        List<Usuarios> u = em.createQuery("SELECT u FROM Usuarios u WHERE u.email IN :list").setParameter("list", a).getResultList();
        return u;
    }
    
    public void deleteActividadesUsuario(int actividadId){
        em.createQuery("DELETE FROM ActividadesUsuario WHERE actividadId = :actividadId").setParameter("actividadId", actividadId).executeUpdate();
    }
}
