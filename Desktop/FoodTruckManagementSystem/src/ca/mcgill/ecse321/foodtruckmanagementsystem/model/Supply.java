/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;

// line 19 "../../../../../../../../ump/161011249430/model.ump"
// line 55 "../../../../../../../../ump/161011249430/model.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private String name;
  private int quantity;
  private String unit;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aName, int aQuantity, String aUnit)
  {
    name = aName;
    quantity = aQuantity;
    unit = aUnit;
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

  public boolean setUnit(String aUnit)
  {
    boolean wasSet = false;
    unit = aUnit;
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

  public String getUnit()
  {
    return unit;
  }

  public void delete()
  {}


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "," +
            "unit" + ":" + getUnit()+ "]"
     + outputString;
  }
}