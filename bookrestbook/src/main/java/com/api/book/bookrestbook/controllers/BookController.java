package com.api.book.bookrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OperatorInstanceof;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.book.bookrestbook.entities.Book;
import com.api.book.bookrestbook.services.BookService;

@RestController
public class BookController {

    @RequestMapping(value = "/books1", method = RequestMethod.GET)
    public String getBooks1() {
        return "This is testing book first from PostMan, using @RequestMapping(value = \"/books\", method = RequestMethod.GET) Annotation";
    }

    @GetMapping("/books2")
    public Book getBooks2() {
        Book book = new Book(12323, "Core Java", "Durgesh");
        // return "This is testing book first from PostMan, using
        // @GetMapping(\"/books\") Annotation";
        return book;
    }

    @Autowired
    private BookService bookService;

    // GET ALL BOOKS
    @GetMapping("/getallbooks")
    public List<Book> getBooks() {
        return this.bookService.getAllBooks();
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks3() {
        List<Book> list = this.bookService.getAllBooks();

        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.of(Optional.of(list));
        }
    }

    // GET SINGLE BOOK BY ID METHOD-I
    @GetMapping("/getsinglebook")
    public Book getsinglebook() {
        Book book = this.bookService.getBookById(1233);
        return book;
    }

    // GET SINGLE BOOK BY ID METHOD-II
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getbook(@PathVariable("id") int id) {
        Book book = this.bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } else {
            return ResponseEntity.of(Optional.of(book));
        }
    }

    // ADDING A NEW BOOK
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;

        try {
            b = this.bookService.addBook(book);
            System.out.println(b);
            // return ResponseEntity.status(HttpStatus.CREATED).build();
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // DELETE BOOK HANDLER
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
        try {
            this.bookService.deleteBook2(bookId);
            System.out.println("Deleted Successfully.!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Ocurred.!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // UPDATE BOOK HANDLER
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
        try {
            this.bookService.updateBook(book, bookId);
            System.out.println("Book details updated Successfully: " + book);
            return ResponseEntity.ok().body(book);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
