package com.example.jwttutorial.repository;

import com.example.jwttutorial.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
