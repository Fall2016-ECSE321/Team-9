/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.sql.Date;

// line 30 "../../../../../FoodTruckManagementSystem.ump"
// line 64 "../../../../../FoodTruckManagementSystem.ump"
public class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private String name;
  private String role;
  private Date schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffMember(String aName, String aRole, Date aSchedule)
  {
    name = aName;
    role = aRole;
    schedule = aSchedule;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setRole(String aRole)
  {
    boolean wasSet = false;
    role = aRole;
    wasSet = true;
    return wasSet;
  }

  public boolean setSchedule(Date aSchedule)
  {
    boolean wasSet = false;
    schedule = aSchedule;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getRole()
  {
    return role;
  }

  public Date getSchedule()
  {
    return schedule;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "role" + ":" + getRole()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "schedule" + "=" + (getSchedule() != null ? !getSchedule().equals(this)  ? getSchedule().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}