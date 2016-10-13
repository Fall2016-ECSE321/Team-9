<?php
require_once __DIR__.'../../Controller/InputValidator.php';
require_once __DIR__.'../../model/Manager.php';
require_once __DIR__.'../../model/Equipment.php';
require_once __DIR__.'../../persistence/PersistenceFoodTruckManagementSystem.php';

class Controller{
	
	public function __construct(){
		
	}
	
	public function createEquipment($equipment_name, $equipment_quantity){
		//1. Validate input
		$name= InputValidator::validate_input($equipment_name);
		$quantity = InputValidator::validate_input($equipment_quantity);
		
		if (($name == null || strlen($name)==0) && ($quantity == null || $quantity==0)){
			throw new Exception("Equipment name and quantity cannot be empty!");
		}	
		else if ($name == null || strlen($name)==0){
			throw new Exception("Equipment name cannot be empty!");
		}
		else if ($quantity == null || $quantity == 0){
			throw new Exception("Equipment quantity cannot be empty or zero!");
		}
		else if ($quantity < 0){
			throw new Exception("Equipment quantity cannot be negative!");
		}
		else{
			//2. Load all of the data
			$pm = new PersistenceFoodTruckManagementSystem();
			$m  = $pm->loadDataFromStore();
			$flag = false;
			
			for($i = 0; $i < $m->numberOfEquipments(); $i++){
				if($name == $m->getEquipment_index($i)->getName()){
					$m->getEquipment_index($i)->setQuantity((string)($quantity + $m->getEquipment_index($i)->getQuantity()));
					$flag = true;
					break;
				}
			}
			if (! $flag){ 
				//3. Add the new equipment
				$equipment = new Equipment($name, $quantity);
				$m->addEquipment($equipment);
			}
			//4. Write all of the data
			$pm->writeDataToStore($m);
		}
	}
	
	
	
	public function createSupply($supply_name, $supply_quantity){
		//NEED TO ADD UNIT VARIABLE - must be updated in domain model and regenerated
	}
	
	public function createStaff($staff_name, $staff_role){
		// NEED TO ADD SCHEDULE
	}
	
}