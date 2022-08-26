package network_lab_req;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class LibClient {
	public static final int BORROW_PORT = 6666;
	public static final int RETURN_BACK_PORT = 6667;

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Enter client's name:");
		Scanner scanner = new Scanner(System.in);
		String client_name=scanner.next();
		
		Socket s1 = new Socket("localhost", BORROW_PORT);
		Socket s2 = new Socket("localhost", RETURN_BACK_PORT);


		PrintWriter out1 = new PrintWriter(s1.getOutputStream(), true);
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(s1.getInputStream()));
		
		PrintWriter out2 = new PrintWriter(s2.getOutputStream(), true);
		BufferedReader socketReader2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
		
		Scanner scanner1;
		Scanner scanner11;
		
		
		
		while(true)
        {
			
			
			System.out.println("Enter the port you want to connect to: (1) Borrow (2) Return Back");
			//Scanner
			scanner1 = new Scanner(System.in);

			int port = (scanner1.nextInt()) == 1 ? BORROW_PORT : RETURN_BACK_PORT;
	 
            System.out.println("Enter book's isbn:");
            //Scanner
            scanner11 = new Scanner(System.in);
    		String book_isbn=scanner11.next();
            
            		
            if(client_name== null || book_isbn==null)
                break;
            
            String result_message=null;
            if(port==BORROW_PORT)
            {
            	out1.println(client_name);
            	out1.println(book_isbn); 
                result_message = socketReader.readLine();  
            }
            else
            {
            	out2.println(client_name);
            	out2.println(book_isbn);
                result_message = socketReader2.readLine();   
            }
            
			System.out.println("The received message = " + result_message + "\n");
        }
		System.out.println("I am exiting now, bye.");
		scanner.close();
		scanner1.close();
		scanner11.close();
		out1.close();
		out2.close();
		//consoleReader.close();
		s1.close();
		s2.close();

	}

}
