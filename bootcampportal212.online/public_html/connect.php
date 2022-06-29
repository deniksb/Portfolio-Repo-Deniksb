<?php

require_once 'connectToDB.php';


$sql = 'SELECT * FROM carouselitems WHERE Program = "'.$_SESSION['program'].'";';

$result = mysqli_query($conn, $sql);

$carouselitems_values = mysqli_fetch_all($result,MYSQLI_ASSOC);



$sql2 = 'SELECT * FROM moduleitems WHERE Program = "'.$_SESSION['program'].'";';

$result2 = mysqli_query($conn, $sql2);

$moduleitems_values = mysqli_fetch_all($result2,MYSQLI_ASSOC);



$sql3 = 'SELECT * FROM additionalrecources WHERE Program = "'.$_SESSION['program'].'";';

$result3 = mysqli_query($conn, $sql3);

$additionalinfo_values = mysqli_fetch_all($result3,MYSQLI_ASSOC);


mysqli_free_result($result3);




?>
