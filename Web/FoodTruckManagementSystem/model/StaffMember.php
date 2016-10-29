<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-d348116 modeling language!*/

class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private $name;
  private $role;
  private $schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aRole)
  {
    $this->name = $aName;
    $this->role = $aRole;
    $this->schedule = array();
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

  public function addSchedule($aSchedule)
  {
    $wasAdded = false;
    $this->schedule[] = $aSchedule;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSchedule($aSchedule)
  {
    $wasRemoved = false;
    unset($this->schedule[$this->indexOfSchedule($aSchedule)]);
    $this->schedule = array_values($this->schedule);
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

  public function getSchedule($index)
  {
    $aSchedule = $this->schedule[$index];
    return $aSchedule;
  }

  public function getSchedule()
  {
    $newSchedule = $this->schedule;
    return $newSchedule;
  }

  public function numberOfSchedule()
  {
    $number = count($this->schedule);
    return $number;
  }

  public function hasSchedule()
  {
    $has = schedule.size() > 0;
    return $has;
  }

  public function indexOfSchedule($aSchedule)
  {
    $rawAnswer = array_search($aSchedule,$this->schedule);
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