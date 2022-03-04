package com.bookwyrm.backend.book.validator;

import com.bookwyrm.backend.book.input.BookUploadInput;

import java.util.ArrayList;
import java.util.List;

public class BookValidator {

    public static List<String> validateUploadInformation(BookUploadInput bookUploadInput){
        List<String> errorList = new ArrayList<>();

        if(bookUploadInput.getAuthor() == null){
            errorList.add("Author name missing. Please add an author name and try again.");
        }
        if(bookUploadInput.getName() == null){
            errorList.add("Book name missing. Please add a book name and try again.");
        }

        return errorList;
    }
}
