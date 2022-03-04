package com.bookwyrm.backend.unit.book.validator;

import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.validator.BookValidator;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.util.List;

@SpringJUnitConfig
public class BookValidatorTests {

    @Test
    public void testHappyPath(){

        //Setup
        BookUploadInput input = new BookUploadInput();
        input.setAuthor("testAuthor");
        input.setName("testName");

        //Run Validation
        List<String> errorList =  BookValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }
    @Test
    public void testMissingAuthor(){
        //Setup
        BookUploadInput input = new BookUploadInput();
        input.setName("testName");

        //Run Validation
        List<String> errorList =  BookValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."), "Expect missing author error");
    }
    @Test
    public void testMissingTitle(){
        //Setup
        BookUploadInput input = new BookUploadInput();
        input.setAuthor("testAuthor");

        //Run Validation
        List<String> errorList =  BookValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Book name missing. Please add a book name and try again."), "Expect missing book name error");
    }
    @Test
    public void testMissingEverything(){
        //Setup
        BookUploadInput input = new BookUploadInput();

        //Run Validation
        List<String> errorList =  BookValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Book name missing. Please add a book name and try again."), "Expect missing book name error");
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."), "Expect missing author error");
    }


}
