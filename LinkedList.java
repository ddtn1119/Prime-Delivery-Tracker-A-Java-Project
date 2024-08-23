// Name: Andy Nguyen
// NetID: axn210059
// course: CS 2336.004 - Feng Ranran
public class LinkedList<T extends Comparable<T>> { // the generic variable is T = type
  // this class is comparable
  // members of the LinkedList class
  private Node<T> head; // points to the beginning of the linked list
  private Node<T> tail; // points to the end of the linked list
  static int counter = 0; // counter to keep track of the number of nodes in the linked list
  // constructor
  public LinkedList(){ // null is the defaut value for both nodes
    this.head = null; 
    this.tail = null;
  }
  // overload constructor
  public LinkedList(Node<T> node){
    this.head = node;
    this.tail = node;
  }
  // accessors & mutators for LinkedList
  public Node<T> getHead(){
    return this.head;
  }

  public void setHead(Node<T> head){
    this.head = head;
  }

  public Node<T> getTail(){
    return this.tail;
  }

  public void setTail(Node<T> tail){
    this.tail = tail;
  }
  // to traverse all elements forward from the beginning to the end of the linked list
  public void moveForward(){
    Node<T> current_node = head;
    while(current_node != null){
      System.out.print(current_node.payload + " ");
      current_node = current_node.next;
    }
  }
  // to traverse all elements backward from the end to the beginning of the linked list
  public void moveBackward(){
    Node<T> current_node = tail;
    while(current_node != null){
      System.out.print(current_node.payload + " ");
      current_node = current_node.prev;
    }
  }
  // insert a node at the beginning of the list
  public void insertAtFirst(T type){
    Node<T> new_node = new Node<>(type);
    new_node.next = head;
    head = new_node;
    counter++;
    if(tail == null){
      tail = head;
    }
  }
  // insert a node at the end of the list
  public void insertAtLast(T type){
    if(tail == null){
      head = tail = new Node<>(type);
    }
    else{
      tail.next = new Node<>(type);
      tail = tail.next;
    }
    counter++;
  }
  // insert a node in a specific position/index
  public void insertAt(int index, T type){
    if(index == 0){
      insertAtFirst(type);
    }
    else if(index >= counter){
      insertAtLast(type);
    }
    else{
      Node<T> current_node = head;
      for(int i=1; i<index; i++){
        current_node = current_node.next;
      }
      Node<T> temp = current_node.next;
      current_node.next = new Node<>(type);
      (current_node.next).next = temp;
      counter++;
    }
  }
  // function to remove the element at the beginning of the list (head/first)
  public T removeFirst(){
    if(counter == 0){
      return null;
    }
    else{
      Node<T> temp = head;
      head = head.next;
      counter--;
      if(head == null){
        tail = null;
      }
      return temp.payload;
    }
  }
  // function to remove the element at the end of the list (tail/last)
  public T removeLast(){
    if(counter == 0){
      return null;
    }
    else if(counter == 1){
      Node<T> temp = head;
      head = tail = null;
      counter = 0;
      return temp.payload;
    }
    else{
      Node<T> current_node = head;
      for(int i=0; i<counter-2; i++){
        current_node = current_node.next;
      }
      Node<T> temp = tail;
      tail = current_node;
      tail.next = null;
      counter--;
      return temp.payload;
    }
  }
  // function to remove the element at the specified index
  public T removeAt(int index){
    // if the index is out of range, return null
    if(index == 0 || index >= counter){
      return null;
    }
    else if(index == 0){
      return removeFirst(); // if the index is 0, remove the first element
    }
    else if(index == counter-1){
      return removeLast(); // if the index is the last element, remove the last element
    }
    else{
      // if the index is in the middle, remove the element at the specified index
      Node<T> prev = head;
      for(int i=1; i<index; i++){
        prev = prev.next;
      }
      // once a node at the specified index is deleted, the next node becomes the new head
      Node<T> current_node = prev.next;
      prev.next = current_node.next; // the node before the deleted node becomes the new head
      counter--; // decrement the counter since a node is deleted
      return current_node.payload; // return the list after a node is deleted
    }
  }
  // function to return the size of the list (# of elements on the list)
  public static int LinkedListLength(){
    return counter;
  }
  // function to clear the whole list
  public void clearList(){
    head = null; // set the head node to null (null means empty)
    counter = 0; // set the counter back to zero once the linked list is cleared
  }
  // function to check whether the linked list is empty or not
  public boolean isEmpty(){
    if(head == null){
      return true;
    }
    else{
      return false;
    }
  }
  // function to rearrange the nodes when sorting by swapping
  private void swapNodes(Node<T> n1, Node<T> n2){
    T temp = n1.getPayload();
    n1.setPayload(n2.getPayload());
    n2.setPayload(temp);
  }
  // function to sort in ascending order
  public void sortAsc(){
    Node<T> current_node = head;
    while(current_node != null){
      // start comparing from the minimum node
      Node<T> min = current_node;
      Node<T> n = current_node.next;
      while(n != null){
        // if the current node is greater than the minimum node, swap the nodes
        if(min.getPayload().compareTo(n.getPayload()) > 0){
          min = n;
        }
        // move to the next node
        n = n.next;
      }
      // swap the current node with the minimum node (rearrange the nodes)
      if(min != current_node){
        swapNodes(min, current_node);
      }
      current_node = current_node.next;
    }
  }
  // function to sort in descending order
  public void sortDes(){
    Node<T> current_node = head;
    while(current_node != null){
      // start comparing from the maximum node
      Node<T> max = current_node;
      Node<T> n = current_node.next;
      // traverse the list
      while(n != null){
        // if the maximum node is less than the current node, then swap
        if(max.getPayload().compareTo(n.getPayload()) < 0){
          max = n;
        }
        // continue to traverse the list
        n = n.next;
      }
      if(max != current_node){
        // swap the maximum node with the current node (rearrange the nodes)
        swapNodes(max, current_node);
      }
      // continue to traverse the list
      current_node = current_node.next;
    }
  }
  
  // toString method in LinkedList class
  @Override
  public String toString(){
    // set the current node to the head node
    Node<T> current_node = head;
    // initialize string str to an empty string
    String str = "";
    // traverse the list
    while(current_node != null){
      str += current_node.payload + " "; // add the payload to the string
      current_node = current_node.next; // move to the next node
    }
    return str; // return the string
  }
}