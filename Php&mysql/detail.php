<?php
if($_POST){
$servername = "localhost";
$username = "id1405861_root";
$password = "password";
$dbname = "id1405861_project";

$response = array();
$response["status"] = 404;  
$response["action"] = "detail";

$id = $_POST["content_id"];
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT content.id,content.title, content.img, content.user_id, content.price, content.created_at , users.username FROM content INNER JOIN users ON content.user_id =users.id WHERE content.id = ".$id."
;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $response = $row;
    }
	$response["status"] = 200;  
} 
echo json_encode($response);
$conn->close();
}

?>