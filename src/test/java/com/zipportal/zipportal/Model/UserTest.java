package com.zipportal.zipportal.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User test = new User();

    @Test
    public void getId() {
        test.setId(33L);
        assertTrue(test.getId() == 33L);
    }

    @Test
    public void getLogin() {
        test.setLogin("DaveLogin");
        assertTrue(test.getLogin().equalsIgnoreCase("davelogin"));
    }

    @Test
    public void getUsername() {
        test.setUsername("DAVE");
        assertTrue(test.getUsername().equalsIgnoreCase("dave"));
    }

    @Test
    public void getEmail() {
        test.setEmail("real@real.com");
        assertTrue(test.getEmail().equalsIgnoreCase("real@real.com"));
    }

}