<?php
require_once 'controller/Controller.php';

session_start ();

$c = new Controller ();
try {
	
	if (isset ( $_POST ['menuItem_name'] )) {
		$itemName = $_POST ['menuItem_name'];
	}
	if (isset ( $_POST ['menuItem_price'] )) {
		$itemPrice = $_POST ['menuItem_price'];
	}
	if (isset ( $_POST ['menuItem_order'] )) {
		$itemQuantityOrder = $_POST ['menuItem_order'];
	}
	
	if (isset ( $_POST ['addMenuItem'] )) {
		$c->createMenuItem ( $itemName, $itemPrice );
		$_SESSION ["successItem"] = "Successfully Add " . $itemName . " item from Menu List!";
	} elseif (isset ( $_POST ['removeMenuItem'] )) {
		$c->removeMenuItem ( $itemName );
		$_SESSION ["successItem"] = "Successfully remove " . $itemName . " item from Menu List!";
	}
	$_SESSION ["errorItem"] = "";
} catch ( Exception $e ) {
	$_SESSION ["errorItem"] = $e->getMessage ();
	$_SESSION ["successItem"] = "";
}
if (isset ( $err_equipment )) {
}

?>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh"
	content="0; url=/FoodTruckManagementSystem/index.php" />
</head>
</html>