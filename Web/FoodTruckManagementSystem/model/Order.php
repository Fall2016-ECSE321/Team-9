<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private $name;
  private $suppliesNeeded;
  private $quantity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aQuantity)
  {
    $this->name = $aName;
    $this->suppliesNeeded = array();
    $this->quantity = $aQuantity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function addSuppliesNeeded($aSuppliesNeeded)
  {
    $wasAdded = false;
    $this->suppliesNeeded[] = $aSuppliesNeeded;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSuppliesNeeded($aSuppliesNeeded)
  {
    $wasRemoved = false;
    unset($this->suppliesNeeded[$this->indexOfSuppliesNeeded($aSuppliesNeeded)]);
    $this->suppliesNeeded = array_values($this->suppliesNeeded);
    $wasRemoved = true;
    return $wasRemoved;
  }

  public function setQuantity($aQuantity)
  {
    $wasSet = false;
    $this->quantity = $aQuantity;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getSuppliesNeeded($index)
  {
    $aSuppliesNeeded = $this->suppliesNeeded[$index];
    return $aSuppliesNeeded;
  }

  public function getSuppliesNeeded()
  {
    $newSuppliesNeeded = $this->suppliesNeeded;
    return $newSuppliesNeeded;
  }

  public function numberOfSuppliesNeeded()
  {
    $number = count($this->suppliesNeeded);
    return $number;
  }

  public function hasSuppliesNeeded()
  {
    $has = suppliesNeeded.size() > 0;
    return $has;
  }

  public function indexOfSuppliesNeeded($aSuppliesNeeded)
  {
    $rawAnswer = array_search($aSuppliesNeeded,$this->suppliesNeeded);
    $index = $rawAnswer == null && $rawAnswer !== 0 ? -1 : $rawAnswer;
    return $index;
  }

  public function getQuantity()
  {
    return $this->quantity;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>