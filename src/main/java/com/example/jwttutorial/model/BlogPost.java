package com.example.jwttutorial.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Blog_Post")
public class BlogPost extends Publication{
    @Column(name = "url", length = 255)
    private String url;

    public BlogPost() {
        super();
    }

    public BlogPost(String url) {
        this.url = url;
    }

    public BlogPost(Integer version, String title, LocalDate publishing_date, String url) {
        super(version, title, publishing_date);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
