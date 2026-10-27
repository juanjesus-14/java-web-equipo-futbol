<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/WebEquipoFutbol/css/styles.css"/>
        <title>Equipo Futbol</title>
    </head>
    <body>
        <div class="inicio">
            <h1>Gestion Equipo Futbol</h1>
            <div class="inicio-botones">
                <a href="/WebEquipoFutbol/EquipoControlador?accion=listar" class="inicio-card">
                    <div class="inicio-titulo">Equipos</div>
                </a>
                <a href="/WebEquipoFutbol/JugadorControlador?accion=listar" class="inicio-card">
                    <div class="inicio-titulo">Jugadores</div>
                </a>
                <a href="/WebEquipoFutbol/JugadorControlador?accion=listarConEquipo" class="inicio-card">
                    <div class="inicio-titulo">Jugadores con Equipo</div>
                </a>
            </div>
        </div>
    </body>
</html>