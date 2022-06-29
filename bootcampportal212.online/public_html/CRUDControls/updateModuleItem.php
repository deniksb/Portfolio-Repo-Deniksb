<?php

session_start();

//connect to db
require_once '../connectToDB.php';



  $id = $conn->real_escape_string($_POST['id']);
  $title = $conn->real_escape_string($_POST['title']);
  $linkAll = $conn->real_escape_string($_POST['linkAll']);
  $linkEx = $conn->real_escape_string($_POST['linkEx']);
  $linkSlides = $conn->real_escape_string($_POST['linkSlides']);
  $linkOther = $conn->real_escape_string($_POST['linkOther']);

if(isset($_POST['update'])){

    //deleting the values from db based on id

        $sql = 'UPDATE moduleitems SET title="'.$title.'",linkAll="'.$linkAll.'",linkEx="'.$linkEx.'",linkSlides="'.$linkSlides.'",linkOther="'.$linkOther.'" WHERE id='.$id;
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
