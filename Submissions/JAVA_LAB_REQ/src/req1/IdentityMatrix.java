package req1;
import java.util.ArrayList;
public class IdentityMatrix extends Matrix<Integer>{


		public IdentityMatrix (int size)
		{
			super(size,size);
		}
		
		public boolean SetNumbers(ArrayList<Integer> arr) 
		{
			for(int i=0; i<arr.size(); i++)
			{
				if(i%(M+1)==0 && arr.get(i)!=1)
					return false;
				if(i%(M+1)!=0 && arr.get(i)!=0)
					return false;
			}
			boolean is_super = super.SetNumbers(arr);
			if (!is_super) //wrong size 
				return false;
			return true;
		}
		
		public void Transpose()
		{
			
		}

	}
