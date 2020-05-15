/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.client;

import com.mygim.entities.Actividades;
import com.mygim.json.ActividadesReader;
import com.mygim.json.ActividadesWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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
        return target
                .register(ActividadesReader.class)
                .path("{ActividadesId}")
                .resolveTemplate("ActividadesId", bean.getActividadesId())
                .request(MediaType.APPLICATION_JSON)
                .get(Actividades.class);
    }

    public void deleteActividades() {
        target.path("{actividadesId}")
                .resolveTemplate("actividadesId", bean.getActividadesId())
                .request()
                .delete();
    }

    public void addActividad() {
        Actividades m = new Actividades();
        DateFormat d=new SimpleDateFormat("dd/mm/yyyy");
        m.setId(1);
        m.setNombre(bean.getActividadesNombre());
        m.setSala(bean.getActividadesSala());
        m.setFecha(d.format(bean.getActividadesFecha()));
        m.setHora(bean.getActividadesHora());
        m.setPrecio(bean.getActividadesPrecio());
        m.setDisponibles(bean.getActividadesDisponibles());
        m.setDescripcion(bean.getActividadesDescripcion());
        m.setCreadaPor("No implementado");
        target.register(ActividadesWriter.class)
                .request()
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }
}
