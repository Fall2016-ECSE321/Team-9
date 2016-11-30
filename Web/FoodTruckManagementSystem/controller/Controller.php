<?php
require_once __DIR__ . '../../Controller/InputValidator.php';
require_once __DIR__ . '../../model/Manager.php';
require_once __DIR__ . '../../model/Equipment.php';
require_once __DIR__ . '../../model/Supply.php';
require_once __DIR__ . '../../model/MenuItem.php';
require_once __DIR__ . '../../model/StaffMember.php';
require_once __DIR__ . '../../persistence/PersistenceFoodTruckManagementSystem.php';
class Controller {
	public function __construct() {
	}
	public function createEquipment($equipment_name, $equipment_quantity) {
		$error = "";
		$name = InputValidator::validate_input ( $equipment_name );
		$quantity = InputValidator::validate_input ( $equipment_quantity );
		if (($name != null || strlen ( $name ) != 0) && ($quantity != null || $quantity != 0) && ($quantity > 0)) {
			// If the input is valid, load the persistance layer into the manager
			$pm = new PersistenceFoodTruckManagementSystem ();
			$m = $pm->loadDataFromStore ();
			// flag is used to determine if the equipment_name is already in the system.
			$flag = false;
			// iterate through the stored equipment in search of an equipment matching equipment_name
			for($i = 0; $i < $m->numberOfEquipments (); $i ++) {
				if ($name == $m->getEquipment_index ( $i )->getName ()) {
					// once found, update the quantity by summing the previous value with the new equipment_quantity
					$m->getEquipment_index ( $i )->setQuantity ( ( string ) ($quantity + $m->getEquipment_index ( $i )->getQuantity ()) );
					$flag = true;
					break;
				}
			} // if the equipment does not already exist, create it
			if (! $flag) {
				$equipment = new Equipment ( $name, $quantity );
				$m->addEquipment ( $equipment );
			}
			// Write all of the data to the manager
			$pm->writeDataToStore ( $m );
		} else {
			// Since the inputs are not valid, throw the appropriate errors
			if ($name == null || strlen ( $name ) == 0) {
				$error .= "Equipment name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0) {
				$error .= " Equipment quantity cannot be empty or zero!";
			}
			if ($quantity < 0) {
				$error .= " Equipment quantity cannot be negative!";
			}
			$error = trim ( $error );
			throw new Exception ( $error );
		}
	}
	public function removeEquipment($equipment_name, $equipment_quantity) {
		$error = "";
		$name = InputValidator::validate_input ( $equipment_name );
		$quantity = InputValidator::validate_input ( $equipment_quantity );
		
		// Load all of the data
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		// flag is used to determine if the equipment_name is already in the system.
		$flag = false;
		
		if (($name != null || strlen ( $name ) != 0) && ($quantity != null || $quantity != 0) && ($quantity > 0)) {
			// If the input is valid, check that the equipment already exists
			for($i = 0; $i < $m->numberOfEquipments (); $i ++) {
				if ($name == $m->getEquipment_index ( $i )->getName ()) {
					$flag = true;
					// upon finding the same equipment, compare the new quantity. Take the appropriate actions
					// depending on the result
					$storeQuantity = $m->getEquipment_index ( $i )->getQuantity ();
					if ($storeQuantity > $quantity) {
						$m->getEquipment_index ( $i )->setQuantity ( ( string ) ($storeQuantity - $quantity) );
						break;
					} elseif ($storeQuantity < $quantity) {
						$error = "Equipment quantity is only: " . $storeQuantity;
						throw new Exception ( $error );
					} else {
						// if new quantity equals the current quantity, remove the equipment from the list
						$m->removeEquipment ( $m->getEquipment_index ( $i ) );
						break;
					}
				}
			}
			
			if (! $flag) {
				$error = "Equipment name does not exist!";
				throw new Exception ( $error );
			}
			
			// Write all of the data
			$pm->writeDataToStore ( $m );
		} else {
			// Since the inputs are not valid, throw the appropriate errors
			if ($name == null || strlen ( $name ) == 0) {
				$error .= "Equipment name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0) {
				$error .= " Equipment quantity cannot be empty or zero!";
			}
			if ($quantity < 0) {
				$error .= " Equipment quantity cannot be negative!";
			}
			$error = trim ( $error );
			throw new Exception ( $error );
		}
	}
	public function createSupply($supply_name, $supply_quantity, $supply_unit) {
		$error = "";
		$name = InputValidator::validate_input ( $supply_name );
		$quantity = InputValidator::validate_input ( $supply_quantity );
		$unit = InputValidator::validate_input ( $supply_unit );
		if (($name != null || strlen ( $name ) != 0) && ($quantity != null || $quantity != 0) && ($quantity > 0) && ($unit != null || strlen ( $unit ) != 0)) {
			// If the input is valid, load the persistance layer into the manager
			$pm = new PersistenceFoodTruckManagementSystem ();
			$m = $pm->loadDataFromStore ();
			// flag is used to determine if the supply_name is already in the system.
			$flag = false;
			
			for($i = 0; $i < $m->numberOfSupplies (); $i ++) {
				if ($name == $m->getSupply_index ( $i )->getName ()) {
					// if the supply is found, and the units match, then update quantity.
					if ($unit == $m->getSupply_index ( $i )->getUnit ()) {
						$m->getSupply_index ( $i )->setQuantity ( ( string ) ($quantity + $m->getSupply_index ( $i )->getQuantity ()) );
						$flag = true;
						break;
					} else {
						// if units do not match but the supply does, throw an error
						$error = "Supply unit does not match: " . $m->getSupply_index ( $i )->getUnit ();
						throw new Exception ( $error );
					}
				}
			}
			if (! $flag) {
				// if not found, create a new supply
				$supply = new Supply ( $name, $quantity, $unit );
				$m->addSupply ( $supply );
			}
			// Write all of the data
			$pm->writeDataToStore ( $m );
		} else {
			// Since the inputs are not valid, throw the appropriate errors
			if ($name == null || strlen ( $name ) == 0) {
				$error .= "Supply name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0) {
				$error .= " Supply quantity cannot be empty or zero!";
			}
			if ($quantity < 0) {
				$error .= " Supply quantity cannot be negative!";
			}
			if ($unit == null || strlen ( $unit ) == 0) {
				$error .= " Supply unit cannot be empty!";
			}
			$error = trim ( $error );
			throw new Exception ( $error );
		}
	}
	public function removeSupply($supply_name, $supply_quantity) {
		// units are unnecessary for removing
		$error = "";
		$name = InputValidator::validate_input ( $supply_name );
		$quantity = InputValidator::validate_input ( $supply_quantity );
		
		// Load all of the data
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		$flag = false;
		
		if (($name != null || strlen ( $name ) != 0) && ($quantity != null || $quantity != 0) && ($quantity > 0)) {
			// If the input is valid, proceed
			for($i = 0; $i < $m->numberOfSupplies (); $i ++) {
				if ($name == $m->getSupply_index ( $i )->getName ()) {
					$flag = true;
					$storeQuantity = $m->getSupply_index ( $i )->getQuantity ();
					// upon finding the same supply, compare the new quantity. Take the appropriate actions
					// depending on the result
					if ($storeQuantity > $quantity) {
						$m->getSupply_index ( $i )->setQuantity ( ( string ) ($storeQuantity - $quantity) );
						break;
					} elseif ($storeQuantity < $quantity) {
						$error = "Supply quantity is only: " . $storeQuantity;
						throw new Exception ( $error );
					} else {
						// if the previous quantity and the input quantity are the same, delete the supply
						$m->removeSupply ( $m->getSupply_index ( $i ) );
						break;
					}
				}
			}
			// throw exception if supply does not already exist
			if (! $flag) {
				$error = "Supply name does not exist!";
				throw new Exception ( $error );
			}
			
			// Write all of the data
			$pm->writeDataToStore ( $m );
		} else {
			// Since the inputs are not valid, throw the appropriate errors
			if ($name == null || strlen ( $name ) == 0) {
				$error .= "Supply name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0) {
				$error .= " Supply quantity cannot be empty or zero!";
			}
			if ($quantity < 0) {
				$error .= " Supply quantity cannot be negative!";
			}
			$error = trim ( $error );
			throw new Exception ( $error );
		}
	}
	public function createStaffMember($name, $role) {
		$error = "";
		$staffName = InputValidator::validate_input ( $name );
		$staffRole = InputValidator::validate_input ( $role );
		// if inputs are not valid, throw appropriate erros
		if ($staffName == null || strlen ( $staffName ) == 0) {
			$error .= "Staff Member name cannot be empty! ";
		}
		if ($staffRole == null || strlen ( $staffRole ) == 0) {
			$error .= "Staff Member role cannot be empty! ";
		}
		$error = trim ( $error );
		
		if (strlen ( $error ) > 0) {
			throw new Exception ( $error );
		}
		
		// load data
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		$flag = false;
		
		for($i = 0; $i < $m->numberOfStaffmembers (); $i ++) {
			if ($staffName == $m->getStaffmember_index ( $i )->getName ()) {
				if ($staffRole == $m->getStaffmember_index ( $i )->getRole ()) {
					// if staff member already exists with given name, throw an error
					throw new Exception ( "Staff Member already exists!" );
				}
				// else, update the staff members role
				$flag = true;
				$m->getStaffmember_index ( $i )->setRole ( $staffRole );
				break;
			}
		}
		// if staff member does not exist in the system, create a new one
		if (! $flag) {
			$staffMember = new StaffMember ( $staffName, $staffRole );
			$m->addStaffmember ( $staffMember );
		}
		// save data
		$pm->writeDataToStore ( $m );
	}
	public function removeStaffMember($name) {
		// staff member role is not needed to remove a staff member since we do not allow identical names
		$error = "";
		$staffName = InputValidator::validate_input ( $name );
		// if input name is invalid, throw an error
		if ($staffName == null || strlen ( $staffName ) == 0) {
			$error .= "Staff Member name cannot be empty! ";
		}
		$error = trim ( $error );
		
		if (strlen ( $error ) > 0) {
			throw new Exception ( $error );
		}
		
		// load the data
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		$flag = false;
		
		for($i = 0; $i < $m->numberOfStaffmembers (); $i ++) {
			// if the staff member indeeds exists, remove it
			if ($staffName == $m->getStaffmember_index ( $i )->getName ()) {
				$flag = true;
				$m->removeStaffmember ( $m->getStaffmember_index ( $i ) );
				break;
			}
		}
		// else, throw an error
		if (! $flag) {
			$error = "Staff Member does not exist!";
			throw new Exception ( $error );
		}
		// save the data
		$pm->writeDataToStore ( $m );
	}
	public function addTimeStaffMember($name, $startTime, $endTime) {
		$error = "";
		$numberOfDaysInWeek = 7;
		$staffName = InputValidator::validate_input ( $name );
		// we allow empty start times and end times, representing a day off.
		if ($staffName == null || strlen ( $staffName ) == 0) {
			$error .= "Staff Member name cannot be empty! ";
		}
		// check errors on startTime/endTime inputs.
		// must cycle through each day of the week and compare
		for($i = 0; $i < $numberOfDaysInWeek; $i ++) {
			
			if ($startTime [$i] == $endTime [$i] && $startTime [$i] != "") {
				$error .= "End time cannot equal start time! ";
				break;
			}
			if ($startTime [$i] != "" && $endTime [$i] == "") {
				$error .= "End time is empty! ";
			}
			if ($startTime [$i] == "" && $endTime [$i] != "") {
				$error .= "Start time is empty! ";
			}
			// now check that the start time is less than the end time
			// times are received in format "hh:mm" where h is hour and m is minute
			// therefore we cast to an integer while removing the ':'
			if ($startTime [$i] != "" && $endTime [$i] != "") {
				$tempStart = "";
				$tempEnd = "";
				for($j = 0; $j < 5; $j ++) {
					if ($j == 2) {
						$j ++; // skip the ':'
					}
					$tempStart .= substr ( $startTime [$i], $j );
					$tempEnd .= substr ( $endTime [$i], $j );
				}
				// we now have our start and end time in a comparable format
				// now verify the inputs are valid
				for($k = 0; $k < 4; $k ++) {
					if (substr ( $tempStart, $k ) < substr ( $tempEnd, $k )) {
						break;
					}
					if (substr ( $tempStart, $k ) == substr ( $tempEnd, $k )) {
					} else {
						$error = "End time must be greater than start time!";
						throw new Exception ( $error );
					}
				}
			}
		}
		
		$error = trim ( $error );
		
		if (strlen ( $error ) > 0) {
			throw new Exception ( $error );
		}
		// now that we know inputs are valid, load data
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		$staffFound = false;
		// if the staff member exists, assign startTimes and endTimes
		for($i = 0; $i < $m->numberOfStaffmembers (); $i ++) {
			if ($staffName == $m->getStaffmember_index ( $i )->getName ()) {
				$staffFound = true;
				$m->getStaffmember_index ( $i )->addStartTime ( $startTime );
				$m->getStaffmember_index ( $i )->addEndTime ( $endTime );
			}
		}
		// else, throw error
		if (! $staffFound) {
			$error = "Staff Member does not exist!";
			throw new Exception ( $error );
		}
		// save data
		$pm->writeDataToStore ( $m );
	}
	public function createMenuItem($menuItemName, $menuItemPrice) {
		$error = "";
		$name = InputValidator::validate_input ( $menuItemName );
		$price = InputValidator::validate_input ( $menuItemPrice );
		if (($name != null || strlen ( $name ) != 0) && ($price != null || $price != 0.00 || $price != 0) && ($price > 0)) {
			// if all inputs are valid, load data
			$pm = new PersistenceFoodTruckManagementSystem ();
			$m = $pm->loadDataFromStore ();
			$flag = false;
			// essential to check the format of the menuItemPrice
			if (! is_numeric ( $price )) {
				$error = "Menu Item price must be a number!";
				throw new Exception ( $error );
			}
			
			// if trying to make menu item that already exists, update its price to new price
			for($i = 0; $i < $m->numberOfMenus (); $i ++) {
				if ($name == $m->getMenus_index ( $i )->getName ()) {
					if (floatval ( $price ) == floatval ( $m->getMenus_index ( $i )->getPrice () )) {
						$error = "Menu Item already exists at price: $" . floatval ( $m->getMenus_index ( $i )->getPrice () );
						throw new Exception ( $error );
					} else {
						// must store the price as a string because the function "serialize", has problems
						// saving and storing floats/doubles.
						// Ex. 14.11 would be displayed roughly like 14.10999999999999999999823
						$tempPrice1 = ($price);
						settype ( $tempPrice1, "string" );
						$m->getMenus_index ( $i )->setPrice ( $tempPrice1 );
						$flag = true; // flag indicates the menu name already exists.
						break;
					}
				}
			}
			if (! $flag) { // if menu name does not exist, create a new menu item.
				$counter = 0; // when creating a new menu item, it has zero popularity
				$tempPrice2 = $price; // see reason above for storing as string
				settype ( $tempPrice2, "string" );
				$menuItem = new MenuItem ( $name, $tempPrice2, $counter );
				$m->addMenus ( $menuItem );
			}
			// save all the data
			$pm->writeDataToStore ( $m );
		} else {
			// Throw appropriate errors if inputs are invalid
			if ($name == null || strlen ( $name ) == 0) {
				$error .= "Menu Item name cannot be empty!";
			}
			if ($price == null || $price == 0 || $price == 0.00) {
				$error .= " Menu Item price cannot be empty or zero!";
			}
			if ($price < 0) {
				$error .= " Menu Item price cannot be negative!";
			}
			$error = trim ( $error );
			throw new Exception ( $error );
		}
	}
	public function removeMenuItem($menuItemName) {
		$error = "";
		$name = InputValidator::validate_input ( $menuItemName );
		
		// load all the data
		$pm = new PersistenceFoodTruckManagementSystem ();
		$m = $pm->loadDataFromStore ();
		$flag = false;
		
		if ($name != null || strlen ( $name ) != 0) {
			// if inputs are valid, and menu item exists, then remove the menu item
			for($i = 0; $i < $m->numberOfMenus (); $i ++) {
				if ($name == $m->getMenus_index ( $i )->getName ()) {
					$flag = true;
					$m->removeMenus ( $m->getMenus_index ( $i ) );
					break;
				}
			}
			// throw error if menu item does not exist
			if (! $flag) {
				$error = "Menu Item name does not exist!";
				throw new Exception ( $error );
			}
			
			// save all the data
			$pm->writeDataToStore ( $m );
		} else {
			// Throw appropriate errors if inputs are invalid
			if ($name == null || strlen ( $name ) == 0) {
				$error = "Menu Item name cannot be empty!";
				throw new Exception ( $error );
			}
		}
	}
	public function menuItemOrdered($menuItemName, $menuItemQuantity) {
		$error = "";
		$name = InputValidator::validate_input ( $menuItemName );
		$quantity = InputValidator::validate_input ( $menuItemQuantity );
		
		if (($name != null || strlen ( $name ) != 0) && ($quantity != null || $quantity != 0) && ($quantity > 0)) {
			// if inputs are valid, Load all of the data
			$pm = new PersistenceFoodTruckManagementSystem ();
			$m = $pm->loadDataFromStore ();
			$flag = false;
			
			// if menu item is ordered that already exists, update its popularity counter
			for($i = 0; $i < $m->numberOfMenus (); $i ++) {
				if ($name == $m->getMenus_index ( $i )->getName ()) {
					$currentQuantity = $m->getMenus_index ( $i )->getPopularityCounter ();
					$m->getMenus_index ( $i )->setPopularityCounter ( ( string ) ($quantity + $currentQuantity) );
					$flag = true; // flag indicates the menu name already exists.
					break;
				}
			}
			if (! $flag) { // if menu name does not exist, throw error.
				$error = "Order name does not exist!";
				throw new Exception ( $error );
			}
			// Write all of the data
			$pm->writeDataToStore ( $m );
		} else {
			// since inputs are invalid, throw appropriate erros
			if ($name == null || strlen ( $name ) == 0) {
				$error .= "Order name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0) {
				$error .= " Order quantity cannot be empty or zero!";
			}
			if ($quantity < 0) {
				$error .= " Order quantity cannot be negative!";
			}
			$error = trim ( $error );
			throw new Exception ( $error );
		}
	}
}
?>