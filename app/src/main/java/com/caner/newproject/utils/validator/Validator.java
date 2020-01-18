package com.caner.newproject.utils.validator;

import android.widget.EditText;

import com.caner.newproject.utils.validator.listener.ValidationListener;
import com.caner.newproject.utils.validator.results.EmailValidationResult;
import com.caner.newproject.utils.validator.results.EmptyValidationResult;
import com.caner.newproject.utils.validator.results.OKValidationResult;
import com.caner.newproject.utils.validator.type.ValidationType;

import java.util.ArrayList;
import java.util.List;


public class Validator {

    private List<String> types;
    private List<ValidationListener> listeners;
    private int validatorId;
    private static List<Validator> validators = null;

    private Validator() {
        validatorId = -1;   // means no such validator
        types = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    private Validator(String type, int validatorId) {
        types = new ArrayList<>();
        types.add(type);
        listeners = new ArrayList<>();
        this.validatorId = validatorId;
    }

    private Validator(List<String> types, int validatorId) {
        this.types = types;
        listeners = new ArrayList<>();
        this.validatorId = validatorId;
    }

    public static Validator getValidatorById(int validatorId) {
        for (Validator validor : validators) {
            if (validor.validatorId == validatorId) return  validor;
        }
        return new Validator();
    }

    public static Validator init(String type, int validatorId) {
        if (validators == null) {
            validators = new ArrayList<>();
        }
        Validator validator = new Validator(type, validatorId);
        validators.add(validator);
        return validator;
    }

    public static Validator init(List<String> types, int validatorId) {
        if (validators == null) {
            validators = new ArrayList<>();
        }
        Validator validator = new Validator(types, validatorId);
        validators.add(validator);
        return validator;
    }

    ValidationResult validate(EditText editText) {
        for (String type : types) {
            switch (type) {
                case ValidationType.EMPTY_VALIDATION: {
                    if (editText.getText().toString().isEmpty()) return new EmptyValidationResult();
                    break;
                }
                case ValidationType.EMAIL_VALIDATION: {
                    if (!editText.getText().toString().matches("^[0-9?A-z0-9?]+(\\.)?[0-9?A-z0-9?]+@[A-z]+\\.[A-z]{3}.?[A-z]{0,3}$")) return new EmailValidationResult();
                    break;
                }
            }
        }

        return new OKValidationResult();
    }

    /*
    * ret value calculated explicitly to show all active validation results to user.
    * Not just the first failed one.
    * */
    public static boolean isValid() {
        boolean ret = true;
        for (Validator validator : validators) {
            for (ValidationListener listener : validator.listeners) {
                if (!listener.checkValidation().isValid()) ret = false;
            }
        }
        return ret;
    }

    public void setValidationListener(ValidationListener listener) {
        listeners.add(listener);
    }

}
