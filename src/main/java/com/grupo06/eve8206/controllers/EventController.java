package com.grupo06.eve8206.controllers;

import com.grupo06.eve8206.mysql.entities.Event;
import com.grupo06.eve8206.repos.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EventController {
    static final String basePath = "/restful";

    // DAO (Data Access Object) / CRUD
    @Autowired
    EventDAO eDao;

    // GET - Extraer info de bbdd (listar todos los usuarios)
    @RequestMapping(value = basePath + "/events", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Event>> getAllUsers() {
        List<Event> eventList = null;
        try {
            eventList = eDao.findAll();
            if (eventList == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    // GET (by id) - Extraer info de bbdd (listar un usuario por id)
    @RequestMapping(value = basePath + "/events/{id_evento}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Event> getUser(@PathVariable Integer id_evento) {
        Event fromDbEvent = null;
        try {
            fromDbEvent = eDao.findById(id_evento).orElse(null);
            if(fromDbEvent == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(fromDbEvent, HttpStatus.OK);
    }

    //POST
    @RequestMapping(value = basePath + "/events", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Event> insertUser(@RequestBody Event e) {
        try {
            if(e.equals(null)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            eDao.save(e);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    // PUT - Actualización de info en BBDD
    @RequestMapping(value = basePath + "/events/{id_evento}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Event> updateUser(@PathVariable Integer id_evento, @RequestBody Event toUpdate) {
        Event updatedEvent = null;
        try {
            if((id_evento == null) || (toUpdate == null)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            Event fromDBEvent = eDao.findById(id_evento).orElse(null);

            if (fromDBEvent != null) {
                eDao.save(toUpdate);
                updatedEvent = toUpdate;
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    // DELETE - Eliminación de info en BBDD
    @RequestMapping(value = basePath + "/events/{id_evento}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Event> deleteOne(@PathVariable Integer id_evento) {
        Event toDeleteEvent = null;
        try {
            if(id_evento == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            toDeleteEvent = eDao.findById(id_evento).orElse(null);
            eDao.delete(toDeleteEvent);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(toDeleteEvent, HttpStatus.OK);
    }
}
