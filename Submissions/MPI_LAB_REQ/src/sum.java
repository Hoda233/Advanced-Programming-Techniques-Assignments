
import java.util.Scanner;
import mpi.MPI;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
//import java.io.IOException;  // Import the IOException class to handle errors
//import java.io.FileWriter;   // Import the FileWriter class

public class sum {
	
    public static void main(String[] args) {

    	
    	MPI.Init(args);
    	int world_rank = MPI.COMM_WORLD.Rank();
        int world_size = MPI.COMM_WORLD.Size();
        
    	//int n=1000;
    	int array [] = new int[2000];
    	
        int local_sum=0; 
        int sub_array_size;
        
    	
    	int send_sum[]=new int[1];
    	int recv_sum[]=new int[1];
    	int length[]=new int[1];
    	
    	
        if(world_rank==0)
        {
        	//write in file
        	/*try {
        	    FileWriter Writer = new FileWriter("myfile.txt");
        	    for(int i=1; i<=n; i++)
        	    Writer.write(Integer.toString(i)+"\n");
        	    Writer.close();
        	   // System.out.println("Successfully written.");
        	}
        	catch (IOException e) {
        	   // System.out.println("An error has occurred.");
        	    e.printStackTrace();
        	}
        	*/
        	//Reads array from a file
        	 try {
        	File Obj = new File("myfile.txt");
            Scanner Reader = new Scanner(Obj);
            int i=0;
            while (Reader.hasNextLine())
            {
              String data = Reader.nextLine();
              array[i] = Integer.valueOf(data);
              i++;
            }
            length[0]=i;
            Reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }  
        	 
        	 //take the remaining from end of list
        	 for(int i=world_size*(length[0]/4); i<length[0]; i++)
        	 {
        		 local_sum+=array[i];
        	 }
          	//System.out.printf("local sum %d %d\n",world_rank,local_sum);

     }
        
        MPI.COMM_WORLD.Bcast(length,0,1, MPI.INT, 0);
        sub_array_size=length[0]/4;
        int local_array []= new int[sub_array_size];
        //scatter array into 4 equal parts without remaining , calculate local sums and print them
     MPI.COMM_WORLD.Scatter(array,world_rank*sub_array_size, sub_array_size, MPI.INT, local_array , 0,sub_array_size, MPI.INT, 0);

     		for (int i = 0; i < sub_array_size; i++) 
            {

         		local_sum+=local_array[i];
            }
         	System.out.printf("local sum of process with rank #%d = %d\n",world_rank,local_sum);
         	
     		

     	
     	//reduce all sums 
     	send_sum[0]=local_sum;  
        MPI.COMM_WORLD.Reduce(send_sum, 0, recv_sum, 0, 1, MPI.INT, MPI.SUM, 0); 
     		
        //print final sum 
        if (world_rank == 0)
        {
            System.out.printf("final sum = %d \n", recv_sum[0]);
        }     

    }

}














