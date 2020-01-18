package com.caner.newproject.utils.validator.results;

import com.caner.newproject.utils.validator.ValidationResult;

public class OKValidationResult implements ValidationResult {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getMessage() {
        return "Validation is OK";
    }
}
