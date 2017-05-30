/**
  * @file BstreeTester.java
  * @author Christopher Steinmuller
  * @date 2009-5-01
  * Description: implements the main method
  * Course: CSC1351.01
  * PAWS ID: cstein1
  * Project #: 5
  * Instructor: Duncan
  */

/**
 *
 * @author Duncan
 */
public class BstreeTester
{
   public static void main(String[] args)
   {
      VisitorFunction<String> f = new VisitorFunction<String>();
      Bstree<String> months = new Bstree<String>();
	  // TO DO ..Complete the main method
      months.insert("JANUARY");
      months.insert("FEBUARY");
      months.insert("MARCH");
      months.insert("APRIL");
      months.insert("MAY");
      months.insert("JUNE");
      months.insert("JULY");
      months.insert("AUGUST");
      months.insert("SEPTEMBER");
      months.insert("OCTOBER");
      months.insert("NOVEMBER");
      months.insert("DECEMBER");
      System.out.println("Tree in order is:");
      System.out.println("--------------------");
      months.inorderTraverse(f);
      System.out.println("Number of Months: "+months.size());
      System.out.println("The tree currently has "+months.countOneChildNodes()+" nodes with exactly one child");
      months.trim();
      System.out.println("Tree in preorder is:");
      System.out.println("--------------------");
      months.preorderTraverse(f);
      System.out.println("Number of Months: "+months.size());
      months.fullify();
      System.out.println("Tree in postorder is:");
      System.out.println("--------------------");
      months.postorderTraverse(f);
      System.out.println("Number of Months: "+months.size());

   }
}
