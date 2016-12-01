<?php
require_once 'controller/Controller.php';

session_start ();

$c = new Controller ();
try {
	
	if (isset ( $_POST ['itemSpinner'] )) {
		$itemName = $_POST ['itemSpinner'];
	}
	
	if (isset ( $_POST ['order_quantity'] )) {
		$itemQuantityOrder = $_POST ['order_quantity'];
	}
	
	$c->menuItemOrdered ( $itemName, $itemQuantityOrder );
	$_SESSION ["successOrder"] = "Successfully ordered " . ucfirst ( $itemName ) . " item(s)!";
	
	$_SESSION ["errorOrder"] = "";
} catch ( Exception $e ) {
	$_SESSION ["errorOrder"] = $e->getMessage ();
	$_SESSION ["successOrder"] = "";
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh"
	content="0; url=/FoodTruckManagementSystem/index.php" />
</head>
</html>