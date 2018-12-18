<?php


	function send_notification ($tokens, $message)
	{
		$url = "https://fcm.googleapis.com/fcm/send";
		$fields = array(
			'registration_ids' => $tokens,
			'data' => $message
			);

		$header = array(
			'Authorization:key = AIzaSyDK62YAFd3jN-CGRjsk4mkNCHmY3--jJB0',
			'Content-Type: application/json'
			);


		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_POST, true);
		curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
		curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));

		$result = curl_exec($ch);
		if($result === FALSE) {
			die('Curl failed: ' . curl_error($ch));
		}

		curl_close($ch);

		return 'Message Send';
	}

	
	DEFINE('HOST', 'localhost');
	DEFINE('USER', 'id2008008_atif');
	DEFINE('PASSWORD', 'database');
	DEFINE('DB', 'id2008008_trices');


	$con = mysqli_connect(HOST, USER, PASSWORD, DB) or die('unable to connect DB');

	$sql = "select device as token from registered_devices where type = 'sms'";
	$result = mysqli_query($con, $sql);


	if(mysqli_num_rows($result) > 0){
		while($row = mysqli_fetch_assoc($result)){
			$tokens[] = $row["token"];
		}
	}

	mysqli_close($con);

	$phone = $_GET['phone'];
	$msg = $_GET['msg'];
	
	$message = array("msg" => $msg,
					 "phone" => $phone
					);
	$message_ststus = send_notification($tokens, $message);

	echo $message_ststus;
?>