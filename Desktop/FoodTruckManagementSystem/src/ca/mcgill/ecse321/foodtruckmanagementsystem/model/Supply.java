/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-c37463a modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;

// line 12 "../../../../../../../../ump/161011249430/model.ump"
// line 50 "../../../../../../../../ump/161011249430/model.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private String name;
  private double quantity;
  private String unit;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aName, double aQuantity, String aUnit)
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

  public boolean setQuantity(double aQuantity)
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

  public double getQuantity()
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