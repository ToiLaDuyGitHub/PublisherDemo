package com.example.jwttutorial.services;

import com.example.jwttutorial.model.Author;
import com.example.jwttutorial.model.BlogPost;
import com.example.jwttutorial.repository.AuthorRepository;
import com.example.jwttutorial.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BlogPostDetailsServiceImpl {
    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    public BlogPostDetailsServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getBlogPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost saveBlogPost(BlogPost blogPost) {
        Set<Author> authors = blogPost.getAuthors();
        Iterator<Author> iterator = authors.iterator();
        while (iterator.hasNext()) {
            Author author = iterator.next();
            Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            if (existingAuthor.isPresent()) {
                iterator.remove();
                authors.add(existingAuthor.get());
            }
        }
        blogPost.setAuthors(authors);
        return blogPostRepository.save(blogPost);
    }

    public void deleteBlogPost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
