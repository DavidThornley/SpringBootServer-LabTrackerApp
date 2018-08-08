package com.zipportal.zipportal.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PullRequestTest {

    PullRequest test = new PullRequest();
    Base base = new Base();
    User user = new User();
    Assignment assignment = new Assignment();

    @Test
    public void getId() {
        test.setId(1L);
        assertTrue(test.getId() == 1L);
    }

    @Test
    public void getBase() {
        test.setBase(base);
        assertTrue(test.getBase().equals(base));
    }

    @Test
    public void getUser() {
        test.setUser(user);
        assertTrue(test.getUser().equals(user));
    }

    @Test
    public void getAssignment() {
        test.setAssignment(assignment);
        assertTrue(test.getAssignment().equals(assignment));
    }
}