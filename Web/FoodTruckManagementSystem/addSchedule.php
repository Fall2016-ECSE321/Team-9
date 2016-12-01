<?php
require_once 'controller/Controller.php';
session_start ();

$c = new Controller ();
$staffStartTime = array ();
$staffEndTime = array ();
try {
	$staffName = NULL;
	if (isset ( $_POST ['staffMemberSpinner'] )) {
		$staffName = $_POST ['staffMemberSpinner'];
	}
	
	// Start time and End time for 7 days
	// Monday
	if (isset ( $_POST ['start_time1'] )) {
		$staffStartTime [0] = $_POST ['start_time1'];
	}
	if (isset ( $_POST ['end_time1'] )) {
		$staffEndTime [0] = $_POST ['end_time1'];
	}
	// Tuesday
	if (isset ( $_POST ['start_time2'] )) {
		$staffStartTime [1] = $_POST ['start_time2'];
	}
	if (isset ( $_POST ['end_time2'] )) {
		$staffEndTime [1] = $_POST ['end_time2'];
	}
	// Wednesday
	if (isset ( $_POST ['start_time3'] )) {
		$staffStartTime [2] = $_POST ['start_time3'];
	}
	if (isset ( $_POST ['end_time3'] )) {
		$staffEndTime [2] = $_POST ['end_time3'];
	}
	// Thurday
	if (isset ( $_POST ['start_time4'] )) {
		$staffStartTime [3] = $_POST ['start_time4'];
	}
	if (isset ( $_POST ['end_time4'] )) {
		$staffEndTime [3] = $_POST ['end_time4'];
	}
	// Friday
	if (isset ( $_POST ['start_time5'] )) {
		$staffStartTime [4] = $_POST ['start_time5'];
	}
	if (isset ( $_POST ['end_time5'] )) {
		$staffEndTime [4] = $_POST ['end_time5'];
	}
	
	// Saturday
	if (isset ( $_POST ['start_time6'] )) {
		$staffStartTime [5] = $_POST ['start_time6'];
	}
	if (isset ( $_POST ['end_time6'] )) {
		$staffEndTime [5] = $_POST ['end_time6'];
	}
	
	// Sunday
	if (isset ( $_POST ['start_time7'] )) {
		$staffStartTime [6] = $_POST ['start_time7'];
	}
	if (isset ( $_POST ['end_time7'] )) {
		$staffEndTime [6] = $_POST ['end_time7'];
	}
	
	if (isset ( $_POST ['editStaffSchedule'] )) {
		$c->addTimeStaffMember ( $staffName, $staffStartTime, $staffEndTime );
		$_SESSION ["successSchedule"] = "Successfully Saved " . ucfirst ( $staffName ) . "'s' new weekly schedule!";
		
		$_SESSION ["errorSchedule"] = "";
	} elseif (isset ( $_POST ['removeStaff'] )) {
		$c->removeStaffMember ( $staffName );
		$_SESSION ["successSchedule"] = "Successfully Removed " . ucfirst ( $staffName ) . " from staff list!";
	}
	$_SESSION ["errorSchedule"] = "";
} catch ( Exception $e ) {
	$_SESSION ["errorSchedule"] = $e->getMessage ();
	$_SESSION ["successSchedule"] = "";
}

?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh"
	content="0; url=/FoodTruckManagementSystem/staffTab.php" />
</head>
</html>