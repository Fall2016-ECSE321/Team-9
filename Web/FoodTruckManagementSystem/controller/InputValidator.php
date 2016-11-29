<?php
class InputValidator{
	public static function validate_input($data){
		$data = trim($data);
		$data = stripslashes($data);
		$data = htmlspecialchars($data);
		//this if is for the case where a price is entered with more than 2 decimals
		if (filter_var($data, FILTER_VALIDATE_FLOAT)){
			//this ensure the data is rounded down, and stored as 2 decimals
			$data = (floatval(intval(floor($data*100)))/(floatval(100.00)));
		}
		if (is_string($data)){
			$data = strtolower($data); //ensures all strings are stored as lower case
		}
		return $data;
	}
}
?> 