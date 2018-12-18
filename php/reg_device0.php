<?php

DEFINE('HOST', 'localhost');
DEFINE('USER', 'id2008008_atif');
DEFINE('PASSWORD', 'database');
DEFINE('DB', 'id2008008_trices');


	if(isset($_GET["Token"])){

		$token = $_GET["Token"];


		$con = mysqli_connect(HOST, USER, PASSWORD, DB) or die('unable to connect DB');


		$query = "INSERT INTO registered_devices (device, type) VALUES ('$token', 'sms') ON DUPLICATE KEY UPDATE device = '$token'";

		mysqli_query($conn, $query);
		mysqli_close($conn);

		echo 'Device Regitered';
	}else{
		echo 'Data NOT Found!';
	}


?>