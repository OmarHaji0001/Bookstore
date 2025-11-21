package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookStore {
    private ArrayList<Book> books;
    public BookStore() {
        books = new ArrayList<>();
    }
    public Book searchByISBN(String ISBN){
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)){
                return book;
            }
        }
        return null;
    }
    public void addBook(Book book){
        if (searchByISBN(book.getISBN()) != null) {
            System.out.println("can't add Book because ISBN already exists");
            return;
        }
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public boolean deleteBookByISBN(String ISBN){
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    public void displayAllBooks(){
        if (books.isEmpty()) {
            System.out.println("No books exist");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }
    public void saveToFile(String filename) {
        try  {
            FileWriter writer = new FileWriter(filename);
            for (Book book : books) {
                writer.write(book.getISBN() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.getPrice() + "\n");
            }
            writer.close();
            System.out.println("Books saved successfully");
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    public void loadFromFile(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("The file does not exist");
            return;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String isbn = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    double price = Double.parseDouble(parts[3]);

                    Book book = new Book(isbn, title, author, price);
                    books.add(book);
                }
            }
            scanner.close();
            System.out.println("Books loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Error on loading file");
            e.printStackTrace();
        }
    }
}
