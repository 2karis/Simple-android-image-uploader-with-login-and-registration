<?php
if($_POST){
$servername = "localhost";
$username = "id1405861_root";
$password = "password";
$dbname = "id1405861_project";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$response = array();
$response["status"] = 404;
$response["action"] = "register";


    $email = $_POST["email"];
    $username = $_POST["username"];
    $password = $_POST["password"];

$sql = "INSERT INTO users (username, email, password)
VALUES (\"".$username."\" , \"".$email."\",  \"".$password."\")";

if ($conn->query($sql) === TRUE) {
$response["status"]=200;
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
echo json_encode($response);
}   
?>