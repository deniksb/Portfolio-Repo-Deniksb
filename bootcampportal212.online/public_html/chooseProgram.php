<?php
session_start();


  $_SESSION['program'] = $_POST['select-button'];


  if(isset($_SESSION['program'])){
    header('Location: mainPage.php');
  }



?>
