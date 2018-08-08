package com.zipportal.zipportal.Model;

public class PullRequestWebHook {

    private String action;
    private PullRequest pull_request;
    private Repo repository;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public PullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequest pull_request) {
        this.pull_request = pull_request;
    }

    public Repo getRepository() {
        return repository;
    }

    public void setRepository(Repo repository) {
        this.repository = repository;
    }
}
