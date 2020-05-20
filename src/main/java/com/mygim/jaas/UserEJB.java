/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.jaas;

import com.mygim.entities.GruposUsuario;
import com.mygim.entities.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sergio 10
 */
@Stateless
public class UserEJB {

    @PersistenceContext
    private EntityManager em;

    public Usuarios createUser(Usuarios user) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        GruposUsuario group = new GruposUsuario();
        group.setEmail(user.getEmail());
        group.setNombregrupo("clientes");
        em.persist(user);
        em.persist(group);
        return user;
    }
    

    public Usuarios findByEmail(String email) {
        TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByEmail", Usuarios.class);
        query.setParameter("email", email);
        Usuarios user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }
}
