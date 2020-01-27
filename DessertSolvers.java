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
 * This class contains two static methods that can be used to solve two different variations of the
 * dilemma about which guest on a table gets served dessert first. It does not contain any fields.
 * 
 * @author yashsukhwani
 *
 */

public class DessertSolvers {

  /**
   * This method calculates which guest will be served a single course in a meal last. Because that
   * means the same guest would be served the next course first. Hence this method is ideal for a
   * two course meal, where dessert is the last meal. The method is able to account for different
   * number of guests on the table and different serving patterns (regarding how many guests before
   * serving the guest next in line).
   * 
   * @param numberOfGuests
   * @param guestsSkipped
   * @throws IllegalArgumentException if numberOfGuests parameter is not positive.
   * @throws IllegalArgumentException if the guestsSkipped parameter is negative
   * @return servedLast
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {

    Guest.resetNextGuestIndex(); // ensuring the indexing of guests starts at 1 for this problem

    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException("WARNING: Number of guests is not positive.");
    }

    if (guestsSkipped < 0) {
      throw new IllegalArgumentException("WARNING: Number of guests skipped cannot be negative.");
    }

    // this queue contains the guests who have not yet been served
    ServingQueue toBeServed = new ServingQueue(numberOfGuests);

    // this queue stores the guests who were served so far in the order in which they were served
    ServingQueue beenServed = new ServingQueue(numberOfGuests);

    for (int i = 0; i < numberOfGuests; i++) { // adding all guests to the queue (yet to serve)
      toBeServed.add(new Guest());
    }

    int beenServedSize = 0; // tracks the size of the queue named 'beenServed'

    while (beenServedSize != numberOfGuests - 1) {

      beenServed.add(toBeServed.remove()); // this statement represents serving a guest

      for (int i = 0; i < guestsSkipped; i++) { // allowing number of skips to be varied by the user
        toBeServed.add(toBeServed.remove()); // this line executes a single skip
      }
      beenServedSize++; // updating the number of guests been served
    }

    Guest servedLast = toBeServed.remove(); // storing a reference to the last guest to be served
    beenServed.add(servedLast); // adding the guest served last to the queue of guests been served

    return servedLast;
  }

  /**
   * This method calculates which guest will be served dessert first. It is able to account for
   * variable number of guests and variable amount of courses in the meal. This method assumes that
   * every alternate guest yet to be served is served until all guests are served.
   * 
   * @param numberOfGuests
   * @param coursesServed
   * @throws IllegalArgumentException if the numberOfGuests parameter is negative
   * @throws IllegalArgumentException if the coursesServed argument is negative
   * @return servedLast
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {

    Guest.resetNextGuestIndex(); // ensuring the indexing of guests starts at 1 for this problem

    if (numberOfGuests < 0) {
      throw new IllegalArgumentException("WARNING! Number of guests cannot be negative.");
    }

    if (coursesServed < 0) {
      throw new IllegalArgumentException("WARNING! Number of courses cannot be negative.");
    }

    // this queue contains the guests who have not yet been served
    ServingQueue toBeServed = new ServingQueue(numberOfGuests);

    // this queue stores the guests who were served so far in the order in which they were served
    ServingQueue beenServed = new ServingQueue(numberOfGuests);

    for (int i = 0; i < numberOfGuests; i++) { // adding all guests to the queue (yet to serve)
      toBeServed.add(new Guest());
    }

    if (coursesServed == 1) { // default return case if coursesServed parameter contains value 1
      return toBeServed.peek();
    }

    int beenServedSize = 0; // tracks the size of the queue named 'beenServed'
    int courseNum = 0; // tracks the number of courses been served so far
    Guest servedLast = null; // indicates the guest who was served last for each course

    while (courseNum < coursesServed - 1) {

      while (beenServedSize != numberOfGuests - 1) {
        beenServed.add(toBeServed.remove()); // this statement represents serving a guest

        toBeServed.add(toBeServed.remove()); // this statement represents skipping a guest
        beenServedSize++; // updating the number of guests been served
      }

      // storing a reference to the guest who was served that particular course last
      servedLast = toBeServed.remove();

      beenServed.add(servedLast); // adding the guest served last to the queue of guests been served

      beenServedSize = 0; // setting back to zero for the next iteration of the loop

      // For the next course, all the guests need to be served again. Hence they are copied back
      // to the toBeServed queue, but starting with the guest who received the previous course last

      toBeServed.add(servedLast);
      // the guest who got served the previous course last gets served the next course first

      for (int i = 0; i < numberOfGuests - 1; i++) {
        // moving guests from beenServed to toBeServed to with the order maintained
        toBeServed.add(beenServed.remove());
      }
      beenServed.remove(); // removing last guest to make the queue empty again for next course

      courseNum++; // updating the number of courses been served
    }
    // returning the guest who was served the second-last course last. He / she gets dessert first
    return servedLast;
  }
}
