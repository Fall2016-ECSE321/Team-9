<?php
/* PLEASE DO NOT EDIT THIS CODE */
/* This code was generated using the UMPLE 1.24.0-c37463a modeling language! */
class Manager {
	
	// ------------------------
	// STATIC VARIABLES
	// ------------------------
	private static $theInstance = null;
	
	// ------------------------
	// MEMBER VARIABLES
	// ------------------------
	
	// Manager Associations
	private $equipments;
	private $supplies;
	private $staffmembers;
	private $menus;
	
	// ------------------------
	// CONSTRUCTOR
	// ------------------------
	private function __construct() {
		$this->equipments = array ();
		$this->supplies = array ();
		$this->staffmembers = array ();
		$this->menus = array ();
	}
	public static function getInstance() {
		if (self::$theInstance == null) {
			self::$theInstance = new Manager ();
		}
		return self::$theInstance;
	}
	
	// ------------------------
	// INTERFACE
	// ------------------------
	public function getEquipment_index($index) {
		$aEquipment = $this->equipments [$index];
		return $aEquipment;
	}
	public function getEquipments() {
		$newEquipments = $this->equipments;
		return $newEquipments;
	}
	public function numberOfEquipments() {
		$number = count ( $this->equipments );
		return $number;
	}
	public function hasEquipments() {
		$has = $this->numberOfEquipments () > 0;
		return $has;
	}
	public function indexOfEquipment($aEquipment) {
		$wasFound = false;
		$index = 0;
		foreach ( $this->equipments as $equipment ) {
			if ($equipment->equals ( $aEquipment )) {
				$wasFound = true;
				break;
			}
			$index += 1;
		}
		$index = $wasFound ? $index : - 1;
		return $index;
	}
	public function getSupply_index($index) {
		$aSupply = $this->supplies [$index];
		return $aSupply;
	}
	public function getSupplies() {
		$newSupplies = $this->supplies;
		return $newSupplies;
	}
	public function numberOfSupplies() {
		$number = count ( $this->supplies );
		return $number;
	}
	public function hasSupplies() {
		$has = $this->numberOfSupplies () > 0;
		return $has;
	}
	public function indexOfSupply($aSupply) {
		$wasFound = false;
		$index = 0;
		foreach ( $this->supplies as $supply ) {
			if ($supply->equals ( $aSupply )) {
				$wasFound = true;
				break;
			}
			$index += 1;
		}
		$index = $wasFound ? $index : - 1;
		return $index;
	}
	public function getStaffmember_index($index) {
		$aStaffmember = $this->staffmembers [$index];
		return $aStaffmember;
	}
	public function getStaffmembers() {
		$newStaffmembers = $this->staffmembers;
		return $newStaffmembers;
	}
	public function numberOfStaffmembers() {
		$number = count ( $this->staffmembers );
		return $number;
	}
	public function hasStaffmembers() {
		$has = $this->numberOfStaffmembers () > 0;
		return $has;
	}
	public function indexOfStaffmember($aStaffmember) {
		$wasFound = false;
		$index = 0;
		foreach ( $this->staffmembers as $staffmember ) {
			if ($staffmember->equals ( $aStaffmember )) {
				$wasFound = true;
				break;
			}
			$index += 1;
		}
		$index = $wasFound ? $index : - 1;
		return $index;
	}
	public function getMenus_index($index) {
		$aMenus = $this->menus [$index];
		return $aMenus;
	}
	public function getMenus() {
		$newMenus = $this->menus;
		return $newMenus;
	}
	public function numberOfMenus() {
		$number = count ( $this->menus );
		return $number;
	}
	public function hasMenus() {
		$has = $this->numberOfMenus () > 0;
		return $has;
	}
	public function indexOfMenus($aMenus) {
		$wasFound = false;
		$index = 0;
		foreach ( $this->menus as $menus ) {
			if ($menus->equals ( $aMenus )) {
				$wasFound = true;
				break;
			}
			$index += 1;
		}
		$index = $wasFound ? $index : - 1;
		return $index;
	}
	public static function minimumNumberOfEquipments() {
		return 0;
	}
	public function addEquipment($aEquipment) {
		$wasAdded = false;
		if ($this->indexOfEquipment ( $aEquipment ) !== - 1) {
			return false;
		}
		$this->equipments [] = $aEquipment;
		$wasAdded = true;
		return $wasAdded;
	}
	public function removeEquipment($aEquipment) {
		$wasRemoved = false;
		if ($this->indexOfEquipment ( $aEquipment ) != - 1) {
			unset ( $this->equipments [$this->indexOfEquipment ( $aEquipment )] );
			$this->equipments = array_values ( $this->equipments );
			$wasRemoved = true;
		}
		return $wasRemoved;
	}
	public function addEquipmentAt($aEquipment, $index) {
		$wasAdded = false;
		if ($this->addEquipment ( $aEquipment )) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfEquipments ()) {
				$index = $this->numberOfEquipments () - 1;
			}
			array_splice ( $this->equipments, $this->indexOfEquipment ( $aEquipment ), 1 );
			array_splice ( $this->equipments, $index, 0, array (
					$aEquipment 
			) );
			$wasAdded = true;
		}
		return $wasAdded;
	}
	public function addOrMoveEquipmentAt($aEquipment, $index) {
		$wasAdded = false;
		if ($this->indexOfEquipment ( $aEquipment ) !== - 1) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfEquipments ()) {
				$index = $this->numberOfEquipments () - 1;
			}
			array_splice ( $this->equipments, $this->indexOfEquipment ( $aEquipment ), 1 );
			array_splice ( $this->equipments, $index, 0, array (
					$aEquipment 
			) );
			$wasAdded = true;
		} else {
			$wasAdded = $this->addEquipmentAt ( $aEquipment, $index );
		}
		return $wasAdded;
	}
	public static function minimumNumberOfSupplies() {
		return 0;
	}
	public function addSupply($aSupply) {
		$wasAdded = false;
		if ($this->indexOfSupply ( $aSupply ) !== - 1) {
			return false;
		}
		$this->supplies [] = $aSupply;
		$wasAdded = true;
		return $wasAdded;
	}
	public function removeSupply($aSupply) {
		$wasRemoved = false;
		if ($this->indexOfSupply ( $aSupply ) != - 1) {
			unset ( $this->supplies [$this->indexOfSupply ( $aSupply )] );
			$this->supplies = array_values ( $this->supplies );
			$wasRemoved = true;
		}
		return $wasRemoved;
	}
	public function addSupplyAt($aSupply, $index) {
		$wasAdded = false;
		if ($this->addSupply ( $aSupply )) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfSupplies ()) {
				$index = $this->numberOfSupplies () - 1;
			}
			array_splice ( $this->supplies, $this->indexOfSupply ( $aSupply ), 1 );
			array_splice ( $this->supplies, $index, 0, array (
					$aSupply 
			) );
			$wasAdded = true;
		}
		return $wasAdded;
	}
	public function addOrMoveSupplyAt($aSupply, $index) {
		$wasAdded = false;
		if ($this->indexOfSupply ( $aSupply ) !== - 1) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfSupplies ()) {
				$index = $this->numberOfSupplies () - 1;
			}
			array_splice ( $this->supplies, $this->indexOfSupply ( $aSupply ), 1 );
			array_splice ( $this->supplies, $index, 0, array (
					$aSupply 
			) );
			$wasAdded = true;
		} else {
			$wasAdded = $this->addSupplyAt ( $aSupply, $index );
		}
		return $wasAdded;
	}
	public static function minimumNumberOfStaffmembers() {
		return 0;
	}
	public function addStaffmember($aStaffmember) {
		$wasAdded = false;
		if ($this->indexOfStaffmember ( $aStaffmember ) !== - 1) {
			return false;
		}
		$this->staffmembers [] = $aStaffmember;
		$wasAdded = true;
		return $wasAdded;
	}
	public function removeStaffmember($aStaffmember) {
		$wasRemoved = false;
		if ($this->indexOfStaffmember ( $aStaffmember ) != - 1) {
			unset ( $this->staffmembers [$this->indexOfStaffmember ( $aStaffmember )] );
			$this->staffmembers = array_values ( $this->staffmembers );
			$wasRemoved = true;
		}
		return $wasRemoved;
	}
	public function addStaffmemberAt($aStaffmember, $index) {
		$wasAdded = false;
		if ($this->addStaffmember ( $aStaffmember )) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfStaffmembers ()) {
				$index = $this->numberOfStaffmembers () - 1;
			}
			array_splice ( $this->staffmembers, $this->indexOfStaffmember ( $aStaffmember ), 1 );
			array_splice ( $this->staffmembers, $index, 0, array (
					$aStaffmember 
			) );
			$wasAdded = true;
		}
		return $wasAdded;
	}
	public function addOrMoveStaffmemberAt($aStaffmember, $index) {
		$wasAdded = false;
		if ($this->indexOfStaffmember ( $aStaffmember ) !== - 1) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfStaffmembers ()) {
				$index = $this->numberOfStaffmembers () - 1;
			}
			array_splice ( $this->staffmembers, $this->indexOfStaffmember ( $aStaffmember ), 1 );
			array_splice ( $this->staffmembers, $index, 0, array (
					$aStaffmember 
			) );
			$wasAdded = true;
		} else {
			$wasAdded = $this->addStaffmemberAt ( $aStaffmember, $index );
		}
		return $wasAdded;
	}
	public static function minimumNumberOfMenus() {
		return 0;
	}
	public function addMenus($aMenus) {
		$wasAdded = false;
		if ($this->indexOfMenus ( $aMenus ) !== - 1) {
			return false;
		}
		$this->menus [] = $aMenus;
		$wasAdded = true;
		return $wasAdded;
	}
	public function removeMenus($aMenus) {
		$wasRemoved = false;
		if ($this->indexOfMenus ( $aMenus ) != - 1) {
			unset ( $this->menus [$this->indexOfMenus ( $aMenus )] );
			$this->menus = array_values ( $this->menus );
			$wasRemoved = true;
		}
		return $wasRemoved;
	}
	public function addMenusAt($aMenus, $index) {
		$wasAdded = false;
		if ($this->addMenus ( $aMenus )) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfMenus ()) {
				$index = $this->numberOfMenus () - 1;
			}
			array_splice ( $this->menus, $this->indexOfMenus ( $aMenus ), 1 );
			array_splice ( $this->menus, $index, 0, array (
					$aMenus 
			) );
			$wasAdded = true;
		}
		return $wasAdded;
	}
	public function addOrMoveMenusAt($aMenus, $index) {
		$wasAdded = false;
		if ($this->indexOfMenus ( $aMenus ) !== - 1) {
			if ($index < 0) {
				$index = 0;
			}
			if ($index > $this->numberOfMenus ()) {
				$index = $this->numberOfMenus () - 1;
			}
			array_splice ( $this->menus, $this->indexOfMenus ( $aMenus ), 1 );
			array_splice ( $this->menus, $index, 0, array (
					$aMenus 
			) );
			$wasAdded = true;
		} else {
			$wasAdded = $this->addMenusAt ( $aMenus, $index );
		}
		return $wasAdded;
	}
	public function equals($compareTo) {
		return $this == $compareTo;
	}
	public function delete() {
		$this->equipments = array ();
		$this->supplies = array ();
		$this->staffmembers = array ();
		$this->menus = array ();
	}
}
?>