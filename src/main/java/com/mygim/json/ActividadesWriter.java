/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.json;

import com.mygim.entities.Actividades;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ActividadesWriter implements MessageBodyWriter<Actividades> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Actividades.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Actividades t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Actividades t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject()
                .write("id", t.getId())
                .write("nombre", t.getNombre())
                .write("sala", t.getSala())
                .write("fecha", t.getFecha())
                .write("horaInicio", t.getHoraInicio())
                .write("horaFinal", t.getHoraFinal())
                .write("precio", t.getPrecio())
                .write("disponibles", t.getDisponibles())
                .write("descripcion", t.getDescripcion())
                .write("creadapor", t.getCreadaPor())
                .writeEnd();
        gen.flush();
    }

}
