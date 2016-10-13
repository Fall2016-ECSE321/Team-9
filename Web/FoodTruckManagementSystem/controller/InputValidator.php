<?php
class InputValidator{
	public static function validate_input($data){
		$data = trim($data);
		$data = stripslashes($data);
		$data = htmlspecialchars($data);
		if (is_string($data)){
			$data = strtolower($data); //ensures all strings are stored as lower case
		}
		return $data;
	}
}
?> 