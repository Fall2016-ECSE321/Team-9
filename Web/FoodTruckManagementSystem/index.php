<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
	.error {color : #FF0000}
</style>
</head>
<body> 
	<?php
	require_once 'model/Equipment.php';
	require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
	
	session_start ();
	
	// Retreive the data from the model
	$pm = new PersistenceFoodTruckManagementSystem ();
	$m = $pm->loadDataFromStore ();
	?>
	<form class="form-inline" action="Equipment.php" method="post">
		<br>
		<br>
		<div class="form-group">
			&nbsp&nbsp <input class="form-control input-lg" type="text"
				name="equipment_name" placeholder="Enter Equipment Name" />

		</div>
		&nbsp&nbsp
		<div class="form-group">

			<input class="form-control input-lg " style="width: 100px"
				type="number" name="equipment_quantity" placeholder="0" />

		</div>
		&nbsp&nbsp <span class="error">
			<?php
			if (isset ( $_SESSION ['errorEquipment'] ) && ! empty ( $_SESSION ['errorEquipment'] )) {
				echo " * " . $_SESSION ["errorEquipment"];
			}
			
			?>
			</span> <br>
		<br> &nbsp&nbsp
		<button type="submit" name="addEquipment" class="btn btn-default">Add Equipment</button>
		<button type="submit" name="removeEquipment" class="btn btn-default">Remove Equipment</button>

	</form>


</body>
</html>