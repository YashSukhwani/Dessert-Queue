////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:            Solving three different problems using a queue implemented
//                  with circular indexing.
//Files:            QueueTests.java     DessertSolvers.java     Guest.java
//                  ServingQueue.java
//Course:           CS 300, Spring 2019
//
//Author:           Yash Sukhwani
//Email:            sukhwani@wisc.edu
//Lecturer's Name:  Gary Dahl
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    (name of your pair programming partner)
//Partner Email:   (email address of your programming partner)
//Partner Lecturer's Name: (name of your partner's lecturer)
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//XXX Write-up states that pair programming is allowed for this assignment.
//XXX We have both read and understand the course Pair Programming Policy.
//XXX We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         None
//Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class represents a queue that simulates the order in which guests will be served their meal
 * on a dinner table. This class contains the relevant fields and methods needed to implement this.
 * The queue is actually implemented as a circular-indexed array.
 * 
 * @author yashsukhwani
 *
 */

public class ServingQueue {

  private Guest[] array; // this array will be used to implement a circular queue for guest objects
  private int size; // tracks the size of the queue
  private int enqueueTo = 0; // tracks the array index at which a new guest should be added

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   * @return None
   */
  public ServingQueue(int seatsAtTable) {

    // creating the array that will use circular indexing and store the guest objects
    array = new Guest[seatsAtTable];
  }

  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @param None
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {

    return this.size == 0; // returns the boolean result of this expression
  }

  /**
   * Adds a single new guest to this queue (to be served after the others that were previously added
   * to the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   * @return None
   */
  public void add(Guest newGuest) {
    if (this.size == array.length) {
      throw new IllegalStateException("WARNING: The queue is full.");
    }

    if (newGuest == null) {
      System.out.println("WARNING! Cannot add a null item to the queue.");
      return; // not adding the null item to the queue prevents NullPointerExceptions in the future
    }

    array[enqueueTo] = newGuest; // adds the new guest at the position indicated by enqueueTo
    this.enqueueTo = (enqueueTo + 1) % array.length; // updates the value of enqueueTo

    this.size++; // updating the size variable
  }

  /**
   * Accessor for the guest that has been in this queue for the longest. This method does not add or
   * remove any guests.
   * 
   * @param None
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    if (this.isEmpty()) {
      throw new IllegalStateException("WARNING! Cannot remove elements from an empty queue.");
    }

    // returning a reference to the Guest object that would be the first to remove
    return array[(enqueueTo + array.length - this.size) % array.length];
    // the formula correctly points to the object that has been in the queue the longest
  }

  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @param None
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    if (this.isEmpty()) {
      throw new IllegalStateException("WARNING! Cannot remove elements from an empty queue.");
    }

    // a reference to to the object to remove is stored in the variable named 'toReturn'
    Guest toReturn = array[(enqueueTo + array.length - this.size) % array.length];
    // the formula correctly ensures the item that has been in the queue longest is removed


    // the location in the queue containing the item to be removed is now set to null after
    array[(enqueueTo + array.length - this.size) % array.length] = null;

    this.size--; // size variable is updated
    return toReturn; // toReturn contained the Guest object that this method would return
  }

  /**
   * The string representation of the guests in this queue should display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue the longest, to the guest that has been in
   * this queue the shortest. When called on an empty ServingQueue, returns "[]".
   * 
   * @param None
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {

    // this field tracks the index of the next guest object to print out
    int removeFrom = (enqueueTo + array.length - this.size) % array.length;
    // it is initialized to the index of the first guest object to print out

    int counter = 0; // indicates the number of guest objects printed out so far
    String toReturn = "["; // this variable represents the string that will be returned

    while (counter < this.size) {
      if (counter == this.size - 1) {
        toReturn += array[removeFrom] + "]"; // closing brakcets added when the last item is printed
      } else {
        toReturn += array[removeFrom] + ", ";
      } // adding more guest objects to the string to return
      counter++;
      removeFrom = (removeFrom + 1) % array.length; // updating the removeFrom value
    }
    return toReturn;

  }
}
