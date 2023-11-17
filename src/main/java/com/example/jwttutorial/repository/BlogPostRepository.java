package com.example.jwttutorial.repository;

import com.example.jwttutorial.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
