/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-2a9bef6 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.sql.Date;
import java.util.*;
import java.sql.Time;

// line 32 "../../../../../../../../ump/161011249430/model.ump"
// line 69 "../../../../../../../../ump/161011249430/model.ump"
public class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private String name;
  private String role;
  private List<Date> schedule;
  private List<Time> startTime;
  private List<Time> endTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffMember(String aName, String aRole)
  {
    name = aName;
    role = aRole;
    schedule = new ArrayList<Date>();
    startTime = new ArrayList<Time>();
    endTime = new ArrayList<Time>();
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

  public boolean addStartTime(Time aStartTime)
  {
    boolean wasAdded = false;
    wasAdded = startTime.add(aStartTime);
    return wasAdded;
  }

  public boolean removeStartTime(Time aStartTime)
  {
    boolean wasRemoved = false;
    wasRemoved = startTime.remove(aStartTime);
    return wasRemoved;
  }

  public boolean addEndTime(Time aEndTime)
  {
    boolean wasAdded = false;
    wasAdded = endTime.add(aEndTime);
    return wasAdded;
  }

  public boolean removeEndTime(Time aEndTime)
  {
    boolean wasRemoved = false;
    wasRemoved = endTime.remove(aEndTime);
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

  public Time getStartTime(int index)
  {
    Time aStartTime = startTime.get(index);
    return aStartTime;
  }

  public Time[] getStartTime()
  {
    Time[] newStartTime = startTime.toArray(new Time[startTime.size()]);
    return newStartTime;
  }

  public int numberOfStartTime()
  {
    int number = startTime.size();
    return number;
  }

  public boolean hasStartTime()
  {
    boolean has = startTime.size() > 0;
    return has;
  }

  public int indexOfStartTime(Time aStartTime)
  {
    int index = startTime.indexOf(aStartTime);
    return index;
  }

  public Time getEndTime(int index)
  {
    Time aEndTime = endTime.get(index);
    return aEndTime;
  }

  public Time[] getEndTime()
  {
    Time[] newEndTime = endTime.toArray(new Time[endTime.size()]);
    return newEndTime;
  }

  public int numberOfEndTime()
  {
    int number = endTime.size();
    return number;
  }

  public boolean hasEndTime()
  {
    boolean has = endTime.size() > 0;
    return has;
  }

  public int indexOfEndTime(Time aEndTime)
  {
    int index = endTime.indexOf(aEndTime);
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