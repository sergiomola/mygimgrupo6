/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.schedule;

import com.mygim.entities.Actividades;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sergio 10
 */
@Named
@SessionScoped
public class MisActividadesView implements Serializable {

    private Integer id=null;
    @Inject
    private ActividadesEJB e;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Actividades> getActividadesPersona() {
        return e.getActividadesPersona();
    }
    
    public Actividades getActividad(){
        return e.getActividad(id);
    }

}
