<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/WebEquipoFutbol/css/styles.css"/>
        <title>Jugador</title>
    </head>
    <body>
        <div class="container">
            <h3>${jugador.id == 0 ? "Nuevo" : "Editar"} jugador</h3>
            <hr>
            <form action="/WebEquipoFutbol/JugadorControlador" method="post">
                <input type="hidden" name="accion" value="guardar">
                <input type="hidden" name="id" value="${jugador.id}">
                <div style="margin-bottom: 10px">
                    <label>Nombre:</label>
                    <input value="${jugador.nombre}" name="nombre" type="text" maxlength="100" required="true">
                </div>
                <div style="margin-bottom: 10px">
                    <label>Posicion:</label>
                    <select name="posicion" required="true">
                        <option value="">-- Selecciona --</option>
                        <option value="Portero">Portero</option>
                        <option value="Defensa">Defensa</option>
                        <option value="Centrocampista">Centrocampista</option>
                        <option value="Delantero">Delantero</option>
                    </select>
                </div>
                <div style="margin-bottom: 10px">
                    <label>Dorsal:</label>
                    <input value="${jugador.dorsal}" name="dorsal" type="number" min="1" max="99" required="true">
                </div>
                <div style="margin-bottom: 10px">
                    <label>Edad:</label>
                    <input value="${jugador.edad}" name="edad" type="number" min="15" max="50" required="true">
                </div>
                <div style="margin-bottom: 10px">
                    <label>Equipo:</label>
                    <select name="idEquipo">
                        <option value="">-- Sin equipo --</option>
                        <c:forEach items="${equipos}" var="equipo">
                            <option value="${equipo.id}">${equipo.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button class="btn btn-guardar">Guardar</button>
                    <a href="/WebEquipoFutbol/JugadorControlador?accion=listar" class="btn btn-volver">Volver atras</a>
                </div>
            </form>
        </div>
    </body>
</html>