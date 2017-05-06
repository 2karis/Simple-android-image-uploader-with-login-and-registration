<?php


if(true){
$servername = "localhost";
$username = "id1405861_root";
$password = "password";
$dbname = "id1405861_project";

$response = array();
$response["status"] = 404;
$response["content"] = array(); 
$id = $_POST["user_id"];
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql1 = "SELECT content.id, content.title, content.price, content.created_at, content.img FROM content WHERE content.user_id =".$id." ORDER BY created_at DESC;"; 

$sql2 = "SELECT users.id, users.username, users.email FROM users WHERE users.id =".$id." ;"; 

$result = $conn->query($sql1);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        array_push($response["content"], $row);
    }

} 
	 
$result2 = $conn->query($sql2);
if ($result2->num_rows > 0) {
    // output data of each row
    while($user = $result2->fetch_assoc()) {
        $user["count"]=$result->num_rows;
        $response["user"] = $user;
    }
$response["status"]=200;
	
} 


	echo json_encode($response);

$conn->close();
}

?>