package com.caner.newproject.utils.validator;

import android.widget.EditText;

import com.caner.newproject.utils.validator.listener.ValidationListener;
import com.caner.newproject.utils.validator.results.EmptyValidationResult;
import com.caner.newproject.utils.validator.results.OKValidationResult;

import java.util.List;

import androidx.databinding.BindingAdapter;

public class ValidationBinding {

    @BindingAdapter("caneru:validation")
    public static void setValidation(EditText view, String value) {
        Validator.init(value, view.getId()).setValidationListener(new ValidationListener() {
            @Override
            public void onValidationPassed() {
                //Validation Passed
            }

            @Override
            public void onValidationFailed(String message) {
                view.setError(message);
            }

            @Override
            public ValidationResult checkValidation() {
                ValidationResult result = Validator.getValidatorById(view.getId()).validate(view);
                if (result.isValid()) {
                    onValidationPassed();
                    return new OKValidationResult();
                } else {
                    onValidationFailed(result.getMessage());
                }
                return new EmptyValidationResult();
            }
        });
    }

    @BindingAdapter("caneru:validation")
    public static void setValidation(EditText view, List<String> values) {
        Validator.init(values, view.getId()).setValidationListener(new ValidationListener() {
            @Override
            public void onValidationPassed() {

            }

            @Override
            public void onValidationFailed(String message) {
                view.setError(message);
            }

            @Override
            public ValidationResult checkValidation() {
                ValidationResult result = Validator.getValidatorById(view.getId()).validate(view);
                if (result.isValid()) {
                    onValidationPassed();
                    return new OKValidationResult();
                } else {
                    onValidationFailed(result.getMessage());
                }
                return new EmptyValidationResult();
            }
        });
    }
}
