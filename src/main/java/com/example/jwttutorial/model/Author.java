package com.example.jwttutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 19, columnDefinition = "number(19)")
    private Long id;

    @Version
    @Column(name = "version", length = 10, columnDefinition = "number(10)")
    private Integer version;

    @Column(name = "last_name", length = 255)
    private String last_name;

    @Column(name = "first_name", length = 255)
    private String first_name;

    @ManyToMany
    @JoinTable(name = "publication_author",
            joinColumns = {
            @JoinColumn(name = "author_id", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "publication_id", referencedColumnName = "id")
    }
    )
    @JsonIgnore
    private Set<Publication> publications = new HashSet<>();

    public Author() {
    }

    public Author(Long id, Integer version, String last_name, String first_name, Set<Publication> publications) {
        this.id = id;
        this.version = version;
        this.last_name = last_name;
        this.first_name = first_name;
        this.publications = publications;
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }
}