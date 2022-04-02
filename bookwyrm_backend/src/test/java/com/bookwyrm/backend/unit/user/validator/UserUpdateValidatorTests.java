package com.bookwyrm.backend.unit.user.validator;

import com.bookwyrm.backend.user.input.UserUpdateInput;
import com.bookwyrm.backend.user.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class UserUpdateValidatorTests {

    @Test
    public void testHappyPath(){
        //Setup
        UserUpdateInput input = new UserUpdateInput();
        input.setUsername("testUsername");
        input.setAuthorName("testAuthorName");
        input.setAuthorFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournalistName");

        //Run Validation
        List<String> errorList =  UserValidator.validateUpdateInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }
    @Test
    public void testMissingUsername(){
        //Setup
        UserUpdateInput input = new UserUpdateInput();
        input.setAuthorName("testAuthorName");
        input.setAuthorFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournalistName");

        //Run Validation
        List<String> errorList =  UserValidator.validateUpdateInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Username missing. Please add Username and try again."), "Expect missing author error");
    }
    @Test
    public void testMissingAuthorName(){
        //Setup
        UserUpdateInput input = new UserUpdateInput();
        input.setUsername("testUsername");
        input.setAuthorFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournalistName");

        //Run Validation
        List<String> errorList =  UserValidator.validateUpdateInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Author Name missing. Please add Author Name and try again."), "Expect missing description error");
    }
    @Test
    public void testMissingAuthorFlag(){
        //Setup
        UserUpdateInput input = new UserUpdateInput();
        input.setUsername("testUsername");
        input.setAuthorName("testAuthorName");
        input.setJournalistFlag(false);
        input.setJournalistName("testJournalistName");

        //Run Validation
        List<String> errorList =  UserValidator.validateUpdateInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Author Flag missing. Please add Author Flag and try again."), "Expect missing description error");
    }
    @Test
    public void testMissingProfJournalistFlag(){
        //Setup
        UserUpdateInput input = new UserUpdateInput();
        input.setUsername("testUsername");
        input.setAuthorName("testAuthorName");
        input.setAuthorFlag(false);
        input.setJournalistName("testJournalistName");

        //Run Validation
        List<String> errorList =  UserValidator.validateUpdateInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Professional Journalist Flag missing. Please add Professional Journalist Flag and try again."), "Expect missing description error");
    }
    @Test
    public void testMissingProfJournalistName(){
        //Setup
        UserUpdateInput input = new UserUpdateInput();
        input.setUsername("testUsername");
        input.setAuthorName("testAuthorName");
        input.setAuthorFlag(false);
        input.setJournalistFlag(false);

        //Run Validation
        List<String> errorList =  UserValidator.validateUpdateInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Professional Journalist Name missing. Please add Professional Journalist Name and try again."), "Expect missing description error");
    }

    @Test
    public void testMissingEverything(){
        //Setup
        UserUpdateInput input = new UserUpdateInput();

        //Run Validation
        List<String> errorList =  UserValidator.validateUpdateInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Professional Journalist Name missing. Please add Professional Journalist Name and try again."), "Expect missing description error");
        Assert.isTrue(errorList.contains("Professional Journalist Flag missing. Please add Professional Journalist Flag and try again."), "Expect missing description error");
        Assert.isTrue(errorList.contains("Author Flag missing. Please add Author Flag and try again."), "Expect missing description error");
        Assert.isTrue(errorList.contains("Author Name missing. Please add Author Name and try again."), "Expect missing description error");
        Assert.isTrue(errorList.contains("Username missing. Please add Username and try again."), "Expect missing author error");
    }
}