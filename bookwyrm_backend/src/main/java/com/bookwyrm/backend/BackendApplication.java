package com.bookwyrm.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

//TESTING
import com.bookwyrm.backend.review.dao.ReviewDao;
import com.bookwyrm.backend.review.dao.ReviewService;
import com.bookwyrm.backend.review.controller.ReviewController;
import com.bookwyrm.backend.review.input.ReviewUploadInput;
import com.bookwyrm.backend.review.payload.ReviewUploadPayload;
//////////////////

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ReviewService.class)
public class BackendApplication implements CommandLineRunner {
	// use CommandLineRunner only to check if the connection is working

	@Autowired
	ReviewService reviewRepo;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	// //FEW EXAMPLES OF HOW THINGS SHOULD WORK
	// //CREATE
	// void createReviews() {
	// reviewRepo.save(new ReviewDao("1", "Long Vu",
	// "Gone with the wind", "Such a classic book though I haven't read before!"));
	// reviewRepo.save(new ReviewDao("2", "Long Vu",
	// "Gone with the wind", "One of the best book I've ever read!"));
	// reviewRepo.save(new ReviewDao("3", "Katniss Everdeen",
	// "The Hunger Games", "Dis games are too easy for me."));
	// reviewRepo.save(new ReviewDao("4", "Peeta Melark",
	// "The Hunger Games", "Katniss sucks."));
	// }

	// // READ

	// // 1. Get comments by books
	// public void getBookReview(String bookName) {
	// List<ReviewDao> list = reviewRepo.findCommentbyBook(bookName);
	// list.forEach(item -> System.out.println("User " + item.getUser() +
	// "says:\n" + item.getComment()));
	// }

	// // 2.Get comments by user
	// public void geReviewbyUser(String user) {
	// List<ReviewDao> list = reviewRepo.findAll();
	// list.forEach(item -> System.out.println("What " + user + " said "
	// + "about " + item.getBookName() + ": " + item.getComment()));
	// }

	// // DELETE
	// // Delete a comment
	// public void deleteReview(String id) {
	// reviewRepo.deleteById(id);
	// System.out.println("Comment is deleted.");
	// }

}
