package com.bookwyrm.backend.unit.user.controller;

import com.bookwyrm.backend.user.controller.UserController;
import com.bookwyrm.backend.user.dao.UserDao;
import com.bookwyrm.backend.user.dao.UserService;
import com.bookwyrm.backend.user.input.UserAuthInput;
import com.bookwyrm.backend.user.input.UserUpdateInput;
import com.bookwyrm.backend.user.payload.UserPayload;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringJUnitConfig
@SpringBootTest
public class UserControllerTests {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController controller;

    @Test
    public void testHappyPathSignup(){

        //Setup
        UserAuthInput input = new UserAuthInput();
        input.setUsername("testUsername");
        input.setPasswordHash(new BigInteger(String.valueOf(123)));

        //Run
        ResponseEntity response =  controller.createUser(input);

        //Check results
        Assert.isTrue(response.getStatusCode().equals( HttpStatus.OK), "Expected successful endpoint call with 200 status");
        Assert.isTrue(((List)response.getBody()).isEmpty() , "Expected successful endpoint call with no error messages");

    }
    @Test
    public void testBadRequest(){
        //Setup
        UserAuthInput input = new UserAuthInput();

        //Run
        ResponseEntity response =  controller.createUser(input);

        //Check results
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Expected failed endpoint call with 400 status");
        Assert.isTrue(!((List)response.getBody()).isEmpty(), "Expected failed endpoint call with error messages");
    }

    @Test
    public void testHappyPathSignIn(){

        MockitoAnnotations.openMocks(this);
        Mockito.when(userService.findById(any(String.class))).then((x)-> Optional.of(new UserDao("testUsername",new BigInteger(String.valueOf(123)))));

        //Setup
        UserAuthInput input = new UserAuthInput();
        input.setUsername("testUsername");
        input.setPasswordHash(new BigInteger(String.valueOf(123)));

        //Run
        ResponseEntity response =  controller.signIn(input);

        //Check results
        Assert.isTrue(response.getStatusCode().equals( HttpStatus.OK), "Expected successful endpoint call with 200 status");
        Assert.notNull(((UserPayload)response.getBody()).getUser() , "Expected successful sign in, with user returned");
    }

    @Test
    public void testFailedSignIn(){

        MockitoAnnotations.openMocks(this);
        Mockito.when(userService.findById(any(String.class))).then((x)-> Optional.of(new UserDao("testUsername",new BigInteger(String.valueOf(1)))));

        //Setup
        UserAuthInput input = new UserAuthInput();
        input.setUsername("testUsername");
        input.setPasswordHash(new BigInteger(String.valueOf(123)));

        //Run
        ResponseEntity response =  controller.signIn(input);

        //Check results
        Assert.isTrue(response.getStatusCode().equals( HttpStatus.OK), "Expected successful endpoint call with 200 status");
        Assert.isNull(((UserPayload)response.getBody()).getUser() , "Expected failed sign in, with user returned");
    }

    @Test
    public void testHappyPathUpdate(){

        MockitoAnnotations.openMocks(this);
        Mockito.when(userService.findById(any(String.class))).then((x)-> Optional.of(new UserDao("testUsername",new BigInteger(String.valueOf(123)))));

        //Setup
        UserUpdateInput input = new UserUpdateInput();
        input.setUsername("testUsername");
        input.setJournalistName("testJournalistName");
        input.setAuthorName("testAuthorName");
        input.setAuthorFlag(false);
        input.setJournalistFlag(false);


        //Run
        ResponseEntity response =  controller.updateUser(input);

        //Check results
        Assert.isTrue(response.getStatusCode().equals( HttpStatus.OK), "Expected successful endpoint call with 200 status");
        Assert.notNull(((UserPayload)response.getBody()).getUser() , "Expected successful update, with user returned");
        Assert.isNull(((UserPayload)response.getBody()).getErrorList() , "Expected successful update, with no errors returned");

    }

    @Test
    public void testFailedUpdate(){

        MockitoAnnotations.openMocks(this);
        Mockito.when(userService.findById(any(String.class))).then((x)-> Optional.of(new UserDao("testUsername",new BigInteger(String.valueOf(1)))));

        //Setup
        UserUpdateInput input = new UserUpdateInput();

        //Run
        ResponseEntity response =  controller.updateUser(input);

        //Check results
        Assert.isTrue(response.getStatusCode().equals( HttpStatus.NOT_FOUND), "Expected failed endpoint call with 400 status");
        Assert.isNull(((UserPayload)response.getBody()).getUser() , "Expected failed update, with user returned");
        Assert.notNull(((UserPayload)response.getBody()).getErrorList() , "Expected failed update, with errors returned");
    }
}

