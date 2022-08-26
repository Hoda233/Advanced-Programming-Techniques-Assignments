package network_lab_req;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
	List<Book> books_array;
	
	Library()
	{
		String fileName = "Books_File.txt";
		Scanner inputStream = null;
		//System.out.println("The file " + fileName + "\ncontains the following lines:\n");
		try
		{
		  inputStream = new Scanner(new File("Books_File.txt"));//The txt file is being read correctly.
		}
		catch(FileNotFoundException e)
		{
		  System.out.println("Error opening the file " + fileName);
		  System.exit(0);
		}
   
		books_array = new ArrayList<Book>(); 
		while (inputStream.hasNextLine()) 
		{
		   String line = inputStream.nextLine();
		   String[] data = line.split(" ");
		   
		   boolean b;
		   if(data[3].equals("1")||data[3].equals("true"))
			  b=true;
		   else
			  b=false;
		   
		   Book book = new Book(data[0],data[1],data[2],b,data[4]);
		   books_array.add(book);
		          
		}

		   inputStream.close();
		
	}
	void Print_test()
	{
		for(int i=0; i<books_array.size(); i++)
		{
			
			System.out.println(books_array.get(i).get_isbn()+ " " + books_array.get(i).get_title()+ " "  +    books_array.get(i).get_author()+ " " + books_array.get(i).get_borrowed()+ " " +books_array.get(i).get_borrower());
		}
		
	}
	void Print()
	{
		for(int i=0; i<books_array.size(); i++)
		{
			System.out.print("Book isbn: "+books_array.get(i).get_isbn()+ " Borrower name:" );
			if(books_array.get(i).get_borrowed()==true)
			   System.out.print(books_array.get(i).get_borrower());
			else
				System.out.print("not borrowed");
			System.out.print("\n");
			
		}
		
	}
	int Borrow(String isbn, String name)
	{
		for(int i=0; i<books_array.size(); i++)
		{
			if(books_array.get(i).get_isbn().equals(isbn))//book found
			{
				if(books_array.get(i).get_borrowed()==false)//not borrowed
				{
					books_array.get(i).set_borrowed(true);
					books_array.get(i).set_borrower(name);
					return 0;//success
				}
				else
				{
					return 1;//already borrowed
				}
			}
				
		}
		return 2;//not found
	}
	int ReturnBack(String isbn, String name)
	{
		for(int i=0; i<books_array.size(); i++)
		{
			if(books_array.get(i).get_isbn().equals(isbn))//book found
			{
				if(books_array.get(i).get_borrowed()==true)//borrowed
				{
					if(books_array.get(i).get_borrower().equals(name))//by this person
					{
					  books_array.get(i).set_borrowed(false);
					  books_array.get(i).set_borrower(" ");
					  return 0;//success
					}
					else
					   return 1;//different client
				}
				else
					return 2;//not borrowed
			}
		}
		return 3;//not found
	}

}
