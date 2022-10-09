<?php
$hostname_localhost ="localhost";
$database_localhost ="peliculas_acevedo";
$username_localhost ="root";
$password_localhost ="";

$json=array();

    if(isset($_GET["id_pelicula"])){
		$id_pelicula=$_GET["id_pelicula"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from comentario where id_pelicula='{$id_pelicula}'";
        $resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){//arreglo result
			while($registro=mysqli_fetch_array($resultado)){
                $result["id"]=$registro['id'];
                $result["calificacion"]=$registro['calificacion'];
                $result["comentario"]=$registro['comentario'];
                $result["id_pelicula"]=$registro['id_pelicula'];
                $json['comentario'][]=$result;
            }
            
		}else{
			$result["id"]=0;
            $result["calificacion"]=0;
            $result["comentario"]="ningun comentario por ahora";
			$result["id_pelicula"]=0;
            $json['comentario'][]=$result;
            
		}
		echo json_encode($json);
		mysqli_close($conexion);
		
	}
	
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['comentario'][]=$resultar;
		echo json_encode($json);
	}
?>