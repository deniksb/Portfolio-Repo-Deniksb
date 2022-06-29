<?php

require_once 'connect.php';


if(isset($_POST['username'])){

  $username = $conn->real_escape_string($_POST['username']);
  $password = $conn->real_escape_string($_POST['password']);

  $sql = "SELECT * FROM adminlogin WHERE username='".$username."' AND password='".$password."' limit 1";


  $result = mysqli_query($conn, $sql);

  if(mysqli_num_rows($result) == true){
    $_SESSION['username'] = $username;


  }
  else{
    echo "Failed to login";
    header('Location: mainPage.php');
  }


}

?>
<!doctype html>
<html lang="en">
<?php
if(!isset($_SESSION['username'])){
  header('Location: mainPage.php');
}
?>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="img\trading-icon.png">
    <!-- ubuntu font links -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <title>Trading212 Bootcamp Portal</title>
  </head>
  <body class="bg-dark text-light">

    <div id="accordion" class="text-dark">
      <h1 class="text-light">Admin Panel</h1>
      <br>
  <div class="card bg-dark text-light">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Create Carousel Item ✔️
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
        <form class="" id="carouselForm" action="./CRUDControls/createCarouselItem.php" method="post" enctype="multipart/form-data">
            <input type="text" name="title" placeholder="Title">
            <br>
            <br>
            <!-- <input type="text" name="description" placeholder="Description"> -->
            <textarea name="description"class="description-form" form="carouselForm" placeholder="Description"></textarea>
            <br>
            <br>
            <input type="text" name="link" placeholder="Link">
            <br>
            <br>
            <input type="file" name="file" value="">
            <br>
            <br>
            <input type="hidden" name="program" value="<?php echo $_SESSION['program']?>">
            <br>
            <br>
            <input type="submit" class="btn btn-primary" name="upload" value="Create">
        </form>
      </div>
    </div>

  </div>
  <div class="card bg-dark  text-light">
    <div class="card-header" id="headingTwo">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Create Module Item ✔️
        </button>
      </h5>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
      <div class="card-body">
        <form class="" action="./CRUDControls/createModuleItem.php" method="post">
            <input type="text" name="title" placeholder="Title">
            <br>
            <br>
            <input type="text" name="linkAll" placeholder="General Link">
            <br>
            <br>
            <input type="text" name="linkEx" placeholder="Exercises Link">
            <br>
            <br>
            <input type="text" name="linkSlides" placeholder="Slides Link">
            <br>
            <br>
            <input type="text" name="linkOther" placeholder="Other Link">
            <br>
            <br>
            <input type="hidden" name="program" value="<?php echo $_SESSION['program']?>">
            <br>
            <br>
            <input type="submit" class="btn btn-primary" name="" value="Create">
        </form>
      </div>
    </div>
  </div>
  <div class="card bg-dark text-light">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          Create Additional Resources Item ✔️
        </button>
      </h5>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
      <div class="card-body">
        <form class="" id="additionalResourceForm" action="./CRUDControls/createAdditionalResourceItem.php" method="post" enctype="multipart/form-data">
            <input type="text" name="title" placeholder="Title">
            <br>
            <br>
            <textarea name="description" class="description-form" id="ar-desc-form" form="additionalResourceForm" placeholder="Description"></textarea>
            <br>
            <br>
            <input type="text" name="link" placeholder="Link">
            <br>
            <br>
            <input type="file" name="file" value="">
            <button type="button" class="btn btn-success" name="addLinkButton" onclick="addLinkTags();">Add link</button>
            <br>
            <br>
            <input type="hidden" name="program" value="<?php echo $_SESSION['program']?>">
            <br>
            <br>
            <input type="submit" class="btn btn-primary" name="upload" value="Create">
        </form>
      </div>
    </div>
  </div>
  <div class="card bg-dark text-light">
    <div class="card-header" id="headingFour">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
          Delete Carousel Item ❌
        </button>
      </h5>
    </div>
    <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
      <div class="card-body">
        <form class="" action="./CRUDControls/deleteCarouselItem.php" method="post" enctype="multipart/form-data">
          <?php
            foreach($carouselitems_values as $value){

              echo '<p>'.$value['title'].'<br> '.$value['description'].'<br>'.$value['link'].'<br>'.$value['imageName'].'</p>';
              echo '<input type="checkbox" id="element'.$value['id'].'" name="element[]" value="'.$value['id'].'">';
              echo "<br><br>";

            }
            ?>

            <input type="submit" class="btn btn-danger" name="delete" value="Delete">
        </form>
      </div>
    </div>
  </div>

  <div class="card bg-dark text-light">
    <div class="card-header" id="headingFive">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
          Delete Module Item ❌
        </button>
      </h5>
    </div>
    <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordion">
      <div class="card-body">
        <form class="" action="./CRUDControls/deleteModuleItem.php" method="post" enctype="multipart/form-data">
          <?php
            foreach($moduleitems_values as $value){

              echo '<p>'.$value['title'].'<br> '.$value['linkAll'].'<br>'.$value['linkEx'].'<br>'.$value['linkSlides'].'<br>'.$value['linkOther'].'</p>';
              echo '<input type="checkbox" id="element'.$value['id'].'" name="element[]" value="'.$value['id'].'">';
              echo "<br><br>";

            }
            ?>


            <input type="submit" class="btn btn-danger" name="delete" value="Delete">
        </form>
      </div>
    </div>
  </div>

  <div class="card bg-dark text-light">
    <div class="card-header" id="headingSix">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
          Delete Additional Resources Item ❌
        </button>
      </h5>
    </div>
    <div id="collapseSix" class="collapse" aria-labelledby="headingSix" data-parent="#accordion">
      <div class="card-body">
        <form class="" action="./CRUDControls/deleteAdditionalResourceItem.php" method="post" enctype="multipart/form-data">
          <?php
            foreach($additionalinfo_values as $value){

              echo '<p>'.$value['title'].'<br> '.$value['description'].'<br>'.$value['link'].'<br>'.$value['imageName'].'</p>';
              echo '<input type="checkbox" id="element'.$value['id'].'" name="element[]" value="'.$value['id'].'">';
              echo "<br><br>";

            }
            ?>

            <input type="submit" class="btn btn-danger" name="delete" value="Delete">
        </form>
      </div>
    </div>
  </div>


  <div class="card bg-dark text-light">
    <div class="card-header" id="headingEight">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEight" aria-expanded="false" aria-controls="collapseSix">
          Update Carousel Item 🔃
        </button>
      </h5>
    </div>
    <div id="collapseEight" class="collapse" aria-labelledby="headingEight" data-parent="#accordion">
      <div class="card-body">

          <?php
            foreach($carouselitems_values as $value){


              echo '<form class="" id="carouselFormUpdate'.$value['id'].'" action="./CRUDControls/updateCarouselItem.php" method="post" enctype="multipart/form-data">';
              echo '<input type="hidden" id="arId-update" name="id" value="'.$value['id'].'">';
              echo '<input type="text" id="arTitle-update" name="title" value="'.$value['title'].'">
              <br><br>
              <textarea name="description" class="description-form" id="arDesc-update" form="carouselFormUpdate'.$value['id'].'" placeholder="Description" >'.$value['description'].'</textarea>
              <br><br>
              <input type="text" id="arLink-update" name="link" value="'.$value['link'].'">
              <br><br>
              <input type="text" id="arImageName-update" name="imageName" value="'.$value['imageName'].'">
              <br><br>
              <input type="submit" class="btn btn-primary" name="update" value="Update"><br><br>';

              echo "</form><hr>";
            }

            ?>


      </div>
    </div>
  </div>


    <div class="card bg-dark text-light">
      <div class="card-header" id="headingNine">
        <h5 class="mb-0">
          <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
            Update Module Item 🔃
          </button>
        </h5>
      </div>
      <div id="collapseNine" class="collapse" aria-labelledby="headingNine" data-parent="#accordion">
        <div class="card-body">

            <?php
              foreach($moduleitems_values as $value){


                echo '<form class="" id="moduleFormUpdate'.$value['id'].'" action="./CRUDControls/updateModuleItem.php" method="post" enctype="multipart/form-data">';
                echo '<input type="hidden" id="arId-update" name="id" value="'.$value['id'].'">';
                echo '<input type="text" id="arTitle-update" name="title" value="'.$value['title'].'">
                <br><br>
                <input type="text" id="arLinkAll-update" name="linkAll" value="'.$value['linkAll'].'">
                <br><br>
                <input type="text" id="arLinkEx-update" name="linkEx" value="'.$value['linkEx'].'">
                <br><br>
                <input type="text" id="arLinkSlides-update" name="linkSlides" value="'.$value['linkSlides'].'">
                <br><br>
                <input type="text" id="arLinkOther-update" name="linkOther" value="'.$value['linkOther'].'">
                <br><br>
                <input type="submit" class="btn btn-primary" name="update" value="Update"><br><br>';

                echo "</form><hr>";
              }

              ?>


        </div>
      </div>
    </div>

    <div class="card bg-dark text-light">
      <div class="card-header" id="headingSeven">
        <h5 class="mb-0">
          <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
            Update Additional Resources Item 🔃
          </button>
        </h5>
      </div>
      <div id="collapseSeven" class="collapse" aria-labelledby="headingSeven" data-parent="#accordion">
        <div class="card-body">

            <?php
              foreach($additionalinfo_values as $value){


                echo '<form class="" id="additionalResourceFormUpdate'.$value['id'].'" action="./CRUDControls/updateAdditionalResourceItem.php" method="post" enctype="multipart/form-data">';
                echo '<input type="hidden" id="arId-update" name="id" value="'.$value['id'].'">';
                echo '<input type="text" id="arTitle-update" name="title" value="'.$value['title'].'">
                <br><br>
                <textarea name="description" class="description-form"  form="additionalResourceFormUpdate'.$value['id'].'" placeholder="Description" >'.$value['description'].'</textarea>
                <br><br>
                <input type="text" id="arLink-update" name="link" value="'.$value['link'].'">
                <br><br>
                <input type="text" id="arImageName-update" name="imageName" value="'.$value['imageName'].'">
                <br><br>
                <input type="submit" class="btn btn-primary" name="update" value="Update"><br><br>';

                echo "</form><hr>";
              }

              ?>


        </div>
      </div>
    </div>

</div>



    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="SaveModuleProgress.js" charset="utf-8"></script>
    <script src="addLinkTags.js" charset="utf-8"></script>
    <script src="loginForm.js" charset="utf-8"></script>
    <script src="updateSectionButton.js" charset="utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>


</html>
