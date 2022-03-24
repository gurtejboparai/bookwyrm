package com.bookwyrm.backend.user.controller;

import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.user.dao.UserDao;
import com.bookwyrm.backend.user.dao.UserService;
import com.bookwyrm.backend.user.input.UserInput;
import com.bookwyrm.backend.user.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<List<String>> createUser(
            @RequestBody UserInput userInput) {

        List<String> errorList = UserValidator.validateSignupInformation(userInput);
        HttpStatus status = HttpStatus.OK;

        if (errorList.isEmpty()) {
            UserDao userDao = new UserDao(
                    userInput.getUsername(),
                    userInput.getPasswordHash()
            );
            userService.save(userDao);
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(errorList);
    }

    @CrossOrigin
    @PutMapping("/login")
    public ResponseEntity<Boolean> signIn(
            @RequestBody UserInput userInput){

        //Check that we have all the expected fields
        Boolean result = UserValidator.validateSignupInformation(userInput).isEmpty();
        if(result) {
            //Check sign in attempt
            result = userService.findById(userInput.getUsername())
                    .get()
                    .getPasswordHash()
                    .equals(userInput.getPasswordHash());

            //Primitive online dictionary attack prevention
            if (!result) {
                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
