package com.bookwyrm.backend.book.validator;

import com.bookwyrm.backend.book.input.BookUpdateInput;
import com.bookwyrm.backend.book.input.BookUploadInput;

import java.util.ArrayList;
import java.util.List;

public class BookValidator {

    public static List<String> validateUploadInformation(BookUploadInput bookUploadInput) {
        List<String> errorList = new ArrayList<>();

        if (bookUploadInput.getAuthor() == null) {
            errorList.add("Author name missing. Please add an author name and try again.");
        }
        if (bookUploadInput.getTitle() == null) {
            errorList.add("Book name missing. Please add a book name and try again.");
        }


        return errorList;
    }

    public static List<String> validateUpdateInformation(BookUpdateInput bookUpdateInput) {
        List<String> errorList = new ArrayList<>();

        if (bookUpdateInput.getDesc() == null) {
            errorList.add("Book description missing. Please add a book description and try again.");
        }
        if (bookUpdateInput.getId() == null) {
            errorList.add("Book id missing. Please add a book id and try again.");
        }
        if(bookUpdateInput.getRate() == null){
            errorList.add("Book update: Review rating is missing. Please add a rating and try again");
        }
        return errorList;
    }
}
