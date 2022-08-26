package req1;

import java.util.ArrayList;
import java.util.Arrays;
 

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//test1 integer 2*3
        System.out.println("Test1");

		       Matrix<Integer> a1 = new Matrix<Integer>(2, 3);
		       ArrayList<Integer> arr = new ArrayList<Integer>();
		       arr.add(1);
		       arr.add(2);
		       arr.add(3);
		       arr.add(4);
		       arr.add(5);
		       arr.add(6);       
		     
		       if (a1.SetNumbers(arr))
		        {
		            System.out.println("matrix");
		            a1.print();     
		            System.out.println("matrix transposed");
		            a1.Transpose();
		            a1.print();
	            }
		       else
		       {
		    	   System.out.println("Failed to set the matrix.");
		       }
		       
	            
	          //test2 double 3*3
		       System.out.println("\nTest2");
	            Matrix<Double> a2 = new Matrix<Double>(3, 3);
	            ArrayList<Double> arr2 = new ArrayList<Double>(Arrays.asList(0.5,0.55,1.55,0.7,0.77,1.77,0.3,0.33,1.33));
	            
	            if (a2.SetNumbers(arr2))
		        {

		            System.out.println("matrix");
		            a2.print();     
		            System.out.println("matrix transposed");
		            a2.Transpose();
		            a2.print();
	            }
	            else
			       {
			    	   System.out.println("Failed to set the matrix.");
			       }
	            
	            
	            //test3 imatrix 2*2 success
			       System.out.println("\nTest3");

	            
	            IdentityMatrix a3=new IdentityMatrix(2);
                ArrayList<Integer> arr3 = new ArrayList<Integer>(Arrays.asList(1,0,0,1));
	            
	            if (a3.SetNumbers(arr3))
		        {

		            System.out.println("Identity Matrix");
		            a3.print();   
		            System.out.println("Identity Matrix transposed");
		            a3.Transpose();
		            a3.print();
	            }
	            else
			       {
			    	   System.out.println("Failed to set the matrix.");
			       }
	            
	            //test4 imatrix fail not 0's and 1's
			       System.out.println("\nTest4");

	            
	            IdentityMatrix a4=new IdentityMatrix(3);
                ArrayList<Integer> arr4 = new ArrayList<Integer>(Arrays.asList(1,0,2,0,1,2,2,1,0));
	            
	            if (a4.SetNumbers(arr4))
		        {

		            System.out.println("Identity Matrix");
		            a4.print();   
		            System.out.println("Identity Matrix transposed");
		            a4.Transpose();
		            a4.print();
	            }
	            else
			       {
			    	   System.out.println("Failed to set the matrix.");
			       }
	            
	            //test5 imatrix
			       System.out.println("\nTest5");

	            
	            IdentityMatrix a5=new IdentityMatrix(3);
                ArrayList<Integer> arr5 = new ArrayList<Integer>(Arrays.asList(1,0,0,0,1,0,0,0,1));
	            
	            if (a5.SetNumbers(arr5))
		        {

		            System.out.println("Identity Matrix");
		            a5.print();   
		            System.out.println("Identity Matrix transposed");
		            a5.Transpose();
		            a5.print();
	            }
	            else
			       {
			    	   System.out.println("Failed to set the matrix.");
			       }
	}
	

}
