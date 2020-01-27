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
 * This class represents guests at a dinner party. It contains all the fields and methods that apply
 * to an object of this class.
 *
 * @author yashsukhwani
 *
 */
public class Guest {

  private static int nextIndex = 1; // tracks the index to be assigned to the next guest created
  private int guestIndex = 0; // used to uniquely identify a guest
  private String dietaryRestriction = null; // tracks the guest's dietary restrictions


  /**
   * Resets the counting of newly constructed guest indexes, so that the next new Guest that is
   * created will be associated with the guest index of one.
   * 
   * Note: that this will be helpful when running several tests, or solving solving several dessert
   * simulation problems. And that this should never be called from ServingQueue.java
   * 
   * @param None
   * @return None
   */
  public static void resetNextGuestIndex() {
    nextIndex = 1; // starts over the indexing for new guest objects
  }

  /**
   * Constructs a new guest with no dietary restrictions. This guest should be associated with an
   * index that is one larger than the previously constructed guest (or 1, if no prior guest, or if
   * resetNextGuestIndex() was called more recently).
   * 
   * @param None
   * @return None
   */
  public Guest() {
    this.guestIndex = nextIndex;
    nextIndex++; // so that each guest is given a unique index

  }

  /**
   * Constructs a new guest with the specified dietary restrictions. This guest should be associated
   * with an index that is one larger than the previously constructed guest (or 1, if no prior
   * guest, or if resetNextGuestIndex() was called more recently).
   * 
   * @param dietaryRestriction describes requirements for what this guest can and cannot eat
   * @return None
   */
  public Guest(String dietaryRestriction) {

    // only if the field if changed from null does the guest have a dietary restriction
    this.dietaryRestriction = dietaryRestriction;

    this.guestIndex = nextIndex;
    nextIndex++; // so that each guest is given a unique index
  }

  /**
   * Access whether this guest has any dietary restrictions or not
   * 
   * @param None
   * @return true for guests constructed using new Guest(String), false otherwise
   */
  public boolean hasDietaryRestriction() {

    return this.dietaryRestriction != null; // null indicates no dietary restriction specified
  }

  /**
   * The string representation of a Guest should be formatted as, for examples: #3(no dairy) for a
   * guest with a guest index of 3 and the dietary restriction of "no dairy", or: #4 for a guest
   * with a guest index of 4 and no dietary restriction
   * 
   * @param None
   * @return string representing the guest index and any dietary restriction they might have
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {

    if (this.hasDietaryRestriction()) {
      return "#" + this.guestIndex + "(" + this.dietaryRestriction + ")";
    } else {
      return "#" + this.guestIndex;
    }
  }
}
