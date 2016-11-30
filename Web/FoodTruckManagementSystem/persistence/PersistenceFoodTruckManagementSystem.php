<?php
require_once __DIR__ . '../../model/Manager.php';
class PersistenceFoodTruckManagementSystem {
	private $filename;
	function __construct($filename = 'data.txt') {
		$this->filename = $filename;
	}
	function loadDataFromStore() {
		if (file_exists ( $this->filename )) {
			$str = file_get_contents ( $this->filename );
			$m = unserialize ( $str );
		} else {
			$m = Manager::getInstance ();
		}
		return $m;
	}
	function writeDataToStore($m) {
		$str = serialize ( $m );
		file_put_contents ( $this->filename, $str );
	}
}
?>