package org.example;

import java.util.Scanner;

public class Main {
    public static void displayMenu(){
        System.out.println("1. View Books");
        System.out.println("2. Add book");
        System.out.println("3. Search for a book by ISBN");
        System.out.println("4. Delete a book by ISBN");
        System.out.println("5. Exit");
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BookStore bookStore = new BookStore();
        bookStore.loadFromFile("bookstore.txt");
        while (true){
            displayMenu();
            int choice = input.nextInt();
            input.nextLine();
            switch (choice){
                case 1:
                    System.out.println("All books");
                    bookStore.displayAllBooks();
                    break;
                case 2:
                    System.out.println("Enter ISBN:");
                    String ISBN = input.nextLine();
                    System.out.println("Enter title:");
                    String title = input.nextLine();
                    System.out.println("Enter author:");
                    String author = input.nextLine();
                    System.out.println("Enter price:");
                    double price = input.nextDouble();
                    Book newBook = new Book(ISBN,title,author,price);
                    bookStore.addBook(newBook);
                    break;
                case 3:
                    System.out.println("Enter ISBN for searching:");
                    String searchIsbn = input.nextLine();
                    Book foundBook = bookStore.searchByISBN(searchIsbn);
                    if (foundBook != null){
                        System.out.println(foundBook);
                    }
                    else
                        System.out.println("Book not found");
                    break;
                case 4:
                    System.out.println("Enter ISBN to delete the book:");
                    String deleteIsbn = input.next();
                    boolean deleted= bookStore.deleteBookByISBN(deleteIsbn);
                    if (deleted){
                        System.out.println("Book deleted successfully");
                    }
                    else
                        System.out.println("Book not found");
                    break;
                case 5:
                    bookStore.saveToFile("bookstore.txt");
                    System.out.println("GoodBye");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}