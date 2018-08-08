package com.zipportal.zipportal.Controller;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.zipportal.zipportal.Model.Assignment;
import com.zipportal.zipportal.Model.Repo;
import com.zipportal.zipportal.Model.User;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
public class AssignmentController {

    @Autowired
    AssignmentRepository assignRepo;

    @Autowired
    RepoRepository repoRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PullRequestRepository prRepo;


    @PostMapping("/assignment")
    public ResponseEntity<String> createUser(@Valid @RequestBody String message) {
       //sorts out text from slackbot /assign command and produces usable assignmenturl
        String url = sortSlackPostRequestIntoUsableUrl(message);

        System.out.println(url);

        //creates assignment
        Assignment assigned = new Assignment();
        Repo assignedRepo = repoRepo.findRepoByHtml_url(url);

        if(assignedRepo != null){
            System.out.println(assignedRepo.getHtml_url());
            assigned.setAssignmentUrl(assignedRepo.getHtml_url());
            assigned.setInstructor(assignedRepo.getOwner());
            assigned.setParent(assignedRepo);
        }

        if(assigned.getParent() != null){
            assigned.setInstructor(assigned.getParent().getOwner());
        }

        //if assignment does not exist for url one is created and saved
        if(assignRepo.findAssignmentsByAssignment_Url(url) == null) {
            assignRepo.save(assigned);
          return new ResponseEntity<String>("New Assignment: " + assigned.getParent().getName(), HttpStatus.OK);
        }

        //if assignment for url already exists it is returned
        assignRepo.getOne(assignRepo.findAssignmentsByAssignment_Url(assigned.getAssignmentUrl()).getId());
        return new ResponseEntity<String>(assigned.getParent().getName() + " Is Already Assigned!", HttpStatus.ALREADY_REPORTED);
    }

    @CrossOrigin
    @GetMapping("/assignment")
    public ResponseEntity<List<Assignment>> getAllAssignment(){
        return new ResponseEntity<>(assignRepo.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/assignment/{userid}")
    public ResponseEntity<List<Assignment>> getAllDistinctAssignments(@PathVariable("userid") Long user_id){
        List<BigInteger> finished = prRepo.findDistinctByUser(user_id);
        List<Assignment> doneSon = new ArrayList<>();
        for(BigInteger l: finished){
            doneSon.add(assignRepo.findById(l.longValue()).get());
        }

        List<Assignment> currentAssignments = assignRepo.findAll();
        List<Assignment> inProgress = new ArrayList<>();

        for(Assignment a: currentAssignments){
            if(!doneSon.contains(a)){
                inProgress.add(a);
            }
        }

        return new ResponseEntity<>(inProgress, HttpStatus.OK);
    }

    @GetMapping("/test/assignment")
    public @ResponseBody String test(){
        return "I Sure Hope This Worked!";
    }



    private String sortSlackPostRequestIntoUsableUrl(String slackPost){

        Pattern inputUrl = Pattern.compile("(?<=&text=)(.*)(?=&response_url)");
        Matcher urlMatch = inputUrl.matcher(slackPost);
        urlMatch.find();
        String url = urlMatch.group();

        Pattern colon = Pattern.compile("%3A");
        Matcher colonMatch = colon.matcher(url);
        String inprogressUrl = colonMatch.replaceAll(":");

        Pattern  slash = Pattern.compile("%2F");
        Matcher slashMatch = slash.matcher(inprogressUrl);
        String finalUrl = slashMatch.replaceAll("/");

        return finalUrl;
    }
}
