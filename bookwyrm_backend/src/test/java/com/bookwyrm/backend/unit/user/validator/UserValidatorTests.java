package com.bookwyrm.backend.unit.user.validator;

import com.bookwyrm.backend.user.input.UserAuthInput;
import com.bookwyrm.backend.user.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class UserValidatorTests {

    @Test
    public void testHappyPath(){
        //Setup
        UserAuthInput input = new UserAuthInput();
        input.setUsername("testUsername");
        input.setPasswordHash(new BigInteger(String.valueOf(123)));

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }
    @Test
    public void testMissingUsername(){
        //Setup
        UserAuthInput input = new UserAuthInput();
        input.setPasswordHash(new BigInteger(String.valueOf(123)));

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Username missing. Please add a Username and try again."), "Expect missing author error");
    }
    @Test
    public void testMissingPassword(){
        //Setup
        UserAuthInput input = new UserAuthInput();
        input.setUsername("testUsername");

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Password missing. Please add a Password and try again."), "Expect missing description error");
    }
    @Test
    public void testMissingEverything(){
        //Setup
        UserAuthInput input = new UserAuthInput();

        //Run Validation
        List<String> errorList =  UserValidator.validateSignupInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Password missing. Please add a Password and try again."), "Expect missing description error");
        Assert.isTrue(errorList.contains("Username missing. Please add a Username and try again."), "Expect missing author error");
    }
}