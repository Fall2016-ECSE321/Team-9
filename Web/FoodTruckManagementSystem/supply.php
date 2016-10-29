<?php
require_once 'controller/Controller.php';

session_start();

$c = new Controller();
try{

	if (isset($_POST['supply_name'])){
		$supplyName = $_POST['supply_name'];
	}
	if (isset($_POST['supply_quantity'])){
		$supplyQuantity = $_POST['supply_quantity'];
	}
	if (isset($_POST['supply_unit'])){
		$supplyUnit= $_POST['supply_unit'];
	}

	if (isset($_POST['addSupply'])) {
		$c->createSupply($supplyName, $supplyQuantity, $supplyUnit);
	}
	elseif (isset($_POST['removeSupply'])){
		$c->removeSupply($supplyName, $supplyQuantity);
	}
	$_SESSION["errorSupply"] = "";
	
} catch (Exception $e){
	$_SESSION["errorSupply"] = $e->getMessage();
}
?>

<!DOCTYPE html> <html>
	<head>
	<meta http-equiv="refresh" content="0; url=/FoodTruckManagementSystem/" />
	</head>
</html>