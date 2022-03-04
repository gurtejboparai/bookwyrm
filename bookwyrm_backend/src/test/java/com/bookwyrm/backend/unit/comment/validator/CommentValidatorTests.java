package com.bookwyrm.backend.unit.comment.validator;

import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.validator.BookValidator;
import com.bookwyrm.backend.comment.input.CommentUploadInput;
import com.bookwyrm.backend.comment.validator.CommentValidator;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig
public class CommentValidatorTests {

    @Test
    public void testHappyPath(){

        //Setup
        CommentUploadInput input = new CommentUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setDescription("testContent");

        //Run Validation
        List<String> errorList =  CommentValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }
    @Test
    public void testMissingAuthor(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();
        input.setAnonymousFlag(true);
        input.setDescription("testContent");

        //Run Validation
        List<String> errorList =  CommentValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."), "Expect missing author error");
    }
    @Test
    public void testMissingDescription(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);

        //Run Validation
        List<String> errorList =  CommentValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Comment description is missing. Please add a comment description and try again."), "Expect missing description error");
    }
    @Test
    public void testMissingAnonymousFlag(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();
        input.setAuthor("testAuthor");
        input.setDescription("testContent");

        //Run Validation
        List<String> errorList =  CommentValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no errors");
    }

    @Test
    public void testMissingEverything(){
        //Setup
        CommentUploadInput input = new CommentUploadInput();

        //Run Validation
        List<String> errorList =  CommentValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Comment description is missing. Please add a comment description and try again."), "Expect missing description error");
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."), "Expect missing author error");
    }

}
