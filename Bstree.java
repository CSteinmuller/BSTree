
/**
  * @file BstreeAPI.java
  * @author Christopher Steinmuller
  * @date 2009-5-01
  * Description: implements a binary search tree
  * Course: CSC1351.01
  * PAWS ID: cstein1
  * Project #: 5
  * Instructor: Duncan
  */
//Note: You must complete the documentation of the methods below.

public class Bstree<E extends Comparable<E>> implements BstreeAPI<E>
{
   /**
      Constructs an empty tree
   */
   public Bstree()
   {
      root = null;
      size = 0;
   }
     /**
      Determine whether the binary search tree is empty.
      @return this function returns true if the tree is empty;
      otherwise, it returns false if the tree contains at least one node.
      @param none
   */
   public boolean isEmpty()
   {
      return (root == null);
   }

   /**
      Inserts an item into the tree.
      @param item the value to be inserted.
      @return none.
   */
   public void insert(E obj)
   {
      Node newNode = new Node(obj);
      if (root == null)
      {
         root = newNode;
         size++;
      }
      else
      {
         Node tmp = root;
         while (true)
         {
            int d = tmp.data.compareTo(obj);
            if (d==0)
            { /* Key already exists. (update) */
               tmp.data = obj;
               return;
            }
            else if (d>0)
            {
               if (tmp.left == null)
               { /* If the key is less than tmp */
                  tmp.left = newNode;
                  size++;
                  return;
               }
               else
               { /* continue searching for insertion pt. */
                  tmp = tmp.left;
               }
            }
            else
            {
               if (tmp.right == null)
               {/* If the key is greater than tmp */
                  tmp.right = newNode;
                  size++;
                  return;
               }
               else
               { /* continue searching for insertion point*/
                  tmp = tmp.right;
               }
            }
         }
      }
   }

   /**
      Determine whether an item is in the tree.
      @param item item with a specified search key.
      @return true on success; false on failure.
   */
   public boolean inTree(E item)
   {
      Node tmp;
      if (isEmpty())
         return false;
      /*find where it is */
      tmp = root;
      while (true)
      {
         int d = tmp.data.compareTo(item);
         if (d == 0)
            return true;
         else if (d > 0)
         {
            if (tmp.left == null)
               return false;
            else
            /* continue searching */
               tmp = tmp.left;
         }
         else
         {
            if (tmp.right ==  null)
               return false;
            else
            /* continue searching for insertion pt. */
               tmp = tmp.right;
         }
      }
   }

   /**
      Delete an item from the tree.
      @param item item with a specified search key.
      @return none.
   */
   public void remove(E item)
   {
      Node nodeptr;
      nodeptr = search(item);
      if (nodeptr != null)
      {
         remove(nodeptr);
         size--;
      }
   }

   /**
      This method traverses a binary tree in inorder
      and calls the function Visit once for each node.
   */
   public void preorderTraverse(VisitorFunction func)
   {
      preorderTraverse(root,func);
   }

   /**
    * This method traverses a binary tree in postorder
    * and calls the method visit in the func class once for each node.
    * @param func an object containing the visit method that is applied
    * to the data field in every node as the tree is traversed.
    */
   public void postorderTraverse(VisitorFunction func)
   {
       postorderTraverse(root, func);
   }

   /**
    * This method traverses a binary tree in postorder
    * and calls the method visit in the func class once for each node.
    * @param func an object containing the visit method that is applied
    * to the data field in every node as the tree is traversed.
    * @param node the node being visited
    */
   private void postorderTraverse(Node node, VisitorFunction<E> func)
   {
       if (node !=null)
       {
           postorderTraverse(node.left, func);
           postorderTraverse(node.right, func);
           func.visit(node.data);
       }
   }

   /**
      returns a reference to the item in the tree with the specified
      key. If the item does not exists, an exception occurs.
      @param key the key to the item to be retrieved.
      @return it with the specified key.
   */
   public E retrieve(E key) throws BstreeException
   {
      Node nodeptr;
      if (isEmpty())
         throw new BstreeException("Non-empty tree expected on retrieve().");
      //
      // Alternatively: assert root != null;
      // Code must be assertion-enabled:
      // java -ea programname OR java -enableassertions programname
      //
      nodeptr = search(key);
      if (nodeptr == null)
         throw new BstreeException("Existent key expected on retrieve().");
      return nodeptr.data;
   }

   /**
      This method returns the size of the binary search tree
   */
   public int size()
   {
       return size;
   }

   /**
      This method traverses a binary tree in inorder
      and calls the function Visit once for each node.
   */
   private void preorderTraverse(Node node, VisitorFunction<E> func)
   {
      if (node != null)
      {
         func.visit(node.data);
         preorderTraverse(node.left,func);
         preorderTraverse(node.right,func);
      }
   }

   /**
    * This method traverses a binary tree in inorder
    * and calls the method visit in the func class once for each node.
    * @param func an object containing the visit method that is applied
    * to the data field in every node as the tree is traversed.
    */
   public void inorderTraverse(VisitorFunction func)
   {
       inorderTraverse(root, func);
   }

   /**
    * This method traverses a binary tree in inorder
    * and calls the method visit in the func class once for each node.
    * @param func an object containing the visit method that is applied
    * to the data field in every node as the tree is traversed.
    * @param node the noide being visited
    */
   private void inorderTraverse(Node node, VisitorFunction<E> func)
   {
       if (node != null)
       {
           inorderTraverse(node.left, func);
           func.visit(node.data);
           inorderTraverse(node.right, func);
       }
   }

   private void remove(Node node)
   {
      E theData;
      Node parent;
      Node replacement;
      parent = findParent(node);
      if ((node.left != null) && (node.right != null))
      {
         replacement = node.right;
         while (replacement.left != null)
            replacement = replacement.left;
         theData = replacement.data;
         remove(replacement);
         node.data = theData;
         return;
      }
      else
      {
         if ((node.left == null) && (node.right == null))
            replacement = null;
         else if (node.left == null)
            replacement = node.right;
         else
            replacement = node.left;
         if (parent==null)
            root = replacement;
         else if (parent.left == node)
            parent.left = replacement;
         else
            parent.right = replacement;
         return;
      }
   }

   private Node search(E obj)
   {
      Node current = root;
      while (current != null)
      {
         int d = current.data.compareTo(obj);
         if (d == 0)
            return current;
         else if (d > 0)
            current = current.left;
         else
            current = current.right;
      }
      return null;
   }
   /**
    * Finds the preceding node
    * @param node the node
    * @return the parent
    */
   private Node findParent(Node node)
   {
      Node tmp;
      tmp = root;
      if (tmp == node)
         return null;
      while(true)
      {
         assert tmp.data.compareTo(node.data) != 0;
         if (tmp.data.compareTo(node.data)>0)
         {
            /* this assert is not need but just
               in case there is a bug         */
            assert tmp.left != null;
            if (tmp.left == node)
               return tmp;
            else
               tmp = tmp.left;
         }
         else
         {
            assert tmp.right != null;
            if (tmp.right == node)
               return tmp;
            else
               tmp = tmp.right;
         }
      }
   }
 /**
  * This method removes all terminal (leaf) nodes from the tree, retaining
  * all its internal nodes.
  */
  public void trim()
  {
      if (isEmpty())
      {
          return;
      }
      if ((root.left==null)&&(root.right==null))
              remove(root);
      else
      {
          trim(root.left);
          trim(root.right);
      }

  }
  /**
  * This method removes all terminal (leaf) nodes from the tree, retaining
  * all its internal nodes.
   * @param current- the current node
  */
  private void trim(Node current)
  {
      if (current != null)
      {
          if ((current.left==null)&&(current.right==null))
          {
              remove(current.data);
          }
          else
          {
              trim(current.left);
              trim(current.right);
          }
      }
  }

  /**
  * This method counts the number of nodes that have either a left child or a
  * right child, but not both.
  * @return a count of the number nodes with only one child.
  */
  public int countOneChildNodes()
  {
     if ((isEmpty())||((root.left == null)&&(root.right==null)))
     {
         return 0;
     }
     if (root.left==null)
     {
         return 1+countOneChildNodes(root.right);
     }
     if (root.right==null)
     {
         return 1+countOneChildNodes(root.left);
     }
     return countOneChildNodes(root.left)+countOneChildNodes(root.right);
  }

/**
  * This method counts the number of nodes that have either a left child or a
  * right child, but not both.
 * @param current the current node
  * @return a count of the number nodes with only one child.
  */
 private int countOneChildNodes(Node current)
 {
     if ((current.left==null)&&(current.right==null))
             return 0;
     if (current.left==null)
         return 1+countOneChildNodes(current.right);
     if (current.right==null)
         return 1+countOneChildNodes(current.left);
     return 0+countOneChildNodes(current.left)+countOneChildNodes(current.right);
 }
 /**
    * This method removes every node that has one child from the binary search tree.
    */
 public void fullify()
 {
     fullify(root);
 }

 /**
    * This method removes every node that has one child from the binary search tree.
  * @param node- the node visited
  */
 private void fullify(Node node)
 {
     if (node == null)
         return;
     if (((node.left==null)&&(node.right!=null))||((node.left!=null)&&(node.right==null)))
     {
           fullify(node.left);
           remove(node.data);
           fullify(node.right);
     }

 }

   private Node root;
   private int size;
   /**
      A node of a tree stores a data item and references
      to the child nodes to the left and to the right.
   */
   private class Node
   {
      /**
         Parameterized constructor.
         @param val value to be inserted in a node.
      */
      public Node(E val)
      {
          data = val;
          left = right = null;
      }
      public E data;
      public Node left;
      public Node right;
   }
}
