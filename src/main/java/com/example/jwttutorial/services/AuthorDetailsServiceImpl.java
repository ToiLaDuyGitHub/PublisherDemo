package com.example.jwttutorial.services;

import com.example.jwttutorial.model.Author;
import com.example.jwttutorial.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorDetailsServiceImpl {
    AuthorRepository authorRepository;

    @Autowired
    public AuthorDetailsServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
