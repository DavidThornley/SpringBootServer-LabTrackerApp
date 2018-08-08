package com.zipportal.zipportal.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RepoTest {

    Repo test = new Repo();
    Repo parent = new Repo();
    User owner = new User();

    @Test
    public void getId() {
        test.setId(1L);
        assertTrue(test.getId() == 1L);
    }

    @Test
    public void getOwner() {
        test.setOwner(owner);
        assertTrue(test.getOwner().equals(owner));
    }

    @Test
    public void getHtml_url() {
        test.setHtml_url("http://realurl.com");
        assertTrue(test.getHtml_url().equalsIgnoreCase("http://realurl.com"));
    }

    @Test
    public void getName() {
        test.setName("Real Repo");
        assertTrue(test.getName().equalsIgnoreCase("real repo"));
    }

    @Test
    public void isFork() {
        test.setFork(true);
        assertTrue(test.isFork()==true);
    }

    @Test
    public void getDescription() {
        test.setDescription("A description that describes this thing");
        assertTrue(test.getDescription().equalsIgnoreCase("A description that describes this thing"));
    }

    @Test
    public void getParent() {
        test.setParent(parent);
        assertTrue(test.getParent().equals(parent));
    }

    @Test
    public void isPrIsOpen() {
        test.setPrIsOpen(true);
        assertTrue(test.isPrIsOpen()==true);
    }
}