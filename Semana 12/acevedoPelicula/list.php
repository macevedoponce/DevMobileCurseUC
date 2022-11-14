<?php

$url="http://192.168.1.46/movies/cover/";

$movies=array(
    array('titulo'=>"Allied",'fecha'=>"2016",'duracion'=>"124 min",'genero'=>"Thriller. Drama. Romance",
    'portada'=>$url."allied.jpg"),
    array('titulo'=>"Avengers: Infinity War",'fecha'=>"2018",'duracion'=>"156 min",'genero'=>"Ciencia ficción. Fantástico. Acción",
    'portada'=>$url."avengers_infinity_war.jpg"),
    array('titulo'=>"Enemy",'fecha'=>"2013",'duracion'=>"91 min",'genero'=>"Drama psicológico",
    'portada'=>$url."enemy.jpg"),
    array('titulo'=>"El Francotirador",'fecha'=>"2014",'duracion'=>"132 min",'genero'=>"Bélico. Drama | Biográfico",
    'portada'=>$url."el_francotirador.jpg"),
    array('titulo'=>"The Fighter",'fecha'=>"2010",'duracion'=>"115 min",'genero'=>"Drama Basado en hechos reales",
    'portada'=>$url."the_fighter.jpg"),
    array('titulo'=>"The Wolf of Wall Street",'fecha'=>"2013",'duracion'=>"179 min",'genero'=>"Comedia. Drama",
    'portada'=>$url."the_wolf_of_wall_street.jpg"),
    array('titulo'=>"Underworld",'fecha'=>"2003",'duracion'=>"121 min",'genero'=>"Vampiros. Hombres lobo",
    'portada'=>$url."underworld.jpg"),
    array('titulo'=>"Contracara",'fecha'=>"1997",'duracion'=>"137 min",'genero'=>"Acción. Thriller | Crimen",
    'portada'=>$url."contracara.jpg")
);

header('Content-Type: application/json');
echo json_encode($movies,JSON_UNESCAPED_UNICODE);