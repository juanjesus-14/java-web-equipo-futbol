/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.webequipofutbol.controlador;

import com.mycompany.webequipofutbol.dao.EquipoDAO;
import com.mycompany.webequipofutbol.modelo.Equipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "EquipoControlador", urlPatterns = {"/EquipoControlador"})
public class EquipoControlador extends HttpServlet {

    private final EquipoDAO equipoDao = new EquipoDAO();

    private static final String PAG_LISTAR = "/vista/listarEquipos.jsp";
    private static final String PAG_NUEVO = "/vista/nuevoEquipo.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "borrar":
                borrar(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void listar(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("equipos", equipoDao.listarTodos());
        req.getRequestDispatcher(PAG_LISTAR).forward(req, res);
    }

    private void nuevo(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("equipo", new Equipo());
        req.getRequestDispatcher(PAG_NUEVO).forward(req, res);
    }

    private void editar(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Equipo eq = equipoDao.buscarPorId(id);
        if (eq != null) {
            req.setAttribute("equipo", eq);
            req.getRequestDispatcher(PAG_NUEVO).forward(req, res);
        } else {
            res.sendRedirect("EquipoControlador?accion=listar");
        }
    }

    private void guardar(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Equipo eq = new Equipo();
        eq.setId(Integer.parseInt(req.getParameter("id")));
        eq.setNombre(req.getParameter("nombre"));
        eq.setCiudad(req.getParameter("ciudad"));
        eq.setEstadio(req.getParameter("estadio"));
        eq.setFundacion(Integer.parseInt(req.getParameter("fundacion")));
        if (eq.getId() == 0) {
            equipoDao.insertar(eq);
        } else {
            equipoDao.editar(eq);
        }
        res.sendRedirect("EquipoControlador?accion=listar");
    }

    private void borrar(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        equipoDao.borrarPorId(id);
        res.sendRedirect("EquipoControlador?accion=listar");
    }
}
