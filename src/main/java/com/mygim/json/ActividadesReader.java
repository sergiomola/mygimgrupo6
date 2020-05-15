/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.json;

import com.mygim.entities.Actividades;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ActividadesReader implements MessageBodyReader<Actividades> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Actividades.class.isAssignableFrom(type);
    }

    @Override
    public Actividades readFrom(Class<Actividades> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Actividades actividad = new Actividades();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            actividad.setId(parser.getInt());
                            break;
                        case "nombre":
                            actividad.setNombre(parser.getString());
                            break;
                        case "sala":
                            actividad.setSala(parser.getString());
                            break;
                        case "fecha":
                            actividad.setFecha(parser.getString());
                            break;
                        case "hora":
                            actividad.setHora(parser.getString());
                            break;
                        case "precio":
                            actividad.setPrecio(parser.getInt());
                            break;
                        case "disponibles":
                            actividad.setDisponibles(parser.getInt());
                            break;
                        case "descripcion":
                            actividad.setDescripcion(parser.getString());
                            break;
                        case "creadapor":
                            actividad.setCreadaPor(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return actividad;
    }
}
