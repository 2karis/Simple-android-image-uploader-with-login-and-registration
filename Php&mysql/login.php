<?php
$servername = "localhost";
$user= "id1405861_root";
$pass= "password";
$dbname = "id1405861_project";

if($_POST){
$response = array();
$response["status"] = 404;
$response["action"] = "login";

    $identifier = $_POST["username"];
    $password = $_POST["password"];

// Create connection
$conn = new mysqli($servername, $user, $pass, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sql = "SELECT * FROM users WHERE username = \"".$identifier."\" OR email = \"".$identifier."\" AND password = \"".$password."\"";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
 while($row = $result->fetch_assoc()) {
        $response["user"] = $row;
    }
$response["status"]= 200;
} 
echo json_encode($response);
$conn->close();
}
?>