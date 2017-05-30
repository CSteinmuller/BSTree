/*
 * Visitor.java
 *
 * Created on April 13, 2007, 8:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


/**
 *
 * @author Duncan
 */
   public interface Visitor<E>
   {
      /**
         visits the data field in a binary search tree node.
         @param obj the object to be visited.
      */
      void visit(E obj);
   }
