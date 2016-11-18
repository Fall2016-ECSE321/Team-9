<?php
require_once 'controller/Controller.php';

session_start();

$c = new Controller();

$val = $_POST["staff_name"];
$val1 = $_POST["staff_role"];
echo "Staff name: ".$val."and Role: ".$val1 ;

try{	

	if (isset($_POST['staff_name'])){ //name
		$staffName = $_POST['staff_name'];
	}
	if (isset($_POST['stuff_role'])){ //role
		$staffRole = $_POST['staff_role'];
	}

	if (isset($_POST['addStaffMember'])) { // check the function name
		$c->createStaffMember($staffName, $staffRole);
		$_SESSION ["successStaff"] = "Successfully add ".$staffName." to staff's list!";
	}
	elseif (isset($_POST['removeStaffMember'])){ //check the function name
		$c->removeStaffMember($staffName, $staffRole);
		$_SESSION ["successStaff"] = "Successfully remove ".$staffName." to staff's list!";
	}
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
