<?php
require_once 'controller/Controller.php';

session_start();

$c = new Controller();
try{
	
	if (isset($_POST['menuItem_name'])){
		$itemName= $_POST['menuItem_name'];
	}

	if (isset($_POST['menuItem_order'])){
		$itemQuantityOrder = $_POST['menuItem_order'];
	}
	$c->menuItemOrder($itemName, $itemQuantityOrder);
	$_SESSION["successOrder"] = "Successfully order ".$itemName." item(s)!";	
	$_SESSION["errorOrder"] = "";
	
} catch (Exception $e){
	$_SESSION["errorOrder"] = $e->getMessage();
	$_SESSION["successOrder"] = "";
}

?>


<!DOCTYPE html> <html>
	<head>
	<meta http-equiv="refresh" content="0; url=/FoodTruckManagementSystem/index.php" />
	</head>
</html>