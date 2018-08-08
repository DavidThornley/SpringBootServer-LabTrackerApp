package com.zipportal.zipportal.Model;

import javax.persistence.*;


@Entity
@Table(name = "repo")
public class Repo {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID")
    private User owner;

    private String html_url;
    private String name;
    private boolean fork;
    private boolean prIsOpen;
    private String description;

    @OneToOne
    @JoinColumn(name = "Parent_REPO_ID")
    Repo parent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Repo getParent() {
        return parent;
    }

    public void setParent(Repo parent) {
        this.parent = parent;
    }

    public boolean isPrIsOpen() {
        return prIsOpen;
    }

    public void setPrIsOpen(boolean prIsOpen) {
        this.prIsOpen = prIsOpen;
    }
}
