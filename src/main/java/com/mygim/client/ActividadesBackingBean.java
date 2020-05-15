/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.client;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Sergio 10
 */
@Named
@SessionScoped
public class ActividadesBackingBean implements Serializable{
    int actividadesId;

    public int getActividadesId() {
        return actividadesId;
    }

    public void setActividadesId(int actividadesId) {
        this.actividadesId = actividadesId;
    }
}
