package com.caner.newproject.utils.formatter;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;

class FormatWatcher implements TextWatcher {
    private EditText editText;
    private String pattern;
    private String specialCharacters;
    private boolean isDelete = false;

    private int[] positionsOnPattern;
    private int entryCount = 0;

    public FormatWatcher(String pattern, EditText editText) {
        this.pattern = pattern;
        this.editText = editText;
        analizePattern();
        addFilters();
    }
    
    private void analizePattern() {
        int starIndex = 0;
        positionsOnPattern = new int[pattern.length()];
        for (int i = 0; i<pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                positionsOnPattern[i] = starIndex;
                starIndex++;
            } else {
                positionsOnPattern[i] = -1;
            }
        }

        entryCount = starIndex;
        specialCharacters = "[" + createRegexForSpecialChararcters() + "]";
    }

    private String createRegexForSpecialChararcters() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<pattern.length(); i++) {
            if (pattern.charAt(i) != '*' && !stringBuilder.toString().contains(pattern.subSequence(i, i+1))) {
                stringBuilder.append(pattern.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    private void addFilters() {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(pattern.length());
        editText.setFilters(filterArray);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (i1 == 1) isDelete = true;
        else if (i2 == 1) isDelete = false;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        editText.removeTextChangedListener(this);

        String formattedEntry = formatText(charSequence);

        editText.getText().clear();
        editText.append(formattedEntry);

        editText.setSelection(Math.min(charSequence.length(),getNewPosition(i)));

        editText.addTextChangedListener(this);
    }

    private int getNewPosition(int oldPosition) {
        if (isDelete) {
            while (oldPosition > 0 && positionsOnPattern[oldPosition-1] == -1) {
                oldPosition--;
            }
        } else {
            oldPosition++;
            while ((oldPosition < pattern.length() && positionsOnPattern[oldPosition] == -1) || (oldPosition > 0 && positionsOnPattern[oldPosition-1] == -1)) {
                oldPosition++;
            }
        }
        return oldPosition;
    }

    private String formatText(CharSequence charSequence) {
        String cleanString = charSequence.toString().replaceAll(specialCharacters, "");
        String subPattern = getSubPattern(cleanString.length());

        return replaceStars(cleanString, subPattern);
    }

    private String getSubPattern(int length) {
        if (length >= entryCount) return pattern;
        else if (length <= 0) return "";
        for (int i=0; i<pattern.length(); i++) {
            if (positionsOnPattern[i] == length) {
                if (!isDelete) return pattern.substring(0, i);
                else {
                    while (i>0 && positionsOnPattern[i-1] == -1) {
                        i--;
                    }
                    return pattern.substring(0,i);
                }
            }
        }
        return "";
    }

    private String replaceStars(String charSequence, String subPattern) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i<subPattern.length(); i++){
            if (subPattern.charAt(i) == '*') {
                stringBuilder.append(charSequence.charAt(positionsOnPattern[i]));
            } else {
                stringBuilder.append(subPattern.charAt(i));
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
