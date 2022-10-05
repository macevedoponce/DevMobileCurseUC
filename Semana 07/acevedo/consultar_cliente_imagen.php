<?PHP
$hostname_localhost ="localhost";
$database_localhost ="bank";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["idcliente"])){
		$idcliente=$_GET["idcliente"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from cliente where idcliente= '{$idcliente}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){//arreglo result
			$result["idcliente"]=$registro['idcliente'];
			$result["nombres"]=$registro['nombres'];
            $result["apellidos"]=$registro['apellidos'];
            $result["email"]=$registro['email'];
            $result["telefono"]=$registro['telefono'];
            $result["imagen"]=base64_encode($registro['imagen']);
            $result["ruta_imagen"]=$registro['ruta_imagen'];
			$json['cliente'][]=$result;
		}else{
			$resultar["idcliente"]=0;
			$result["nombres"]="no registra";
            $result["apellidos"]="no registra";
            $result["email"]="no registra";
            $result["telefono"]="no registra";
			$json['cliente'][]=$resultar;
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