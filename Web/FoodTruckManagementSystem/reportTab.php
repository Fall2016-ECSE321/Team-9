
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

tr {
	width: 100%;
	display: inline-table;
	table-layout: fixed;
}

table {
	height: 200px;
	//
	<--
	Select
	the
	height
	of
	the
	table
	display
	:
	-moz-groupbox;
	//
	Firefox
	Bad
	Effect
}

tbody {
	overflow-y: scroll;
	height: 180px; // <-- Select the height of the body width : 100%;
	position: absolute;
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
			<li><a href="index.php">Home</a></li>
			<li><a href="inventoryTab.php">Inventory</a></li>
			<li><a href="staffTab.php">Staff</a></li>
			<li class="active"><a href="reportTab.php">Report</a></li>
		</ul>
		<br>

    <?php
				require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
				require_once 'model/StaffMember.php';
				require_once 'model/Manager.php';
				require_once 'model/MenuItem.php';
				require_once 'model/Supply.php';
				require_once 'model/Equipment.php';
				
				session_start ();
				
				$pm = new PersistenceFoodTruckManagementSystem ();
				$m = $pm->loadDataFromStore ();
				
				?>
    <br>
		<div class="form-group">
			<div class="row">
				<div class="col-sm-6 " style="background-color: #BCB7C1;">
					<h3 style="text-align: center;">Staff List</h3>
					<br>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Employee Name</th>
								<th>Role</th>
							</tr>
						</thead>
						<tbody>
        <?php
								$i = 1;
								foreach ( $m->getStaffMembers () as $staff ) {
									echo "<tr> <th scope='row' >" . $i;
									echo "</th>";
									echo "<td>" . $staff->getName () . "</td>";
									echo "<td>" . $staff->getRole () . "</td>";
									echo "</tr>";
									$i = $i + 1;
								}
								?>
        </tbody>
					</table>
				</div>
				<div class="col-sm-6" style="background-color: lightcyan;">
					<h3 style="text-align: center;">Popularity List</h3>
					<br>
					<table class="table table table-hover">
						<thead>
							<tr>
								<th>Rank</th>
								<th>Name</th>
								<th>Order Quantity</th>
							</tr>
						</thead>
						<tbody>
        <?php
								$rank = array ();
								$i = 1;
								foreach ($m->getMenus() as $menu) {
								$rank[$menu->getName()] = $menu->getPopularityCounter();
								}
								arsort ( $rank );
								foreach ( $rank as $itemName => $itemQuantity ) {
									echo "<tr> <th scope='row' >" . $i;
									echo "</th>";
									echo "<td>" . $itemName . "</td>";
									echo "<td>" . $itemQuantity . "</td>";
									echo "</tr>";
									$i = $i + 1;
								}
								?>
        </tbody>
					</table>
				</div>

				<div class="col-sm-6" style="background-color: lightgray;">
					<h3 style="text-align: center;">Supply List</h3>
					<br>
					<table class="table ttable table-hover table-inverse">
						<thead>
							<tr>
								<th>#</th>
								<th>Name</th>
								<th>Quantity</th>
								<th>Unit</th>
							</tr>
						</thead>
						<tbody>
        <?php
								$i = 1;
								foreach ( $m->getSupplies () as $supply ) {
									echo "<tr> <th scope='row' >" . $i;
									echo "</th>";
									echo "<td>" . $supply->getName () . "</td>";
									echo "<td>" . $supply->getQuantity () . "</td>";
									echo "<td>" . $supply->getUnit () . "</td>";
									echo "</tr>";
									$i = $i + 1;
								}
								?>
        </tbody>
					</table>
				</div>

				<div class="col-sm-6" style="background-color: lavenderblush;">
					<h3 style="text-align: center;">Equipment List</h3>
					<br>
					<table class="table table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Name</th>
								<th>Quantity</th>
							</tr>
						</thead>
						<tbody>
           <?php
											$i = 1;
											foreach ( $m->getEquipments () as $equipment ) {
												echo "<tr> <th scope='row' >" . $i;
												echo "</th>";
												echo "<td>" . $equipment->getName () . "</td>";
												echo "<td>" . $equipment->getQuantity () . "</td>";
												echo "</tr>";
												$i = $i + 1;
											}
											?>
        </tbody>
					</table>
				</div>
			</div>

		</div>


	</div>

  <?php
		
		require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
		
		?>

    

</body>
</html>