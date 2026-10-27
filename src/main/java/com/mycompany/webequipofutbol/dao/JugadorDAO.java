/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webequipofutbol.dao;

import com.mycompany.webequipofutbol.config.ConexionBD;
import com.mycompany.webequipofutbol.modelo.Jugador;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author juand
 */
public class JugadorDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Jugador> listarTodos() {
        ArrayList<Jugador> lista = new ArrayList<>();
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement("SELECT * FROM jugadores ORDER BY nombre");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs, false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return lista;
    }

    public ArrayList<Jugador> listarConEquipo() {
        ArrayList<Jugador> lista = new ArrayList<>();
        String sql = "SELECT j.*, e.nombre AS nombreEquipo " + "FROM jugadores j " + "LEFT JOIN equipos e ON j.idEquipo = e.id " + "ORDER BY j.nombre";
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs, true));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return lista;
    }

    public Jugador buscarPorId(int id) {
        Jugador j = null;
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement("SELECT * FROM jugadores WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                j = mapear(rs, false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return j;
    }

    public int insertar(Jugador j) {
        int result = 0;
        try {
            cn = ConexionBD.getConnection();
            if (j.getIdEquipo() == 0) {
                ps = cn.prepareStatement("INSERT INTO jugadores (nombre, posicion, dorsal, edad) VALUES (?,?,?,?)");
                ps.setString(1, j.getNombre());
                ps.setString(2, j.getPosicion());
                ps.setInt(3, j.getDorsal());
                ps.setInt(4, j.getEdad());
            } else {
                ps = cn.prepareStatement("INSERT INTO jugadores (nombre, posicion, dorsal, edad, idEquipo) VALUES (?,?,?,?,?)");
                ps.setString(1, j.getNombre());
                ps.setString(2, j.getPosicion());
                ps.setInt(3, j.getDorsal());
                ps.setInt(4, j.getEdad());
                ps.setInt(5, j.getIdEquipo());
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return result;
    }

    public int editar(Jugador j) {
        int result = 0;
        try {
            cn = ConexionBD.getConnection();
            if (j.getIdEquipo() == 0) {
                ps = cn.prepareStatement("UPDATE jugadores SET nombre=?, posicion=?, dorsal=?, edad=?, idEquipo=NULL WHERE id=?");
                ps.setString(1, j.getNombre());
                ps.setString(2, j.getPosicion());
                ps.setInt(3, j.getDorsal());
                ps.setInt(4, j.getEdad());
                ps.setInt(5, j.getId());
            } else {
                ps = cn.prepareStatement("UPDATE jugadores SET nombre=?, posicion=?, dorsal=?, edad=?, idEquipo=? WHERE id=?");
                ps.setString(1, j.getNombre());
                ps.setString(2, j.getPosicion());
                ps.setInt(3, j.getDorsal());
                ps.setInt(4, j.getEdad());
                ps.setInt(5, j.getIdEquipo());
                ps.setInt(6, j.getId());
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return result;
    }

    public int borrarPorId(int id) {
        int result = 0;
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement("DELETE FROM jugadores WHERE id=?");
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return result;
    }

    private Jugador mapear(ResultSet rs, boolean conEquipo) throws SQLException {
        Jugador j = new Jugador();
        j.setId(rs.getInt("id"));
        j.setNombre(rs.getString("nombre"));
        j.setPosicion(rs.getString("posicion"));
        j.setDorsal(rs.getInt("dorsal"));
        j.setEdad(rs.getInt("edad"));
        int idEq = rs.getInt("idEquipo");
        j.setIdEquipo(rs.wasNull() ? 0 : idEq);
        if (conEquipo) {
            String ne = rs.getString("nombreEquipo");
            j.setNombreEquipo(ne != null ? ne : "Sin equipo");
        }
        return j;
    }

    private void cerrar() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
