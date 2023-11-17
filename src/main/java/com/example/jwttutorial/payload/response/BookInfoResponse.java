package com.example.jwttutorial.payload.response;

import java.time.LocalDate;

public class BookInfoResponse {
    private Long id;
    private Integer pages;
    private Integer version;
    private String title;
    private LocalDate publishing_date;
    private String img_url;
    public BookInfoResponse() {

    }

    public BookInfoResponse(Integer pages, Integer version, String title, LocalDate publishing_date, String img_url) {
        this.pages = pages;
        this.version = version;
        this.title = title;
        this.publishing_date = publishing_date;
        this.img_url = img_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishing_date() {
        return publishing_date;
    }

    public void setPublishing_date(LocalDate publishing_date) {
        this.publishing_date = publishing_date;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
