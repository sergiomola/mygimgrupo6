/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.salas;

import com.mygim.entities.Salas;
import com.mygim.json.SalasReader;
import com.mygim.json.SalasWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sergio 10
 */
@Named
@RequestScoped
public class SalasClientBean {

    Client client;
    WebTarget target;
    @Inject
    SalasBackingBean bean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/mygimgrupo6/webresources/com.mygim.entities.salas");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Salas[] getSalas() {
        return target.request().get(Salas[].class);
    }

    public Salas getSala() {
        return target
                .register(SalasReader.class)
                .path("{nombre}")
                .resolveTemplate("nombre", bean.getNombre())
                .request(MediaType.APPLICATION_JSON)
                .get(Salas.class);
    }

    public void deleteSalas() {
        target.path("{nombre}")
                .resolveTemplate("nombre", bean.getNombre())
                .request()
                .delete();
    }

    public void addSalas() {
        Salas m = new Salas();
        m.setNombre(bean.getNombre());
        m.setAforo(bean.getAforo());
        target.register(SalasWriter.class)
                .request()
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public void editSalas() {
        Salas m = new Salas();
        m.setNombre(bean.getNombre());
        m.setAforo(bean.getAforo());
        target.register(SalasWriter.class)
                .path("{nombre}")
                .resolveTemplate("nombre", bean.getNombre())
                .request()
                .put(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public void validateNombre(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        
        UIInput uiInputNombre = (UIInput) components.findComponent("nombre");
        String nombre = uiInputNombre.getLocalValue().toString();
        
        Salas a=target
                .register(SalasReader.class)
                .path("{nombre}")
                .resolveTemplate("nombre", nombre)
                .request(MediaType.APPLICATION_JSON)
                .get(Salas.class);
        
        if (a != null) {
            FacesMessage msg = new FacesMessage("Ya existe una sala con ese nombre");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputNombre.getClientId(), msg);
            facesContext.renderResponse();
        }
    }

}
