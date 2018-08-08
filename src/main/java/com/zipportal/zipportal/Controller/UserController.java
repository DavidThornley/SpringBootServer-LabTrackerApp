package com.zipportal.zipportal.Controller;

import com.zipportal.zipportal.Model.User;
import com.zipportal.zipportal.Repository.RepoRepository;
import com.zipportal.zipportal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RepoRepository repoRepo;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        return new ResponseEntity<User>(userRepo.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserById(@PathVariable("username") String username) {
        return new ResponseEntity<User>(userRepo.findUserByUsername(username), HttpStatus.OK);

    }

}
