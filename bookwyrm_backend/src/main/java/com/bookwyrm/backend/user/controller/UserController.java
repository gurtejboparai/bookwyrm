package com.bookwyrm.backend.user.controller;

import com.bookwyrm.backend.user.dao.UserDao;
import com.bookwyrm.backend.user.dao.UserService;
import com.bookwyrm.backend.user.input.UserAuthInput;
import com.bookwyrm.backend.user.input.UserUpdateInput;
import com.bookwyrm.backend.user.payload.UserPayload;
import com.bookwyrm.backend.user.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<List<String>> createUser(
            @RequestBody UserAuthInput userAuthInput) {

        List<String> errorList = UserValidator.validateSignupInformation(userAuthInput);
        HttpStatus status = HttpStatus.OK;

        Optional<UserDao> foundUser = userService.findById(userAuthInput.getUsername());
        if(foundUser.isPresent()){
            errorList.add("Username already exists. Please try a different username.");
        }
        if (errorList.isEmpty()) {
            UserDao userDao = new UserDao(
                    userAuthInput.getUsername(),
                    userAuthInput.getPasswordHash()
            );
            userService.save(userDao);
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(errorList);
    }

    @CrossOrigin
    @PutMapping("/login")
    public ResponseEntity<UserPayload> signIn(
            @RequestBody UserAuthInput userAuthInput){
        Optional<UserDao> foundUser = userService.findById(userAuthInput.getUsername());
        UserPayload payload = new UserPayload();

        //Check that we have all the expected fields
        if(UserValidator.validateSignupInformation(userAuthInput).isEmpty()) {
            //Check sign in attempt
            if(foundUser.isPresent() && foundUser.get().getPasswordHash().equals(userAuthInput.getPasswordHash())) {
                payload.setUser(foundUser.get());
            }
        }
        //Primitive online dictionary attack prevention
        if (payload.getUser()==null) {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(payload);
    }
    @CrossOrigin
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPayload> updateUser(
            @RequestBody UserUpdateInput userUpdateInput){
        Optional<UserDao> foundUser = userService.findById(userUpdateInput.getUsername());
        HttpStatus status = HttpStatus.OK;
        UserPayload payload = new UserPayload();
        if(UserValidator.validateUpdateInformation(userUpdateInput).isEmpty()) {

            //If user exists, update their details
            if(foundUser.isPresent()) {
                foundUser.get().setAuthorFlag(userUpdateInput.getAuthorFlag());
                foundUser.get().setJournalistFlag(userUpdateInput.getJournalistFlag());
                foundUser.get().setAuthorName(userUpdateInput.getAuthorName());
                foundUser.get().setJournalistName(userUpdateInput.getJournalistName());

                //Save user
                userService.save(foundUser.get());
                payload.setUser(foundUser.get());
            }
        }else{
            payload.setErrorList(UserValidator.validateUpdateInformation(userUpdateInput));
        }
        if(foundUser.isEmpty()){
            status = HttpStatus.NOT_FOUND;
            payload.setErrorList(Arrays.asList("User could not be found"));
        }


        return ResponseEntity.status(status).body(payload);
    }
}
