/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.client;

import com.mygim.entities.Actividades;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@RequestScoped
public class ActividadesClientBean {

    Client client;
    WebTarget target;
    @Inject
    ActividadesBackingBean bean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target
                = client.target("http://localhost:8080/mygimgrupo6/webresources/com.mygim.entities.actividades");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Actividades[] getActividades() {
        return target
                .request()
                .get(Actividades[].class);
    }

    public Actividades getActividad() {
        Actividades m = target
                .path("{actividadesId}")
                .resolveTemplate("actividadesId", bean.getActividadesId())
                .request()
                .get(Actividades.class);
        return m;
    }

    public void deleteActividades() {
        target.path("{actividadesId}")
                .resolveTemplate("actividadesId", bean.getActividadesId())
                .request()
                .delete();
    }
}
