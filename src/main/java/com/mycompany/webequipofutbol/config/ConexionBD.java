/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webequipofutbol.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author juand
 */
public class ConexionBD {

    public static final String username = "root";
    public static final String password = "CAMBIA_ESTA_PASSWORD";
    public static final String database = "equipo_futbol";
    public static final String url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Madrid";

    public static Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexion con " + database + " establecida.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo conectar con " + database);
            e.printStackTrace();
        }
        return cn;
    }
}
