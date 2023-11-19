package com.example.jwttutorial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwttutorial.model.Book;
import com.example.jwttutorial.repository.BookRepository;
import com.example.jwttutorial.services.BookDetailsServiceImpl;

@CrossOrigin(origins = "http://localhost:3070/", allowCredentials = "true")
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookDetailsServiceImpl bookDetailsService;

    @Autowired
    public BookController(BookDetailsServiceImpl bookService) {
        this.bookDetailsService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('ADMIN')")
    public List<Book> getAllBooks() {
        return bookDetailsService.getAllBooks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('ADMIN')")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookDetailsService.getBookById(id);
        return optionalBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookDetailsService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookDetailsService.getBookById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        Book updatedBook = bookDetailsService.saveBook(book);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        if (!bookDetailsService.getBookById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bookDetailsService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
