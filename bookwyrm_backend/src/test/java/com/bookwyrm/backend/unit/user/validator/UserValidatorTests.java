package com.bookwyrm.backend.unit.user.validator;

import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.validator.ReviewValidator;
import com.bookwyrm.backend.user.input.UserInput;
import com.bookwyrm.backend.user.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class UserValidatorTests {

    @Test
    public void testHappyPath(){
        //Setup
        UserInput input = new UserInput();
        input.setUsername("testUsername");
        input.setPasswordHash("testPasswordHash");

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }
    @Test
    public void testMissingUsername(){
        //Setup
        UserInput input = new UserInput();
        input.setPasswordHash("testPasswordHash");

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Username missing. Please add a Username and try again."), "Expect missing author error");
    }
    @Test
    public void testMissingPassword(){
        //Setup
        UserInput input = new UserInput();
        input.setUsername("testUsername");

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Password missing. Please add a Password and try again."), "Expect missing description error");
    }
    @Test
    public void testMissingEverything(){
        //Setup
        UserInput input = new UserInput();

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Password missing. Please add a Password and try again."), "Expect missing description error");
        Assert.isTrue(errorList.contains("Username missing. Please add a Username and try again."), "Expect missing author error");
    }
}