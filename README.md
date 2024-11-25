# Prime Delivery Tracker (CS 2336)

This program calculates the area of the routes of Amazon Prime drivers from their route coordinates. Linked list is implemented as the database of the drivers and their routes without
using any pre-defined Java classes. Sorting and searching can be performed on the linked list. 

* The Driver.java class is comparable, with attributes: Name (String), Area (double), and comparison (static) variable (to determine whether to sort the list by driver's name or driver's area).
* The Node.java class is also comparable and doubly linked.
* The LinkedList.java class is generic to be used for any objects.

The program will read the driver route file first. Each line in the file will contain the driver's first name followed by a list of coordinates. There will be a new line at the end of each line.
The format for each line is <name><space><x0>,<y0><space><x1>,<y1>,...<xn-1><yn-1><newline>. Each line in the file represents a different driver. The first and last set of coordinates will be the same (or the input will be invalid).
All invalid inputs will be ignored. The second input file is searching and sorting information.

Valid commands:
<sort><area/driver><asc/des>: Sort the linked list by area or driver, in ascending or descending order.
<driver name>: search the linked list for the driver's name.
<number>: search the linked list for the area.

Sample input file:
driver.txt:
Greedo -2,-1 3,-1, -2,3, 1,3 4,2 3,1 1,1 1,-1, -2,-1
Jane 4,0 4,7.5, 7,7.5, 7,3 9,0 7,0 4,0, 5,0
cmd.txt:
sort area asc
Gredo
10

Sample output file:

Greedo 18
Greedo 18
Greedo 18
10 not found.
