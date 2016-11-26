<?php
class InputValidator{
	public static function validate_input($data){
		$data = trim($data);
		$data = stripslashes($data);
		$data = htmlspecialchars($data);
		//this if is for the case where a price is entered with more than 2 decimals
		if (filter_var($data, FILTER_VALIDATE_FLOAT)){
			$data = floor($data*100)/100;
		}
		if (is_string($data)){
			$data = strtolower($data); //ensures all strings are stored as lower case
		}
		return $data;
	}
}
?> 