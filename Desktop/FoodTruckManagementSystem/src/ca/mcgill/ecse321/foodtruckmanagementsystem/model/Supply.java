/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;

// line 19 "../../../../../FoodTruckManagementSystem.ump"
// line 54 "../../../../../FoodTruckManagementSystem.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private String name;
  private int quantity;

  //Supply Associations
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aName, int aQuantity, Order aOrder)
  {
    name = aName;
    quantity = aQuantity;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create supply due to order");
    }
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

  public int getQuantity()
  {
    return quantity;
  }

  public Order getOrder()
  {
    return order;
  }

  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeSupply(this);
    }
    order.addSupply(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Order placeholderOrder = order;
    this.order = null;
    placeholderOrder.removeSupply(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null")
     + outputString;
  }
}