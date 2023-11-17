package com.example.jwttutorial.controller;

import com.example.jwttutorial.model.Author;
import com.example.jwttutorial.services.AuthorDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    AuthorDetailsServiceImpl authorDetailsService;
    @Autowired
    public AuthorController(AuthorDetailsServiceImpl authorDetailsService){
        this.authorDetailsService = authorDetailsService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('ADMIN')")
    public List<Author> getAllAuthors() {
        return authorDetailsService.getAllAuthors();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('ADMIN')")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> optionalAuthor = authorDetailsService.getAuthorById(id);
        return optionalAuthor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        Author savedAuthor = authorDetailsService.saveAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        if (!authorDetailsService.getAuthorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        author.setId(id);
        Author updatedAuthor = authorDetailsService.saveAuthor(author);

        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        if (!authorDetailsService.getAuthorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        authorDetailsService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }
}