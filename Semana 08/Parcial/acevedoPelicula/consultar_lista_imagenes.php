<?PHP
$hostname_localhost ="localhost";
$database_localhost ="peliculas_acevedo";
$username_localhost ="root";
$password_localhost ="";

$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from pelicula";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["title"]=$registro['title'];
			$result["description"]=$registro['description'];
			$result["time"]=$registro['time'];
			$result["year"]=$registro['year'];
			$result["director"]=$registro['director'];
			$result["category"]=$registro['category'];
			$result["imagen"]=base64_encode($registro['imagen']);
			$result["ruta_imagen"]=$registro['ruta_imagen'];
			$json['pelicula'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>