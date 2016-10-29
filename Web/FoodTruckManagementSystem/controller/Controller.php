<?php
require_once __DIR__.'../../Controller/InputValidator.php';
require_once __DIR__.'../../model/Manager.php';
require_once __DIR__.'../../model/Equipment.php';
require_once __DIR__.'../../model/Supply.php';
//require_once __DIR__.'../../model/Order.php';
//require_once __DIR__.'../../model/StaffMember.php';
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
		
	}
		
	public function removeEquipment($equipment_name, $equipment_quantity){

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
	
	public function createSupply($supply_name, $supply_quantity, $supply_unit){
	
		$error = "";
		$name= InputValidator::validate_input($supply_name);
		$quantity = InputValidator::validate_input($supply_quantity);
		$unit= InputValidator::validate_input($supply_unit);
		if (($name != null || strlen($name)!=0)&&($quantity != null || $quantity !=0) &&($quantity>0)&&($unit != null || strlen($unit)!=0)){
			//1. Load all of the data
			$pm = new PersistenceFoodTruckManagementSystem();
			$m  = $pm->loadDataFromStore();
			$flag = false;
	
			for($i = 0; $i < $m->numberOfSupplies(); $i++){
				if($name == $m->getSupply_index($i)->getName()){
					if($unit == $m->getSupply_index($i)->getName()){
						$m->getSupply_index($i)->setQuantity((string)($quantity + $m->getSupply_index($i)->getQuantity()));
						$flag = true;
						break;
					}
					else{
						$error = "Supply unit does not match: ".$m->getSupply_index($i)->getUnit();
						throw new Exception($error);
					}				
				}
			}
			if (! $flag){
				//2. Add the new supply
				$supply = new Supply($name, $quantity, $unit);
				$m->addSupply($supply);
			}
			//3. Write all of the data
			$pm->writeDataToStore($m);
		}
		else{
			// 4. Validate all the innputs
			if ($name == null || strlen($name)==0){
				$error .="Supply name cannot be empty!";
			}
			if ($quantity == null || $quantity == 0){
				$error .= " Supply quantity cannot be empty or zero!";
			}
			if ($quantity < 0){
				$error .= " Supply quantity cannot be negative!";
			}
			if ($unit == null || strlen($unit) ==0){
				$error .=" Supply unit cannot be empty!";
			}
			$error = trim($error);
			throw new Exception ($error);
		}
	
	}
	
}
?>