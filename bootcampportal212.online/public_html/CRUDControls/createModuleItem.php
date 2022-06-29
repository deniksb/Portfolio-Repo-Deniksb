<?php

session_start();


//connect to db
require_once '../connectToDB.php';

if(isset($_POST['title'])){

  $title = $conn->real_escape_string($_POST['title']);
  $linkAll = $conn->real_escape_string($_POST['linkAll']);
  $linkEx = $conn->real_escape_string($_POST['linkEx']);
  $linkSlides = $conn->real_escape_string($_POST['linkSlides']);
  $linkOther = $conn->real_escape_string($_POST['linkOther']);
  $program = $conn->real_escape_string($_POST['program']);

  $sql = "INSERT INTO moduleitems (title, linkAll, linkEx, linkSlides,linkOther,Program)" . "VALUES ('".$title."', '".$linkAll."', '".$linkEx."', '".$linkSlides."', '".$linkOther."', '".$program."');";


  if(mysqli_query($conn, $sql)){

    echo "Success";
    if(isset($_SESSION['username']) && !empty($_SESSION['username'])){
    header('Location: ../login.php');
    }

  }
  else{
    echo "Failed to create element";
  }


}



?>
