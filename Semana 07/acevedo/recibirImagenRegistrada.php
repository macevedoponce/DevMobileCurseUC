<?PHP
$hostname_localhost ="localhost";
$database_localhost ="bank";
$username_localhost ="root";
$password_localhost ="";

$json['img']=array();

	//if(true){)
	if(isset($_POST["btn"])){
		
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$ruta="imagenes";
		$archivo=$_FILES['imagen']['tmp_name'];
		echo 'Archivo';
		echo '<br>';
		echo $archivo;
		$nombreArchivo=$_FILES['imagen']['name'];
		echo 'Nombre Archivo';
		echo '<br>';
		echo $nombreArchivo;
		move_uploaded_file($archivo,$ruta."/".$nombreArchivo);
		$ruta=$ruta."/".$nombreArchivo;
		$idcliente=$_POST['idcliente'];
		$nombres=$_POST['nombres'];
		$apellidos=$_POST['apellidos'];
		$email=$_POST['email'];
		$telefono=$_POST['telefono'];
		
		echo '<br>';
		echo 'Documento: ';
		echo $idcliente;
		echo '<br>';
		echo 'Nombres: ';
		echo $nombres;
		echo '<br>';
		echo 'Apellidos: ';
		echo $apellidos;
		echo '<br>';
		echo 'Email: ';
		echo $email;
		echo '<br>';
		echo 'Telefono: ';
		echo $telefono;
		echo '<br>';
		echo 'ruta :';
		echo $ruta;
		echo '<br>';
		echo 'Tipo Imagen: ';
		echo ($_FILES['imagen']['type']);
		echo '<br>';
		echo '<br>';
		echo "Imagen: <br><img src='$ruta'>";
		echo '<br>';
		echo '<br>';
		echo 'imagen en Bytes: ';
		echo '<br>';
		echo '<br>';
		echo $ImagenenBytes=file_get_contents($ruta);
		echo '<br>';
		
		$sql="INSERT INTO cliente(idcliente,nombres,apellidos,email, telefono,imagen,ruta_imagen) VALUES (?,?,?,?,?,?,?)";
		$stm=$conexion->prepare($sql);
		$stm->bind_param('issssss',$idcliente,$nombres,$apellidos, $email, $telefono,$ImagenenBytes,$ruta);
		
		if($stm->execute()){
			echo 'imagen Insertada Exitosamente ';
			$consulta="select * from cliente where idcliente='{$idcliente}'";
			$resultado=mysqli_query($conexion,$consulta);
			echo '<br>';
			while ($row=mysqli_fetch_array($resultado)){
				echo $row['idcliente'].' - '.$row['nombres'].'<br/>';
				array_push($json['img'],
				array('idcliente'=>$row['idcliente'],
				'nombres'=>$row['nombres'],
				'apellidos'=>$row['apellidos'],
				'email'=>$row['email'],
				'telefono'=>$row['telefono'],
				'imagen'=>base64_encode($row['imagen']),
				'ruta'=>$row['ruta_imagen']));
			}
			mysqli_close($conexion);
			
			echo '<br>';
			echo 'Objeto JSON 2';
			echo '<br>';
			echo json_encode($json);
		}
	}
?>