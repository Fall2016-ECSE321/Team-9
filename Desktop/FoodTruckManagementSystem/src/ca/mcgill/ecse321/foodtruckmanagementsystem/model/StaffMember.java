/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-d348116 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.sql.Date;
import java.util.*;

// line 32 "../../../../../../../../ump/161011249430/model.ump"
// line 67 "../../../../../../../../ump/161011249430/model.ump"
public class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private String name;
  private String role;
  private List<Date> schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffMember(String aName, String aRole)
  {
    name = aName;
    role = aRole;
    schedule = new ArrayList<Date>();
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

  public boolean addSchedule(Date aSchedule)
  {
    boolean wasAdded = false;
    wasAdded = schedule.add(aSchedule);
    return wasAdded;
  }

  public boolean removeSchedule(Date aSchedule)
  {
    boolean wasRemoved = false;
    wasRemoved = schedule.remove(aSchedule);
    return wasRemoved;
  }

  public String getName()
  {
    return name;
  }

  public String getRole()
  {
    return role;
  }

  public Date getSchedule(int index)
  {
    Date aSchedule = schedule.get(index);
    return aSchedule;
  }

  public Date[] getSchedule()
  {
    Date[] newSchedule = schedule.toArray(new Date[schedule.size()]);
    return newSchedule;
  }

  public int numberOfSchedule()
  {
    int number = schedule.size();
    return number;
  }

  public boolean hasSchedule()
  {
    boolean has = schedule.size() > 0;
    return has;
  }

  public int indexOfSchedule(Date aSchedule)
  {
    int index = schedule.indexOf(aSchedule);
    return index;
  }

  public void delete()
  {}


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "role" + ":" + getRole()+ "]"
     + outputString;
  }
}