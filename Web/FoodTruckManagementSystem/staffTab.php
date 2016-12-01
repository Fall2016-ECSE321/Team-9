
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
	background: #444344;
	color: #fff;
}

.btn-default:hover {
	background: #fff;
	color: #444344;
}

.error {
	color: #D8000C;
}

.success {
	color: #4F8A10;
}

.positon_time {
	position: relative;
	left: 60px;
}

.tab-color {
	color: #099595;
}

.align-schedule {
	display: inline-block;
	width: 200px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>
			<strong style="color: #808080">Food Truck Management System</strong><img
				style="width: 15%; height: auto;" src="img/logo.png">
		</h2>
		<ul class="nav nav-tabs ">
			<li><a href="index.php"><h4 class="tab-color">Home</h4></a></li>
			<li><a href="inventoryTab.php"><h4 class="tab-color">Inventory</h4></a></li>
			<li class="active"><a href="staffTab.php"><h4 class="tab-color">Staff</h4></a></li>
			<li><a href="reportTab.php"><h4 class="tab-color">Report</h4></a></li>
		</ul>
		<br>

    <?php
				require_once 'persistence/PersistenceFoodTruckManagementSystem.php';
				require_once 'model/StaffMember.php';
				require_once 'model/Manager.php';
				
				session_start ();
				// Retreive the data from the model
				$pm = new PersistenceFoodTruckManagementSystem ();
				$m = $pm->loadDataFromStore ();
				
				?>
    <br>

		<h3 style="color: #778899">
			<strong>Add New Staff</strong>
		</h3>
		<div style="background-color: #BCB7C1; color: black; padding: 20px;"">
			
<!-- 			Form to add employee -->
			<form class="form-inline" action="addStaffMember.php" method="post">

				<span class="error input-lg">
          <?php
										if (isset ( $_SESSION ['errorStaff'] ) && ! empty ( $_SESSION ['errorStaff'] )) {
											echo " * " . $_SESSION ["errorStaff"];
											session_unset ( $_SESSION ["errorStaff"] );
										}
										?>
        <span class="success input-md">
          <?php
										
										if (isset ( $_SESSION ['successStaff'] ) && ! empty ( $_SESSION ['successStaff'] )) {
											echo $_SESSION ["successStaff"];
											session_unset ( $_SESSION ["successStaff"] );
										}
										?>
        </span>
				</span> <br>
				<div class="form-group">
					&nbsp&nbsp <input class="form-control input-lg" type="text"
						name="staff_name" placeholder="Enter First Last Name" />
				</div>
				&nbsp&nbsp
				<div class="form-group">
					<label for="select_1"><h4>Select Role:</h4></label> <select
						class="form-control input-lg" id="select_1" name="staff_role">
						<option value="cashier">Cashier</option>
						<option value="chef">Chef</option>
						<option value="manager">Manager</option>
						<option value="clerk">Inventory Clerk</option>
					</select>
				</div>
				<br> <br>&nbsp&nbsp
				<button type="submit" name="addStaffMember"
					class="btn btn-default btn-lg">Add</button>
			</form>
		</div>
		<br>
		<h3 style="color: #778899">
			<strong>Register Staff's Weekly Schedule</strong>
		</h3>
		<div
			style="max-height: 450px; overflow-y: scroll; background-color: #BCB7C1; color: black; padding: 20px;"">
			
<!-- 			Form to remove Employee and add employee weekly schedule -->
			<form class="form-inline" action="addSchedule.php" method="post">

				<span class="error input-lg">
          <?php
										if (isset ( $_SESSION ['errorSchedule'] ) && ! empty ( $_SESSION ['errorSchedule'] )) {
											echo " * " . $_SESSION ["errorSchedule"];
											session_unset ( $_SESSION ["errorSchedule"] );
										}
										?>
        <span class="success input-lg">
          <?php
										
										if (isset ( $_SESSION ['successSchedule'] ) && ! empty ( $_SESSION ['successSchedule'] )) {
											echo $_SESSION ["successSchedule"];
											session_unset ( $_SESSION ["successSchedule"] );
										}
										?>
        </span>
				</span> <br>


				<div class="form-group">
					<!-- Change equipment to staff name later on, this is just a place holder fo -->
        <?php
								echo "<p><strong><h4>Select Staff Name: </strong><select class='form-control input-lg' name='staffMemberSpinner'>";
								foreach ( $m->getStaffMembers () as $staff ) {
									echo "<option>" . ucfirst ( $staff->getName () ) . "</option>";
								}
								echo "</select>";
								?>
        <button align="right" type="submit" name="removeStaff"
						class="btn btn-default btn-lg">Remove Staff</button>

					<br> <br>
					<dd>
						<label
							style="color: #4E4651; display: inline-block; width: 220px;"><h3>
								<strong>Day</strong>
							</h3></label> <label
							style="color: #4E4651; display: inline-block; width: 150px;"><h3>
								<strong>Start Time</strong>
							</h3></label> <label
							style="color: #4E4651; display: inline-block; width: 220px;"><h3>
								<strong>End Time</strong>
							</h3></label>

					</dd>

					<label class="align-schedule"><h4>Monday:</h4></label> <input
						class="form-control input-lg" type="time" name="start_time1" /> <input
						class="form-control input-lg" type="time" name="end_time1" /> <br>
					<br> <label class="align-schedule"><h4>Tuesday:</h4></label> <input
						class="form-control input-lg" type="time" name="start_time2" /> <input
						class="form-control input-lg" type="time" name="end_time2" /> <br>
					<br> <label class="align-schedule"><h4>Wednesday:</h4></label> <input
						class="form-control input-lg" type="time" name="start_time3" /> <input
						class="form-control input-lg" type="time" name="end_time3" /> <br>
					<br> <label class="align-schedule"><h4>Thursday:</h4></label> <input
						class="form-control input-lg" type="time" name="start_time4" /> <input
						class="form-control input-lg" type="time" name="end_time4" /> <br>
					<br> <label class="align-schedule"><h4>Friday:</h4></label> <input
						class="form-control input-lg" type="time" name="start_time5" /> <input
						class="form-control input-lg" type="time" name="end_time5" /> <br>
					<br> <label class="align-schedule"><h4>Saturday:</h4></label> <input
						class="form-control input-lg" type="time" name="start_time6" /> <input
						class="form-control input-lg" type="time" name="end_time6" /> <br>
					<br> <label class="align-schedule"><h4>Sunday:</h4></label> <input
						class="form-control input-lg" type="time" name="start_time7" /> <input
						class="form-control input-lg" type="time" name="end_time7" /> <br>
					<br>
					<button type="submit" name="editStaffSchedule"
						class="btn btn-default btn-lg">Save</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>