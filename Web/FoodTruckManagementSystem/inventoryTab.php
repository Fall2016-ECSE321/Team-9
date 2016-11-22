<?php
session_start ();
?>
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
	    <li class="active"><a href="inventoryTab.php">Inventory</a></li>
	    <li><a href="staffTab.php">Staff</a></li>
	    <li><a href="reportTab.php">Report</a></li>
	  </ul>

		  
		<?php
		require_once 'persistence/PersistenceFoodTruckManagementSystem.php';

		
		// Retreive the data from the model
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		?>
		<br><br>

		<h4 style="color:#778899"> <strong>Equipment Item</strong></h4>
		<!-- #c0c0c0 -->
		<div style="background-color:#BCB7C1;color:black;padding:20px;"">
            <!-- <h4> <strong>Equipment Item</strong></h4> -->
       
			<form class="form-inline" action="addRemoveEquipment.php" method="post">
				
				<span class="error input-sm">
					<?php
						if (isset ( $_SESSION ['errorEquipment'] ) && ! empty ( $_SESSION ['errorEquipment'] )) {
							echo " * " . $_SESSION ["errorEquipment"];
							session_unset($_SESSION ["errorEquipment"]);
						}
					?>
				<span class="success input-md">
					<?php
						
						if (isset ( $_SESSION ['successEquipment'] ) && ! empty ( $_SESSION ['successEquipment'] )) {
						echo $_SESSION ["successEquipment"];
						session_unset($_SESSION ["successEquipment"]);
						}	
					?>
				</span> 
				</span>
				<br>
				<div class="form-group">
					&nbsp&nbsp <input class="form-control input-sm" type="text"
						name="equipment_name" placeholder="Enter Equipment Name" />
				</div>
				&nbsp&nbsp
				<div class="form-group">

					<input class="form-control input-sm" style="width: 100px"
						type="number" name="equipment_quantity" placeholder="0" />

				</div>
				<br><br>&nbsp&nbsp
				<button type="submit" name="addEquipment" class="btn btn-default">Add</button>
				<button type="submit" name="removeEquipment" class="btn btn-default">Remove</button>
			</form>
		</div>

		<br><br>

		<h4 style="color:#778899">  <strong>Supply Item</strong></h4>
		<div style="background-color:#BCB7C1;color:black;padding:20px;"">
            
			<form class="form-inline" action="addRemoveSupply.php" method="post">
				<span class="error input-sm">
					<?php
						if (isset ( $_SESSION ['errorSupply'] ) && ! empty ( $_SESSION ['errorSupply'] )) {
							echo " * " . $_SESSION ["errorSupply"];
							session_unset($_SESSION ["errorSupply"]);
						}
					?>
				 
				<span class="success input-md">
					<?php
						
						if (isset ( $_SESSION ['successSupply'] ) && ! empty ( $_SESSION ['successSupply'] )) {
						echo $_SESSION ["successSupply"];
						session_unset($_SESSION ["successSupply"]);
						}	
					?>
				</span>
				</span>
				<br>
				<div class="form-group">
				&nbsp&nbsp 
					<input  class="form-control input-sm" type="text"
						name="supply_name" placeholder=" Enter Supply Name" />
				</div>
				&nbsp&nbsp
				<div class="form-group">

					<input class="form-control input-sm " style="width: 100px"
						type="number" name="supply_quantity" placeholder="0" />
				</div>
				&nbsp&nbsp
				<div class="form-group">

					<input class="form-control input-sm " style="width: 100px"
						type="text" name="supply_unit" placeholder="kg" />
				</div>

				<br><br>&nbsp&nbsp
				<button type="submit" name="addSupply" class="btn btn-default">Add</button>
				<button type="submit" name="removeSupply" class="btn btn-default">Remove</button>
			</form>
		</div>
	</div>



</body>
</html>