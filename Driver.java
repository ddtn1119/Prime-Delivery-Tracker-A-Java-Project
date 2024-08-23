// Name: Andy Nguyen
// NetID: axn210059
// course: CS 2336.004 - Feng Ranran
public class Driver implements Comparable<Driver> {
  // comparable class
  // private members of the Driver class
  private String name;
  private double area;
  // static comparison variable (to determine the linked list sort criteria (by names/areas))
  public static String comparison_var;
  // accessors
  public String getName(){
    return name;
  }
  public double getArea(){
    return area;
  }
  public static String getComparisonVar(){
    return comparison_var;
  }
  // mutators
  public void setName(String n){
    this.name = n;
  }
  public void setArea(double a){
    this.area = a;
  }
  public static void setComparisonVar(String cvar){
    comparison_var = cvar;
  }
  // constructors
  public Driver(){
    // default constructor
  }
  /// overload constructors
  public Driver(String n){ // pass in driver names (String)
    this.name = n;
  }
  public Driver(String n, double a){ // pass in both driver names (String) and areas (double)
    this.name = n;
    this.area = a;
  }
  // compareTo method implementation (this method is provided by the Comparable interface to compare two variables/values)
  // determine how to sort the linked list
  public int compareTo(Driver other){
    if(comparison_var.equals("driver")){
      // compare the driver names by their alphabetical order if the comparison variable is "driver"
      return this.name.compareTo(other.name);
    }
    else if(comparison_var.equals("area")){
      // compare the areas if the comparison variable is "area"
      return Double.compare(this.area, other.area);
    }
    else{
      // else if the comparison variable equals neither of the words above, return 0
      return 0;
    }
  }

  // toString method implementation
  // to display the result on the console
  @Override
  public String toString(){
    return name + "\t" + String.format("%.2f", area) + "\n"; // return the name and area (round the area up to 2 decimal places)
  }
}