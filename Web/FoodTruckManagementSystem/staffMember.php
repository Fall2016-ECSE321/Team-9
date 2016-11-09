<?php
require_once 'controller/Controller.php';

session_start();

$c = new Controller();
try{
	
	if (isset($_POST['stuff_name'])){ //name
		$stuffName = $_POST['stuff_name'];
	}
	if (isset($_POST['stuff_role'])){ //role
		$stuffRole = $_POST['stuff_role'];
	}

	if (isset($_POST['stuff_schedule'])){ //schedule
		$stuffSchedule = $_POST['stuff_schedule'];
	}

	if (isset($_POST['addStuffMember'])) { // check the function name
		$c->createStuffMember($stuffName, $stuffRole);
	}
	elseif (isset($_POST['removeStuffMember'])){ //check the function name
		$c->removeStuffMember($stuffName, $stuffRole);
	}
	elseif (isset($_POST['editStuffSchedule'])){ //check the function name
		$c->removeStuffMember($stuffName, $stuffSchedule);
	}
	$_SESSION["errorStuff"] = "";
	
} catch (Exception $e){
	$_SESSION["errorStuff"] = $e->getMessage();
}
?>

<!DOCTYPE html> <html>
	<head>
	<meta http-equiv="refresh" content="0; url=/FoodTruckManagementSystem/" />
	</head>
</html>
