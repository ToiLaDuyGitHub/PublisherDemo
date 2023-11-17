package com.example.jwttutorial.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Book")
public class Book extends Publication {
    @Column(name = "pages", length = 10, columnDefinition = "number(10)")
    private Integer page;
    private String imgUrl;

    public Book() {
        super();
    }

    public Book(Integer page, String imgUrl) {
        this.page = page;
        this.imgUrl = imgUrl;
    }

    public Book(Integer version, String title, LocalDate publishing_date, Integer page, String imgUrl) {
        super(version, title, publishing_date);
        this.page = page;
        this.imgUrl = imgUrl;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}