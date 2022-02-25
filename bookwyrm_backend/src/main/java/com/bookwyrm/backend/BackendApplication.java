package com.bookwyrm.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = CommentRepository.class)
public class BackendApplication implements CommandLineRunner {
	// use CommandLineRunner only to check if the connection is working

	@Autowired
	CommentRepository commentRepo;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	// CREATE
	void createComments() {
		commentRepo.save(new Comment("Gone with the wind", "Long Vu",
				"Gone with the wind", "Such a classic book though I haven't read before!"));
		commentRepo.save(new Comment("Gone with the wind", "Long Vu",
				"Gone with the wind", "One of the best book I've ever read!"));
		commentRepo.save(new Comment("The Hunger Games", "Katniss Everdeen",
				"The Hunger Games", "Dis games are too easy for me."));
		commentRepo.save(new Comment("The Hunger Games", "Peeta Melark",
				"The Hunger Games", "Katniss sucks."));
	}

	// READ

	// 1. Get comments by books
	public void getBookReview(String bookName) {
		List<Comment> list = commentRepo.findAll(bookName);
		list.forEach(item -> System.out.println("User " + item.getUser() +
				"says:\n" + item.getComment()));
	}

	// 2.Get comments by user
	public void getCommentbyUser(String user) {
		List<Comment> list = commentRepo.findAll(user);
		list.forEach(item -> System.out.println("What " + user + " said "
				+ "about " + item.getBookName + ": " + item.getComment()));
	}

	// DELETE
	// Delete a comment
	public void deleteComment(String id) {
		commentRepo.deleteById(id);
		System.out.println("Comment is deleted.");
	}

	// implement CommandLineRunner.run() to test the above methods
	public void run(String... args) {
		System.out.println("Write some comments");
		// createComments();
		System.out.println("Get comments by books");
		// getBookReview("The Hunger Games");
		System.out.println("Get comments by user");
		// getCommentbyUser("Long Vu");
		System.out.println("Delete comment");
		// deleteComment("Gone with the wind");

	}
}
