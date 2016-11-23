/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-c37463a modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.sql.Time;
import java.util.*;

// line 25 "../../../../../../../../ump/161011249430/model.ump"
// line 61 "../../../../../../../../ump/161011249430/model.ump"
public class StaffMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffMember Attributes
  private String name;
  private String role;
  private List<Time> startTimes;
  private List<Time> endTimes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffMember(String aName, String aRole)
  {
    name = aName;
    role = aRole;
    startTimes = new ArrayList<Time>();
    endTimes = new ArrayList<Time>();
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

  public boolean addStartTime(Time aStartTime)
  {
    boolean wasAdded = false;
    wasAdded = startTimes.add(aStartTime);
    return wasAdded;
  }

  public boolean removeStartTime(Time aStartTime)
  {
    boolean wasRemoved = false;
    wasRemoved = startTimes.remove(aStartTime);
    return wasRemoved;
  }

  public boolean addEndTime(Time aEndTime)
  {
    boolean wasAdded = false;
    wasAdded = endTimes.add(aEndTime);
    return wasAdded;
  }

  public boolean removeEndTime(Time aEndTime)
  {
    boolean wasRemoved = false;
    wasRemoved = endTimes.remove(aEndTime);
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

  public Time getStartTime(int index)
  {
    Time aStartTime = startTimes.get(index);
    return aStartTime;
  }

  public Time[] getStartTimes()
  {
    Time[] newStartTimes = startTimes.toArray(new Time[startTimes.size()]);
    return newStartTimes;
  }

  public int numberOfStartTimes()
  {
    int number = startTimes.size();
    return number;
  }

  public boolean hasStartTimes()
  {
    boolean has = startTimes.size() > 0;
    return has;
  }

  public int indexOfStartTime(Time aStartTime)
  {
    int index = startTimes.indexOf(aStartTime);
    return index;
  }

  public Time getEndTime(int index)
  {
    Time aEndTime = endTimes.get(index);
    return aEndTime;
  }

  public Time[] getEndTimes()
  {
    Time[] newEndTimes = endTimes.toArray(new Time[endTimes.size()]);
    return newEndTimes;
  }

  public int numberOfEndTimes()
  {
    int number = endTimes.size();
    return number;
  }

  public boolean hasEndTimes()
  {
    boolean has = endTimes.size() > 0;
    return has;
  }

  public int indexOfEndTime(Time aEndTime)
  {
    int index = endTimes.indexOf(aEndTime);
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