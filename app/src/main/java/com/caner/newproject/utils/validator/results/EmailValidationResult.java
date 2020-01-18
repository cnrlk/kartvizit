package com.caner.newproject.utils.validator.results;

import com.caner.newproject.utils.validator.ValidationResult;

public class EmailValidationResult implements ValidationResult {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getMessage() {
        return "Not a valid e-mail";
    }
}
