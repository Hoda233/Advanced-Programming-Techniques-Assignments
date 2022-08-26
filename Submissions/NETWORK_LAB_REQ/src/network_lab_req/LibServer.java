package network_lab_req;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;




public class LibServer {
	public static Library mylibrary=new Library();
	public static final int BORROW_PORT = 6666;
	public static final int RETURN_BACK_PORT = 6667;

	public static int clientNumber = 0; 
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("The server started .. ");

		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(BORROW_PORT);
					while (true) {
						new books_borrower(ss.accept(), clientNumber++).start();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(RETURN_BACK_PORT);
					while (true) {
						new books_borrower(ss.accept(), clientNumber++).start();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

	}

	private static class books_borrower extends Thread {
		Socket socket;
		int clientNo;

		public books_borrower(Socket s, int clientNo) {
			this.socket = s;
			this.clientNo = clientNo;
			System.out.println("Connection with Client #" + clientNo + "at socket " + socket);
		}
		
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
				
               // out.println("Hello, you are client #" + clientNo + ".");

				 
				String client_name_ = null;
				String book_isbn_ = null;
				
				int result;
				String message = null;
				
				while (true) {
					if ((client_name_ = br.readLine()) == null||(book_isbn_ = br.readLine())== null)
						break;
					if (this.socket.getLocalPort() == BORROW_PORT)
					{
						//System.out.println("name here " + client_name_ +"  isbn " + book_isbn_ );
						System.out.println("----------------------------------------------------------------");
						System.out.println("Borrow port");
						System.out.println("___________");
						result=mylibrary.Borrow(book_isbn_,client_name_);
						
						if(result==0)
							message="the borrow is done successfully (Success)";
				        else if(result==1)
				        	message="the isbn is already borrowed (Failure)";
				        else if(result==2)
				        	message="the isbn is not found in library (Failure)";
						
						//out.println(message);
						//mylibrary.Print();
					}
						
					else //if (this.socket.getLocalPort() == RETURN_BACK_PORT)
					{
						//System.out.println("name " + client_name_ +"  isbn " + book_isbn_ );
						System.out.println("----------------------------------------------------------------");
						System.out.println("Return Back port");
						System.out.println("________________");
						result=mylibrary.ReturnBack(book_isbn_,client_name_);
						
						if(result==0)
							message="the return back is done successfully (Success)";
				        else if(result==1)
				        	message="the isbn is borrowed by a different client name (Failure)";
				        else if(result==2)
				        	message="the isbn is not borrowed (Failure)";
				        else if(result==3)
				        	message="the isbn is not found in library (Failure)";
						
						//out.println(message);
						//mylibrary.Print();
					}
					out.println(message);
					mylibrary.Print();
					
				}
				out.close();
				br.close();
				//socket.close();
				//System.out.println("Connection with Client #" + this.clientNo + " finished..");
			} catch (IOException e) {
                System.out.println("Error handling client# " + this.clientNo + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                System.out.println("Connection with client# " + this.clientNo + " closed");
            }
		}
	}
}
