/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.client;

import com.mygim.entities.Actividades;
import static com.mygim.entities.Actividades_.id;
import com.mygim.reserva.ActividadesUsuarioEJB;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sergio 10
 */
@Named
@SessionScoped
public class ActividadesBackingBean implements Serializable{
    int actividadesId;
    String actividadesNombre;
    @Inject
    private ActividadesUsuarioEJB actividadesEJB;
    public String getActividadesNombre() {
        return actividadesNombre;
    }

    public void setActividadesNombre(String actividadesNombre) {
        this.actividadesNombre = actividadesNombre;
    }
    Date actividadesFecha;
    String actividadesHoraFinal;
    String actividadesHoraInicio;
    int actividadesPrecio;
    String actividadesDescripcion;
    int actividadesDisponibles;
    String actividadesSala;
    private Date hoy=new Date();

    public Date getHoy() {
        return hoy;
    }

    public Date getActividadesFecha() {
        return actividadesFecha;
    }

    public void setActividadesFecha(Date actividadesFecha) {
        this.actividadesFecha = actividadesFecha;
    }

    public String getActividadesHoraFinal() {
        return actividadesHoraFinal;
    }

    public void setActividadesHoraFinal(String actividadesHoraFinal) {
        this.actividadesHoraFinal = actividadesHoraFinal;
    }

    public String getActividadesHoraInicio() {
        return actividadesHoraInicio;
    }

    public void setActividadesHoraInicio(String actividadesHoraInicio) {
        this.actividadesHoraInicio = actividadesHoraInicio;
    }

    public int getActividadesPrecio() {
        return actividadesPrecio;
    }

    public void setActividadesPrecio(int actividadesPrecio) {
        this.actividadesPrecio = actividadesPrecio;
    }

    public String getActividadesDescripcion() {
        return actividadesDescripcion;
    }

    public void setActividadesDescripcion(String actividadesDescripcion) {
        this.actividadesDescripcion = actividadesDescripcion;
    }

    public int getActividadesDisponibles() {
        return actividadesDisponibles;
    }

    public void setActividadesDisponibles(int actividadesDisponibles) {
        this.actividadesDisponibles = actividadesDisponibles;
    }

    public String getActividadesSala() {
        return actividadesSala;
    }

    public void setActividadesSala(String actividadesSala) {
        this.actividadesSala = actividadesSala;
    }

    public int getActividadesId() {
        return actividadesId;
    }

    public void setActividadesId(int actividadesId) {
        this.actividadesId = actividadesId;
    }
    
    public void ponerInfo(){
        Actividades act= actividadesEJB.findById(actividadesId);
        actividadesNombre = act.getNombre();
        try {
            actividadesFecha = new SimpleDateFormat("yyyy-MM-dd").parse(act.getFecha());
        } catch (Exception e) {
        }
        actividadesHoraFinal = act.getHoraFinal();
        actividadesHoraInicio = act.getHoraInicio();
        actividadesPrecio = act.getPrecio();
        actividadesDescripcion = act.getDescripcion();
        actividadesDisponibles = act.getDisponibles();
        actividadesSala = act.getSala();
    }
}
