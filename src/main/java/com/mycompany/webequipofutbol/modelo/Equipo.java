/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webequipofutbol.modelo;

/**
 *
 * @author juand
 */
public class Equipo {

    private int id;
    private String nombre;
    private String ciudad;
    private String estadio;
    private int fundacion;

    public Equipo() {
    }

    public Equipo(int id, String nombre, String ciudad, String estadio, int fundacion) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.fundacion = fundacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    @Override
    public String toString() {
        return "Equipo{id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + "}";
    }
}
