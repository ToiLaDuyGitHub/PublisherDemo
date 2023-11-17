package com.example.jwttutorial.controller;

import com.example.jwttutorial.model.BlogPost;
import com.example.jwttutorial.repository.BlogPostRepository;
import com.example.jwttutorial.services.BlogPostDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogpost")
public class BlogPostController {
    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    BlogPostDetailsServiceImpl blogPostDetailsService;

    @Autowired
    public BlogPostController(BlogPostDetailsServiceImpl blogPostService) {
        this.blogPostDetailsService = blogPostService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('ADMIN')")
    public List<BlogPost> getAllBlogPosts() {
        return blogPostDetailsService.getAllBlogPosts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('AUTHOR') or hasRole('ADMIN')")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        Optional<BlogPost> optionalBlogPost = blogPostDetailsService.getBlogPostById(id);
        return optionalBlogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<BlogPost> saveBlogPost(@RequestBody BlogPost blogPost) {
        BlogPost savedBlogPost = blogPostDetailsService.saveBlogPost(blogPost);
        return ResponseEntity.ok(savedBlogPost);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        if (!blogPostDetailsService.getBlogPostById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        blogPost.setId(id);
        BlogPost updatedblogPost = blogPostDetailsService.saveBlogPost(blogPost);

        return ResponseEntity.ok(updatedblogPost);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<?> deleteBlogPost(@PathVariable Long id) {
        if (!blogPostDetailsService.getBlogPostById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        blogPostDetailsService.deleteBlogPost(id);
        return ResponseEntity.ok().build();
    }
}
