package com.bookwyrm.backend.user.validator;

import com.bookwyrm.backend.user.input.UserAuthInput;
import com.bookwyrm.backend.user.input.UserUpdateInput;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validateSignupInformation(UserAuthInput userAuthInput) {
        List<String> errorList = new ArrayList<>();

        if (userAuthInput.getUsername() == null) {
            errorList.add("Username missing. Please add a Username and try again.");
        }
        if (userAuthInput.getPasswordHash() == null) {
            errorList.add("Password missing. Please add a Password and try again.");
        }

        return errorList;
    }
    public static List<String> validateUpdateInformation(UserUpdateInput userUpdateInput) {
        List<String> errorList = new ArrayList<>();

        if (userUpdateInput.getUsername() == null) {
            errorList.add(createMissingFieldMessage("Username"));
        }
        if (userUpdateInput.getAuthorFlag() == null) {
            errorList.add(createMissingFieldMessage("Author Flag"));
        }
        if (userUpdateInput.getAuthorName() == null) {
            errorList.add(createMissingFieldMessage("Author Name"));
        }
        if (userUpdateInput.getProfJournalistFlag() == null) {
            errorList.add(createMissingFieldMessage("Professional Journalist Flag"));
        }
        if (userUpdateInput.getProfJournalistName() == null) {
            errorList.add(createMissingFieldMessage("Professional Journalist Name"));
        }

        return errorList;
    }

    private static String createMissingFieldMessage(String field){
        return ""+field+" missing. Please add "+field+" and try again.";
    }

}
