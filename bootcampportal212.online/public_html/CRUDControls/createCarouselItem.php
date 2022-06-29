<?php


//connect to db
require_once '../connectToDB.php';


if(isset($_POST['title']) && isset($_POST['upload'])){

  $title = $conn->real_escape_string($_POST['title']);
  $desc = $conn->real_escape_string($_POST['description']);
  $link = $conn->real_escape_string($_POST['link']);
  $program = $conn->real_escape_string($_POST['program']);

  $file = $_FILES['file']['name'];
  $file_loc = $_FILES['file']['tmp_name'];
  $file_size = $_FILES['file']['size'];
  $file_type = $_FILES['file']['type'];
  $folder = "img/";


  $formatted_file_name = strtolower($file);
  $formatted_file_name = str_replace(' ','-',$formatted_file_name);

  $sql = "INSERT INTO carouselitems (title, description, link, imageName,Program)" . "VALUES ('".$title."', '".$desc."', '".$link."', '".$formatted_file_name."', '".$program."');";


  if(mysqli_query($conn, $sql)){

    if(move_uploaded_file($file_loc,$folder.$formatted_file_name)){
      echo "Success";
    }


    if(isset($_SESSION['username']) && !empty($_SESSION['username'])){
    header('Location: ../login.php');
    }

  }
  else{
    echo "Failed to create element";
  }


}




?>
