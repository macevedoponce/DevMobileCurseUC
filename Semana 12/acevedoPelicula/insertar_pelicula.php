<?php
$hostname_localhost="localhost";
$database_localhost="peliculas_acevedo";
$username_localhost="root";
$password_localhost="";
$json=array();

    if(isset($_GET["id"]) && isset($_GET["title"]) && isset($_GET["description"]) && isset($_GET["time"])  && isset($_GET["year"])  && isset($_GET["director"]) && isset($_GET["category"]) && isset($_GET["ruta_imagen"])){
        $id=$_GET['id'];
        $title=$_GET['title'];
        $description=$_GET['description'];
        $time=$_GET['time'];
        $year=$_GET['year'];
        $director=$_GET['director'];
        $category=$_GET['category'];
        //$imagen=$_GET['imagen'];
        $ruta_imagen=$_GET['ruta_imagen'];

        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $insert="INSERT INTO pelicula(id, title, description, time, year, director,category,ruta_imagen) VALUES ('{$id}','{$title}','{$description}','{$time}','{$year}','{$director}','{$category}','{$ruta_imagen}')";
        $resultado_insert=mysqli_query($conexion,$insert);
        

        if($resultado_insert){
            $consulta="SELECT * FROM pelicula WHERE id = '{$id}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['pelicula'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);

        }
        else{
            $resulta["id"]=0;
            $resulta["title"]='No registra';
            $resulta["description"]='No registra';
            $resulta["time"]='No registra';
            $resulta["year"]='No registra';
            $resulta["director"]='No registra';
            $resulta["category"]='No registra';
            $resulta["imagen"]='No registra';
            $resulta["ruta_imagen"]='No registra';
            $json['pelicula'][]=$resulta;
            echo json_encode($json);

        }
    }
    else{
        $resulta["id"]=0;
            $resulta["title"]='WS no retorna';
            $resulta["description"]='WS no retorna';
            $resulta["time"]='WS no retorna';
            $resulta["year"]='WS no retorna';
            $resulta["director"]='WS no retorna';
            $resulta["category"]='WS no retorna';
            $resulta["imagen"]='WS no retorna';
            $resulta["ruta_imagen"]='No registra';
            $json['pelicula'][]=$resulta;
            echo json_encode($json);
        }


?>