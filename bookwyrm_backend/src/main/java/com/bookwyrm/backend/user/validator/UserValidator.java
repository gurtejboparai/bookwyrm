package com.bookwyrm.backend.user.validator;

import com.bookwyrm.backend.user.input.UserInput;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validateSignupInformation(UserInput userInput) {
        List<String> errorList = new ArrayList<>();

        if (userInput.getUsername() == null) {
            errorList.add("Username missing. Please add a Username and try again.");
        }
        if (userInput.getPasswordHash() == null) {
            errorList.add("Password missing. Please add a Password and try again.");
        }

        return errorList;
    }
}
