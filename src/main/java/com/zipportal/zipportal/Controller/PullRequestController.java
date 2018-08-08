package com.zipportal.zipportal.Controller;


import com.zipportal.zipportal.Model.Assignment;
import com.zipportal.zipportal.Model.PullRequest;
import com.zipportal.zipportal.Repository.AssignmentRepository;
import com.zipportal.zipportal.Repository.PullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PullRequestController {

    @Autowired
    PullRequestRepository prRepo;

    @Autowired
    AssignmentRepository assignRepo;

    @GetMapping("/pr/{user_id}")
    public ResponseEntity<List<Assignment>> getAllDistinctPrs(@PathVariable("user_id") Long user_id){
        List<BigInteger> finished = prRepo.findDistinctByUser(user_id);
        List<Assignment> doneSon = new ArrayList<>();
        for(BigInteger l: finished){
            doneSon.add(assignRepo.findById(l.longValue()).get());
        }

        return new ResponseEntity<>( doneSon, HttpStatus.OK);
    }
}
