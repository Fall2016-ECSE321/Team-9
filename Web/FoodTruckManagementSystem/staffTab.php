<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Food Truck Management System</title>
</head>
<body>

<div class="container">
  <h3>Food Truck Management System</h3>
  <ul class="nav nav-tabs">
    <li class="active"><a href="index.php">Home</a></li>
    <li><a href="inventoryTab.php">Inventory</a></li>
    <li><a href="staffTab.php">Staff</a></li>
    <li><a href="reportTab.php">Report</a></li>
  </ul>
    <br>
    <p><strong>Note:</strong> Display staff member, add new staff, remove existing staff, view and edit the exist stuff schedule</p>

</div>

	<?php
	
	require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
	
?>

		

</body>
</html>