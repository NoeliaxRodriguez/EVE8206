package com.grupo06.eve8206;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EventController {
    static final String basePath = "/restful";

    // DAO (Data Access Object) / CRUD
    @Autowired
    UserDAO uDao;

    // GET - Extraer info de bbdd (listar todos los usuarios)
    @RequestMapping(value = basePath + "/users", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = null;
        try {
            userList = uDao.findAll();
            if (userList == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // GET (by id) - Extraer info de bbdd (listar un usuario por id)
    @RequestMapping(value = basePath + "/users/{user_name}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<User> getUser(@PathVariable String user_name) {
        User fromDbUser = null;
        try {
            fromDbUser = uDao.findById(user_name).orElse(null);
            if(fromDbUser == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(fromDbUser, HttpStatus.OK);
    }

    //POST
    @RequestMapping(value = basePath + "/users", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<User> insertUser(@RequestBody User u) {
        try {
            if(u.equals(null)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            uDao.save(u);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    // PUT - Actualización de info en BBDD
    @RequestMapping(value = basePath + "/users/{user_name}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<User> updateUser(@PathVariable String user_name, @RequestBody User toUpdate) {
        User updatedUser = null;
        try {
            if((user_name == null) || (toUpdate == null)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            User fromDBUser = uDao.findById(user_name).orElse(null);

            if (fromDBUser != null) {
                uDao.save(toUpdate);
                updatedUser = toUpdate;
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // DELETE - Eliminación de info en BBDD
    @RequestMapping(value = basePath + "/users/{user_name}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<User> deleteOne(@PathVariable String user_name) {
        User toDeleteUser = null;
        try {
            if(user_name == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            toDeleteUser = uDao.findById(user_name).orElse(null);
            uDao.delete(toDeleteUser);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(toDeleteUser, HttpStatus.OK);
    }
}
