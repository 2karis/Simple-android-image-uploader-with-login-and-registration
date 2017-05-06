<?php
if(true){
$servername = "localhost";
$username = "id1405861_root";
$password = "password";
$dbname = "id1405861_project";

$response = array();
$response["status"] = 404;  
$response["content"] = array();
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT content.id, content.title, content.price, content.img, content.created_at FROM content ORDER BY created_at DESC";
$result = $conn->query($sql);
	
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
         array_push($response["content"], $row);
    }
	$response["status"] = 200;  
} 
echo json_encode($response);
$conn->close();
}
?>