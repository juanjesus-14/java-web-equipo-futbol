/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.webequipofutbol.controlador;

import com.mycompany.webequipofutbol.dao.EquipoDAO;
import com.mycompany.webequipofutbol.dao.JugadorDAO;
import com.mycompany.webequipofutbol.modelo.Jugador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "JugadorControlador", urlPatterns = {"/JugadorControlador"})
public class JugadorControlador extends HttpServlet {

    private final JugadorDAO jugadorDao = new JugadorDAO();
    private final EquipoDAO equipoDao = new EquipoDAO();

    private static final String PAG_LISTAR = "/vista/listarJugadores.jsp";
    private static final String PAG_LISTAR_JOIN = "/vista/listarJugadoresEquipos.jsp";
    private static final String PAG_NUEVO = "/vista/nuevoJugador.jsp";

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
            case "listarConEquipo":
                listarConEquipo(request, response);
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
        req.setAttribute("jugadores", jugadorDao.listarTodos());
        req.getRequestDispatcher(PAG_LISTAR).forward(req, res);
    }

    private void listarConEquipo(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("jugadores", jugadorDao.listarConEquipo());
        req.getRequestDispatcher(PAG_LISTAR_JOIN).forward(req, res);
    }

    private void nuevo(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("jugador", new Jugador());
        req.setAttribute("equipos", equipoDao.listarTodos());
        req.getRequestDispatcher(PAG_NUEVO).forward(req, res);
    }

    private void editar(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Jugador j = jugadorDao.buscarPorId(id);
        if (j != null) {
            req.setAttribute("jugador", j);
            req.setAttribute("equipos", equipoDao.listarTodos());
            req.getRequestDispatcher(PAG_NUEVO).forward(req, res);
        } else {
            res.sendRedirect("JugadorControlador?accion=listar");
        }
    }

    private void guardar(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Jugador j = new Jugador();
        j.setId(Integer.parseInt(req.getParameter("id")));
        j.setNombre(req.getParameter("nombre"));
        j.setPosicion(req.getParameter("posicion"));
        j.setDorsal(Integer.parseInt(req.getParameter("dorsal")));
        j.setEdad(Integer.parseInt(req.getParameter("edad")));
        String idEqStr = req.getParameter("idEquipo");
        j.setIdEquipo((idEqStr == null || idEqStr.isEmpty()) ? 0
                : Integer.parseInt(idEqStr));
        if (j.getId() == 0) {
            jugadorDao.insertar(j);
        } else {
            jugadorDao.editar(j);
        }
        res.sendRedirect("JugadorControlador?accion=listar");
    }

    private void borrar(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        jugadorDao.borrarPorId(id);
        res.sendRedirect("JugadorControlador?accion=listar");
    }
}
