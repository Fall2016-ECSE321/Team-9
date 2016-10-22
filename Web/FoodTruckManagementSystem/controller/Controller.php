<?php
require_once __DIR__.'../../Controller/InputValidator.php';
require_once __DIR__.'../../model/Manager.php';
require_once __DIR__.'../../model/Equipment.php';
require_once __DIR__.'../../persistence/PersistenceFoodTruckManagementSystem.php';

class Controller{
	
	public function __construct(){
		
	}
	
	public function createEquipment($equipment_name, $equipment_quantity){

		$error = "";
		$name= InputValidator::validate_input($equipment_name);
		$quantity = InputValidator::validate_input($equipment_quantity);
		if (($name != null || strlen($name)!=0)&&($quantity != null || $quantity !=0) &&($quantity>0)){
			//1. Load all of the data
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
				//2. Add the new equipment
				$equipment = new Equipment($name, $quantity);
				$m->addEquipment($equipment);
			}
			//3. Write all of the data
			$pm->writeDataToStore($m);
		}
		else{
			// 4. Validate all the innputs 
			if ($name == null || strlen($name)==0){
				$error .="Equipment name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0){
				$error .= " Equipment quantity cannot be empty or zero!";
			} 
			if ($quantity < 0){
				$error .= " Equipment quantity cannot be negative!";
			}
			$error = trim($error);
			throw new Exception ($error);
		}	
		
	}	public function removeEquipment($equipment_name, $equipment_quantity){

		$error = "";
		$name= InputValidator::validate_input($equipment_name);
		$quantity = InputValidator::validate_input($equipment_quantity);
		
		//1. Load all of the data
		$pm = new PersistenceFoodTruckManagementSystem();
		$m  = $pm->loadDataFromStore();
		$flag = false;
		
		if (($name != null || strlen($name)!=0)&&($quantity != null || $quantity !=0) &&($quantity>0)){
			//($name != null || strlen($name)!=0)&&($quantity != null || $quantity !=0) &&($quantity>0)
			//2. Remove the equipment
			for($i = 0; $i < $m->numberOfEquipments(); $i++){
				if($name == $m->getEquipment_index($i)->getName()){
					$flag = true;
					$storeQuantity = $m->getEquipment_index($i)->getQuantity();
					if($storeQuantity > $quantity){
						$m->getEquipment_index($i)->setQuantity((string)($storeQuantity - $quantity));
						break;
					}
					elseif ($storeQuantity < $quantity){
						$error = "Equipment quantity is only: ".$storeQuantity;
						throw new Exception($error);
					}
					else{
						// $m->getEquipment_index($i)->delete();
						$m->removeEquipment($m->getEquipment_index($i));
						break;
					}
				}
			}
			
			if (!$flag){
				$error = "Equipment name does not exist!";
				throw new Exception($error);
			}
			
			//3. Write all of the data
			$pm->writeDataToStore($m);
		}
		else{
			// 4. Validate all the innputs 
			if ($name == null || strlen($name)==0){
				$error .="Equipment name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0){
				$error .= " Equipment quantity cannot be empty or zero!";
			} 
			if ($quantity < 0){
				$error .= " Equipment quantity cannot be negative!";
			}
			$error = trim($error);
			throw new Exception ($error);
		}	
	}
	
}
?>