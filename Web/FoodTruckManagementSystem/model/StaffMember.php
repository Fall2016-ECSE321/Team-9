<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-2a9bef6 modeling language!*/

class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private $name;
  private $role;
  private $startTimes;
  private $endTimes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aRole)
  {
    $this->name = $aName;
    $this->role = $aRole;
    $this->startTimes = array();
    $this->endTimes = array();
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

  public function setRole($aRole)
  {
    $wasSet = false;
    $this->role = $aRole;
    $wasSet = true;
    return $wasSet;
  }

  public function addStartTime($aStartTime)
  {
    $wasAdded = false;
    $this->startTimes[] = $aStartTime;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStartTime($aStartTime)
  {
    $wasRemoved = false;
    unset($this->startTimes[$this->indexOfStartTime($aStartTime)]);
    $this->startTimes = array_values($this->startTimes);
    $wasRemoved = true;
    return $wasRemoved;
  }

  public function addEndTime($aEndTime)
  {
    $wasAdded = false;
    $this->endTimes[] = $aEndTime;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEndTime($aEndTime)
  {
    $wasRemoved = false;
    unset($this->endTimes[$this->indexOfEndTime($aEndTime)]);
    $this->endTimes = array_values($this->endTimes);
    $wasRemoved = true;
    return $wasRemoved;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getRole()
  {
    return $this->role;
  }

  public function getStartTime($index)
  {
    $aStartTime = $this->startTimes[$index];
    return $aStartTime;
  }

  public function getStartTimes()
  {
    $newStartTimes = $this->startTimes;
    return $newStartTimes;
  }

  public function numberOfStartTimes()
  {
    $number = count($this->startTimes);
    return $number;
  }

  public function hasStartTimes()
  {
    $has = startTimes.size() > 0;
    return $has;
  }

  public function indexOfStartTime($aStartTime)
  {
    $rawAnswer = array_search($aStartTime,$this->startTimes);
    $index = $rawAnswer == null && $rawAnswer !== 0 ? -1 : $rawAnswer;
    return $index;
  }

  public function getEndTime($index)
  {
    $aEndTime = $this->endTimes[$index];
    return $aEndTime;
  }

  public function getEndTimes()
  {
    $newEndTimes = $this->endTimes;
    return $newEndTimes;
  }

  public function numberOfEndTimes()
  {
    $number = count($this->endTimes);
    return $number;
  }

  public function hasEndTimes()
  {
    $has = endTimes.size() > 0;
    return $has;
  }

  public function indexOfEndTime($aEndTime)
  {
    $rawAnswer = array_search($aEndTime,$this->endTimes);
    $index = $rawAnswer == null && $rawAnswer !== 0 ? -1 : $rawAnswer;
    return $index;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>