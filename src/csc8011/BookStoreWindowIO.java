package csc8011;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BookStoreWindowIO { //the BookStoreWindowIO class is the main class which will include all the code for the user to interact with the help of calling to the other two java classes that were made

    private static BookStoreWindow newBooks; //create new object of "newBooks" for the books that will be in the csv file

    private static boolean readFiles = false; //set the default state of reading a file to false

    private static boolean optionOneStatus = false; //set a boolean to fix issue with running option 1, then option 2, and then trying to select option 1 or 2 again

    public static void main(String[] args) {

        boolean done = false;

        Scanner s = new Scanner(System.in); //create a scanner for human input

        while (!done)
        {
            //This is the main menu area which the user will interact with
            printMenu();
            System.out.println("Enter an option: ");
            String response = s.nextLine(); //The next thing that the user inputs will be saved as "response", which will go through the switch cases below

            switch (response){ //switch cases to identify which option the user has inputted
                case "1":
                    setBookStoreName();
                    break;
                case "2":
                    if (!optionOneStatus){ //condition placed in setBookStoreName() that checks if the bookstore name had already been set or not
                        System.out.println("Please enter a bookstore name first in order to assign it to the .csv file");
                        System.out.println("--------------------------------------------------------------------------------");
                        break;
                    }
                    readInFile();
                    break;
                case "3":
                    printBookStoreSummary();
                    break;
                case "4":
                    printBookStatistics();
                    break;
                case "5":
                    done = true;
                    System.out.println("Goodbye!");
                    break;
                case "csc8011 is great": //this is just a fun easter egg I hope it doesn't mark me down
                    System.out.println("""
                                          ,---------------------------,
                                          |  /---------------------\\  |
                                          | |                       | |
                                          | |    CSC8011            | |
                                          | |       is              | |
                                          | |         great         | |
                                          | |                       | |
                                          |  \\_____________________/  |
                                          |___________________________|
                                        ,---\\_____     []     _______/------,
                                      /         /______________\\           /|
                                    /___________________________________ /  | ___
                                    |                                   |   |    )
                                    |  _ _ _                 [-------]  |   |   (
                                    |  o o o                 [-------]  |  /    _)_
                                    |__________________________________ |/     /  /
                                /-------------------------------------/|      ( )/
                              /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /
                            /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /
                            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""");

                    System.out.println("\nSmall easter egg, art found on https://www.asciiart.eu/computers/computers");
                    System.out.println("--------------------------------------------------------------------------------");


                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    System.out.println("--------------------------------------------------------------------------------");
                    //These lines were added throughout this class to make the visual break between each input interaction easier on the eyes

                    break;
            }
        }
    }

    private static void printMenu(){ //Menu for the user to see
        System.out.println("Main Menu:");
        System.out.println("1. Set the bookstore name");
        System.out.println("2. Read in CSV file");
        System.out.println("3. Summary of the bookstore and its books");
        System.out.println("4. Statistics on the books");
        System.out.println("5. Quit");
    }

    private static boolean readInFile(){ //This readInFile has been repurposed from the code found in CSC8011 Canvas page, FoodInputIO.java
        if ((readFiles != true) && (newBooks != null)) {
            try {

                File input = new File("books.csv"); //Read the csv file

                Scanner line = new Scanner(input);

                line.nextLine(); //Skip the header

                while (line.hasNext()) {
                    //split the line
                    String[] split = line.nextLine().split(",");

                    Book b = new Book(split[0], split[1], Integer.parseInt(split[2]), Double.parseDouble(split[3]));


                    newBooks.addBook(b);
                }

                System.out.println("<<.csv file read successfully>>"); //if the csv file has been read correctly
                System.out.println("--------------------------------------------------------------------------------");

                readFiles = true;
                return true;
            } catch (FileNotFoundException e){
                System.out.println("<<.csv file not found, please retry>>"); //if the file could not be found
                System.out.println("--------------------------------------------------------------------------------");
                readFiles = false;
                return false;
            }
        }

        else{

            System.out.println("A .csv file is already read, please enter a new bookstore name first with option 1 to assign it to a new .csv file"); //This message is to catch if someone were to try and enter option 2 before assigning a bookstore name first
            System.out.println("--------------------------------------------------------------------------------");

            return false;
        }
    }

    private static void setBookStoreName(){
        Scanner s = new Scanner(System.in); //Scanner in
        System.out.print("Please enter the bookstore name: ");
        String input = s.nextLine(); //next input will become the bookstore name saved as variable "input"
        newBooks = new BookStoreWindow(input); //save new bookstore name into newBooks
        while (newBooks.getBookStoreName().isEmpty() || newBooks.getBookStoreName().matches("\\s+")) { //while the new bookstore name is empty, this will keep looping until the bookstore is named
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("A bookstore name cannot be assigned empty, please enter a valid name:");
            Scanner news = new Scanner(System.in); //Second scanner in
            String newInput = news.nextLine(); //next input will become the bookstore name saved as variable "newInput"
            newBooks = new BookStoreWindow(newInput); //save new bookstore name into newBooks
        }

        optionOneStatus = true; //set true to allow access the readInFile() section
        readFiles = false;

        System.out.println("<<Bookstore name has been saved>>");

        System.out.println("--------------------------------------------------------------------------------");
    }

    private static void printBookStoreSummary(){
        if (readFiles && newBooks != null){ //if true
            System.out.println("Bookstore Name: " + newBooks.getBookStoreName()); //This will print out the bookstore name
            for (Book b : newBooks.getBooks()) {
                System.out.println(b); //This loops through every book in the csv file and show the information for each book from the toString of the Book class
            }
            System.out.println("--------------------------------------------------------------------------------");
        }
        else
        {
            System.out.println("Error, please make sure you've completed both options 1 and 2 first"); //This message is shown when either option 1 or 2 has not been done yet before getting to option 3
            System.out.println("--------------------------------------------------------------------------------");
        }

    }

    private static void printBookStatistics(){
        if (readFiles && newBooks != null){ //if true
            System.out.println("The statistics on highest value, oldest book, and average value of the books: ");
            System.out.println("Highest value book: " + newBooks.bookWithHighestValue().getTitle() + (" (published ") + newBooks.bookWithHighestValue().getYearPublished() + "), Value: £" + newBooks.bookWithHighestValue().formattedGetValue()); //call to the highest value method in BookStoreWindow class
            System.out.println("Oldest book: " + newBooks.oldestBook().getTitle() + (" (published ") + newBooks.oldestBook().getYearPublished() + ")"); //call to the oldest book method in BookStoreWindow class
            System.out.println("Average value of books: £" + newBooks.averageValue()); //call to the average value method in BookStoreWindow class
            System.out.println("--------------------------------------------------------------------------------");

        }
        else
        {
            System.out.println("Error, please make sure you've completed both options 1 and 2 first"); //This message is shown when either option 1 or 2 has not been done yet before getting to option 4
            System.out.println("--------------------------------------------------------------------------------");
        }

    }

}