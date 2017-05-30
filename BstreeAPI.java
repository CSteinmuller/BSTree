/*
 * BstreeAPI.java
 *
 * Created on April 13, 2007, 8:34 PM
 *
 */

/**
  * @file BstreeAPI.java
  * @author Christopher Steinmuller
  * @date 2009-5-01
  * Description: Implements the BstreeAPI interface
  * Course: CSC1351.01
  * PAWS ID: cstein1
  * Project #: 5
  * Instructor: Duncan
  */



/**
 *
 * @author Duncan
 */
public interface BstreeAPI<E extends Comparable>
{
   /**
      Determine whether the binary search tree is empty.
      @return this function returns true if the tree is empty;
      otherwise, it returns false if the tree contains at least one node.
      @param none
   */
   boolean isEmpty();

   /**
      Inserts an item into the tree.
      @param item the value to be inserted.
      @return none.
   */
   void insert(E item);

   /**
      Determine whether an item is in the tree.
      @param item item with a specified search key.
      @return true on success; false on failure.
   */
   boolean inTree(E item);

   /**
      Delete an item from the tree.
      @param item item with a specified search key.
      @return none.
   */
   void remove(E item);

   /**
      returns a reference to the item in the tree with the specified
      key. If the item does not exists, an exception occurs.
      @param key the key to the item to be retrieved.
      @return it with the specified key.
   */
   E retrieve(E key) throws BstreeException;

   /**
      This method traverses a binary tree in inorder
      and calls the function Visit once for each node.
   */
   void preorderTraverse(VisitorFunction func);
   
   /**
      This method returns the size of the binary search tree
   */
   int size();

    /**
    * This method traverses a binary tree in inorder
    * and calls the method visit in the func class once for each node.
    * @param func an object containing the visit method that is applied
    * to the data field in every node as the tree is traversed.
    */
    void inorderTraverse(VisitorFunction func);
 /**
    * This method traverses a binary tree in postorder
    * and calls the method visit in the func class once for each node.
    * @param func an object containing the visit method that is applied
    * to the data field in every node as the tree is traversed.
    */
    void postorderTraverse(VisitorFunction func);
 /**
    * This method removes every node that has one child from the binary search tree.
    */
    void fullify();
/**
  * This method removes all terminal (leaf) nodes from the tree, retaining
  * all its internal nodes.
  */
  void trim();
/**
  * This method counts the number of nodes that have either a left child or a
  * right child, but not both.
  * @return a count of the number nodes with only one child.
  */
  int countOneChildNodes();

}
