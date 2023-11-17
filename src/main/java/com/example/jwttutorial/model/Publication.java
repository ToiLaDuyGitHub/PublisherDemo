package com.example.jwttutorial.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "Publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 19, columnDefinition = "number(19)", updatable = false, nullable = false)
    private Long id;

    @Version
    @Column(name = "version", length = 10, columnDefinition = "number(10)")
    private Integer version;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "publishing_date", columnDefinition = "date")
    private LocalDate publishing_date;

    @ManyToMany
    @JoinTable(name = "Publication_Author", joinColumns = {
            @JoinColumn(name = "publication_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "author_id", referencedColumnName = "id")
    })
    private Set<Author> authors = new HashSet<>();

    public Publication() {

    }

    public Publication(Integer version, String title, LocalDate publishing_date) {
        this.version = version;
        this.title = title;
        this.publishing_date = publishing_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
