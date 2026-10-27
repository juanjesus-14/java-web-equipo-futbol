<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/WebEquipoFutbol/css/styles.css"/>
        <title>Jugadores con Equipo</title>
    </head>
    <body>
        <h1>Jugadores con su Equipo</h1>
        <div class="tabla">
            <h3>Listado combinado jugadores y equipos</h3>
            <hr>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre jugador</th>
                        <th>Posicion</th>
                        <th>Dorsal</th>
                        <th>Edad</th>
                        <th>Equipo</th>
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
                            <td>${jugador.nombreEquipo}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty jugadores}">
                        <tr>
                            <td colspan="6">No hay registros.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <a href="/WebEquipoFutbol/index.jsp" class="btn btn-volver">Volver al inicio</a>
        </div>
    </body>
</html>