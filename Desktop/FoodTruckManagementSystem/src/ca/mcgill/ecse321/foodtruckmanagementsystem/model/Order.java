/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.util.*;

// line 12 "../../../../../FoodTruckManagementSystem.ump"
// line 48 "../../../../../FoodTruckManagementSystem.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private String name;
  private List<String> suppliesNeeded;
  private int quantity;

  //Order Associations
  private List<Supply> supplies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(String aName, int aQuantity)
  {
    name = aName;
    suppliesNeeded = new ArrayList<String>();
    quantity = aQuantity;
    supplies = new ArrayList<Supply>();
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

  public static int minimumNumberOfSupplies()
  {
    return 0;
  }

  public Supply addSupply(String aName, int aQuantity)
  {
    return new Supply(aName, aQuantity, this);
  }

  public boolean addSupply(Supply aSupply)
  {
    boolean wasAdded = false;
    if (supplies.contains(aSupply)) { return false; }
    Order existingOrder = aSupply.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aSupply.setOrder(this);
    }
    else
    {
      supplies.add(aSupply);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supply aSupply)
  {
    boolean wasRemoved = false;
    //Unable to remove aSupply, as it must always have a order
    if (!this.equals(aSupply.getOrder()))
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

  public void delete()
  {
    for(int i=supplies.size(); i > 0; i--)
    {
      Supply aSupply = supplies.get(i - 1);
      aSupply.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "]"
     + outputString;
  }
}