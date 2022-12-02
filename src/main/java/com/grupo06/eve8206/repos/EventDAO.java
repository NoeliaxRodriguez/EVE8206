package com.grupo06.eve8206.repos;

import com.grupo06.eve8206.mysql.entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/*
 * Un crud repository tiene una serie de métodos ya disponibles que permiten operar sobre entidades:
 * save( Entity) -> Hacer persistente una instancia de entidad en BBDD
 * findById()
 * findAll()
 * count()
 * delete(Entity) -> Eliminar de la base de datos..
 * exist...
 */


public interface EventDAO extends CrudRepository<Event, Integer>{


    // En el cuerpo de esta interfaz debemos incorporar todas las queries en forma de métodos que necesitemos realizar sobre nuestras tablas:
    // Hay 2 formas de autogenerar la implementación de los métodos que realizan queries en un CrudRepository:
    //  1) Especificar queries por nombrado (camelCase)  findByNombreAtributoAndNombreAtributo2( ...)
    //  2) Especificar queries explícitamente por JPQL
    List<Event> findAll();


    //  Ejemplo query por nombrado
//    List<Computer> findByBrand(String brand);
//    // Especificar queries por JPQL (lo que va tras ':' es un parámetro que se reemplaza por los valores que pasemos al llamar al método)
//    @Query("SELECT Computer c WHERE c.brand=:brand")
//    List<Computer> query1(String brand);


}
