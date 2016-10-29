<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-d348116 modeling language!*/

class Manager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Associations
  private $equipments;
  private $supplies;
  private $orders;
  private $staffmembers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->equipments = array();
    $this->supplies = array();
    $this->orders = array();
    $this->staffmembers = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new Manager();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipments[$index];
    return $aEquipment;
  }

  public function getEquipments()
  {
    $newEquipments = $this->equipments;
    return $newEquipments;
  }

  public function numberOfEquipments()
  {
    $number = count($this->equipments);
    return $number;
  }

  public function hasEquipments()
  {
    $has = $this->numberOfEquipments() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipments as $equipment)
    {
      if ($equipment->equals($aEquipment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
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

  public function getOrder_index($index)
  {
    $aOrder = $this->orders[$index];
    return $aOrder;
  }

  public function getOrders()
  {
    $newOrders = $this->orders;
    return $newOrders;
  }

  public function numberOfOrders()
  {
    $number = count($this->orders);
    return $number;
  }

  public function hasOrders()
  {
    $has = $this->numberOfOrders() > 0;
    return $has;
  }

  public function indexOfOrder($aOrder)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->orders as $order)
    {
      if ($order->equals($aOrder))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getStaffmember_index($index)
  {
    $aStaffmember = $this->staffmembers[$index];
    return $aStaffmember;
  }

  public function getStaffmembers()
  {
    $newStaffmembers = $this->staffmembers;
    return $newStaffmembers;
  }

  public function numberOfStaffmembers()
  {
    $number = count($this->staffmembers);
    return $number;
  }

  public function hasStaffmembers()
  {
    $has = $this->numberOfStaffmembers() > 0;
    return $has;
  }

  public function indexOfStaffmember($aStaffmember)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->staffmembers as $staffmember)
    {
      if ($staffmember->equals($aStaffmember))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEquipments()
  {
    return 0;
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $this->equipments[] = $aEquipment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipment($aEquipment) != -1)
    {
      unset($this->equipments[$this->indexOfEquipment($aEquipment)]);
      $this->equipments = array_values($this->equipments);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipments()) { $index = $this->numberOfEquipments() - 1; }
      array_splice($this->equipments, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipments, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipments()) { $index = $this->numberOfEquipments() - 1; }
      array_splice($this->equipments, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipments, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfSupplies()
  {
    return 0;
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $this->supplies[] = $aSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfSupply($aSupply) != -1)
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

  public static function minimumNumberOfOrders()
  {
    return 0;
  }

  public function addOrder($aOrder)
  {
    $wasAdded = false;
    if ($this->indexOfOrder($aOrder) !== -1) { return false; }
    $this->orders[] = $aOrder;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeOrder($aOrder)
  {
    $wasRemoved = false;
    if ($this->indexOfOrder($aOrder) != -1)
    {
      unset($this->orders[$this->indexOfOrder($aOrder)]);
      $this->orders = array_values($this->orders);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addOrderAt($aOrder, $index)
  {  
    $wasAdded = false;
    if($this->addOrder($aOrder))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOrderAt($aOrder, $index)
  {
    $wasAdded = false;
    if($this->indexOfOrder($aOrder) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOrderAt($aOrder, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfStaffmembers()
  {
    return 0;
  }

  public function addStaffmember($aStaffmember)
  {
    $wasAdded = false;
    if ($this->indexOfStaffmember($aStaffmember) !== -1) { return false; }
    $this->staffmembers[] = $aStaffmember;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStaffmember($aStaffmember)
  {
    $wasRemoved = false;
    if ($this->indexOfStaffmember($aStaffmember) != -1)
    {
      unset($this->staffmembers[$this->indexOfStaffmember($aStaffmember)]);
      $this->staffmembers = array_values($this->staffmembers);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStaffmemberAt($aStaffmember, $index)
  {  
    $wasAdded = false;
    if($this->addStaffmember($aStaffmember))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffmembers()) { $index = $this->numberOfStaffmembers() - 1; }
      array_splice($this->staffmembers, $this->indexOfStaffmember($aStaffmember), 1);
      array_splice($this->staffmembers, $index, 0, array($aStaffmember));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStaffmemberAt($aStaffmember, $index)
  {
    $wasAdded = false;
    if($this->indexOfStaffmember($aStaffmember) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffmembers()) { $index = $this->numberOfStaffmembers() - 1; }
      array_splice($this->staffmembers, $this->indexOfStaffmember($aStaffmember), 1);
      array_splice($this->staffmembers, $index, 0, array($aStaffmember));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStaffmemberAt($aStaffmember, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->equipments = array();
    $this->supplies = array();
    $this->orders = array();
    $this->staffmembers = array();
  }

}
?>