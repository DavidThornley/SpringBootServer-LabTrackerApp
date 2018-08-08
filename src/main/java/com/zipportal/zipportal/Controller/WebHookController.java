package com.zipportal.zipportal.Controller;

import com.zipportal.zipportal.Model.*;
import com.zipportal.zipportal.Repository.AssignmentRepository;
import com.zipportal.zipportal.Repository.PullRequestRepository;
import com.zipportal.zipportal.Repository.RepoRepository;
import com.zipportal.zipportal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class WebHookController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    RepoRepository repoRepo;
    @Autowired
    AssignmentRepository assignRepo;
    @Autowired
    PullRequestRepository pullRequestRepo;


    @PostMapping("/webhook")
    public ResponseEntity<Repo> processWebHook(@Valid @RequestBody WebHook webHook){

        //Discover repos associated with event
        Repo hookRepo = webHook.getRepository();
        Repo parentRepo = null;
        if(webHook.getRepository().getParent() != null) {
            parentRepo = webHook.getRepository().getParent();
        }

        //Document repo ownership and user
        User owner = hookRepo.getOwner();
        userRepo.save(owner);
        repoRepo.save(hookRepo);

        //determine if event is associated with assignment
        //Assignment assignment= assignRepo.findAssignmentsByAssignment_Url(parentRepo.getHtml_url());


        if(parentRepo != null) {
            User parentOwner = webHook.getRepository().getParent().getOwner();
            userRepo.save(parentOwner);
            repoRepo.save(parentRepo);
        }


        System.out.println("FORK STATUS: "+ hookRepo.isFork());
        return new ResponseEntity(hookRepo, HttpStatus.OK);
    }

    @PostMapping("/webhook/PR")
    public ResponseEntity<HttpStatus> processPR(@Valid @RequestBody PullRequestWebHook pr){

        //make sure that repo is being saved is source(student) repo and not target(parent) repo...
        PullRequestWebHook pullRequest = pr;
        PullRequest pull_request = pullRequest.getPull_request();
        String action = pullRequest.getAction();
        Repo prRepo = pullRequest.getRepository();
        User user = pull_request.getUser();
        Assignment assignment = assignRepo.findAssignmentsByAssignment_Url(prRepo.getHtml_url());

        if(userRepo.existsById(user.getId()) == false) {
            userRepo.save(user);
        }

        if(repoRepo.existsById(prRepo.getId()) == false) {
            repoRepo.save(prRepo);
        }


        if(action.equalsIgnoreCase("opened")) {
            if(!pullRequestRepo.findAllByUser_Id(pull_request.getUser().getId()).contains(pull_request)) {
                Repo temp = prRepo;
                temp.setPrIsOpen(true);
                repoRepo.save(temp);

                PullRequest savePr = pull_request;
                savePr.setUser(savePr.getUser());
                savePr.setAssignment(assignRepo.findAssignmentsByAssignment_Url(savePr.getBase().getRepo().getHtml_url()));
                pullRequestRepo.saveAndFlush(savePr);
                assignment.addPullRequest(savePr);
                assignRepo.saveAndFlush(assignment);
            }
        }



        System.out.println("Repo Id: " + prRepo.getId() + " for User: " + user.getUsername() + " has a PR Status of: " + prRepo.isPrIsOpen());
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }


}
