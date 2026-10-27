# Gestión de Equipo de Fútbol — Java Web (Jakarta EE)

Aplicación web para gestionar **equipos** y **jugadores** de fútbol sobre una base de datos MySQL. Está construida con Servlets y JSP siguiendo el patrón **MVC** con una capa **DAO** de acceso a datos mediante JDBC.

Es el proyecto final de la unidad 9 (aplicaciones web con Java) del módulo de Programación de 1.º de DAW.

## Funcionalidades

- **Equipos**: listar, crear, editar y borrar (nombre, ciudad, estadio y año de fundación).
- **Jugadores**: listar, crear, editar y borrar (nombre, posición, dorsal, edad y equipo).
- Un jugador puede **no tener equipo** (en la base de datos se guarda `NULL`).
- **Listado combinado** de jugadores con el nombre de su equipo (`LEFT JOIN`). Si no tiene equipo, muestra "Sin equipo".
- Al borrar un equipo, sus jugadores se quedan sin equipo (`ON DELETE SET NULL`).

## Tecnologías

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 11 |
| Web | Jakarta Servlet 6.0 (Jakarta EE 10), JSP, JSTL 3.0 |
| Datos | JDBC con `PreparedStatement`, MySQL (`mysql-connector-j` 9.6.0) |
| Build | Maven (empaquetado `war`) |
| Servidor | Apache Tomcat 10.1 |

## Arquitectura

```
Navegador ──► Controlador (Servlet) ──► DAO (JDBC) ──► MySQL
                    │
                    └──► Vista (JSP + JSTL)
```

- `controlador/`: `EquipoControlador` y `JugadorControlador`. Reciben el parámetro `accion` (`listar`, `nuevo`, `guardar`, `editar`, `borrar`, y además `listarConEquipo` en jugadores) y redirigen a la vista correspondiente.
- `dao/`: `EquipoDAO` y `JugadorDAO`. Hacen las operaciones CRUD con consultas parametrizadas.
- `modelo/`: los POJOs `Equipo` y `Jugador`.
- `config/ConexionBD`: abre la conexión JDBC.
- `webapp/vista/`: las páginas JSP de listado y de formulario.

## Estructura del proyecto

```
java-web-equipo-futbol/
├── database/
│   └── equipo_futbol.sql          # Esquema + datos de ejemplo
├── pom.xml
└── src/main/
    ├── java/com/mycompany/webequipofutbol/
    │   ├── config/ConexionBD.java
    │   ├── controlador/           # Servlets
    │   ├── dao/                   # Acceso a datos
    │   └── modelo/                # Equipo, Jugador
    └── webapp/
        ├── index.jsp              # Menú principal
        ├── css/styles.css
        └── vista/                 # listarEquipos, nuevoEquipo, listarJugadores, nuevoJugador, listarJugadoresEquipos
```

## Cómo ejecutarlo

**Requisitos:** JDK 11 o superior, Maven, MySQL 8 y Apache Tomcat 10.1.

1. Crea la base de datos con los datos de ejemplo:
   ```bash
   mysql -u root -p < database/equipo_futbol.sql
   ```
2. Configura las credenciales en `src/main/java/com/mycompany/webequipofutbol/config/ConexionBD.java`. La contraseña aparece como `CAMBIA_ESTA_PASSWORD`. Por defecto se conecta a `localhost:3306/equipo_futbol` con el usuario `root`.
3. Compila:
   ```bash
   mvn clean package
   ```
4. Copia el WAR a Tomcat **con el nombre `WebEquipoFutbol.war`**. Las páginas usan rutas absolutas con ese contexto (`/WebEquipoFutbol/...`):
   ```bash
   cp target/WebEquipoFutbol-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/WebEquipoFutbol.war
   ```
5. Abre <http://localhost:8080/WebEquipoFutbol/>.

## Modelo de datos

- `equipos (id, nombre, ciudad, estadio, fundacion)`
- `jugadores (id, nombre, posicion, dorsal, edad, idEquipo → equipos.id)`

## Problemas conocidos

- Si se llama a un controlador sin el parámetro `accion`, `switch (accion)` lanza `NullPointerException` (HTTP 500).
- `guardar()` convierte `id`, `dorsal`, `edad` y `fundacion` con `Integer.parseInt` sin validar. Un campo vacío provoca `NumberFormatException`.
- Las credenciales de la base de datos están escritas en `ConexionBD.java`, no en un fichero de configuración.
- `jakarta.servlet-api` llega como dependencia transitiva y se empaqueta en el WAR, aunque Tomcat ya la incluye.

## Autor

**Juan Jesús Díaz Garrido** — [github.com/juanjesus-14](https://github.com/juanjesus-14)

## Licencia

[MIT](LICENSE)
