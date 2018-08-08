package com.zipportal.zipportal.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseTest {

    Base test = new Base();
    Repo repo = new Repo();

    @Test
    public void getRepo_id() {
        repo.setId(22L);
        test.setRepo(repo);
        test.setRepo_id(test.getRepo().getId());
        assertTrue(test.getRepo_id() == repo.getId());
    }

    @Test
    public void getRepo() {
        test.setRepo(repo);
        assertTrue(test.getRepo().equals(repo));
    }
}