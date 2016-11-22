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
		$_SESSION ["successSupply"] = "Successfully add ".$supplyName." item(s)!";
	}
	elseif (isset($_POST['removeSupply'])){
		$c->removeSupply($supplyName, $supplyQuantity);
		$_SESSION ["successSupply"] = "Successfully remove ".$supplyName." item(s)!";
	}
	$_SESSION["errorSupply"] = "";
	
} catch (Exception $e){
	$_SESSION["errorSupply"] = $e->getMessage();
	$_SESSION ["successSupply"] = "";
}
?>

<!DOCTYPE html> <html>
	<head>
	<meta http-equiv="refresh" content="0; url=/FoodTruckManagementSystem/inventoryTab.php" />
	</head>
</html>