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
import java.util.Date;
import java.util.List;
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

    public void editActividad() {
        Actividades m = new Actividades();
        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        m.setId(bean.getActividadesId());
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
                .path("{id}")
                .resolveTemplate("id", bean.getActividadesId())
                .request()
                .put(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public List<Usuarios> getUsuariosRegistrados() {
        return e.getActivityUsers(bean.getActividadesId());
    }

    public void comprobarHoras(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputHoraI = (UIInput) components.findComponent("horaInicio");
        String HoraI = uiInputHoraI.getLocalValue() == null ? "" : uiInputHoraI.getLocalValue().toString();
        UIInput uiInputHoraF = (UIInput) components.findComponent("horaFinal");
        String HoraF = uiInputHoraF.getLocalValue() == null ? "" : uiInputHoraF.getLocalValue().toString();
        UIInput uiInputSala = (UIInput) components.findComponent("sala");
        String Sala = uiInputSala.getLocalValue() == null ? "" : uiInputSala.getLocalValue().toString();
        UIInput uiInputFecha = (UIInput) components.findComponent("fecha");
        Date Fecha = (Date) uiInputFecha.getLocalValue();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        if (HoraI.isEmpty() || HoraF.isEmpty()) {
            return;
        }

        if (HoraI.substring(0, 2).compareTo("24") >= 0 || HoraI.substring(3, 5).compareTo("60") >= 0) {
            FacesMessage msg = new FacesMessage("La hora introducida no es válidas");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputHoraI.getClientId(), msg);
            facesContext.renderResponse();
        }

        if (HoraF.substring(0, 2).compareTo("24") >= 0 || HoraF.substring(3, 5).compareTo("60") >= 0) {
            FacesMessage msg = new FacesMessage("Laa hora introducida no es válida");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputHoraF.getClientId(), msg);
            facesContext.renderResponse();
        }

        if (HoraI.compareTo(HoraF) >= 0) {
            FacesMessage msg = new FacesMessage("La hora inicial tiene que ser inferior a la posterior");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputHoraI.getClientId(), msg);
            facesContext.renderResponse();
        }
        System.out.println(f.format(Fecha) + HoraI + HoraF + Sala);
        if (e.haySuperposicion(f.format(Fecha), HoraI, HoraF, Sala, bean.getActividadesId())) {
            FacesMessage msg = new FacesMessage("La sala está ocupada a esas horas");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputHoraI.getClientId(), msg);
            facesContext.renderResponse();
        }
    }
    
    public void set0Id(){
        bean.setActividadesId(0);
    }
    
    public void comprobarAforo(ComponentSystemEvent event){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputSala = (UIInput) components.findComponent("sala");
        String Sala = uiInputSala.getLocalValue() == null ? "" : uiInputSala.getLocalValue().toString();
        UIInput uiInputDisponibles = (UIInput) components.findComponent("disponiboles");
        int Plazas= (Integer) uiInputDisponibles.getLocalValue() ;
        int aforo = e.getAforo(Sala);
        if(Plazas > aforo){
            FacesMessage msg = new FacesMessage("El aforo de la sala (" + aforo +") es menor que el numero de plazas disponibles");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputDisponibles.getClientId(), msg);
            facesContext.renderResponse();
        }
    }
}
