package com.grupo06.eve8206.mysql.entities;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the usuario database table.
 */
@Entity
@Table(name="usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
private static final long serialVersionUID = 1L;

@PersistenceConstructor
public User(String apellido1, String apellido2, String correo, String nombre, String password, int telefono) {
this.apellido1 = apellido1;
this.apellido2 = apellido2;
this.correo = correo;
this.nombre = nombre;
this.password = password;
this.telefono = telefono;
}
@Id
@Column(name = "user_name")
private String userName;

private int admin;

private String apellido1;

private String apellido2;

private String correo;

private String nombre;

private String password;

private int telefono;

//bi-directional many-to-one association to Ticket
/*@OneToMany(mappedBy = "usuarioBean")
private List<Ticket> tickets;
*/
public User() {
}

public String getUserName() {
    return this.userName;
}

public void setUserName(String userName) {
    this.userName = userName;
}

public int getAdmin() {
    return this.admin;
}

public void setAdmin(int admin) {
    this.admin = admin;
}

public String getApellido1() {
    return this.apellido1;
}

public void setApellido1(String apellido1) {
    this.apellido1 = apellido1;
}

public String getApellido2() {
    return this.apellido2;
}

public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
}

public String getCorreo() {
    return this.correo;
}

public void setCorreo(String correo) {
    this.correo = correo;
}

public String getNombre() {
    return this.nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getPassword() {
    return this.password;
}

public void setPassword(String password) {
    this.password = password;
}

public int getTelefono() {
    return this.telefono;
}

public void setTelefono(int telefono) {
    this.telefono = telefono;
}
/*
public List<Ticket> getTickets() {
    return this.tickets;
}

public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
}

public Ticket addTicket(Ticket ticket) {
    getTickets().add(ticket);
    ticket.setUsuarioBean(this);

    return ticket;
}

public Ticket removeTicket(Ticket ticket) {
    getTickets().remove(ticket);
    ticket.setUsuarioBean(null);

    return ticket;
}
*/

}

