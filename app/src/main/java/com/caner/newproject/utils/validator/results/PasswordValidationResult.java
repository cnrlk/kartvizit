package com.caner.newproject.utils.validator.results;

import com.caner.newproject.utils.validator.ValidationResult;

public class PasswordValidationResult implements ValidationResult {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getMessage() {
        return "Please enter a valid password";
    }
}
