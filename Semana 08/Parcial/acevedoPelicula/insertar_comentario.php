<?php
$hostname_localhost="localhost";
$database_localhost="peliculas_acevedo";
$username_localhost="root";
$password_localhost="";
$json=array();

    if(isset($_GET["comentario"]) && isset($_GET["calificacion"]) && isset($_GET["id_pelicula"])){
        $idPelicula=$_GET['id_pelicula'];
        $comentario=$_GET['comentario'];
        $calificacion=$_GET['calificacion'];

        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $insert="INSERT INTO comentario(calificacion, comentario, id_pelicula) VALUES ('{$calificacion}','{$comentario}','{$idPelicula}')";
        $resultado_insert=mysqli_query($conexion,$insert);
        

        if($resultado_insert){
            $consulta="SELECT * FROM comentario WHERE id_pelicula = '{$idPelicula}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['comentario'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);

        }
        else{
            $resulta["id"]=0;
            $resulta["calificacion"]='No registra';
            $resulta["comentario"]='No registra';
            $resulta["id_pelicula"]='No registra';
            echo json_encode($json);

        }
    }
    else{
        $resulta["id"]=0;
        $resulta["calificacion"]='WS no retorna';
        $resulta["comentario"]='WS no retorna';
        $resulta["id_pelicula"]='WS no retorna';
        echo json_encode($json);
        }


?>