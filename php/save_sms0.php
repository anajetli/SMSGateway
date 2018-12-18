<?php

DEFINE('HOST', 'localhost');
DEFINE('USER', 'id2008008_atif');
DEFINE('PASSWORD', 'database');
DEFINE('DB', 'id2008008_trices');


$con = mysqli_connect(HOST, USER, PASSWORD, DB) or die('unable to connect DB');

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $phone = $_GET['phone'];
    $msg = $_GET['msg'];
    $user = '';
	$date = '0000-00-00 00:00:00';
	$date_sent = '0000-00-00 00:00:00';

    if($phone != "" && $msg != ""){
        if(isset($_GET['user'])){$user = $_GET['user'];}
		if(isset($_GET['date'])){$date = $_GET['date'];}
		if(isset($_GET['date_sent'])){$date_sent = $_GET['date_sent'];}
		
		$query = "select * from inbox where phone_num = '$phone' and message = '$msg' and datestamp = '$date'";
		$queryResult = mysqli_query($con, $query);
		if(!$queryResult || mysqli_num_rows($queryResult) == 0){
			$query = "INSERT INTO sms_received(user, phone_num, message, datestamp, date_sent) VALUES ('$user', '$phone', '$msg', '$date', '$date_sent')";
			//var_dump($query);
			mysqli_query($con, $query);
			echo json_encode("Success", 200);
			return;
		}
    }else{
        echo json_encode("please provide data", 500);
        return;
    }
}else{
    echo json_encode("Bad request", 500);
    return;
}



?>