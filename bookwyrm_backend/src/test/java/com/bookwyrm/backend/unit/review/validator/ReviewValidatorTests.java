package com.bookwyrm.backend.unit.review.validator;

import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.input.ReviewVotingInput;
import com.bookwyrm.backend.review.validator.ReviewValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringJUnitConfig
@SpringBootTest
public class ReviewValidatorTests {

    @Test
    public void testHappyPath(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setBookId("testId");
        input.setAnonymousFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournName");
        Map<String, Float> map = new HashMap<>();
        map.put("overall", 3.5F);
        input.setRatings(map);
        //Run Validation
        List<String> errorList =  ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }
    @Test
    public void testMissingAuthor(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setBookId("testId");
        input.setAnonymousFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournName");
        Map<String, Float> map = new HashMap<>();
        map.put("overall", 3.5F);
        input.setRatings(map);
        //Run Validation
        List<String> errorList =  ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."), "Expect missing author error");
    }
    @Test
    public void testMissingContent(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setBookId("testId");
        input.setAnonymousFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournName");
        Map<String, Float> map = new HashMap<>();
        map.put("overall", 3.5F);
        input.setRatings(map);

        //Run Validation
        List<String> errorList =  ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Review content is missing. Please add some content and try again."), "Expect missing description error");
    }
    @Test
    public void testMissingAnonymousFlag(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setContent("testContent");
        input.setBookId("testId");
        input.setAnonymousFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournName");
        Map<String, Float> map = new HashMap<>();
        map.put("overall", 3.5F);
        input.setRatings(map);
        //Run Validation
        List<String> errorList =  ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no errors");
    }
    @Test
    public void testMissingBookId(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setAnonymousFlag(false);
        input.setJournalistFlag(false);
        input.setJournalistName("testJournName");
        Map<String, Float> map = new HashMap<>();
        map.put("overall", 3.5F);
        input.setRatings(map);
        //Run Validation
        List<String> errorList =  ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Book ID missing. Please add a book Id and try again."), "Expect missing book ID error");
    }
    @Test
    public void testEmptyRatings(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setBookId("testId");
        Map<String, Float> map = new HashMap<>();
        input.setRatings(map);
        //Run Validation
        List<String> errorList = ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Ratings are missing. Please rate the book and try again."), "Expect empty ratings error");
    }
    @Test
    public void TestMissingRatings(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();
        input.setAuthor("testAuthor");
        input.setAnonymousFlag(true);
        input.setContent("testContent");
        input.setBookId("testId");
        //Run Validation
        List<String> errorList = ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Rating object is missing."), "Expect empty ratings error");
    }

    @Test
    public void testMissingEverything(){
        //Setup
        ReviewUploadInput input = new ReviewUploadInput();

        //Run Validation
        List<String> errorList =  ReviewValidator.validateUploadInformation(input);

        //Check output
        Assert.isTrue(errorList.contains("Review content is missing. Please add some content and try again."), "Expect missing description name error");
        Assert.isTrue(errorList.contains("Author name missing. Please add an author name and try again."), "Expect missing author error");
        Assert.isTrue(errorList.contains("Book ID missing. Please add a book Id and try again."), "Expect missing book ID error");
    }

    @Test
    public void testGoodValidateVotingInput() {
        //Setup
        ReviewVotingInput input = new ReviewVotingInput();
        input.setUserId("TestUserId");
        input.setReviewId("TestReviewId");
        input.setVoteValue(true);

        //Run Validation
        List<String> errorList = ReviewValidator.validateVotingInput(input);

        //Check output
        Assert.isTrue(errorList.isEmpty(), "Expect no messages from valid input");
    }

    @Test
    public  void testMissingUserIdVotingInput() {
        //Setup
        ReviewVotingInput input = new ReviewVotingInput();
        input.setReviewId("TestReviewId");
        input.setVoteValue(true);

        //Run Validation
        List<String> errorList = ReviewValidator.validateVotingInput(input);

        //Check output
        Assert.isTrue(errorList.contains("User ID missing. Please try again."), "Expect missing user ID error");
    }

    @Test
    public  void testMissingReviewIdVotingInput() {
        //Setup
        ReviewVotingInput input = new ReviewVotingInput();
        input.setUserId("TestUserId");
        input.setVoteValue(true);

        //Run Validation
        List<String> errorList = ReviewValidator.validateVotingInput(input);

        //Check output
        Assert.isTrue(errorList.contains("review ID missing. Please try again."), "Expect missing review ID error");
    }

    @Test
    public  void testMissingVoteValueVotingInput() {
        //Setup
        ReviewVotingInput input = new ReviewVotingInput();
        input.setUserId("TestUserId");
        input.setReviewId("TestReviewId");

        //Run Validation
        List<String> errorList = ReviewValidator.validateVotingInput(input);

        //Check output
        Assert.isTrue(errorList.contains("Vote value missing. Please down vote or upvote and try again."), "Expect missing vote value error");
    }

    @Test
    public void testMissingEverythingVotingInput(){
        //Setup
        ReviewVotingInput input = new ReviewVotingInput();

        //Run Validation
        List<String> errorList =  ReviewValidator.validateVotingInput(input);

        //Check output
        Assert.isTrue(errorList.contains("User ID missing. Please try again."), "Expect missing user ID error");
        Assert.isTrue(errorList.contains("review ID missing. Please try again."), "Expect missing review ID error");
        Assert.isTrue(errorList.contains("Vote value missing. Please down vote or upvote and try again."), "Expect missing vote value error");
    }

}