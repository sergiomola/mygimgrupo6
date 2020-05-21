/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.jaas;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("DNIValidador")
public class DNIValidador implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       char[] abcdario = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
       String dniString = value.toString();
        if (dniString.length() == 9) {
            char letra = Character.toUpperCase(dniString.substring(8, 9).charAt(0));
            dniString = dniString.substring(0, 8);
            try {
                int dniInt = Integer.parseInt(dniString);
                int resto = dniInt % 23;
                if (letra != abcdario[resto]) {
                    throw new ValidatorException(new FacesMessage("Formato de DNI incorrecto"));
                }
            } catch (NumberFormatException e) {
                throw new ValidatorException(new FacesMessage("Formato de DNI incorrecto"));
            }
        }else{
            throw new ValidatorException(new FacesMessage("Formato de DNI incorrecto"));
        }
    }
    
}
