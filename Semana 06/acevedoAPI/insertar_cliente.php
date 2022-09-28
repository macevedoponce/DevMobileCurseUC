<?php
$hostname_localhost="localhost";
$database_localhost="bank";
$username_localhost="root";
$password_localhost="";

$json=array();

    if(isset($_GET["id"]) && isset($_GET["nombres"]) && isset($_GET["apellidos"])  && isset($_GET["email"])  && isset($_GET["telefono"])){
        $id=$_GET['id'];
        $nombres=$_GET['nombres'];
        $apellidos=$_GET['apellidos'];
        $email=$_GET['email'];
        $telefono=$_GET['telefono'];

        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $insert="INSERT INTO cliente(idcliente, nombres, apellidos, email, telefono) VALUES ('{$id}','{$nombres}','{$apellidos}','{$email}','{$telefono}')";
        $resultado_insert=mysqli_query($conexion,$insert);
        

        if($resultado_insert){
            $consulta="SELECT * FROM cliente WHERE idcliente = '{$id}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['cliente'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);

        }
        else{
            $resulta["id"]=0;
            $resulta["nombres"]='No registra';
            $resulta["apellidos"]='No registra';
            $resulta["email"]='No registra';
            $resulta["telefono"]='No registra';
            $json['cliente'][]=$resulta;
            echo json_encode($json);

        }
    }
    else{
        $resulta["id"]=0;
        $resulta["nombres"]='WS no retorna';
        $resulta["apellidos"]='WS no retorna';
        $resulta["email"]='WS no retorna';
        $resulta["telefono"]='WS no retorna';
        $json['cliente'][]=$resulta;
            echo json_encode($json);
        }


?>