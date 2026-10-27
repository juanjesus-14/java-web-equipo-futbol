/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webequipofutbol.dao;

import com.mycompany.webequipofutbol.config.ConexionBD;
import com.mycompany.webequipofutbol.modelo.Equipo;
import java.sql.*;
import java.util.*;

/*
 * @author juand
 */

public class EquipoDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Equipo> listarTodos() {
        ArrayList<Equipo> lista = new ArrayList<>();
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement("SELECT * FROM equipos ORDER BY nombre");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return lista;
    }

    public Equipo buscarPorId(int id) {
        Equipo eq = null;
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement("SELECT * FROM equipos WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                eq = mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return eq;
    }

    public int insertar(Equipo eq) {
        int result = 0;
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement("INSERT INTO equipos (nombre, ciudad, estadio, fundacion) VALUES (?,?,?,?)");
            ps.setString(1, eq.getNombre());
            ps.setString(2, eq.getCiudad());
            ps.setString(3, eq.getEstadio());
            ps.setInt(4, eq.getFundacion());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return result;
    }

    public int editar(Equipo eq) {
        int result = 0;
        try {
            cn = ConexionBD.getConnection();
            ps = cn.prepareStatement("UPDATE equipos SET nombre=?, ciudad=?, estadio=?, fundacion=? WHERE id=?");
            ps.setString(1, eq.getNombre());
            ps.setString(2, eq.getCiudad());
            ps.setString(3, eq.getEstadio());
            ps.setInt(4, eq.getFundacion());
            ps.setInt(5, eq.getId());
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
            ps = cn.prepareStatement("DELETE FROM equipos WHERE id=?");
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return result;
    }

    private Equipo mapear(ResultSet rs) throws SQLException {
        Equipo eq = new Equipo();
        eq.setId(rs.getInt("id"));
        eq.setNombre(rs.getString("nombre"));
        eq.setCiudad(rs.getString("ciudad"));
        eq.setEstadio(rs.getString("estadio"));
        eq.setFundacion(rs.getInt("fundacion"));
        return eq;
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
