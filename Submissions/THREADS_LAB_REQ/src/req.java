/***********************************Requirement description**********************************************/
// •	There is a bookstore that has different branches, each of them sell a number of books, these books are supplied by the Supplier. 
// •	A bookstore shouldn’t sell a book when the number of books is 0, it should block and notify the Supplier to provide more
// •	A supplier shouldn’t provide a book when the max count of books is reached, when it provides a book, it should tell stores that there are more books available.
// •	Modify the requirement_student.java code to reflect this behaviour.
// •	All threads should execute in parallel, you cannot allow a thread to stop another thread (should guarantee progress)
// •	Follow the 8 TODOs in the Code
 /***********************************************************************************************/
//my modifications
//supplier and branch
//implements Runnable,run function

class Threadsrequirement {
	 public static void main(String...args) throws InterruptedException {
	 	BookStock b = new BookStock (10);
		
		//TODO-1: Create 4 threads,
		// 1 for Supplier
	 	 Thread supplier_thread = new Thread(new Supplier(b));
	       
		// 3 for StoreBranches and Name them as the following: Giza branch, Cairo branch, Daqahley branch
	 	Thread Giza_thread = new Thread(new StoreBranch (b));
	 	Thread Cairo_thread = new Thread(new StoreBranch (b));
	 	Thread Daqahley_thread = new Thread(new StoreBranch (b));

	 	//set threads names for printing purposes
	 	supplier_thread.setName("supplier");
	 	Giza_thread.setName("Giza branch");
	 	Cairo_thread.setName("Cairo branch");
	 	Daqahley_thread.setName("Daqahley branch");
	 	
		//TODO-2: Run the 4 threads
	 	supplier_thread.start();
	 	Giza_thread.start();
	 	Cairo_thread.start();
	 	Daqahley_thread.start();
    }
}

class BookStock {
	private int book_count;
	private int max;
	public BookStock  (int max) {
		this.max = max;
	}
	public void produce() {
		book_count++;
	}

	public void consume () {
		book_count--;
	}

	public int getCount () {
		return book_count;
	}
	public boolean is_count_max()
	{
		return (max==book_count);
	}
}

//TODO-3: should it implement or extend anything?
//yes, implement runnable interface or extend thread class to create threads
class Supplier implements Runnable {
	private BookStock b;

	public Supplier (BookStock b) {
		this.b = b;
	}
//TODO-4:is a function missing ?
	//yes, I added run() function to be called when start the thread
	
	
	public void doWork ()
	{
		while (true) 
		{ 
		synchronized (b)
		{
			//try
			//{
	//TODO-5: how to make it stop producing when it reaches max, without adding extra sleeps or busy waiting ?
			while(b.is_count_max())
			{
				try { 
					b.wait();
				} catch (InterruptedException e) {}	
			}

			
			
			b.produce();
			b.notifyAll(); //after it produced let other threads know it finished 
			System.out.println (Thread.currentThread().getName() + " provided a book, total " +b.getCount());
			
			//}
			//catch(InterruptedException e)
			//{
			//}
		}
			try 
			{
				Thread.sleep (200);
			}
			catch (InterruptedException e) 
			{
				System.out.println (Thread.currentThread().getName() + "is awaken");
			}
        }
}
	public void run() {
		// TODO Auto-generated method stub
		 doWork ();
	}

}

//TODO-6: should it implement or extend anything?
class StoreBranch implements Runnable {
	private BookStock b;

	public StoreBranch (BookStock b) {
		this.b = b;
	}

	
	
//TODO-7: is a function missing ?
	public void doWork () 
	{
		while (true) 
		{
		synchronized (b)
		{
		    //try
			//{
		
		//TODO-8: how to make it stop consuming when the store is empty, without adding extra sleeps or busy waiting ?
			while(b.getCount()==0)
			{
				b.notifyAll();
				try { 
					b.wait();
				} catch (InterruptedException e) {}	
			}


			
			b.consume();
			//b.notifyAll(); //after it consumed let thread supplier know it finished 

			System.out.println (Thread.currentThread().getName() + " sold a book");
		//}
			//catch(InterruptedException e)
			//{
			//}
		}
			try 
			{
				Thread.sleep (2000);
			} 
			catch (InterruptedException e) 
			{
				System.out.println (Thread.currentThread().getName() + "is awaken");
			}
        }
}
	public void run() {
		// TODO Auto-generated method stub
		doWork();
	}
	
}