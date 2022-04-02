package com.bookwyrm.backend.unit.book.validator;

import com.bookwyrm.backend.book.input.BookUpdateInput;
import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.validator.BookValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringJUnitConfig
@SpringBootTest
public class BookValidatorTests {

    @Test
    public void testHappyPath() {

        // Setup
        BookUploadInput input = new BookUploadInput();
        input.setAuthor("testAuthor");
        input.setTitle("testName");

        // Run Validation
        List<String> errorList = BookValidator.validateUploadInformation(input);

        // Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }

    @Test
    public void testMissingAuthor() {
        // Setup
        BookUploadInput input = new BookUploadInput();
        input.setTitle("testName");

        // Run Validation
        List<String> errorList = BookValidator.validateUploadInformation(input);

        // Check output
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."),
                "Expect missing author error");
    }

    @Test
    public void testMissingTitle() {
        // Setup
        BookUploadInput input = new BookUploadInput();
        input.setAuthor("testAuthor");

        // Run Validation
        List<String> errorList = BookValidator.validateUploadInformation(input);

        // Check output
        Assert.isTrue(errorList.contains("Book name missing. Please add a book name and try again."),
                "Expect missing book name error");
    }

    @Test
    public void testMissingEverything() {
        // Setup
        BookUploadInput input = new BookUploadInput();

        // Run Validation
        List<String> errorList = BookValidator.validateUploadInformation(input);

        // Check output
        Assert.isTrue(errorList.contains("Book name missing. Please add a book name and try again."),
                "Expect missing book name error");
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."),
                "Expect missing author error");
    }

    @Test
    public void testHappyPathUpdate() {
        // Setup
        BookUpdateInput input = new BookUpdateInput();
        input.setDesc("testDesc");
        input.setId("testId");
        Map<String, Float> map = new HashMap<String, Float>();
        input.setRate(map);

        // Run Validation
        List<String> errorList = BookValidator.validateUpdateInformation(input);

        // Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }

    @Test
    public void testMissingIdUpdate() {
        // Setup
        BookUpdateInput input = new BookUpdateInput();
        input.setDesc("testDesc");
        Map<String, Float> map = new HashMap<String, Float>();
        input.setRate(map);

        // Run Validation
        List<String> errorList = BookValidator.validateUpdateInformation(input);

        // Check output
        Assert.isTrue(errorList.contains("Book id missing. Please add a book id and try again."),
                "Expect missing book id error");
    }

    @Test
    public void testMissingDescUpdate() {
        // Setup
        BookUpdateInput input = new BookUpdateInput();
        input.setId("testId");
        Map<String, Float> map = new HashMap<String, Float>();
        input.setRate(map);

        // Run Validation
        List<String> errorList = BookValidator.validateUpdateInformation(input);

        // Check output
        Assert.isTrue(errorList.contains("Book description missing. Please add a book description and try again."),
                "Expect missing book description error");
    }
    @Test
    public void testMissingRateUpdate(){
        //Setup
        BookUpdateInput input = new BookUpdateInput();
        input.setDesc("testDesc");
        input.setId("testId");
        // Run Validation
        List<String> errorList = BookValidator.validateUpdateInformation(input);

        // Check output
        Assert.isTrue(errorList.contains("Book update: Review rating is missing. Please add a rating and try again"),
                "Expect missing review ratings error");

    }

    @Test
    public void testMissingEverythingUpdate() {
        // Setup
        BookUpdateInput input = new BookUpdateInput();

        // Run Validation
        List<String> errorList = BookValidator.validateUpdateInformation(input);

        // Check output
        Assert.isTrue(errorList.contains("Book id missing. Please add a book id and try again."),
                "Expect missing book id error");
        Assert.isTrue(errorList.contains("Book description missing. Please add a book description and try again."),
                "Expect missing book description error");
        Assert.isTrue(errorList.contains("Book update: Review rating is missing. Please add a rating and try again"),
                "Expect missing review ratings error");
    }

}
