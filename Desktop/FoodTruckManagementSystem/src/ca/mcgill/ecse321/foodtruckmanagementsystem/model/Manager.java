/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-c37463a modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.util.*;
import java.sql.Time;

// line 4 "../../../../../../../../ump/161011249430/model.ump"
// line 39 "../../../../../../../../ump/161011249430/model.ump"
public class Manager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Manager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Associations
  private List<Equipment> equipments;
  private List<Supply> supplies;
  private List<StaffMember> staffmembers;
  private List<MenuItem> menus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private Manager()
  {
    equipments = new ArrayList<Equipment>();
    supplies = new ArrayList<Supply>();
    staffmembers = new ArrayList<StaffMember>();
    menus = new ArrayList<MenuItem>();
  }

  public static Manager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new Manager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
  }

  public Supply getSupply(int index)
  {
    Supply aSupply = supplies.get(index);
    return aSupply;
  }

  public List<Supply> getSupplies()
  {
    List<Supply> newSupplies = Collections.unmodifiableList(supplies);
    return newSupplies;
  }

  public int numberOfSupplies()
  {
    int number = supplies.size();
    return number;
  }

  public boolean hasSupplies()
  {
    boolean has = supplies.size() > 0;
    return has;
  }

  public int indexOfSupply(Supply aSupply)
  {
    int index = supplies.indexOf(aSupply);
    return index;
  }

  public StaffMember getStaffmember(int index)
  {
    StaffMember aStaffmember = staffmembers.get(index);
    return aStaffmember;
  }

  public List<StaffMember> getStaffmembers()
  {
    List<StaffMember> newStaffmembers = Collections.unmodifiableList(staffmembers);
    return newStaffmembers;
  }

  public int numberOfStaffmembers()
  {
    int number = staffmembers.size();
    return number;
  }

  public boolean hasStaffmembers()
  {
    boolean has = staffmembers.size() > 0;
    return has;
  }

  public int indexOfStaffmember(StaffMember aStaffmember)
  {
    int index = staffmembers.indexOf(aStaffmember);
    return index;
  }

  public MenuItem getMenus(int index)
  {
    MenuItem aMenus = menus.get(index);
    return aMenus;
  }

  public List<MenuItem> getMenus()
  {
    List<MenuItem> newMenus = Collections.unmodifiableList(menus);
    return newMenus;
  }

  public int numberOfMenus()
  {
    int number = menus.size();
    return number;
  }

  public boolean hasMenus()
  {
    boolean has = menus.size() > 0;
    return has;
  }

  public int indexOfMenus(MenuItem aMenus)
  {
    int index = menus.indexOf(aMenus);
    return index;
  }

  public static int minimumNumberOfEquipments()
  {
    return 0;
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    equipments.add(aEquipment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipments.contains(aEquipment))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSupplies()
  {
    return 0;
  }

  public boolean addSupply(Supply aSupply)
  {
    boolean wasAdded = false;
    if (supplies.contains(aSupply)) { return false; }
    supplies.add(aSupply);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supply aSupply)
  {
    boolean wasRemoved = false;
    if (supplies.contains(aSupply))
    {
      supplies.remove(aSupply);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSupplyAt(Supply aSupply, int index)
  {  
    boolean wasAdded = false;
    if(addSupply(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupplyAt(Supply aSupply, int index)
  {
    boolean wasAdded = false;
    if(supplies.contains(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupplyAt(aSupply, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfStaffmembers()
  {
    return 0;
  }

  public boolean addStaffmember(StaffMember aStaffmember)
  {
    boolean wasAdded = false;
    if (staffmembers.contains(aStaffmember)) { return false; }
    staffmembers.add(aStaffmember);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStaffmember(StaffMember aStaffmember)
  {
    boolean wasRemoved = false;
    if (staffmembers.contains(aStaffmember))
    {
      staffmembers.remove(aStaffmember);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addStaffmemberAt(StaffMember aStaffmember, int index)
  {  
    boolean wasAdded = false;
    if(addStaffmember(aStaffmember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffmembers()) { index = numberOfStaffmembers() - 1; }
      staffmembers.remove(aStaffmember);
      staffmembers.add(index, aStaffmember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStaffmemberAt(StaffMember aStaffmember, int index)
  {
    boolean wasAdded = false;
    if(staffmembers.contains(aStaffmember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffmembers()) { index = numberOfStaffmembers() - 1; }
      staffmembers.remove(aStaffmember);
      staffmembers.add(index, aStaffmember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStaffmemberAt(aStaffmember, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfMenus()
  {
    return 0;
  }

  public boolean addMenus(MenuItem aMenus)
  {
    boolean wasAdded = false;
    if (menus.contains(aMenus)) { return false; }
    menus.add(aMenus);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenus(MenuItem aMenus)
  {
    boolean wasRemoved = false;
    if (menus.contains(aMenus))
    {
      menus.remove(aMenus);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenusAt(MenuItem aMenus, int index)
  {  
    boolean wasAdded = false;
    if(addMenus(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenusAt(MenuItem aMenus, int index)
  {
    boolean wasAdded = false;
    if(menus.contains(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenusAt(aMenus, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    equipments.clear();
    supplies.clear();
    staffmembers.clear();
    menus.clear();
  }

}