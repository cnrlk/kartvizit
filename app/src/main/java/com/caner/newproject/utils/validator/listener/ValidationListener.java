package com.caner.newproject.utils.validator.listener;

import com.caner.newproject.utils.validator.ValidationResult;

public interface ValidationListener {

    public void onValidationPassed();
    public void onValidationFailed(String message);
    public ValidationResult checkValidation();
}
