package com.example.jwttutorial.services;

import com.example.jwttutorial.model.Author;
import com.example.jwttutorial.model.Book;
import com.example.jwttutorial.repository.AuthorRepository;
import com.example.jwttutorial.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookDetailsServiceImpl {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    public BookDetailsServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        Set<Author> authors = book.getAuthors();
        Iterator<Author> iterator = authors.iterator();
        while (iterator.hasNext()) {
            Author author = iterator.next();
            Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            if (existingAuthor.isPresent()) {
                iterator.remove();
                authors.add(existingAuthor.get());
            }
        }
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
