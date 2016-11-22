<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-c37463a modeling language!*/

class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private $name;
  private $price;
  private $popularityCounter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aPrice, $aPopularityCounter)
  {
    $this->name = $aName;
    $this->price = $aPrice;
    $this->popularityCounter = $aPopularityCounter;
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

  public function setPrice($aPrice)
  {
    $wasSet = false;
    $this->price = $aPrice;
    $wasSet = true;
    return $wasSet;
  }

  public function setPopularityCounter($aPopularityCounter)
  {
    $wasSet = false;
    $this->popularityCounter = $aPopularityCounter;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getPrice()
  {
    return $this->price;
  }

  public function getPopularityCounter()
  {
    return $this->popularityCounter;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>