<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-e0b6705 modeling language!*/

class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private $name;
  private $quantity;

  //Supply Associations
  private $order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aQuantity, $aOrder)
  {
    $this->name = $aName;
    $this->quantity = $aQuantity;
    $didAddOrder = $this->setOrder($aOrder);
    if (!$didAddOrder)
    {
      throw new Exception("Unable to create supply due to order");
    }
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

  public function getQuantity()
  {
    return $this->quantity;
  }

  public function getOrder()
  {
    return $this->order;
  }

  public function setOrder($aOrder)
  {
    $wasSet = false;
    if ($aOrder == null)
    {
      return $wasSet;
    }
    
    $existingOrder = $this->order;
    $this->order = $aOrder;
    if ($existingOrder != null && $existingOrder != $aOrder)
    {
      $existingOrder->removeSupply($this);
    }
    $this->order->addSupply($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderOrder = $this->order;
    $this->order = null;
    $placeholderOrder->removeSupply($this);
  }

}
?>