/*
 * VisitorFunction.java
 *
 * Created on April 13, 2007, 8:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


/**
 *
 * @author Duncan
 */
   public class VisitorFunction<E> implements Visitor<E>
   {
      public void visit(E s)
      {
         System.out.println(s);
      }
   }
