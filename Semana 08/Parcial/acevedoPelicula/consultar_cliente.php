<?php
$hostname_localhost="localhost";
$database_localhost="bank";
$username_localhost="root";
$password_localhost="";

$json=array();

    if(isset($_GET["idcliente"])){
        $idcliente=$_GET['idcliente'];
        
        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $consulta="select idcliente, nombres, apellidos, email, telefono from cliente where idcliente= '{$idcliente}'";
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_assoc($resultado)){
            $json['cliente'][]=$registro;
        }
        else{
            $resultar["idcliente"]=0;
            $resultar["nombres"]='No registra';
            $resultar["apellidos"]='No registra';
            $resultar["email"]='No registra';
            $resultar["telefono"]='No registra';
            $resultar["imagen"]='No registra';
            $resultar["ruta_imagen"]='No registra';
            $json['cliente'][]=$resultar;
            }
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else{
            $resultar["success"]=0;
            $resultar["message"]='WS no retorna';
            $resultar["message"]='Error de conexión';
            $json['cliente'][]=$resultar;
            echo json_encode($json);
        }
?>