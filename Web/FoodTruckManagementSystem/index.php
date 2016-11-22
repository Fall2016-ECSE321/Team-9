
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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

.error {
	color: #D8000C;
}

.success {
	color: #4F8A10;
}
</style>
</head>
<body>
	<div class="container">
		<h3>
			<strong style="color: #808080">Food Truck Management System</strong><img
				style="width: 10%; height: auto;" src="img/logo.png">
		</h3>
		<ul class="nav nav-tabs">
			<li class="active"><a href="index.php">Home</a></li>
			<li><a href="inventoryTab.php">Inventory</a></li>
			<li><a href="staffTab.php">Staff</a></li>
			<li><a href="reportTab.php">Report</a></li>
		</ul>
		<br>
  

  <?php
		require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
		require_once 'model/Manager.php';
		require_once 'model/MenuItem.php';
		
		session_start ();
		// Retreive the data from the model
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		
		?>
    <br>
		<br>

		<h4 style="color: #778899">
			<strong>Menu Item</strong>
		</h4>
		<!-- #c0c0c0 -->
		<div style="background-color: #BCB7C1; color: black; padding: 20px;"">
			<!-- <h4> <strong>Equipment Item</strong></h4> -->

			<form class="form-inline" action="addRemoveMenuItem.php"
				method="post">

				<span class="error input-sm">
          <?php
										if (isset ( $_SESSION ['errorItem'] ) && ! empty ( $_SESSION ['errorItem'] )) {
											echo " * " . $_SESSION ["errorItem"];
											session_unset ( $_SESSION ["errorItem"] );
										}
										?>
        <span class="success input-md">
          <?php
										
										if (isset ( $_SESSION ['successItem'] ) && ! empty ( $_SESSION ['successItem'] )) {
											echo $_SESSION ["successItem"];
											session_unset ( $_SESSION ["successItem"] );
										}
										?>
        </span>
				</span> <br>
				<div class="form-group">
					&nbsp&nbsp <input class="form-control input-sm" type="text"
						name="menuItem_name" placeholder="Enter Item Name" />
				</div>
				&nbsp&nbsp
				<div class="form-group">

					<input class="form-control input-sm" style="width: 110px"
						type="number" name="menuItem_price" placeholder="Enter Price($)" />

				</div>
				<br>
				<br>&nbsp&nbsp
				<button type="submit" name="addMenuItem" class="btn btn-default">Add
					To Menu List</button>
				<button type="submit" name="removeMenuItem" class="btn btn-default">Remove
					Item</button>
			</form>
		</div>

		<br>
		<br>

		<h4 style="color: #778899">
			<strong>Add Order</strong>
		</h4>
		<div style="background-color: #BCB7C1; color: black; padding: 20px;"">
			<form class="form-inline" action="addOrder.php"
				method="post">

				<span class="error input-sm">
          <?php
										if (isset ( $_SESSION ['errorOrder'] ) && ! empty ( $_SESSION ['errorOrder'] )) {
											echo " * " . $_SESSION ["errorOrder"];
											session_unset ( $_SESSION ["errorOrder"] );
										}
										?>
        <span class="success input-md">
          <?php
										
										if (isset ( $_SESSION ['successOrder'] ) && ! empty ( $_SESSION ['successOrder'] )) {
											echo $_SESSION ["successOrder"];
											session_unset ( $_SESSION ["successOrder"] );
										}
										?>
        </span>
				</span> <br>
				<div class="form-group">
					<!-- Change equipment to staff name later on, this is just a place holder fo -->
        <?php
								echo "<p><strong>&nbsp&nbsp&nbsp Select Item to Order: </strong><select class='form-control input-md' name='itemSpinner'>";
								foreach ( $m->getMenus () as $order ) {
									echo "<option>" . $order->getName () . "</option>";
								}
								echo "</select>";
								echo "&nbsp&nbsp <input class='form-control input-md' name='order_quantity' style='width: 120px' placeholder='Enter quantity'>";
								?>

        <br>
					<br>&nbsp&nbsp
					<button type="submit" name="menuItemOrder" class="btn btn-default">Order</button>
			
			</form>
		</div>
	</div>


</body>
</html>