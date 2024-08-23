// Name: Andy Nguyen
// generic Node class for the LinkedList
// doubly linked list, each node contains 3 data members, link to both the previous node and the next node
// store values of any data type
public class Node<T extends Comparable<T>> { 
  // this class is comparable
  // public members of the Node class
  T payload; // generic value of node to store data (payload): T - type
  Node<T> prev; // reference to the previous node
  Node<T> next; // reference to the next node
  // constructors
  public Node (){
    this.payload = null;
    this.prev = null;
    this.next = null;
  }
  // overloaded constructor
  public Node (T payload){
    this.payload = payload; // set the class member to the parameter
    this.prev = null;
    this.next = null;
  }
  // accessors
  public T getPayload(){ // get the payload data
    return payload; 
  }

  public Node<T> getPrevious(){ // get the previous node
    return prev;
  }

  public Node<T> getNext(){ // get the next node
    return next;
  }
  // mutators
  public void setPayload(T payload){ // set the payload data to certain values.
    this.payload = payload;
  }

  public void setPrevious(Node<T> prev){ // set the previous nodes to certain values.
    this.prev = prev;
  }

  public void setNext(Node<T> next){ // set the next nodes to certain values.
    this.next = next;
  }
  
  @Override
  public String toString(){ // toString method in Node class
    return payload.toString(); // return the data in String
  }
}
