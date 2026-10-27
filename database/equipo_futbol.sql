-- ============================================================
--  Base de datos: equipo_futbol
--  Proyecto: WebEquipoFutbol
-- ============================================================

CREATE DATABASE IF NOT EXISTS equipo_futbol;

USE equipo_futbol;

CREATE TABLE IF NOT EXISTS equipos (
    id         INT          NOT NULL AUTO_INCREMENT,
    nombre     VARCHAR(100) NOT NULL,
    ciudad     VARCHAR(100) NOT NULL,
    estadio    VARCHAR(150) NOT NULL,
    fundacion  INT          NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS jugadores (
    id       INT         NOT NULL AUTO_INCREMENT,
    nombre   VARCHAR(100) NOT NULL,
    posicion VARCHAR(50)  NOT NULL,
    dorsal   INT          NOT NULL,
    edad     INT          NOT NULL,
    idEquipo INT          NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_jugador_equipo
        FOREIGN KEY (idEquipo) REFERENCES equipos(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

INSERT INTO equipos (nombre, ciudad, estadio, fundacion) VALUES
    ('Málaga CF',       'Málaga',    'La Rosaleda',       1994),
    ('FC Barcelona',    'Barcelona', 'Spotify Camp Nou',  1899),
    ('Real Madrid CF',  'Madrid',    'Santiago Bernabéu', 1902);

INSERT INTO jugadores (nombre, posicion, dorsal, edad, idEquipo) VALUES
('Alfonso Herrero',       'Portero',         1,  32, 1),
('Carlos López',          'Portero',        13,  21, 1),
('Carlos Puga',           'Defensa',         3,  25, 1),
('Einar Galilea',         'Defensa',         4,  32, 1),
('Javier Montero',        'Defensa',        20,  27, 1),
('Juanpe',                'Centrocampista',  8,  30, 1),
('Carlos Dotor',          'Centrocampista', 12,  25, 1),
('Dani Lorenzo',          'Centrocampista', 22,  23, 1),
('David Larrubia Romano', 'Delantero',      10,  24, 1),
('Joaquín Muñoz',         'Delantero',      11,  27, 1),
('Adrián Niño',           'Delantero',      21,  21, 1),
('Marc-André ter Stegen', 'Portero',         1,  34, 2),
('Joan Garcia',           'Portero',        13,  25, 2),
('Ronald Araújo',         'Defensa',         4,  27, 2),
('Pau Cubarsí',           'Defensa',         5,  19, 2),
('Jules Koundé',          'Defensa',        23,  27, 2),
('Gavi',                  'Centrocampista',  6,  21, 2),
('Pedri',                 'Centrocampista',  8,  23, 2),
('Frenkie de Jong',       'Centrocampista', 21,  29, 2),
('Robert Lewandowski',    'Delantero',       9,  37, 2),
('Lamine Yamal',          'Delantero',      10,  18, 2),
('Raphinha',              'Delantero',      11,  29, 2),
('Thibaut Courtois',      'Portero',         1,  34, 3),
('Andriy Lunin',          'Portero',        13,  27, 3),
('Dani Carvajal',         'Defensa',         2,  34, 3),
('Antonio Rüdiger',       'Defensa',        22,  33, 3),
('Dean Huijsen',          'Defensa',        24,  21, 3),
('Jude Bellingham',       'Centrocampista',  5,  22, 3),
('Federico Valverde',     'Centrocampista',  8,  27, 3),
('Aurélien Tchouaméni',   'Centrocampista', 14,  26, 3),
('Vinícius Júnior',       'Delantero',       7,  25, 3),
('Endrick',               'Delantero',       9,  19, 3),
('Kylian Mbappé',         'Delantero',      10,  27, 3);