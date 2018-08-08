package com.zipportal.zipportal.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pullrequest")
public class PullRequest {
    @Id
    private Long Id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Transient
    private Base base;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
