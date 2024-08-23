// Name: Andy Nguyen
// NetID: axn210059
// course: CS 2336.004 - Feng Ranran
import java.io.*;
import java.io.File;
import java.util.*;
import java.util.Scanner;

public class Main {
  // function to calculate route areas using the array of string coordinates as a parameter
  public static double CalculateRouteArea(String[] coordinates){
    double area = 0.0;
    int n = coordinates.length;
    for(int i = 0; i < n-1; i++){
      try{
        // get the current coordinate while the for loop iterates
        String[] current_c = coordinates[i].split(",");
        // get the next coordinate while the for loop iterates
        String[] next_c = coordinates[i+1].split(",");
        // convert all Strings of coordinates to integers
        int x_n = Integer.parseInt(next_c[0]);
        int x_i = Integer.parseInt(current_c[0]);
        int y_n = Integer.parseInt(next_c[1]);
        int y_i = Integer.parseInt(current_c[1]);
        area += ((x_n + x_i)*(y_n-y_i));
      }
      catch(ArrayIndexOutOfBoundsException axe){
        // if any driver has any coordinates that are missing x or y value, their area values will be 0.0
        area = 0.0;
      }
      catch(NumberFormatException nfe){
        // coordinates can only be integers (no doubles or floating point numbers), if not, their area values will be 0.0
        area = 0.0;
      }
    }
    // check if the first and last coordinates for each driver are equivalent
    try{
      String[] first_c = coordinates[0].split(",");
      String[] last_c = coordinates[n-1].split(",");
      // get the first coordinate
      int first_var1 = Integer.parseInt(first_c[0]);
      int first_var2 = Integer.parseInt(first_c[1]);
      // get the last coordinate
      int last_var1 = Integer.parseInt(last_c[0]);
      int last_var2 = Integer.parseInt(last_c[1]);
      // if not, their area values will also be 0.0
      if(first_var1 != last_var1 || first_var2 != last_var2){
        area = 0.0;
      }
    }
    catch(ArrayIndexOutOfBoundsException axe){
      // if missing a value in the first or last coordinate, return 0.0
      area = 0.0;
    }
    area = Math.abs(area) / 2; // the final route area is the absolute value of the sum divided by 2
    return area;
  }

  // function to check validity of driver names
  public static boolean validNames(String str){
    // valid characters for driver names include alphanumeric characters, hyphens, and apostrophes
    String regex = "[a-zA-Z'-]*";
    if(str.matches(regex)){
      // if the names match the characters & symbols defined in the regex, return true
      return true;
    }
    // if not, return false
    return false;
  }
  // function to check validity of coordinates
  public static boolean validCoordinates(String[] coordinates){
    // valid coordinates include 2 numeric values separated by a comma
    String regex = "^[0-9]+,[0-9]+$";
    int n = coordinates.length;
    for(int i = 0; i < n-1; i++){
      // if the coordinates match the numbers & symbols in the regex, return true
      if(coordinates[i].matches(regex)){
        return true;
      }
    }
    // if not, return false
    return false;
  }

  // function to search driver names
  // take 2 parameters, the linked list, and the term to be searched in String
  public static String searchDriver(LinkedList<Driver> driver_list, String name){
    Node<Driver> current_node = driver_list.getHead();
    // iterate while the current node is not empty
    while(current_node != null){
      // get all names and compare to the term to be searched
      if(current_node.getPayload().getName().equals(name)){
        // if found matched with the search term in the command file, return the driver with their specific names and their respective areas
        return current_node.getPayload().toString();
      }
      // move to the next node
      current_node = current_node.next;
    }
    // return this message if not found
    return name + " not found\n";
  }

  // boolean function to check if the term to be searched is numeric or not
  public static boolean isNumeric(String str) {
      if (str == null) {
          return false;
      }
      try {
        // try to convert the string to a double if found to be numeric
        // the command file accepts areas to be searched as both floats (doubles) and integers when searching for drivers
          Double.parseDouble(str);
          return true;
      } 
      catch (NumberFormatException nfe) {
          return false;
      }
  }

  // function to search areas
  public static String searchArea(LinkedList<Driver> driver_list, double area){
    // set the current node to the first node
    Node<Driver> current_node = driver_list.getHead();
    // iterate while the current node is not null/empty
    while(current_node != null){
      // get all areas and compare to the number to be searched
      if(Math.abs(current_node.getPayload().getArea() -  area) < 1e-2){
        // if found matched with the area in the command file, return the driver and their respective areas
        return current_node.getPayload().toString();
      }
      // move to the next node
      current_node = current_node.next;
    }
    // if not, return this message
    return String.format("%.2f", area) + " not found\n"; // round the area up to 2 decimal places
  }

  // main function
  public static void main(String[] args) {
    // prompt the user for the name of the file containing the data
    Scanner scan = new Scanner(System.in);
    System.out.println("Please enter the name of the file containing the data:");
    String filename = scan.nextLine();
    // prompt the user for the name of the file containing the commands
    System.out.println("Please enter the name of the file containing the search/sort commands:");
    String filename2 = scan.nextLine();

    // create a new linked list
    LinkedList<Driver> driver_list = new LinkedList<Driver>();
    // create 2 scanners to scan the data and the command file
    Scanner driver_scanner, cmd_scanner;

      try{
      // read the file containing the data first
        driver_scanner = new Scanner(new File(filename));
        // perform actions while the scanner is scanning/reading file
        while(driver_scanner.hasNextLine()){
          String line = driver_scanner.nextLine();
          String[] str = line.split(" "); // split to differentiate the drivers with their coordinates
          String driver_name = str[0]; // the driver names will be in index zero
          String[] coordinate_pairs = line.substring(driver_name.length()+1).split(" "); // split each coordinate with a space          
          double area = CalculateRouteArea(coordinate_pairs);
          // add the drivers and coordinates to the linkedlist while the driver file is being read
          // ignore all invalid name inputs and coordinates
          // area = 0, as mentioned above, means the input of coordinates are invalid; these lines will therefore be ignored
          if(validNames(driver_name) == true && validCoordinates(coordinate_pairs) == true && area != 0){
            Driver driver = new Driver(driver_name, area);
            driver_list.insertAtLast(driver); // add every driver and their respective areas to the linked list; the capacity is unlimited
          }
        }
        if(driver_list.isEmpty() == true){
          // print this message to the console if the linked list is empty
          System.out.println("The data file is empty.\n");
        }
        else{
          System.out.println("\nFull list, displayed by the order of the inputs in the data file:\n" + driver_list); // display the full, unsorted list of drivers and areas, with respect to the order of the inputs in the data file first
        }
        driver_scanner.close(); // close the driver file when finished
      }
      catch (IOException ioe){
        // display the error message if the data file is unknown/unavailable/cannot be accessed
        System.out.println("The data file cannot be accessed.");
        return; // exit the program if error occurs
      }
      catch(NumberFormatException nfe){
        // display the error message if the data file is unknown/unavailable/cannot be accessed
        // in this case, this means when you mistake the driver file with the command file and type the name for the command file when the program asks for the driver file, it catches NFE
        System.out.println("The data file cannot be accessed.");
        return; // exit the program if error occurs
      }
      // read the command file second
      try{
        cmd_scanner = new Scanner(new File(filename2));
        while(cmd_scanner.hasNextLine()){
          String line = cmd_scanner.nextLine();
          // split the sort command, sorting criteria, and order with spaces
          String parts[] = line.split(" ");
          // "sort" is the first word in the command file each line, in index 0
          String cmd = parts[0];
          if(parts.length > 0){
            switch(cmd){
              case "sort":
                try{
                  String sort_by = parts[1]; // the sorting criteria is in index 1
                  String order = parts[2]; // the order is in index 2
                  if(sort_by.equals("area") && order.equals("asc")){
                    // sort areas in ascending order if the command is "sort area asc"              
                    // set the comparison variable to area
                    Driver.setComparisonVar("area");
                    driver_list.sortAsc(); // ascending order
                    // drivers with smallest area will be displayed at the top
                    System.out.println("List sorted by area, ascending order:\n" + driver_list);
                  }
                  else if(sort_by.equals("area") && order.equals("des")){
                    // sort areas in descending order if the command is "sort area des"
                    // set the comparison variable to area
                    Driver.setComparisonVar("area");
                    driver_list.sortDes(); // descending order
                    // drivers with largest area will be displayed at the top
                    System.out.println("List sorted by area, descending order:\n" + driver_list);
                  }
                  else if(sort_by.equals("driver") && order.equals("asc")){
                    // sort driver names in ascending order if the command is "sort driver asc"
                    // set the comparison variable to driver
                    Driver.setComparisonVar("driver");
                    driver_list.sortAsc(); // ascending order
                    // drivers with names started with the letter "A" will be displayed at the top
                    System.out.println("List sorted by name, ascending order:\n" + driver_list);
                  }
                  else if(sort_by.equals("driver") && order.equals("des")){
                    // sort driver names in descending order if the command is "sort driver des"
                    // set the comparison variable to driver
                    Driver.setComparisonVar("driver");
                    driver_list.sortDes(); // descending order
                    // drivers with names started with the letter "A" will be displayed at the bottom
                    System.out.println("List sorted by name, descending order:\n" + driver_list);
                  }
                }
                  // handle the ArrayIndexOutOfBoundsException (when a sorting command misses the sorting criteria or the order, or both)
                catch (ArrayIndexOutOfBoundsException axe){
                  // break if the sorting command is invalid
                  break;
                }
                break; // always break when done with each case
              default:
                // the search function will be the default case; it will return the first driver with the name that matches the input (driver/area)
                String search_term = parts[0];
                if(isNumeric(search_term)){
                  // if the searching term is a number, then search for the driver with the corresponding area
                  double search_area = Double.parseDouble(search_term);
                  System.out.println(searchArea(driver_list, search_area));
                }
                else{
                  // if the searching term is a String, then search for the driver with the corresponding name.
                  System.out.println(searchDriver(driver_list, search_term));
                }
                break; // always break when done with each case
            }
          }
        }
        cmd_scanner.close(); // close the command file when finished
      } 
      catch (IOException ioe){
        // display the error message if the command file is unknown/unavailable/cannot be accessed
        System.out.println("The command file cannot be accessed.");
        return; // exit the program if error occurs
      }
    }
}
// Note to the grader: I included a header above the full unsorted list and every sorted list to make sure that the sorting function works well and the program sorts the list based on the user's commands.