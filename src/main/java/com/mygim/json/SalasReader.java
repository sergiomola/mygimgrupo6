/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.json;

import com.mygim.entities.Actividades;
import com.mygim.entities.Salas;
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

/**
 *
 * @author Sergio 10
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class SalasReader implements MessageBodyReader<Salas> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Salas.class.isAssignableFrom(type);
    }

    @Override
    public Salas readFrom(Class<Salas> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Salas sala = new Salas();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "aforo":
                            sala.setAforo(parser.getInt());
                            break;
                        case "nombre":
                            sala.setNombre(parser.getString());
                            break;

                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return sala;
    }
}
