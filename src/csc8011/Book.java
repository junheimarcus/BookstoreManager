package csc8011;

import java.text.DecimalFormat;

public class Book { //The Book class is made to give each book an identity with the use of fields, methods, and constructors

    //creating fields for the class Book
    private String bookID;
    private String title;
    private int yearPublished;
    private double value;

    //constructors
    public Book(String bookID, String title, int yearPublished, double value){ //constructing the book class with the corresponding fields that was created above
        this.bookID = bookID;
        this.title = title;
        this.yearPublished = yearPublished;
        this.value = value;
    }

    //getters

    public int getYearPublished(){
        return yearPublished;
    }

    public double getValue(){
        return value;
    }

    public String formattedGetValue(){ // This was made to call back the value as a string in order to get values of books that end with .00 to still show two decimal places
        return twoDecimals.format(value);
    }

    public String getTitle(){
        return title;
    }

    //Some of these getters and setters were used during testing of the methods, however has now been left as comments in case needed in the future

    /*
    public String getBookID(){
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setValue(double value) {
        this.value = value;
    }
    */

    DecimalFormat twoDecimals = new DecimalFormat("#.00"); //added a formatting variable in case the book ends with cost #.00, so that all books show two decimal places as it would previously only show one zero if the books ended with .00
    @Override
    public String toString(){//to give the Book class an identity if called to, which it will be in the BookStoreWindowIO class to show information of each book
        return "Book ID: " + bookID + ", Title: " + title + ", Year published: " + yearPublished + ", Value: £" + twoDecimals.format(value);
    }

}
