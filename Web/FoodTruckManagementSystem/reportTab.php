
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Food Truck Management System</title>

<style>
  .btn-default {
    background: #000;
    color: #fff;
  }
  
  .btn-default:hover {
    background: #fff;
    color: #000;
  }
  .error {
      color: #D8000C;
  }
  .success {
      color: #4F8A10;
  }
</style>
</head>

<body > 
  <div class="container">
    <h3 ><strong style="color:#808080">Food Truck Management System</strong><img style="width: 10%;
    height: auto;" src="img/logo.png"></h3>
  <ul class="nav nav-tabs">
    <li><a href="index.php">Home</a></li>
    <li><a href="inventoryTab.php">Inventory</a></li>
    <li><a href="staffTab.php">Staff</a></li>
    <li class="active"><a href="reportTab.php">Report</a></li>
  </ul>
  <br>
  <p><strong>Note:</strong> This page is to display four differents reports: Popular menu, Staff member list, Equipiment list and Supply list</p>

</div>

	<?php
	
	require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
	
?>

		

</body>
</html>