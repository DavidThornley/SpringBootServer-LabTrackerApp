package com.zipportal.zipportal.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date assigned_on;

    private String assignment_Url;

    @ManyToOne
    @JoinColumn(name ="INSTRUCTOR_ID")
    private User instructor;

    @OneToOne
    @JoinColumn(name = "PARENT_REPO_ID")
    private Repo parent;

    @OneToMany(mappedBy = "assignment")
    @JsonIgnore
    private Set<PullRequest> pullRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentUrl() {
        return assignment_Url;
    }

    public void setAssignmentUrl(String assignmentUrl) {
        this.assignment_Url = assignmentUrl;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public Repo getParent() {
        return parent;
    }

    public void setParent(Repo parent) {
        this.parent = parent;
    }

    public void addPullRequest(PullRequest pullRequest){
        this.pullRequests.add(pullRequest);
    }

    public Date getAssigned_on() {
        return assigned_on;
    }

    public void setAssigned_on(Date assigned_on) {
        this.assigned_on = assigned_on;
    }


}
