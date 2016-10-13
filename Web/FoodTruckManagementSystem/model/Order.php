<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-e0b6705 modeling language!*/

class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private $name;
  private $suppliesNeeded;
  private $quantity;

  //Order Associations
  private $supplies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aQuantity)
  {
    $this->name = $aName;
    $this->suppliesNeeded = array();
    $this->quantity = $aQuantity;
    $this->supplies = array();
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

  public function getSupply_index($index)
  {
    $aSupply = $this->supplies[$index];
    return $aSupply;
  }

  public function getSupplies()
  {
    $newSupplies = $this->supplies;
    return $newSupplies;
  }

  public function numberOfSupplies()
  {
    $number = count($this->supplies);
    return $number;
  }

  public function hasSupplies()
  {
    $has = $this->numberOfSupplies() > 0;
    return $has;
  }

  public function indexOfSupply($aSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supplies as $supply)
    {
      if ($supply->equals($aSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfSupplies()
  {
    return 0;
  }

  public function addSupplyVia($aName, $aQuantity)
  {
    return new Supply($aName, $aQuantity, $this);
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $existingOrder = $aSupply->getOrder();
    $isNewOrder = $existingOrder != null && $this !== $existingOrder;
    if ($isNewOrder)
    {
      $aSupply->setOrder($this);
    }
    else
    {
      $this->supplies[] = $aSupply;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    //Unable to remove aSupply, as it must always have a order
    if ($this !== $aSupply->getOrder())
    {
      unset($this->supplies[$this->indexOfSupply($aSupply)]);
      $this->supplies = array_values($this->supplies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSupplyAt($aSupply, $index)
  {  
    $wasAdded = false;
    if($this->addSupply($aSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSupplyAt($aSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfSupply($aSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyAt($aSupply, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->supplies as $aSupply)
    {
      $aSupply->delete();
    }
  }

}
?>