package com.bookwyrm.backend.book.controller;

import com.bookwyrm.backend.book.input.BookUploadInput;
import com.bookwyrm.backend.book.payload.BookUploadPayload;
import com.bookwyrm.backend.book.validator.BookUploadValidator;
import com.fasterxml.jackson.core.util.RequestPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    /*
    * Requires some tests:
    * Happy case (everything works as expected)
    * Missing Author
    * Missing Name
    * Missing Everything
    * */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookUploadPayload> createBook(
            @RequestBody BookUploadInput bookUploadInput,
            HttpServletRequest request){
        BookUploadPayload response = new BookUploadPayload();
        List<String> errorList = BookUploadValidator.validateUploadInformation(bookUploadInput);
        HttpStatus status = HttpStatus.OK;

        if(errorList.isEmpty()) {
            //Do work
            response.setBookName(bookUploadInput.getName());
        }else{
            //Inform user of error
            response.setMessages(errorList);
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(response);
    }
}
