<?php

require_once ('dbconnect.php');

$name = $_POST['name'];
$food = $_POST['food'];
$phone = $_POST['phone'];
if (isset($_POST['submit'])) {



    $sql = "INSERT INTO orders(phone, name, food) VALUES (?,?,?)";
    $stmt = $conn->prepare ("INSERT INTO orders(phone, name, food) VALUES (?,?,?)");
$stmt->bindValue(1, $phone);
    $stmt->bindValue(2, $name);
    $stmt->bindValue(3, $food);

    $stmt -> execute();


    try{
      echo '<script language="javascript">';
echo 'alert("Order successfully submited!")';
echo '</script>';

    } catch (PDOException $e) {
      echo "FAILED" . $e -> getMessage();
    }


}



 ?>
 <html lang="en" dir="ltr">
 <style>
input[type=text], select {
  width: 20%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 10%;
  background-color: green;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}
input[type=button] {
  width: 10%;
  background-color: green;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
input[type=button]:hover {
  background-color: #45a049;
}


</style>
  <head>
    <meta charset="utf-8">
      <link rel="stylesheet" type="text/css" href="/BulgariaApp/stylesheet.css">
    <title></title>
  </head>
  <body style="background-image: url('https://wallpaperaccess.com/full/191357.jpg') ;   ">
    <script>
    function myFunction1() {
      var value  =  document.getElementById("food").value;
      value = value + " Лютеница Дерони";
      document.getElementById("food").value = value;
    }
    function myFunction2() {
      var value  =  document.getElementById("food").value;
      value = value + " Кренвирш Кен";
      document.getElementById("food").value = value;
    }
    function myFunction3() {
      var value  =  document.getElementById("food").value;
      value = value + " Кисело мляко Верея";
      document.getElementById("food").value = value;
    }
    function clearFunction() {
      document.getElementById("food").value = "";
    }

    </script>
    <header>
      <div class="logo">
      <img style="padding-left: 750px;" src="/BulgarianApp/LogoMakr_9ORckJ.png" alt="Girl in a jacket">
      </div>
    </header>

    <form style="text-align: center; padding-top:100px;" class = "upload" action="?" method="POST" enctype="multipart/form-data">
      <label style="color: white;">Phone Number</label></br>
      <input type="text" name="phone" class = "field" required></br>
        </br>
      <label style="color: white">Address</label></br>
      <input type="text" name="name" class = "field" required></br>
      </br>
      <input id="button" type = "button" name="button1" onclick="myFunction1()" value = "Лютеница Дерони" />
      <input id="button"  type = "button"  name="button2" onclick="myFunction2()" value = "Кренвирш Кен" />
      <input id="button" type = "button"  name="button3" onclick="myFunction3()" value = "Кисело мляко Верея" />
</br></br>
      <label style="color: white">Your Order</label></br>
      <input id="food" type="text" name="food" class = "field" style="" value="" readonly required></br>
      </br>
      <input type="submit" name="submit" value = "Order" >
      <input id="button" type = "button"  name="buttonreset" onclick="clearFunction()" value = "Clear" />



       </form>


  </body>
</html>
