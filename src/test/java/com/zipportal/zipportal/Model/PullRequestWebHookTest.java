package com.zipportal.zipportal.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PullRequestWebHookTest {

    PullRequestWebHook test = new PullRequestWebHook();
    Repo repo = new Repo();
    PullRequest pr = new PullRequest();

    @Test
    public void getAction() {
        test.setAction("opened");
        assertTrue(test.getAction().equalsIgnoreCase("opened"));
    }

    @Test
    public void getPull_request() {
        test.setPull_request(pr);
        assertTrue(test.getPull_request().equals(pr));
    }

    @Test
    public void getRepository() {
        test.setRepository(repo);
        assertTrue(test.getRepository().equals(repo));
    }
}