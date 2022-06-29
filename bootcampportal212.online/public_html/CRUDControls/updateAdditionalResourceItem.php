<?php

session_start();

//connect to db
require_once '../connectToDB.php';




  $id = $conn->real_escape_string($_POST['id']);
  $title = $conn->real_escape_string($_POST['title']);
  $desc = $conn->real_escape_string($_POST['description']);
  $link = $conn->real_escape_string($_POST['link']);
  $file = $conn->real_escape_string($_POST['imageName']);

if(isset($_POST['update'])){

    //deleting the values from db based on id

        $sql = 'UPDATE additionalrecources SET title="'.$title.'",description="'.$desc.'",link="'.$link.'",imageName="'.$file.'" WHERE id='.$id;
        if(mysqli_query($conn, $sql)){

          if(isset($_SESSION['username']) && !empty($_SESSION['username'])){
          header('Location: ../login.php');
          }

        }
        else{
          echo "Failed to update element";
        }




}



?>
