<?php
require_once __DIR__ . '../../model/Equipment.php';
require_once __DIR__ . '../../model/Manager.php';
require_once __DIR__.'../../model/Supply.php';
//require_once __DIR__.'../../model/Order.php';
//require_once __DIR__.'../../model/StaffMember.php';
require_once __DIR__ . '../../persistence/PersistenceFoodTruckManagementSystem.php';
require_once __DIR__ . '../../controller/Controller.php';

class ControllerTest extends PHPUnit_Framework_TestCase {
	protected $c;
	protected $pm;
	protected $m;
	protected function setUp() {
		$this->c = new Controller ();
		$this->pm = new PersistenceFoodTruckManagementSystem ();
		$this->m = $this->pm->loadDataFromStore ();
		$this->m->delete ();
		$this->pm->writeDataToStore ( $this->m );
	}
	
	protected function tearDown() {
	}
	
	public function testCreateEquipment() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		
		$equipmentName = "knife";
		$equipmentQuantity = 3;
		
		try {
			$this->c->createEquipment ( $equipmentName, $equipmentQuantity );
		} catch ( Exception $e ) {
			// check that no error occurred
			$this->fail ();
		}
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
		$this->assertEquals ( $equipmentName, $this->m->getEquipment_index ( 0 )->getName () );
		$this->assertEquals ( $equipmentQuantity, $this->m->getEquipment_index ( 0 )->getQuantity () );
		
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testCreateEquipmentNull() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		
		$equipmentName0 = null;
		$equipmentQuantity0 = null;
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
		
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName1, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty!", $error1 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error2 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testCreateEquipmentEmpty() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		
		$equipmentName0 = "";
		$equipmentQuantity0 = "";
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
		
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName1, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty!", $error1 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error2 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testCreateEquipmentSpaces() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		
		$equipmentName0 = " ";
		$equipmentQuantity0 = " ";
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
		
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName1, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty!", $error1 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error2 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testCreateEquipmentNegativeQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		
		$equipmentName1 = "";
		$equipmentName2 = " ";
		$equipmentName3 = Null;
		$equipmentName4 = "spoon";
		$equipmentQuantity = - 1;
		
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
		
		try {
			$this->c->createEquipment ( $equipmentName1, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName2, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName3, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName4, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be negative!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be negative!", $error1 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be negative!", $error2 );
		$this->assertEquals ( "Equipment quantity cannot be negative!", $error3 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testCreateEquipmentZeroQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		
		$equipmentName1 = "";
		$equipmentName2 = " ";
		$equipmentName3 = Null;
		$equipmentName4 = "spoon";
		$equipmentQuantity = 0;
		
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
		
		try {
			$this->c->createEquipment ( $equipmentName1, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName2, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName3, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName4, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error1 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error2 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error3 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testRemoveEquipment() {
		$equipmentName = "knife";
		$equipmentQuantity = 3;
		$equipmentQuantity1 = 2;
		
 		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );	
		$this->c->createEquipment($equipmentName, $equipmentQuantity);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
		
		try {
			$this->c->removeEquipment($equipmentName, $equipmentQuantity1);
		} catch ( Exception $e ) {
			// check that no error occurred
			$this->fail ();
		}
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	}

	public function testRemoveEntireEquipment() {
		$equipmentName = "knife";
		$equipmentQuantity = 3;
	
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		$this->c->createEquipment($equipmentName, $equipmentQuantity);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	
		try {
			$this->c->removeEquipment($equipmentName, $equipmentQuantity);
		} catch ( Exception $e ) {
			// check that no error occurred
			$this->fail ();
		}
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
	}
	
	public function testRemoveEquipmentNull() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		$this->c->createEquipment("Cutting board", 3);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
		
		$equipmentName0 = null;
		$equipmentQuantity0 = null;
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->removeEquipment ( $equipmentName0, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName0, $equipmentQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName1, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty!", $error1 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error2 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testRemoveEquipmentEmpty() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		$this->c->createEquipment("Cutting board", 3);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	
		$equipmentName0 = "";
		$equipmentQuantity0 = "";
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName1, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty!", $error1 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error2 );
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testRemoveEquipmentSpaces() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		$this->c->createEquipment("Cutting board", 3);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	
		$equipmentName0 = " ";
		$equipmentQuantity0 = " ";
		$equipmentName1 = "Cutting board";
		$equipmentQuantity1 = 2;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName0, $equipmentQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createEquipment ( $equipmentName1, $equipmentQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty!", $error1 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error2 );
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testRemoveEquipmentNegativeQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		$this->c->createEquipment("spoon", 3);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	
		$equipmentName1 = "";
		$equipmentName2 = " ";
		$equipmentName3 = Null;
		$equipmentName4 = "spoon";
		$equipmentQuantity = - 1;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
	
		try {
			$this->c->removeEquipment ( $equipmentName1, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName2, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName3, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName4, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be negative!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be negative!", $error1 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be negative!", $error2 );
		$this->assertEquals ( "Equipment quantity cannot be negative!", $error3 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	}
	
	public function testRemoveEquipmentZeroQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		$this->c->createEquipment("spoon", 3);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	
		$equipmentName1 = "";
		$equipmentName2 = " ";
		$equipmentName3 = Null;
		$equipmentName4 = "spoon";
		$equipmentQuantity = 0;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
	
		try {
			$this->c->removeEquipment ( $equipmentName1, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName2, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName3, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->removeEquipment ( $equipmentName4, $equipmentQuantity );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		// check error
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error1 );
		$this->assertEquals ( "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!", $error2 );
		$this->assertEquals ( "Equipment quantity cannot be empty or zero!", $error3 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	}
	
	public function testRemoveEquipmentNegativeResult(){
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
		$equipmentQuantity1 = 3;
		$this->c->createEquipment("chair", $equipmentQuantity1);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	
	
		$equipmentName = "chair";
		$equipmentQuantity = 4;
	
		$error;
	
		try {
			$this->c->removeEquipment ($equipmentName,$equipmentQuantity);
		} catch ( Exception $e){
			$error = $e->getMessage();
		}
	
		//check error
		$this->assertEquals( "Equipment quantity is only: ".$equipmentQuantity1, $error);
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getEquipments () ) );
	}
	
	public function testCreateSupply() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	
		$supplyName = "carrot";
		$supplyQuantity = 3;
		$supplyUnit = "kg";
	
		try {
			$this->c->createSupply ( $supplyName, $supplyQuantity, $supplyUnit );
		} catch ( Exception $e ) {
			// check that no error occurred
			$this->fail ();
		}
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
		$this->assertEquals ( $supplyName, $this->m->getSupply_index ( 0 )->getName () );
		$this->assertEquals ( $supplyQuantity, $this->m->getSupply_index ( 0 )->getQuantity () );
		$this->assertEquals ( $supplyUnit, $this->m->getSupply_index ( 0 )->getUnit () );
	}
	
	public function testCreateSupplyNull() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = null;
		$supplyQuantity0 = null;
		$supplyUnit0 = null;
		$supplyName1 = "carrot";
		$supplyQuantity1 = 2;
		$supplyUnit1 = "kg";
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
		$error4 = "";
		$error5 = "";
		$error6 = "";
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity0, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity1, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
			try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity0, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity1, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity0, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error4 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity1, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error5 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity0, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error6 = $e->getMessage ();
		}
		
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty!", $error1 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error2 );
		$this->assertEquals ( "Supply unit cannot be empty!", $error3);
		$this->assertEquals ( "Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error4);
		$this->assertEquals ( "Supply name cannot be empty! Supply unit cannot be empty!", $error5);
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error6);
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	}
	
	public function testCreateSupplyEmpty() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = "";
		$supplyQuantity0 = "";
		$supplyUnit0 = "";
		$supplyName1 = "carrot";
		$supplyQuantity1 = 2;
		$supplyUnit1 = "kg";
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
		$error4 = "";
		$error5 = "";
		$error6 = "";
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity0, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity1, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity0, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity1, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity0, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error4 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity1, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error5 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity0, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error6 = $e->getMessage ();
		}
	
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty!", $error1 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error2 );
		$this->assertEquals ( "Supply unit cannot be empty!", $error3);
		$this->assertEquals ( "Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error4);
		$this->assertEquals ( "Supply name cannot be empty! Supply unit cannot be empty!", $error5);
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error6);
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	}
	
	public function testCreateSupplySpaces() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = " ";
		$supplyQuantity0 = " ";
		$supplyUnit0 = " ";
		$supplyName1 = "carrot";
		$supplyQuantity1 = 2;
		$supplyUnit1 = "kg";
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
		$error4 = "";
		$error5 = "";
		$error6 = "";
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity0, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity1, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity0, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity1, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity0, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error4 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity1, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error5 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity0, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error6 = $e->getMessage ();
		}
	
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty!", $error1 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error2 );
		$this->assertEquals ( "Supply unit cannot be empty!", $error3);
		$this->assertEquals ( "Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error4);
		$this->assertEquals ( "Supply name cannot be empty! Supply unit cannot be empty!", $error5);
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error6);
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	}
	
	public function testCreateSupplyNegativeQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = "";
		$supplyName1 = " ";
		$supplyName2 = null;
		$supplyName3 = "carrot";
		
		$supplyQuantity = -1;

		$supplyUnit0 = "";
		$supplyUnit1 = " ";
		$supplyUnit2 = null;
		$supplyUnit3 = "kg";

	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
		$error4 = "";
		$error5 = "";
		$error6 = "";
		$error7 = "";
		$error8 = "";
		$error9 = "";
		$error10 = "";
		$error11 = "";
		$error12 = "";
		$error13 = "";
		$error14 = "";
		$error15 = "";
		
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error4 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error5 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error6 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error7 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error8 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error9 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error10 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error11 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error12 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error13 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error14 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error15 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error1 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error2 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error3 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error4 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error5 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error6 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error7 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!", $error8 );
		$this->assertEquals ( "Supply quantity cannot be negative! Supply unit cannot be empty!", $error9 );
		$this->assertEquals ( "Supply quantity cannot be negative! Supply unit cannot be empty!", $error10 );
		$this->assertEquals ( "Supply quantity cannot be negative! Supply unit cannot be empty!", $error11 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative!", $error12 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative!", $error13 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative!", $error14 );
		$this->assertEquals ( "Supply quantity cannot be negative!", $error15 );
			
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	}
	
	public function testCreateSupplyZeroQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = "";
		$supplyName1 = " ";
		$supplyName2 = null;
		$supplyName3 = "carrot";
	
		$supplyQuantity = 0;
	
		$supplyUnit0 = "";
		$supplyUnit1 = " ";
		$supplyUnit2 = null;
		$supplyUnit3 = "kg";
	
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
		$error4 = "";
		$error5 = "";
		$error6 = "";
		$error7 = "";
		$error8 = "";
		$error9 = "";
		$error10 = "";
		$error11 = "";
		$error12 = "";
		$error13 = "";
		$error14 = "";
		$error15 = "";
	
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error4 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error5 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error6 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error7 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error8 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit0 );
		} catch ( Exception $e ) {
			$error9 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit1 );
		} catch ( Exception $e ) {
			$error10 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit2 );
		} catch ( Exception $e ) {
			$error11 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName0, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error12 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName1, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error13 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName2, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error14 = $e->getMessage ();
		}
		try {
			$this->c->createSupply ( $supplyName3, $supplyQuantity, $supplyUnit3 );
		} catch ( Exception $e ) {
			$error15 = $e->getMessage ();
		}
		
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error1 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error2 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error3 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error4 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error5 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error6 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error7 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error8 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error9 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error10 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero! Supply unit cannot be empty!", $error11 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error12 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error13 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error14 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error15 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	}
	
	public function testCreateSupplyDifferentUnit() {	
		$supplyName = "carrot";
		$supplyQuantity0 = 3;
		$supplyQuantity1 = 2;
		$supplyUnit0 = "kg";
		$supplyUnit1 = "mg";
		
		$error = "";
		
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply($supplyName, $supplyQuantity0, $supplyUnit0);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		try {
			$this->c->createSupply ( $supplyName, $supplyQuantity1, $supplyUnit1 );
		} catch ( Exception $e ) {
			// check that no error occurred
			$error=$e->getMessage();
		}
		
		$this->assertEquals("Supply unit does not match: ".$supplyUnit0, $error);
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
		$this->assertEquals ( $supplyName, $this->m->getSupply_index ( 0 )->getName () );
		$this->assertEquals ( $supplyQuantity0, $this->m->getSupply_index ( 0 )->getQuantity () );
		$this->assertEquals ( $supplyUnit0, $this->m->getSupply_index ( 0 )->getUnit () );
	}
	
	public function testRemoveSupply() {
		$supplyName = "potato";
		$supplyUnit = "kg";
		$supplyQuantity = 3;
		$supplyQuantity1 = 2;
	
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply($supplyName, $supplyQuantity, $supplyUnit);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		try {
			$this->c->removeSupply($supplyName, $supplyQuantity1);
		} catch ( Exception $e ) {
			// check that no error occurred
			$this->fail ();
		}
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	}
	
	public function testRemoveEntireSupply() {
		$supplyName = "potato";
		$supplyUnit = "kg";
		$supplyQuantity = 3;
	
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply($supplyName, $supplyQuantity, $supplyUnit);
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		try {
			$this->c->removeSupply($supplyName, $supplyQuantity);
		} catch ( Exception $e ) {
			// check that no error occurred
			$this->fail ();
		}
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
	}
	
	public function testRemoveSupplyNull() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply("apples", 3, "kg");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = null;
		$supplyQuantity0 = null;
		$supplyName1 = "apples";
		$supplyQuantity1 = 2;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->removeSupply ( $supplyName0, $supplyQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName0, $supplyQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName1, $supplyQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty!", $error1 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error2 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
		// $this->assertEquals(0, count($this->m->getSupplies()));
		// $this->assertEquals(0, count($this->m->getStaff()));
	}
	
	public function testRemoveSupplyEmpty() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply("apples", 3, "kg");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = "";
		$supplyQuantity0 = "";
		$supplyName1 = "apples";
		$supplyQuantity1 = 2;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->removeSupply ( $supplyName0, $supplyQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName0, $supplyQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName1, $supplyQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty!", $error1 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error2 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	}
	
	public function testRemoveSupplySpaces() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply("apples", 3, "kg");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		$supplyName0 = " ";
		$supplyQuantity0 = " ";
		$supplyName1 = "apples";
		$supplyQuantity1 = 2;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		try {
			$this->c->removeSupply ( $supplyName0, $supplyQuantity0 );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName0, $supplyQuantity1 );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName1, $supplyQuantity0 );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty!", $error1 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error2 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	}
	
	public function testRemoveSupplyNegativeQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply("apples", 3, "kg");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		$supplyName1 = "";
		$supplyName2 = " ";
		$supplyName3 = Null;
		$supplyName4 = "apples";
		$supplyQuantity = - 1;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
	
		try {
			$this->c->removeSupply ( $supplyName1, $supplyQuantity );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName2, $supplyQuantity );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName3, $supplyQuantity );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName4, $supplyQuantity );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative!", $error1 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be negative!", $error2 );
		$this->assertEquals ( "Supply quantity cannot be negative!", $error3 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	}
	
	public function testRemoveSupplyZeroQuantity() {
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$this->c->createSupply("apples", 3, "kg");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	
		$supplyName1 = "";
		$supplyName2 = " ";
		$supplyName3 = Null;
		$supplyName4 = "apples";
		$supplyQuantity = 0;
	
		$error0 = "";
		$error1 = "";
		$error2 = "";
		$error3 = "";
	
		try {
			$this->c->removeSupply ( $supplyName1, $supplyQuantity );
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName2, $supplyQuantity );
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName3, $supplyQuantity );
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
		try {
			$this->c->removeSupply ( $supplyName4, $supplyQuantity );
		} catch ( Exception $e ) {
			$error3 = $e->getMessage ();
		}
	
		// check error
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error0 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error1 );
		$this->assertEquals ( "Supply name cannot be empty! Supply quantity cannot be empty or zero!", $error2 );
		$this->assertEquals ( "Supply quantity cannot be empty or zero!", $error3 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	}
	
	public function testRemoveSupplyNegativeResult(){
		$this->assertEquals ( 0, count ( $this->m->getSupplies () ) );
		$supplyQuantity1 = 3;
		$this->c->createSupply("apples", $supplyQuantity1, "kg");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
		

		$supplyName = "apples";
		$supplyQuantity = 4;
		
		$error;
		
		try {
			$this->c->removeSupply ($supplyName,$supplyQuantity);
		} catch ( Exception $e){
			$error = $e->getMessage();
		}
		
		//check error
		$this->assertEquals( "Supply quantity is only: ".$supplyQuantity1, $error);
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getSupplies () ) );
	}
}
?>
