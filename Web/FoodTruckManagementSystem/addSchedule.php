<?php
require_once 'controller/Controller.php'; 
session_start();

$c = new Controller();
try {
$staffName = NULL;
if (isset($_POST['staffMemberSpinner'])) {
$staffName= $_POST['staffMemberSpinner'];
}
// Start time and End time for 7 days 

// Monday
if (isset($_POST['start_time1'])){ //start time 
	$staffStartTime1 = $_POST['start_time1'];
}
if (isset($_POST['end_time1'])){ //end time
	$$staffEndTime1 = $_POST['end_time1'];
}
// Tuesday
if (isset($_POST['start_time2'])){ //start time 
	$staffStartTime2 = $_POST['start_time2'];
}
if (isset($_POST['end_time2'])){ //end time
	$$staffEndTime2 = $_POST['end_time2'];
}
//	Wednesday
if (isset($_POST['start_time3'])){ //start time 
	$staffStartTime3 = $_POST['start_time3'];
}
if (isset($_POST['end_time3'])){ //end time
	$staffEndTime3 = $_POST['end_time3'];
}
// Thurday
if (isset($_POST['start_time4'])){ //start time 
	$staffEndTime4 = $_POST['start_time4'];
}
if (isset($_POST['end_time4'])){ //end time
	$staffEndTime4 = $_POST['end_time4'];
}
//	Friday
if (isset($_POST['start_time5'])){ //start time 
	$staffStartTime5 = $_POST['start_time5'];
}
if (isset($_POST['end_time5'])){ //end time
	$staffEndTime5 = $_POST['end_time5'];
}

// Saturday
if (isset($_POST['start_time6'])){ //start time 
	$staffStartTime6 = $_POST['start_time6'];
}
if (isset($_POST['end_time6'])){ //end time
	$staffEndTime6 = $_POST['end_time6'];
}

// Sunday
if (isset($_POST['start_time7'])){ //start time 
	$staffStartTime7 = $_POST['start_time7'];
}
if (isset($_POST['end_time7'])){ //end time
	$staffEndTime7 = $_POST['end_time7'];
}

	
$c->addStaffSchedule($staffName, $staffStartTime1, $staffEndTime1,$staffStartTime2, $staffEndTime2,$staffStartTime3, $staffEndTime3,$staffStartTime4, $staffEndTime4,$staffStartTime5, $staffEndTime5,$staffStartTime6, $staffEndTime6,$staffStartTime7, $staffEndTime7);
$_SESSION ["successStaff"] = "Successfully save".$staffName."'s' new weekly schedule!";

$_SESSION["errorStaff"] = "";
	
} catch (Exception $e){ 
	$_SESSION["errorStaff"] = $e->getMessage();
	$_SESSION ["successStaff"] = "";
}

?>

<!DOCTYPE html> <html>
	<head>
	<meta http-equiv="refresh" content="0; url=/FoodTruckManagementSystem/staffTab.php" />
	</head>
</html>