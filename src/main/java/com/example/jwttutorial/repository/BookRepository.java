package com.example.jwttutorial.repository;

import com.example.jwttutorial.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
