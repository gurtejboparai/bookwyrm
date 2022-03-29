package com.bookwyrm.backend.unit.user.controller;

import com.bookwyrm.backend.user.controller.UserController;
import com.bookwyrm.backend.user.dao.UserService;
import com.bookwyrm.backend.user.input.UserAuthInput;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.List;

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
}

