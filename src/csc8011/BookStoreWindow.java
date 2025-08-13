package csc8011;

import java.util.ArrayList;

public class BookStoreWindow { //The BookStoreWindow class is to contain all the fields, method and constructors of the books that will be added into the bookstore window


    //fields for the BookStoreWindow class
    private String bookStoreName;
    private ArrayList<Book> books;

    //constructors
    public BookStoreWindow(String bookStoreName){ //construction of the BookStoreWindow class with the fields created above
        this.bookStoreName = bookStoreName;
        this.books = new ArrayList<>();
    }

    //methods
    public void addBook(Book b){ //to add a new book into the ArrayList
        books.add(b);
    } //Method for adding new books into the bookstore

    public String getBookStoreName() {
        return bookStoreName;
    } //Method to call the name of the bookstore that the user has inputted

    public ArrayList<Book> getBooks() {
        return books;
    } //Method to return information of each book in the bookstore with the use of the Book Class' toString


    public Book bookWithHighestValue(){ //Define method to retrieve the book with the highest value

        if (books.isEmpty()){
            return null; //return null in case the user tries to call this method without entering books into the bookstore first, rather than crashing
        }

        else{
            Book highestValue = books.get(0);

            for (Book b : books){
                if (b.getValue() > highestValue.getValue()) {
                    highestValue = b;
                }

                /*Using a for each loop, it will loop through each book and compare the value of the previous book,
                 * if the previous book has a higher value than the current one, it will go to the next book. However, if the current
                 * book has a higher value than the previous one, that book will then take the place of the highestValue object.  */

            }

            return highestValue;

        }

    }

    public Book oldestBook(){ //define method to retrieve the book with the highest value

        if (books.isEmpty()){
            return null; //return null in case of a bug where the user is able to call this method without entering books into the bookstore first, prevents crashing
        }

        else{
            Book oldestbook = books.get(0);

            for (Book b : books){
                if (b.getYearPublished() < oldestbook.getYearPublished()) {
                    oldestbook = b; //Same concept as the highestValue method, however this time it compares the published year
                }

            }

            return oldestbook;

        }

    }

    public Double averageValue(){
        if (books.isEmpty()){
            return null; //return null in case of a bug where the user is able to call this method without entering books into the bookstore first, prevents crashing
        }

        else{
            double sum = 0;

            for (Book b : books){
                sum += b.getValue(); //for each book in the arraylist, it will add up the value from 0 as sum = 0

            }

            double theAverage = sum / books.size(); //initialize a variable to the average in order to do rounding in the next line

            return Double.valueOf(String.format("%.2f", theAverage)); //This is done to round the double value into 2 decimal places

        }

    }

}
