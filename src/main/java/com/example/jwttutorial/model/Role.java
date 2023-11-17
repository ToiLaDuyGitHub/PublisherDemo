package com.example.jwttutorial.model;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Role", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Slf4j
public class Role implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, columnDefinition = "number(10)")
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(name = "enable", nullable = false, length = 1, columnDefinition = "number(1)")
    @NotNull
    private Integer enable;

    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UsersRole> usersRoles;

    @ManyToMany
    @JoinTable(
            name = "Role_Privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Set<Privilege> privileges = new HashSet<>();

    public Role() {
        super();
    }

    public Role (String name) {
        this.name = name;
    }

    public Role(String name, Integer enable) {
        this.name = name;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Set<UsersRole> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<UsersRole> usersRoles) {
        this.usersRoles = usersRoles;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
