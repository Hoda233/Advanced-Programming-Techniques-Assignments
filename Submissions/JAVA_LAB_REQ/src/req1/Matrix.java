package req1;
import java.util.ArrayList;
public class Matrix<E extends Number>{
	
		int M; //# of rows
		int N; //# of columns
		ArrayList<ArrayList<E>> Numbers; 
		
		public Matrix(int rows, int cols)
		{
			M=rows;
			N=cols;
			
			Numbers = new ArrayList<ArrayList<E>>(M);
			for(int i=0; i < M; i++)
			{
			    ArrayList<E> temp_row = new ArrayList<E>(N);
			    for(int j=0; j < N; j++)
			    {
			         temp_row.add(null);
			    }
			    Numbers.add(temp_row);		    
			}
		}
		public boolean SetNumbers(ArrayList<E> arr) 
		{
			if(arr.size()!= M*N) //wrong size
				return false;
			else
			{
				for(int index=0; index<arr.size(); index++)
				{
					Numbers.get(index/N).set(index%N,arr.get(index));	
				}
				return true;
			}	
		}
		
		public void print()
		{
			for (int i = 0; i < Numbers.size(); i++)
			{
				{
					for (int j = 0; j < Numbers.get(0).size(); j++)
					{
				       E number = (Numbers.get(i)).get(j);
				       System.out.print(number + " ");
				    }
					System.out.print("\n");
				}
			    
			}
			
		}
		
		public void Transpose() 
		{
		    ArrayList<ArrayList<E>> transpose = new ArrayList<ArrayList<E>>();
	        for (int i = 0; i < N; i++) 
	        {
	            ArrayList<E> temp_list = new ArrayList<E>();
	            for (int j = 0; j < M; j++)
	            {
	                E num=Numbers.get(j).get(i);
	                temp_list.add(num);
	            }
	           transpose.add(temp_list);
	        }
	        this.Numbers=transpose;
	        int temp=this.M;
	        this.N=this.M;
	        this.M=temp;	       
			}
	    }

