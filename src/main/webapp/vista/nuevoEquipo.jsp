<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/WebEquipoFutbol/css/styles.css"/>
        <title>Equipo</title>
    </head>
    <body>
        <div class="container">
            <h3>${equipo.id == 0 ? "Nuevo" : "Editar"} equipo</h3>
            <hr>
            <form action="/WebEquipoFutbol/EquipoControlador" method="post">
                <input type="hidden" name="accion" value="guardar">
                <input type="hidden" name="id" value="${equipo.id}">
                <div style="margin-bottom: 10px">
                    <label>Nombre:</label>
                    <input value="${equipo.nombre}" name="nombre" type="text" maxlength="100" required="true">
                </div>
                <div style="margin-bottom: 10px">
                    <label>Ciudad:</label>
                    <input value="${equipo.ciudad}" name="ciudad" type="text" maxlength="100" required="true">
                </div>
                <div style="margin-bottom: 10px">
                    <label>Estadio:</label>
                    <input value="${equipo.estadio}" name="estadio" type="text" maxlength="150" required="true">
                </div>
                <div style="margin-bottom: 10px">
                    <label>Año de fundacion:</label>
                    <input value="${equipo.fundacion}" name="fundacion" type="number" min="1850" max="2100" required="true">
                </div>
                <div>
                    <button class="btn btn-guardar">Guardar</button>
                    <a href="/WebEquipoFutbol/EquipoControlador?accion=listar" class="btn btn-volver">Volver atras</a>
                </div>
            </form>
        </div>
    </body>
</html>