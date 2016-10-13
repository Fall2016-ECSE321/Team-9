<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-e0b6705 modeling language!*/

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

  public function __construct($aName, $aRole, $aSchedule)
  {
    $this->name = $aName;
    $this->role = $aRole;
    $this->schedule = $aSchedule;
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

  public function setSchedule($aSchedule)
  {
    $wasSet = false;
    $this->schedule = $aSchedule;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getRole()
  {
    return $this->role;
  }

  public function getSchedule()
  {
    return $this->schedule;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>