//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Solving three different problems using a queue implemented
//                  with circular indexing.
// Files:           QueueTests.java     DessertSolvers.java     Guest.java
//                  ServingQueue.java
// Course:          CS 300, Spring 2019
//
// Author:          Yash Sukhwani
// Email:           sukhwani@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   XXX Write-up states that pair programming is allowed for this assignment.
//   XXX We have both read and understand the course Pair Programming Policy.
//   XXX We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains test methods for the ServingQueue class. Methods in this class test specific
 * methods in that class. All the test methods are static and return a boolean result.
 * 
 * @author yashsukhwani
 *
 */

public class QueueTests {

  /**
   * This method tests the isEmpty method from the ServingQueue class.
   * 
   * @param none
   * @return true / false
   */
  public static boolean testIsEmpty() {

    Guest.resetNextGuestIndex(); // ensuring the indexing of guests starts at 1 for this test

    ServingQueue testQueue = new ServingQueue(5); // creating a new serving queue for this method

    // testing the isEmpty() method before guests are added to the queue
    if (!testQueue.isEmpty() == true) {
      System.out.println("WARNING! isEmpty failed on an empty list.");
      return false;
    }

    // creating new guest objects for this method
    Guest test1 = new Guest();
    Guest test2 = new Guest();
    Guest test3 = new Guest("United");
    Guest test4 = new Guest("Unite");
    Guest test5 = new Guest("Unit");

    // adding the guests to the queue
    testQueue.add(test1);
    testQueue.add(test2);
    testQueue.add(test3);
    testQueue.add(test4);
    testQueue.add(test5);

    // testing the isEmpty() method after guests are added to the queue
    if (!testQueue.isEmpty() == false) {
      System.out.println("WARNING! isEmpty failed on a non-empty list.");
      return false;
    }

    return true; // method returns true if none of the tests fail
  }

  /**
   * This method tests the remove method from the ServingQueue class.
   * 
   * @param none
   * @return true / false
   */
  public static boolean testRemove() {

    Guest.resetNextGuestIndex(); // ensuring the indexing of guests starts at 1 for this test

    ServingQueue testQueue = new ServingQueue(5); // creating a new serving queue for this method

    // creating new guest objects for this method
    Guest test1 = new Guest();
    Guest test2 = new Guest();
    Guest test3 = new Guest("United");
    Guest test4 = new Guest("Unite");
    Guest test5 = new Guest("Unit");

    // adding the guests to the queue
    testQueue.add(test1);
    testQueue.add(test2);
    testQueue.add(test3);

    // checks that test1 is removed first (because it was added first)
    if (!testQueue.remove().equals(test1)) {
      System.out.println("WARNING! Failed to correctly remove a guest.");
      return false;
    }

    testQueue.add(test4); // adds a guest to see whether it affects the running of the remove method

    // checks that test2 is removed second (because it was added second)
    if (!testQueue.remove().equals(test2)) {
      System.out.println("WARNING! Failed to correctly remove a guest.");
      return false;
    }

    testQueue.add(test5); // adds a guest to see whether it affects the running of the remove method

    // checks that test3 is removed next (because it was added after test2)
    if (!testQueue.remove().equals(test3)) {
      System.out.println("WARNING! Failed to correctly remove a guest.");
      return false;
    }
    return true; // method returns true if none of the tests fail
  }

  /**
   * This method tests the peek method from the ServingQueue class.
   * 
   * @param none
   * @return true / false
   */
  public static boolean testPeek() {

    Guest.resetNextGuestIndex(); // ensuring the indexing of guests starts at 1 for this test

    ServingQueue testQueue = new ServingQueue(5); // creating a new serving queue for this method

    // creating new guest objects for this method
    Guest test1 = new Guest();
    Guest test2 = new Guest();
    Guest test3 = new Guest("United");
    Guest test4 = new Guest("Unite");
    Guest test5 = new Guest("Unit");

    // adding the guests to the queue
    testQueue.add(test1);
    testQueue.add(test2);
    testQueue.add(test3);

    // checks that test1 would currently be the first to be removed
    if (!testQueue.peek().equals(test1)) {
      System.out.println("WARNING! Failed to correctly peek for a guest.");
      return false;
    }

    testQueue.add(test4); // adds a guest to see whether it affects the running of the peek method
    testQueue.remove(); // removes one guest (test1 if working correctly so far)

    // now checks that test2 would currently be the first to be removed
    if (!testQueue.peek().equals(test2)) {
      System.out.println("WARNING! Failed to correctly peek for a guest.");
      return false;
    }

    testQueue.add(test5); // adds a guest to see whether it affects the running of the peek method
    testQueue.remove(); // removes one guest (test2 if working correctly so far)

    // now checks that test3 would currently be the first to be removed
    if (!testQueue.peek().equals(test3)) {
      System.out.println("WARNING! Failed to correctly peek for a guest.");
      return false;
    }
    return true; // method returns true if none of the tests fail
  }

  public static void main(String[] args) {

    System.out.println("testPeek() passed: " + testPeek());
    System.out.println("testRemove() passed: " + testRemove());
    System.out.println("testIsEmpty() passed: " + testIsEmpty());
  }
}
