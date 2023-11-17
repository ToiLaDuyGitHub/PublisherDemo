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

    @Column
    private String lastName;

    @Column
    private String firstName;

    @ManyToMany(cascade = {CascadeType.MERGE})
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

    public Author(Integer version, String lastName, String firstName) {
        this.version = version;
        this.lastName = lastName;
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }
}