package com.example.jwttutorial.model;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Slf4j
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, columnDefinition = "number(10)")
    private Long id;

    @NotBlank
    @Column(name = "username", length = 255)
    private String username;

    @NotBlank
    @Column(name = "email", length = 255)
    private String email;

    @NotBlank
    @Column(name = "password", length = 255)
    private String password;

    @NotNull
    @Column(name = "enable", length = 1, columnDefinition = "number(1)")
    private Integer enable;

    @NotNull
    @Column(name = "account_non_lock", length = 1, columnDefinition = "number(1)")
    private Integer account_non_lock;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<UsersRole> usersRoles;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "Users_Role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> roles;


    public User() {
        super();
    }

    public User(String username){
        super();
        this.username = username;
    }

    public User(String username, String email, String password, Integer enable, Integer account_non_lock) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.account_non_lock = account_non_lock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getAccount_non_lock() {
        return account_non_lock;
    }

    public void setAccount_non_lock(Integer account_non_lock) {
        this.account_non_lock = account_non_lock;
    }

    public Set<UsersRole> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<UsersRole> usersRoles) {
        this.usersRoles = usersRoles;
    }
}
