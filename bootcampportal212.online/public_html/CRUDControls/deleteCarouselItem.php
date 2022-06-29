<?php

session_start();

//connect to db
require_once '../connectToDB.php';


if(isset($_POST['delete'])){
  if(!empty($_POST['element'])) {

    //getting all checked boxes
    $checked_count = count($_POST['element']);

    //deleting the values from db based on id
    foreach($_POST['element'] as $selected) {
        $sql = 'DELETE FROM carouselitems WHERE id='.$selected.';';
        if(mysqli_query($conn, $sql)){

          if(isset($_SESSION['username']) && !empty($_SESSION['username'])){
          header('Location: ../login.php');
          }

        }
        else{
          echo "Failed to delete element";
        }
}


  }
  else{
    echo '<b>Nothing selected</b>';
  }
}







?>
