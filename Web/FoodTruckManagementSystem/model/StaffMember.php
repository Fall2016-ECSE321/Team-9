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
  private $schedule;
  private $startTime;
  private $endTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aRole)
  {
    $this->name = $aName;
    $this->role = $aRole;
    $this->schedule = array();
    $this->startTime = array();
    $this->endTime = array();
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

  public function addStartTime($aStartTime)
  {
    $wasAdded = false;
    $this->startTime[] = $aStartTime;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStartTime($aStartTime)
  {
    $wasRemoved = false;
    unset($this->startTime[$this->indexOfStartTime($aStartTime)]);
    $this->startTime = array_values($this->startTime);
    $wasRemoved = true;
    return $wasRemoved;
  }

  public function addEndTime($aEndTime)
  {
    $wasAdded = false;
    $this->endTime[] = $aEndTime;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEndTime($aEndTime)
  {
    $wasRemoved = false;
    unset($this->endTime[$this->indexOfEndTime($aEndTime)]);
    $this->endTime = array_values($this->endTime);
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

  public function getStartTime($index)
  {
    $aStartTime = $this->startTime[$index];
    return $aStartTime;
  }

  public function getStartTime()
  {
    $newStartTime = $this->startTime;
    return $newStartTime;
  }

  public function numberOfStartTime()
  {
    $number = count($this->startTime);
    return $number;
  }

  public function hasStartTime()
  {
    $has = startTime.size() > 0;
    return $has;
  }

  public function indexOfStartTime($aStartTime)
  {
    $rawAnswer = array_search($aStartTime,$this->startTime);
    $index = $rawAnswer == null && $rawAnswer !== 0 ? -1 : $rawAnswer;
    return $index;
  }

  public function getEndTime($index)
  {
    $aEndTime = $this->endTime[$index];
    return $aEndTime;
  }

  public function getEndTime()
  {
    $newEndTime = $this->endTime;
    return $newEndTime;
  }

  public function numberOfEndTime()
  {
    $number = count($this->endTime);
    return $number;
  }

  public function hasEndTime()
  {
    $has = endTime.size() > 0;
    return $has;
  }

  public function indexOfEndTime($aEndTime)
  {
    $rawAnswer = array_search($aEndTime,$this->endTime);
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