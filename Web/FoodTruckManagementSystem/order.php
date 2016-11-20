<?php
require_once 'controller/Controller.php';

session_start();

$c = new Controller();
try{
	
	if (isset($_POST['order_name'])){
		$equipmentName = $_POST['order_name'];
	}
	if (isset($_POST['order_quantity'])){
		$equipmentQuantity = $_POST['order_quantity'];
	}

	if (isset($_POST['addOrder'])) {
		$c->createEquipment($equipmentName, $equipmentQuantity);
	}
	elseif (isset($_POST['removeOrder'])){
		$c->removeEquipment($equipmentName, $equipmentQuantity);
	}
	$_SESSION["errorOrder"] = "";
	
} catch (Exception $e){
	$_SESSION["errorOrder"] = $e->getMessage();
}
?>

<!DOCTYPE html> <html>
	<head>
	<meta http-equiv="refresh" content="0; url=/FoodTruckManagementSystem/" />
	</head>
</html>