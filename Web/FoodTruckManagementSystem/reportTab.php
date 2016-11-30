
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

.tab-color {
	color: #099595;
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
	height: 160px; // <-- Select the height of the body width : 100%;
	position: absolute;
}
</style>

</head>
<body>
	<div class="container">
		<h2>
			<strong style="color: #808080">Food Truck Management System</strong><img
				style="width: 15%; height: auto;" src="img/logo.png">
		</h2>
		<ul class="nav nav-tabs">
			<li><a href="index.php"><h4 class="tab-color">Home</h4></a></li>
			<li><a href="inventoryTab.php"><h4 class="tab-color">Inventory</h4></a></li>
			<li><a href="staffTab.php"><h4 class="tab-color">Staff</h4></a></li>
			<li class="active"><a href="reportTab.php"><h4 class="tab-color">Report</h4></a></li>
		</ul>
		<br> <br>

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
				<div class="col-lg-5 " style="background-color: #BCB7C1;">
					<h2 style="text-align: center;">Staff List</h2>
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
									echo "<td>" . ucfirst ( $staff->getName () ) . "</td>";
									echo "<td>" . ucfirst ( $staff->getRole () ) . "</td>";
									echo "</tr>";
									$i = $i + 1;
								}
								?>
        </tbody>
					</table>
				</div>
				<div class="col-lg-2"></div>
				<div class="col-lg-5" style="background-color: lightcyan;">
					<h2 style="text-align: center;">Popularity List</h2>
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
										foreach ( $m->getMenus () as $menu ) {
											$rank [$menu->getName ()] = $menu->getPopularityCounter ();
										}
										arsort ( $rank );
										foreach ( $rank as $itemName => $itemQuantity ) {
											echo "<tr> <th scope='row' >" . $i;
											echo "</th>";
											echo "<td>" . ucfirst ( $itemName ) . "</td>";
											echo "<td>" . $itemQuantity . "</td>";
											echo "</tr>";
											$i = $i + 1;
										}
										?>
            </tbody>
					</table>
				</div>
			</div>
		</div>
		<br> <br> <br> <br>
		<div class="form-group">
			<div class="row">
				<div class="col-lg-5" style="background-color: lightgray;">
					<h2 style="text-align: center;">Supply List</h2>
					<br>
					<table class="table ttable table-hover table-inverse ">
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
															echo "<td>" . ucfirst ( $supply->getName () ) . "</td>";
															echo "<td>" . $supply->getQuantity () . "</td>";
															echo "<td>" . ucfirst ( $supply->getUnit () ) . "</td>";
															echo "</tr>";
															$i = $i + 1;
														}
														?>
            </tbody>
					</table>
				</div>

				<div class="col-lg-2"></div>
				<div class="col-lg-5" style="background-color: lavenderblush;">
					<h2 style="text-align: center;">Equipment List</h2>
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
												echo "<td>" . ucfirst ( $equipment->getName () ) . "</td>";
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