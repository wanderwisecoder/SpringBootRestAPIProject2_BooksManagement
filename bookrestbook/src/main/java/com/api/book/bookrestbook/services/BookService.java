package com.api.book.bookrestbook.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bookrestbook.dao.BookRepository;
import com.api.book.bookrestbook.entities.Book;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    // private static List<Book> list = new ArrayList<>();
    // static {
    // list.add(new Book(134, "Spring Boot Frameworks", "Durgesh"));
    // list.add(new Book(12, "Hibernate", "Navin"));
    // list.add(new Book(453, "Servlet", "Harry"));
    // list.add(new Book(1235, "HTML & CSS", "Rahul"));
    // list.add(new Book(1442, "React", "Hitesh"));
    // }

    // get all books
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    // get single book by id
    public Book getBookById(int id) {
        Book book = null;
        try {
            // book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    // adding a book
    public Book addBook(Book b) {
        Book result = bookRepository.save(b);
        return result;
    }

    // deleting a book method-i
    public void deleteBook1(int bookId) {
        // list.remove(bookId);
        // System.out.println(list.get(bookId));
        System.out.println("Deleted Successfully.!");
    }

    // deleting a book method-ii: By using if else condition inside stream-filter
    // method
    public void deleteBook2(int bookId) {
        // list.stream().filter(book -> { if (book.getId() != bookId) { return true;}
        // else { return false; } }).collect(Collectors.toList());
        bookRepository.deleteById(bookId);
        System.out.println("Deleted Successfully.!");
        // System.out.println(list.get(bookId));
    }

    // deleting a book method-iii: By using lambda function inside stream-filter
    // method
    // public void deleteBook3(int bookId) {
    // System.out.println(list.get(bookId));
    // list.stream().filter(book -> book.getId() !=
    // bookId).collect(Collectors.toList());
    // System.out.println("Deleted Successfully.!");
    // }

    // update the book
    public void updateBook(Book book, int bookId) {

        // list = list.stream().map(b -> { if (b.getId() == bookId) {
        // b.setTitle(book.getTitle()); b.setAuthor(book.getAuthor()); } return b;
        // }).collect(Collectors.toList());
        book.setId(bookId);
        bookRepository.save(book);
    }
}
