package com.caner.newproject.utils.formatter;

import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class FormatterBinding {

    @BindingAdapter("caneru:formatter")
    public static void setFormatter(EditText editText, String pattern) {
        editText.addTextChangedListener(new FormatWatcher(pattern, editText));
    }
}
