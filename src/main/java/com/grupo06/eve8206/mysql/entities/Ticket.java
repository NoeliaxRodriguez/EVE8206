package com.grupo06.eve8206.mysql.entities;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;



/**
 * The persistent class for the ticket database table.
 *
 */
@Entity
@Table(name="ticket")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceConstructor
    public Ticket(BigDecimal precio, String tipo) {
        this.precio = precio;
        this.tipo = tipo;
    }

    @Id
    @Column(name="num_referencia")
    private int numReferencia;

    private BigDecimal precio;

    private String tipo;

    //bi-directional many-to-one association to Evento
    @ManyToOne
    @JoinColumn(name="evento")
    private Event eventoBean;

    //bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name="usuario")
    private User usuarioBean;

    public Ticket() {
    }

    public int getNumReferencia() { return this.numReferencia; }

    public void setNumReferencia(int numReferencia) { this.numReferencia = numReferencia; }

    public BigDecimal getPrecio() { return this.precio; }

    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Event getEventoBean() {
        return this.eventoBean;
    }

    public void setEventoBean(Event eventoBean) {
        this.eventoBean = eventoBean;
    }

    public User getUsuarioBean() {
        return this.usuarioBean;
    }

    public void setUsuarioBean(User usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

}