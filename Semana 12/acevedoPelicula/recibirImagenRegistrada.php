<?PHP
$hostname_localhost ="localhost";
$database_localhost ="peliculas_acevedo";
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

		$id=$_POST['id'];
        $title=$_POST['title'];
        $description=$_POST['description'];
        $time=$_POST['time'];
        $year=$_POST['year'];
        $director=$_POST['director'];
        $category=$_POST['category'];
		
		echo '<br>';

		echo 'Id: ';
		echo $id;
		echo '<br>';

		echo 'Title: ';
		echo $title;
		echo '<br>';

		echo 'Description: ';
		echo $description;
		echo '<br>';

		echo 'time: ';
		echo $time;
		echo '<br>';

		echo 'Year: ';
		echo $year;
		echo '<br>';

		echo 'Director: ';
		echo $director;
		echo '<br>';

		echo 'Category: ';
		echo $category;
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
		
		$sql="INSERT INTO pelicula(id, title, description, time, year, director,category,imagen,ruta_imagen) VALUES (?,?,?,?,?,?,?,?,?)";
		$stm=$conexion->prepare($sql);
		$stm->bind_param('issssssss',$id,$title,$description, $time, $year,$director,$category,$ImagenenBytes,$ruta);
		
		if($stm->execute()){
			echo 'imagen Insertada Exitosamente ';
			$consulta="select * from pelicula where id='{$id}'";
			$resultado=mysqli_query($conexion,$consulta);
			echo '<br>';
			while ($row=mysqli_fetch_array($resultado)){
				echo $row['id'].' - '.$row['title'].'<br/>';
				array_push($json['img'],
				array('id'=>$row['id'],
				'title'=>$row['title'],
				'description'=>$row['description'],
				'time'=>$row['time'],
				'year'=>$row['year'],
				'director'=>$row['director'],
				'category'=>$row['category'],
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