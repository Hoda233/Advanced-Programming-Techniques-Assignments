package network_lab_req;

public class Book {
	String isbn;
	String title;
	String author;
    boolean borrowed;
    String borrower;

    
    Book(String isbn_, String title_, String author_,boolean borrowed_,String borrower_)
    {
    	isbn=isbn_;
    	title=title_;
    	author=author_;
        borrowed=borrowed_;
        borrower=borrower_;

    }
    public String get_isbn()
    {
        return this.isbn;
    }
    public String get_title()
    {
        return this.title;
    }
    public String get_author()
    {
        return this.author;
    }
    public boolean get_borrowed()
    {
        return this.borrowed;
    }
    public String get_borrower()
    {
        return this.borrower;
    }
    public void set_borrowed(boolean b)
    {
        this.borrowed=b;
    }
    public void set_borrower(String b)
    {
        this.borrower=b;
    }
}
