<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/WebEquipoFutbol/css/styles.css"/>
        <title>Listado de jugadores</title>
    </head>
    <body>
        <h1>Lista de jugadores</h1>
        <div class="tabla">
            <h3>Gestion de jugadores</h3>
            <hr>
            <a href="/WebEquipoFutbol/JugadorControlador?accion=nuevo" class="btn btn-nuevo">Nuevo</a>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Posicion</th>
                        <th>Dorsal</th>
                        <th>Edad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${jugadores}" var="jugador">
                        <tr>
                            <td>${jugador.id}</td>
                            <td>${jugador.nombre}</td>
                            <td>${jugador.posicion}</td>
                            <td>${jugador.dorsal}</td>
                            <td>${jugador.edad}</td>
                            <td>
                                <a href="/WebEquipoFutbol/JugadorControlador?accion=editar&id=${jugador.id}" class="btn btn-modificar">Modificar</a>
                                <a href="/WebEquipoFutbol/JugadorControlador?accion=borrar&id=${jugador.id}" class="btn btn-borrar">Borrar</a>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty jugadores}">
                        <tr>
                            <td colspan="6">No hay registros de jugadores.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <a href="/WebEquipoFutbol/index.jsp" class="btn btn-volver">Volver al inicio</a>
        </div>
    </body>
</html>