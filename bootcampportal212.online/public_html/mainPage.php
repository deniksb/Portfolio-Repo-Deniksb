<!doctype html>
<html lang="en">
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
    <?php require "connect.php";?>
    <!-- navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <a class="navbar-brand text-primary" href="#">Bootcamp Portal</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home🏠<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#modules-section">Modules☕</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#additional-section">Additional Resources📖</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#communication-section">Communication💬</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="https://neetcode.io/" target="_blank">NeetCode🧠</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="https://trading212uk.e-learningportal.com/" target="_blank">Learning Portal🧩</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="https://timesheet.trading212.int/" target="_blank">Timesheet💼</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="https://sites.google.com/a/trading212.com/company-structure/home" target="_blank">Company Structure📈</a>
      </li>
    </ul>

    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
      Admin
    </button>

  </div>
</nav>

<!-- carousel -->
<div id="carousel" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner" >
    <?php
    $index = 0;
      foreach(array_reverse($carouselitems_values) as $value){
        $addToDisplay = "";
        if($index == 0){
            $addToDisplay = "active";
            $index = 1;
        }

        echo '<div class="carousel-item '.$addToDisplay.'">';
        echo '<img class="crsl-image d-block w-100" src="img\\'.$value['imageName'].'" alt="Third slide">';
        echo '<div class="carousel-caption d-none d-md-block">';
        echo '<h5>'.$value['title'].'</h5>';
        echo '<a href="'.$value['link'].' target="_blank"> <p>'.$value['description'].'</p></a>';
        echo '</div>';
        echo '</div>';

      }
      ?>
  </div>

  <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
  <span class="sr-only">Previous</span>
</a>
<a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
  <span class="carousel-control-next-icon" aria-hidden="true"></span>
  <span class="sr-only">Next</span>
</a>
</div>

<br>

<!-- MODULES SECTION -->
<div id = "modules-section" class="section">
  <h1 class="text-center">Modules</h1>
  <p class="text-center">In this section you can find all the information you need regarding the topics and excercises within the modules of the bootcamp.</p>

  <table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Exercises</th>
      <th scope="col">Slides</th>
      <th scope="col">Other</th>
      <th scope="col">Completed</th>
    </tr>
  </thead>
  <tbody>
    <?php
      foreach($moduleitems_values as $value){
        echo '<tr><th scope="row">'.$value['id'].'</th>';
        echo '<td><a href="'.$value['linkAll'].'" target="_blank">'.$value['title'].'</a></td>';
        echo '<td><a href="'.$value['linkEx'].'" target="_blank" >Here</a></td>';
        echo '<td> <a href="'.$value['linkSlides'].'" target="_blank">Here</a></td>';
        echo '<td> <a href="'.$value['linkOther'].'" target="_blank">Here</a></td>';
        echo '<td><input class="form-check-input" type="checkbox" value="" onchange="saveCheckboxValue(this)" id="module'.$value['id'].'-checkbox"></td>';
        echo '</tr>';
      }
      ?>


    <!-- <tr>
      <th scope="row">1</th>
      <td><a href="https://drive.google.com/drive/folders/1R88D_NGm6CBG1_V5reZyWsnKTptYsME3" target="_blank">Intro to programming</a></td>
      <td><a href="https://drive.google.com/drive/folders/1zemYqaG4-T7yAIGu1W8sXDWMsMMzPc8Z" target="_blank" >Here</a></td>
      <td> <a href="https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS" target="_blank">Here</a></td>
      <td> <a href="https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS" target="_blank">Here</a></td>
      <td><input class="form-check-input" type="checkbox" value="" onchange="saveCheckboxValue(this)" id="module1-checkbox"></td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td><a href="https://drive.google.com/drive/folders/1wt26zClrr-GKfT_gjiTo4nLu6EIIiy5w" target="_blank">OOP</a></td>
      <td><a href="https://drive.google.com/drive/folders/1wt26zClrr-GKfT_gjiTo4nLu6EIIiy5w" target="_blank">Here</a></td>
      <td><a href="#">Here</a></td>
      <td> <a href="https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS" target="_blank">Here</a></td>
      <td><input class="form-check-input" type="checkbox" onchange="saveCheckboxValue(this)" id="module2-checkbox"></td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td><a href="#" target="_blank">Databases</a></td>
      <td><a href="#" target="_blank">Here</a></td>
      <td><a href="#">Here</a></td>
      <td> <a href="https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS" target="_blank">Here</a></td>
      <td><input class="form-check-input" type="checkbox" value="" onchange="saveCheckboxValue(this)" id="module3-checkbox"></td>
    </tr>
    <tr>
      <th scope="row">4</th>
      <td><a href="#" target="_blank">Web</a></td>
      <td><a href="#" target="_blank" >Here</a></td>
      <td><a href="#">Here</a></td>
      <td> <a href="https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS" target="_blank">Here</a></td>
      <td><input class="form-check-input" type="checkbox" value="" onchange="saveCheckboxValue(this)" id="module4-checkbox"></td>
    </tr>
    <tr>
      <th scope="row">5</th>
      <td><a href="#" target="_blank">Advanced Topics</a></td>
      <td><a href="#" target="_blank">Here</a></td>
      <td><a href="#">Here</a></td>
      <td> <a href="https://drive.google.com/drive/folders/1VjdaEO62D1p_OvtU_x8DT0oCKHHtBLeS" target="_blank">Here</a></td>
      <td><input class="form-check-input" type="checkbox" value="" onchange="saveCheckboxValue(this)" id="module5-checkbox"></td>
    </tr> -->
  </tbody>
</table>
</div>

<br>
<br>

<!-- ADDITIONAL RESOURCES SECTION -->
<div id = "additional-section" class="section">
<h1 class="text-center">Additional Resources</h1>
<p class="text-center">Here you can find extra knowledge you can benefit from in and beyond the bootcamp.</p>

<?php
$index = 0;
  foreach(array_reverse($additionalinfo_values) as $value){
    echo '<div class="row g-0 w-50 bg-dark position-relative mx-auto w-50" id="additional-info-element">';
    echo '<div class="col-md-6 mb-md-0 p-md-4">';
    echo '<a href="'.$value['link'].'" target="_blank"><img src="img\\'.$value['imageName'].'" class="w-100 h-100" alt="..."></a>';
    echo '</div>';
    echo '<div class="col-md-6 p-4 ps-md-0">';
    echo '<h5 class="mt-0">'.$value['title'].'</h5>';
    echo '<p class="w-100" data-toggle="modal" data-target="#modal-'.$index.'"><a href="" onclick="return false;">'.'Read more'.'</a></p>';
    echo '<a href="'.$value['link'].'" target="_blank">Go learn</a>';
    echo '</div>';
    echo '</div>';

$index += 1;
  }
  ?>

<br>
<br>
</div>
<!-- COMMUNICATION SECTION -->
<div id = "communication-section" class="section">
<h1 class="text-center">Communication</h1>
<p class="text-center">Here you can find everything needed for internal communication during the bootcamp.</p>

<div class="row" id="communication-cards">

  <!-- FIRST CARD -->
  <div class="card mx-auto bg-dark text-light col-xs-6" style="width: 18rem;">
    <img src="img\gitlab-logo.png" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">GitLab</h5>
      <p class="card-text">Here you can store all the exercises and projects during the bootcamp.</p>
      <p class="card-text">
        <a href="https://git.trading212.io/" class="stretched-link text-danger" style="position: relative;" target="_blank">https://git.trading212.io/</a>
      </p>
    </div>
  </div>

  <!--Trading212 ICON -->
  <div class="mx-auto col-xs-6">
      <a href="https://www.trading212.com/" target="_blank" class="mx-auto mt-5"><img src="img\trading-icon.png" class="img-fluid h-50 border border-primary" alt="Responsive image"></a>
  </div>

  <!-- LAST CARD -->
  <div class="card mx-auto bg-dark text-light col-xs-6" style="width: 18rem;">
    <img src="img\slack-logo.png" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">Slack</h5>
      <p class="card-text">Here you can chat with colleagues and receive important information.</p>
      <p class="card-text">
        <a href="https://trading212.slack.com/" class="stretched-link text-danger" style="position: relative;" target="_blank">https://trading212.slack.com/</a>
      </p>

    </div>
  </div>

</div>

</div>

<footer id="sticky-footer" class="flex-shrink-0 py-4 bg-dark text-white-50">
  <div class="container text-center">
    <small>Trading212 Bootcamp Portal 2022</small>
    <br>
    <small>Built by Denislav Berberov 😉</small>
  </div>
</footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="SaveModuleProgress.js" charset="utf-8"></script>
    <script src="loginForm.js" charset="utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>

  <!-- login form -->
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title text-dark" id="exampleModalLabel">Admin Login</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form class="" action="login.php" method="post">
              <input type="text" name="username" placeholder="Username">
              <br>
              <br>
              <input type="password" name="password" placeholder="Password">
              <div class="modal-footer">
                  <input type="submit" class="btn btn-primary" name="" value="Login">
              </div>
          </form>
        </div>

      </div>
    </div>
  </div>

  <?php
  $index = 0;
    foreach(array_reverse($additionalinfo_values) as $value){
echo '<div class="modal fade " id="modal-'.$index.'" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content bg-dark text-light">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">'.$value['title'].'</h5>
        <button type="button" class="close text-light" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body "><p>'.$value['description'].'</p></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>';
$index += 1;
}
      ?>


</html>
