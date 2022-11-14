<?php
$hostname_localhost ="localhost";
$database_localhost ="peliculas_acevedo";
$username_localhost ="root";
$password_localhost ="";

$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

if(isset($_GET["id_pelicula"])){

	$id_pelicula=$_GET["id_pelicula"];

	$result = array();
	$result['comentario'] = array();
	$query = "select * from comentario where id_pelicula='{$id_pelicula}'";
	$response = mysqli_query($conexion,$query);

	if(mysqli_num_rows($response)==0){ //si la cantidad de filas recepcionadas es 0 se envia que no hay comentarios
		$resultado["id"]=0;
		$resultado["calificacion"]=0;
		$resultado["comentario"]="ningun comentario por ahora";
		$resultado["id_pelicula"]=0;
		$result['comentario'][]=$resultado;
	}else{ // si la cnatidad de filas recepcionadas es mayor que 0, se devuelven los datos recepcionados
		while ($registro = mysqli_fetch_array($response)) {
			$resultado["id"]=$registro['id'];
			$resultado["calificacion"]=$registro['calificacion'];
			$resultado["comentario"]=$registro['comentario'];
			$resultado["id_pelicula"]=$registro['id_pelicula'];
			
			array_push($result['comentario'],$resultado);
		}
	}
	echo json_encode($result);
	mysqli_close($conexion);
}
else{
	echo "Se requiere que envie id_pelicula";
}

?>