<?PHP
$hostname_localhost ="localhost";
$database_localhost ="peliculas_acevedo";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["title"])){
		$title=$_GET["title"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from pelicula where title like '%{$title}%'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){//arreglo result
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
		}else{
			$resultar["id"]=0;
			$result["title"]="no registra";
            $result["description"]="no registra";
            $result["time"]="no registra";
            $result["year"]="no registra";
			$result["director"]="no registra";
			$result["category"]="no registra";
			$result["imagen"]="no registra";
			$result["ruta_imagen"]="no registra";
			$json['pelicula'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['usuario'][]=$resultar;
		echo json_encode($json);
	}
?>