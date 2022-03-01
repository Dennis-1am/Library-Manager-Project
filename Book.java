/**
 * Dennis Lam
 * CSE 017 Spring
 * Feb 26th 2022
 * Project 1
 * Last edited: Feb 26th 2022
 */
public class Book extends LibraryMedia {
    private String author;
    private String ISBN;

    /**
     * Empty default constructor
     */
    public Book() {
        super();
        this.author = "";
        this.ISBN = "";
    }

    /**
     * 7 parameter constructor
     */
    public Book(String title, String publisher, int year, String callNumber, int copies, String author, String ISBN) {
        super(title, publisher, year, callNumber, copies);
        this.author = author;
        this.ISBN = ISBN;
    }

    /**
     * @return String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return String
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
