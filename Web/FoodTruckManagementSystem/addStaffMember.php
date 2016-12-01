<?php
require_once 'controller/Controller.php';

session_start ();

$c = new Controller ();

try {
	
	if (isset ( $_POST ['staff_name'] )) { // name
		$staffName = $_POST ['staff_name'];
	}
	if (isset ( $_POST ['staff_role'] )) { // role
		$staffRole = $_POST ['staff_role'];
	}
	
	$c->createStaffMember ( $staffName, $staffRole );
	$_SESSION ["successStaff"] = "Successfully Added " . ucfirst ( $staffName ) . " to staff's list!";
	
	$_SESSION ["errorStaff"] = "";
} catch ( Exception $e ) {
	$_SESSION ["errorStaff"] = $e->getMessage ();
	$_SESSION ["successStaff"] = "";
}

?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh"
	content="0; url=/FoodTruckManagementSystem/staffTab.php" />
</head>
</html>
