/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.jaas;

import com.mygim.entities.GruposUsuario;
import com.mygim.entities.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public Usuarios createEntrenador(Usuarios user) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        GruposUsuario group = new GruposUsuario();
        group.setEmail(user.getEmail());
        group.setNombregrupo("entrenadores");
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

    public void deleteUser(String email) {
        Query query = em.createQuery("DELETE FROM Usuarios u WHERE u.email=:email");
        query.setParameter("email", email);
        query.executeUpdate();
    }

    public List<Usuarios> findAllEntrenadores() {
        TypedQuery<String> query = em.createNamedQuery("GruposUsuario.emailsByNombregrupo", String.class);
        query.setParameter("nombregrupo", "entrenadores");
        List<String> emails = null;
        try {
            emails = query.getResultList();
        } catch (Exception e) {
        }

        TypedQuery<Usuarios> query2 = em.createQuery("SELECT u FROM Usuarios u WHERE u.email IN :email", Usuarios.class);
        query2.setParameter("email", emails);
        List<Usuarios> users = null;
        try {
            users = query2.getResultList();
        } catch (Exception e) {
        }
        return users;
    }
}
