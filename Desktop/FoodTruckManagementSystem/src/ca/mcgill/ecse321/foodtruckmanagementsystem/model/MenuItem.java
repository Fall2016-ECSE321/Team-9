/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-c37463a modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;

// line 32 "../../../../../../../../ump/161011249430/model.ump"
// line 66 "../../../../../../../../ump/161011249430/model.ump"
public class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private String name;
  private double price;
  private int popularityCounter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aName, double aPrice, int aPopularityCounter)
  {
    name = aName;
    price = aPrice;
    popularityCounter = aPopularityCounter;
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

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setPopularityCounter(int aPopularityCounter)
  {
    boolean wasSet = false;
    popularityCounter = aPopularityCounter;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
  }

  public int getPopularityCounter()
  {
    return popularityCounter;
  }

  public void delete()
  {}


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "popularityCounter" + ":" + getPopularityCounter()+ "]"
     + outputString;
  }
}