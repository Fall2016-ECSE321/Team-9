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

		$error_msg1 = "Equipment name cannot be empty! Equipment quantity cannot be negative!";
		$error_msg2 = "Equipment quantity cannot be negative!";
		
		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg2, $error3 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
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

		$error_msg1 = "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!";
		$error_msg2 = "Equipment quantity cannot be empty or zero!";
		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg2, $error3 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getEquipments () ) );
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
		
		$error_msg1 = "Equipment name cannot be empty! Equipment quantity cannot be negative!";
		$error_msg2 = "Equipment quantity cannot be negative!";
		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg2, $error3 );
	
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

		$error_msg1 = "Equipment name cannot be empty! Equipment quantity cannot be empty or zero!";
		$error_msg2 = "Equipment quantity cannot be empty or zero!";

		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg2, $error3 );
	
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
		
		$error_msg1 = "Supply name cannot be empty! Supply quantity cannot be negative! Supply unit cannot be empty!";
		$error_msg2 = "Supply quantity cannot be negative! Supply unit cannot be empty!";
		$error_msg3 = "Supply name cannot be empty! Supply quantity cannot be negative!";
		$error_msg4 = "Supply quantity cannot be negative!";

		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg1, $error3 );
		$this->assertEquals ( $error_msg1, $error4 );
		$this->assertEquals ( $error_msg1, $error5 );
		$this->assertEquals ( $error_msg1, $error6 );
		$this->assertEquals ( $error_msg1, $error7 );
		$this->assertEquals ( $error_msg1, $error8 );
		$this->assertEquals ( $error_msg2, $error9 );
		$this->assertEquals ( $error_msg2, $error10 );
		$this->assertEquals ( $error_msg2, $error11 );
		$this->assertEquals ( $error_msg3, $error12 );
		$this->assertEquals ( $error_msg3, $error13 );
		$this->assertEquals ( $error_msg3, $error14 );
		$this->assertEquals ( $error_msg4, $error15 );
			
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
		
		$error_msg1 = "Supply name cannot be empty! Supply quantity cannot be empty or zero! Supply unit cannot be empty!";
		$error_msg2 = "Supply quantity cannot be empty or zero! Supply unit cannot be empty!";
		$error_msg3 = "Supply name cannot be empty! Supply quantity cannot be empty or zero!";
		$error_msg4 = "Supply quantity cannot be empty or zero!";
		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg1, $error3 );
		$this->assertEquals ( $error_msg1, $error4 );
		$this->assertEquals ( $error_msg1, $error5 );
		$this->assertEquals ( $error_msg1, $error6 );
		$this->assertEquals ( $error_msg1, $error7 );
		$this->assertEquals ( $error_msg1, $error8 );
		$this->assertEquals ( $error_msg2, $error9 );
		$this->assertEquals ( $error_msg2, $error10 );
		$this->assertEquals ( $error_msg2, $error11 );
		$this->assertEquals ( $error_msg3, $error12 );
		$this->assertEquals ( $error_msg3, $error13 );
		$this->assertEquals ( $error_msg3, $error14 );
		$this->assertEquals ( $error_msg4, $error15 );
		
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
		
		$error_msg1 = "Supply name cannot be empty! Supply quantity cannot be negative!";
		$error_msg2 = "Supply quantity cannot be negative!";
		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg2, $error3 );
	
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
		
		$error_msg1 = "Supply name cannot be empty! Supply quantity cannot be empty or zero!";
		$error_msg2 = "Supply quantity cannot be empty or zero!";
		// check error
		$this->assertEquals ( $error_msg1, $error0 );
		$this->assertEquals ( $error_msg1, $error1 );
		$this->assertEquals ( $error_msg1, $error2 );
		$this->assertEquals ( $error_msg2, $error3 );
	
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
	
	public function testCreateStaffMember() {
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
	
		$name = "jim";
		$role = "cook";
	
		try {
			$this->c->createStaffMember($name, $role);
		} catch ( Exception $e ) {
			// check that no error occurred
			$this->fail ();
		}
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals ( $name, $this->m->getStaffmember_index(0)->getName () );
		$this->assertEquals ( $role, $this->m->getStaffmember_index(0)->getRole () );
	}
	
	public function testCreateStaffMemberNull() {
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
	
		$name0 = null;
		$name1 = "jim";
		$role0 = null;
		$role1 = "cook";
		
		$error0="";
		$error1="";
		$error2="";
	
		try {
			$this->c->createStaffMember($name0, $role0);
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createStaffMember($name0, $role1);
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createStaffMember($name1, $role0);
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		$this->assertEquals ( "Staff Member name cannot be empty! Staff Member role cannot be empty!", $error0 );
		$this->assertEquals ( "Staff Member name cannot be empty!", $error1 );
		$this->assertEquals ( "Staff Member role cannot be empty!", $error2 );
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );	
	}
	
	public function testCreateStaffMemberEmpty() {
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
	
		$name0 = "";
		$name1 = "jim";
		$role0 = "";
		$role1 = "cook";
	
		$error0="";
		$error1="";
		$error2="";
	
		try {
			$this->c->createStaffMember($name0, $role0);
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createStaffMember($name0, $role1);
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createStaffMember($name1, $role0);
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		$this->assertEquals ( "Staff Member name cannot be empty! Staff Member role cannot be empty!", $error0 );
		$this->assertEquals ( "Staff Member name cannot be empty!", $error1 );
		$this->assertEquals ( "Staff Member role cannot be empty!", $error2 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
	}
	
	public function testCreateStaffMemberSpaces() {
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
	
		$name0 = " ";
		$name1 = "jim";
		$role0 = " ";
		$role1 = "cook";
	
		$error0="";
		$error1="";
		$error2="";
	
		try {
			$this->c->createStaffMember($name0, $role0);
		} catch ( Exception $e ) {
			$error0 = $e->getMessage ();
		}
		try {
			$this->c->createStaffMember($name0, $role1);
		} catch ( Exception $e ) {
			$error1 = $e->getMessage ();
		}
		try {
			$this->c->createStaffMember($name1, $role0);
		} catch ( Exception $e ) {
			$error2 = $e->getMessage ();
		}
	
		$this->assertEquals ( "Staff Member name cannot be empty! Staff Member role cannot be empty!", $error0 );
		$this->assertEquals ( "Staff Member name cannot be empty!", $error1 );
		$this->assertEquals ( "Staff Member role cannot be empty!", $error2 );
	
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
	}
	
	public function testCreateStaffMemberAlreadyExists(){
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		
		$name = "jim";
		$role = "cook";
		$error = "";
		
		try {
			$this->c->createStaffMember($name, $role);
		} catch ( Exception $e ) {
			$error = $e->getMessage();
		}
		
		// check file contents
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals ( $name, $this->m->getStaffmember_index(0)->getName () );
		$this->assertEquals ( $role, $this->m->getStaffmember_index(0)->getRole () );
		$this->assertEquals ( "Staff Member already exists!",$error );
	}
	
	public function testRemoveStaffMember(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$name = "jim";
		
		try{
			$this->c->removeStaffMember($name);
		} catch (Exception $e){
			$this->fail();
		}
		
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 0, count ( $this->m->getStaffmembers () ) );
	}
	
	public function testRemoveStaffMemberNull(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$name = null;
		$error = "";
	
		try{
			$this->c->removeStaffMember($name);
		} catch (Exception $e){
			$error = $e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("Staff Member name cannot be empty!", $error);
	}
	
	public function testRemoveStaffMemberEmpty(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$name = "";
		$error = "";
	
		try{
			$this->c->removeStaffMember($name);
		} catch (Exception $e){
			$error = $e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("Staff Member name cannot be empty!", $error);
	}
	
	public function testRemoveStaffMemberSpaces(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$name = " ";
		$error = "";
	
		try{
			$this->c->removeStaffMember($name);
		} catch (Exception $e){
			$error = $e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("Staff Member name cannot be empty!", $error);
	}
	
	public function testRemoveStaffMemberDoesNotExist(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		
		$name = "sam";
		$error = "";
	
		try{
			$this->c->removeStaffMember($name);
		} catch (Exception $e){
			$error = $e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("Staff Member does not exist!", $error);
	}
	
	public function testAddTimeStaffMember(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		
		$name = "jim";
		$error = "";
		$startTime = array("1","2","3","4","5","6","7");
		$endTime = array("2","3","4","5","6","7","8");
		
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$this->fail();
		}
		
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		
	}
	
	public function testAddTimeStaffMemberNull(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = null;
		$error = "";
		$startTime = array("1","2","3","4","5","6","7");
		$endTime = array("2","3","4","5","6","7","8");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
		
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("Staff Member name cannot be empty!", $error);	
	}
	
	public function testAddTimeStaffMemberEmpty(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = "";
		$error = "";
		$startTime = array("1","2","3","4","5","6","7");
		$endTime = array("2","3","4","5","6","7","8");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("Staff Member name cannot be empty!", $error);
	}
	
	public function testAddTimeStaffMemberSpaces(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = " ";
		$error = "";
		$startTime = array("1","2","3","4","5","6","7");
		$endTime = array("2","3","4","5","6","7","8");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("Staff Member name cannot be empty!", $error);
	}
	
	public function testAddTimeStaffMemberMultipleEqualTime(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = "jim";
		$error = "";
		$startTime = array("02:00","03:00","04:00","04:00","05:00","07:00","07:00");
		$endTime = array("02:00","03:00","04:00","05:00","06:00","07:00","08:00");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("End time cannot equal start time!", $error);
	}
	
	public function testAddTimeStaffMemberSingleEqualTime(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = "jim";
		$error = "";
		$startTime = array("03:00","02:00","03:00","04:00","05:00","06:00","07:00");
		$endTime = array("03:00","03:00","04:00","05:00","06:00","07:00","08:00");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals ( 1, count ( $this->m->getStaffmembers () ) );
		$this->assertEquals("End time cannot equal start time!", $error);
	}
	
	public function testAddTimeStaffMemberDoesNotExist(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("joe", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = "jim";
		$error = "";
		$startTime = array("1","2","3","4","5","6","7");
		$endTime = array("2","3","4","5","6","7","8");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$this->assertEquals("Staff Member does not exist!", $error);
	}
	
	public function testAddTimeStaffMemberEndBeforeStart(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = "jim";
		$error = "";
		$startTime = array("12:12","02:00","03:00","04:00","17:00","06:20","21:42");
		$endTime = array("12:11","03:00","02:00","05:00","06:00","06:10","08:00");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$this->assertEquals("End time must be greater than start time!", $error);
	}
	
	public function testAddTimeStaffMemberOnlyStartTimeEntered(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = "jim";
		$error = "";
		$startTime = array("03:00","02:00","03:00","04:00","05:00","06:00","07:00");
		$endTime = array("","03:00","04:00","05:00","06:00","07:00","08:00");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$this->assertEquals("End time is empty!", $error);
	}
	
	public function testAddTimeStaffMemberOnlyEndTimeEntered(){
		$this->assertEquals(0, count($this->m->getStaffmembers()));
		$this->c->createStaffMember("jim", "cook");
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
	
		$name = "jim";
		$error = "";
		$startTime = array("03:00","02:00","03:00","04:00","05:00","06:00","");
		$endTime = array("4:00","03:00","04:00","05:00","06:00","07:00","08:00");
	
		try{
			$this->c->addTimeStaffMember($name, $startTime, $endTime);
		} catch (Exception $e){
			$error=$e->getMessage();
		}
	
		$this->m = $this->pm->loadDataFromStore ();
		$this->assertEquals(1, count($this->m->getStaffmembers()));
		$this->assertEquals("Start time is empty!", $error);
	}
	
}
?>
