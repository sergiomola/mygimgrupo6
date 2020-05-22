/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.client;

import com.mygim.entities.Actividades;
import com.mygim.entities.Usuarios;
import com.mygim.json.ActividadesReader;
import com.mygim.json.ActividadesWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
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

    @Inject
    UsuariosEJB e;

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

        e.deleteActividadesUsuario(bean.getActividadesId());
    }

    public Actividades[] getActividadesPersonalizadas() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.isUserInRole("admin")) {
            return getActividades();
        } else {
            return target.path("entrenador/{email}")
                    .resolveTemplate("email", request.getUserPrincipal().getName())
                    .request()
                    .get(Actividades[].class);
        }
    }

    public void addActividad() {
        Actividades m = new Actividades();
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        m.setId(1);
        m.setNombre(bean.getActividadesNombre());
        m.setSala(bean.getActividadesSala());
        m.setFecha(d.format(bean.getActividadesFecha()));
        m.setHoraInicio(bean.getActividadesHoraInicio());
        m.setHoraFinal(bean.getActividadesHoraFinal());
        m.setPrecio(bean.getActividadesPrecio());
        m.setDisponibles(bean.getActividadesDisponibles());
        m.setDescripcion(bean.getActividadesDescripcion());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        m.setCreadaPor(request.getUserPrincipal().getName());
        target.register(ActividadesWriter.class)
                .request()
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public List<Usuarios> getUsuariosRegistrados() {
        return e.getActivityUsers(bean.getActividadesId());
    }

}
