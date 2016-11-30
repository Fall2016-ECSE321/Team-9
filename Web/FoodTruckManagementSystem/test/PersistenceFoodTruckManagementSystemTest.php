<?php
require_once __DIR__ . '../../model/Equipment.php';
require_once __DIR__ . '../../model/Manager.php';
require_once __DIR__ . '../../persistence/PersistenceFoodTruckManagementSystem.php';
class PersistenceFoodTruckManagementSystemTest extends PHPUnit_Framework_TestCase {
	protected $pm;
	protected function setUp() {
		$this->pm = new PersistenceFoodTruckManagementSystem ();
	}
	protected function tearDown() {
	}
	public function testPersistence() {
		
		// 1. Create test data
		$m = Manager::getInstance ();
		$e = new Equipment ( "table", 5 );
		$m->addEquipment ( $e );
		
		// 2. Write all of the data
		$this->pm->writeDataToStore ( $m );
		
		// 3. Clear the data from memory
		$m->delete ();
		
		$this->assertEquals ( 0, count ( $m->getEquipments () ) );
		
		// 4. Load it back in
		$m = $this->pm->loadDataFromStore ();
		
		// 5. Check that we got it back
		$this->assertEquals ( 1, count ( $m->getEquipments () ) );
		$myEquipment = $m->getEquipment_index ( 0 );
		$this->assertEquals ( "table", $myEquipment->getName () );
		$this->assertEquals ( 5, $myEquipment->getQuantity () );
	}
}	