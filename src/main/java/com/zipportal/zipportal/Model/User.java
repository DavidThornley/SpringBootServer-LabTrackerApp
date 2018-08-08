package com.zipportal.zipportal.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String login;
    private String username;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_pullrequest",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pullrequest_prid",
                    referencedColumnName = "id"))
    private List<PullRequest> studentPrs;

    public User(Long id, String login, String username, String email) {
        this.id = id;
        this.login = login;
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

}
