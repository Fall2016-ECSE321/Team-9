<?php
require_once 'controller/Controller.php';

session_start();

$c = new Controller();
try{
	
	if (isset($_POST['staff_name'])){ //name
		$staffName = $_POST['staff_name'];
	}
	if (isset($_POST['staff_role'])){ //role
		$staffRole = $_POST['staff_role'];
	}

	if (isset($_POST['staff_schedule'])){ //schedule
		$staffSchedule = $_POST['staff_schedule'];
	}

	if (isset($_POST['addStaffMember'])) { // check the function name
		$c->createStaffMember($staffName, $staffRole);
	}
	elseif (isset($_POST['removeStaffMember'])){ //check the function name
		$c->removeStaffMember($staffName, $staffRole);
	}
	elseif (isset($_POST['editStaffSchedule'])){ //check the function name
		$c->removeStaffMember($staffName, $staffSchedule);
	}
	$_SESSION["errorStaff"] = "";
	
} catch (Exception $e){
	$_SESSION["errorStaff"] = $e->getMessage();
}
?>

<!DOCTYPE html> <html>
	<head>
	<meta http-equiv="refresh" content="0; url=/FoodTruckManagementSystem/" />
	</head>
</html>
