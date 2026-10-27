<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/WebEquipoFutbol/css/styles.css"/>
        <title>Listado de equipos</title>
    </head>
    <body>
        <h1>Lista de equipos</h1>
        <div class="tabla">
            <h3>Gestion de equipos</h3>
            <hr>
            <a href="/WebEquipoFutbol/EquipoControlador?accion=nuevo" class="btn btn-nuevo">Nuevo</a>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Ciudad</th>
                        <th>Estadio</th>
                        <th>Fundacion</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${equipos}" var="equipo">
                        <tr>
                            <td>${equipo.id}</td>
                            <td>${equipo.nombre}</td>
                            <td>${equipo.ciudad}</td>
                            <td>${equipo.estadio}</td>
                            <td>${equipo.fundacion}</td>
                            <td>
                                <a href="/WebEquipoFutbol/EquipoControlador?accion=editar&id=${equipo.id}" class="btn btn-modificar">Modificar</a>
                                <a href="/WebEquipoFutbol/EquipoControlador?accion=borrar&id=${equipo.id}" class="btn btn-borrar">Borrar</a>                      
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty equipos}">
                        <tr>
                            <td colspan="6">No hay registros de equipos.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <a href="/WebEquipoFutbol/index.jsp" class="btn btn-volver">Volver al inicio</a>
        </div>
    </body>
</html>