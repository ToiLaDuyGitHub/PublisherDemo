package com.example.jwttutorial.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Privilege", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 19, columnDefinition = "number(19)")
    private Long id;
    @Column(name = "source", nullable = false, length = 255)
    @NotBlank
    private String source;
    @Column(name = "path", length = 255)
    private String path;
    @Column(name = "parent_source", length = 255)
    private String parent_source;
    @Column(name = "name", nullable = false, length = 1000)
    @NotBlank
    private String name;
    @Column(name = "action", length = 255)
    private String action;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();

    public Privilege() {
    }

    public Privilege(Long id, String source, String path, String parent_source, String name, String action) {
        this.id = id;
        this.source = source;
        this.path = path;
        this.parent_source = parent_source;
        this.name = name;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParent_source() {
        return parent_source;
    }

    public void setParent_source(String parent_source) {
        this.parent_source = parent_source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
