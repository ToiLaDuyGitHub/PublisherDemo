package com.example.jwttutorial.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users_Role")
public class UsersRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    public UsersRole() {
        super();
    }

    public UsersRole(User users, Role roles) {
        super();
        this.users = users;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role role) {
        this.roles = role;
    }

    @Override
    public String toString() {
        return "UsersRole [id=" + id + ", roles=" + roles + "]";
    }

}
