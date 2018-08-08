package com.zipportal.zipportal.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class WebHookTest {

    WebHook test = new WebHook();
    Repo repo = new Repo();

    @Test
    public void getRepository() {
        test.setRepository(repo);
        assertTrue(test.getRepository().equals(repo));
    }
}