<?php

require_once __DIR__.'../../model/Equipment.php';
require_once __DIR__.'../../model/Manager.php';
require_once __DIR__.'../../persistence/PersistenceFoodTruckManagementSystem.php';
require_once __DIR__.'../../controller/Controller.php';

// require_once 'C:\Users\MalkolmPC\Desktop\EclipseWorkspace\FoodTruckManagementSystem\model\Order.php';
// require_once 'C:\Users\MalkolmPC\Desktop\EclipseWorkspace\FoodTruckManagementSystem\model\StaffMember.php';
// require_once 'C:\Users\MalkolmPC\Desktop\EclipseWorkspace\FoodTruckManagementSystem\model\Supply.php';


class ControllerTest extends PHPUnit_Framework_TestCase
{
	protected $c;
	protected $pm;
	protected $m;

	protected function setUp()
	{
		$this->c = new Controller();
		$this->pm = new PersistenceFoodTruckManagementSystem();
		$this->m = $this->pm->loadDataFromStore();
		$this->m->delete();
		$this->pm->writeDataToStore($this->m);
	}

	protected function tearDown()
	{
	}

	public function testCreateEquipment() {
		$this->assertEquals(0, count($this->m->getEquipments()));

		$equipmentName = "knife";
		$equipmentQuantity = 3;

		try {
			$this->c->createEquipment($equipmentName, $equipmentQuantity);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}

		// check file contents
		$this->m = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->m->getEquipments()));
		$this->assertEquals($equipmentName, $this->m->getEquipment_index(0)->getName());
		$this->assertEquals($equipmentQuantity, $this->m->getEquipment_index(0)->getQuantity());
		
// 		$this->assertEquals(0, count($this->m->getSupplies()));
// 		$this->assertEquals(0, count($this->m->getStaff()));
	}

	public function testCreateEquipmentNull() {
		$this->assertEquals(0, count($this->m->getEquipments()));

		$equipmentName0 = null;
		$equipmentQuantity0 = null;
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;

		

		$error0 = "";
		$error1 = "";
		$error2 = "";
			try {
			$this->c->createEquipment($equipmentName0, $equipmentQuantity0);
		} catch (Exception $e) {
			$error0 = $e->getMessage();
		}
		try {
			$this->c->createEquipment($equipmentName0, $equipmentQuantity1);
		} catch (Exception $e) {
			$error1 = $e->getMessage();
		}
		try {
			$this->c->createEquipment($equipmentName1, $equipmentQuantity0);
		} catch (Exception $e) {
			$error2 = $e->getMessage();
		}

		// check error
		$this->assertEquals("Equipment name and quantity cannot be empty!", $error0);
		$this->assertEquals("Equipment name cannot be empty!", $error1);
		$this->assertEquals("Equipment quantity cannot be empty or zero!", $error2);
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->m->getEquipments()));
// 		$this->assertEquals(0, count($this->m->getSupplies()));
// 		$this->assertEquals(0, count($this->m->getStaff()));
	}

	public function testCreateEquipmentEmpty() {
		$this->assertEquals(0, count($this->m->getEquipments()));

		$equipmentName0 = "";
		$equipmentQuantity0 = "";	
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
		
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->createEquipment($equipmentName0, $equipmentQuantity0);
		} catch (Exception $e) {
			$error0 = $e->getMessage();
		}
		try {
			$this->c->createEquipment($equipmentName0, $equipmentQuantity1);
		} catch (Exception $e) {
			$error1 = $e->getMessage();
		}
		try {
			$this->c->createEquipment($equipmentName1, $equipmentQuantity0);
		} catch (Exception $e) {
			$error2 = $e->getMessage();
		}
		
		// check error
		$this->assertEquals("Equipment name and quantity cannot be empty!", $error0);
		$this->assertEquals("Equipment name cannot be empty!", $error1);
		$this->assertEquals("Equipment quantity cannot be empty or zero!", $error2);
		// check file contents
		$this->m = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->m->getEquipments()));
// 		$this->assertEquals(0, count($this->m->getSupplies()));
// 		$this->assertEquals(0, count($this->m->getStaff()));

	}

	public function testCreateEquipmentSpaces() {
		$this->assertEquals(0, count($this->m->getEquipments()));

		$equipmentName0 = " ";
		$equipmentQuantity0 = " ";	
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
		
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->createEquipment($equipmentName0, $equipmentQuantity0);
		} catch (Exception $e) {
			$error0 = $e->getMessage();
		}
		try {
			$this->c->createEquipment($equipmentName0, $equipmentQuantity1);
		} catch (Exception $e) {
			$error1 = $e->getMessage();
		}
		try {
			$this->c->createEquipment($equipmentName1, $equipmentQuantity0);
		} catch (Exception $e) {
			$error2 = $e->getMessage();
		}
		
		// check error
		$this->assertEquals("Equipment name and quantity cannot be empty!", $error0);
		$this->assertEquals("Equipment name cannot be empty!", $error1);
		$this->assertEquals("Equipment quantity cannot be empty or zero!", $error2);
		// check file contents
		$this->m = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->m->getEquipments()));
// 		$this->assertEquals(0, count($this->m->getSupplies()));
// 		$this->assertEquals(0, count($this->m->getStaff()));
		
	}
	
	public function testCreateEquipmentNegativeQuantity() {
		$this->assertEquals(0, count($this->m->getEquipments()));
	
		$equipmentName = "spoon";
		$equipmentQuantity = -1;
	
		$error = "";

		try {
			$this->c->createEquipment($equipmentName, $equipmentQuantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}

	
		// check error
		$this->assertEquals("Equipment quantity cannot be negative!", $error);

		// check file contents
		$this->m = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->m->getEquipments()));
		// 		$this->assertEquals(0, count($this->m->getSupplies()));
		// 		$this->assertEquals(0, count($this->m->getStaff()));
	
	}
	
	public function testCreateEquipmentZeroQuantity() {
		$this->assertEquals(0, count($this->m->getEquipments()));
	
		$equipmentName = "spoon";
		$equipmentQuantity = 0;
	
		$error = "";
	
		try {
			$this->c->createEquipment($equipmentName, $equipmentQuantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("Equipment quantity cannot be empty or zero!", $error);
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->m->getEquipments()));
		// 		$this->assertEquals(0, count($this->m->getSupplies()));
		// 		$this->assertEquals(0, count($this->m->getStaff()));
	
	}
	



	 
}
?>
