/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-2a9bef6 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.util.*;

// line 13 "../../../../../../../../ump/161011249430/model.ump"
// line 52 "../../../../../../../../ump/161011249430/model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private String name;
  private List<String> suppliesNeeded;
  private int quantity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(String aName, int aQuantity)
  {
    name = aName;
    suppliesNeeded = new ArrayList<String>();
    quantity = aQuantity;
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

  public boolean addSuppliesNeeded(String aSuppliesNeeded)
  {
    boolean wasAdded = false;
    wasAdded = suppliesNeeded.add(aSuppliesNeeded);
    return wasAdded;
  }

  public boolean removeSuppliesNeeded(String aSuppliesNeeded)
  {
    boolean wasRemoved = false;
    wasRemoved = suppliesNeeded.remove(aSuppliesNeeded);
    return wasRemoved;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getSuppliesNeeded(int index)
  {
    String aSuppliesNeeded = suppliesNeeded.get(index);
    return aSuppliesNeeded;
  }

  public String[] getSuppliesNeeded()
  {
    String[] newSuppliesNeeded = suppliesNeeded.toArray(new String[suppliesNeeded.size()]);
    return newSuppliesNeeded;
  }

  public int numberOfSuppliesNeeded()
  {
    int number = suppliesNeeded.size();
    return number;
  }

  public boolean hasSuppliesNeeded()
  {
    boolean has = suppliesNeeded.size() > 0;
    return has;
  }

  public int indexOfSuppliesNeeded(String aSuppliesNeeded)
  {
    int index = suppliesNeeded.indexOf(aSuppliesNeeded);
    return index;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void delete()
  {}


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "]"
     + outputString;
  }
}