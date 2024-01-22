/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 22
 */

package com.mich.gwan.shallom.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
    private final Context context;

    /**
     * Constructs an instance of the InputValidation class.
     *
     * @param context The context used for various operations within the InputValidation class.
     */
    public InputValidation(Context context) {
        this.context = context;
    }
    /**
     * Checks whether the content of an input text is filled (not empty).
     * <p>
     * This method validates whether the content of the provided TextInputEditText is filled, i.e., not empty.
     * If the content is empty, it displays an error message in the specified TextInputLayout and hides the keyboard.
     *
     * @param textInputEditText The TextInputEditText to check for filled content.
     * @param textInputLayout   The TextInputLayout to display the error message.
     * @param message           The error message to display if the content is empty.
     * @return True if the content is filled; false otherwise.
     */
    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    /**
     * Checks whether a spinner has a selected value.
     * <p>
     * This method validates whether the provided AppCompatSpinner has a selected value.
     * If no value is selected, it displays an error message in the specified TextInputLayout.
     *
     * @param spinner          The AppCompatSpinner to check for a selected value.
     * @param textInputLayout  The TextInputLayout to display the error message.
     * @param message          The error message to display if no value is selected.
     * @return True if the spinner has a selected value; false otherwise.
     */
    public boolean isSpinnerEmpty(AppCompatSpinner spinner, TextInputLayout textInputLayout, String message){
        if(spinner.getSelectedItem() == "Select User Type" ) {
            textInputLayout.setError(message);
            return false;
        } else  {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Checks if the content of an EditText is occupied (not empty).
     * <p>
     * This method validates whether the content of the provided EditText is occupied, i.e., not empty.
     * If the content is empty, it displays an error message in the EditText and hides the keyboard.
     *
     * @param textInputEditText The EditText to check for occupation.
     * @param message           The error message to display if the content is empty.
     * @return True if the content is occupied;
     * */
    public boolean isEditTextOccupied(EditText textInputEditText, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            //textInputEditText.set(false);
        }

        return true;
    }


    /**
     * Checks if the content of an EditText matches one of the specified options.
     * <p>
     * This method validates whether the content of the provided EditText matches any of the specified options.
     * Valid options are "ADMIN", "EMPLOYEE", or "MANAGER". If the content does not match any of the options,
     * it displays an error message in the EditText and hides the keyboard.
     *
     * @param textInputEditText The EditText to check for matching options.
     * @param message           The error message to display if the content does not match any of the options.
     * @return True if the content matches one of the specified options; false otherwise.
     */
    public boolean isEditTextMatch(EditText textInputEditText, String message) {
        String value = textInputEditText.getText().toString().toUpperCase().trim();
        String [] options =  {"ADMIN","EMPLOYEE","MANAGER"};
        if(!Arrays.asList(options).contains(value))
        {
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        return true;
    }
    /**
     * Checks if the transaction type is valid.
     * <p>
     * This method validates whether the content of the provided EditText represents a valid transaction type.
     * Valid transaction types are either "CASH" or "CREDIT". If the content is not a valid transaction type,
     * it displays an error message in the EditText and hides the keyboard.
     *
     * @param textInputEditText The EditText containing the transaction type.
     * @param message           The error message to display if the content is not a valid transaction type.
     * @return True if the content represents a valid transaction type; false otherwise.
     */
    public boolean isCredit(EditText textInputEditText, String message) {
        String value = textInputEditText.getText().toString().toUpperCase().trim();
        String [] options =  {"CASH","CREDIT"};
        if(!Arrays.asList(options).contains(value))
        {
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        return true;
    }


    /**
     * Checks whether the content of an input text is a valid email address.
     * <p>
     * This method validates whether the content of the provided TextInputEditText is a valid email address.
     * If the content is not a valid email address, it displays an error message in the specified TextInputLayout
     * and hides the keyboard from the TextInputEditText.
     *
     * @param textInputEditText The TextInputEditText to validate as an email address.
     * @param textInputLayout    The TextInputLayout to display the error message.
     * @param message            The error message to display if the content is not a valid email address.
     * @return True if the content is a valid email address; false otherwise.
     */
    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Checks whether the content of an input text is a valid email address.
     * <p>
     * This method validates whether the content of the provided EditText is a valid email address.
     * If the content is not a valid email address, it displays an error message in the EditText
     * and hides the keyboard from the EditText.
     *
     * @param textEditText The EditText to validate as an email address.
     * @param message      The error message to display if the content is not a valid email address.
     * @return True if the content is a valid email address; false otherwise.
     */
    public boolean isInputEditTextEmail(EditText textEditText, String message) {
        String value = textEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textEditText.setError(message);
            hideKeyboardFrom(textEditText);
            return false;
        }
        return true;
    }

    /**
     * Checks whether the content of two input texts match.
     * <p>
     * This method compares the content of two TextInputEditText fields and ensures that they match.
     * If the contents do not match, it displays an error message in the specified TextInputLayout and hides
     * the keyboard from the second TextInputEditText.
     *
     * @param textInputEditText1 The first TextInputEditText for comparison.
     * @param textInputEditText2 The second TextInputEditText for comparison.
     * @param textInputLayout    The TextInputLayout to display the error message.
     * @param message            The error message to display if the contents do not match.
     * @return True if the contents of the input texts match; false otherwise.
     */
    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = Objects.requireNonNull(textInputEditText1.getText()).toString().trim();
        String value2 = Objects.requireNonNull(textInputEditText2.getText()).toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Validates the strength of a password and displays an error message if invalid.
     * <p>
     * This method checks whether the provided password meets certain strength criteria.
     * The password must include at least one uppercase letter, one lowercase letter, one special character,
     * and have a minimum length of 6 characters.
     *
     * @param textInputEditText The TextInputEditText containing the password.
     * @param textInputLayout   The TextInputLayout to display the error message.
     * @param message           The error message to display if the password is invalid.
     * @return True if the password meets the strength criteria; false otherwise.
     */
    public boolean isValidPassword(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString();
        String password="^(?=.*[0-9]).{5,}$";
        Pattern pattern=Pattern.compile(password);
        Matcher matcher=pattern.matcher(value);
        if (value.isEmpty() || !matcher.matches()){
            textInputLayout.setError(message);
            return false;
        } else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
        //(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+,.\\\/;':"-]).{5,}
    }

    /**
     * Validates a phone number and displays an error message if invalid.
     * <p>
     * This method checks whether the provided phone number is valid based on a regular expression pattern.
     * If the phone number is invalid, it displays the error message in the specified TextInputLayout.
     *
     * @param textInputEditText The TextInputEditText containing the phone number.
     * @param textInputLayout   The TextInputLayout to display the error message.
     * @param message           The error message to display if the phone number is invalid.
     * @return True if the phone number is valid; false otherwise.
     */
    public boolean isValidPhoneNumber(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = Objects.requireNonNull(textInputEditText.getText()).toString();
        String password="^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
        Pattern pattern= Pattern.compile(password);
        Matcher matcher=pattern.matcher(value);
        if (value.isEmpty() || !matcher.matches()){
            textInputLayout.setError(message);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Validates a phone number and displays an error message if invalid.
     * <p>
     * This method checks whether the provided phone number is valid based on a regular expression pattern.
     * If the phone number is invalid, it displays the error message in the specified EditText.
     *
     * @param textEditText The EditText containing the phone number.
     * @param message      The error message to display if the phone number is invalid.
     * @return True if the phone number is valid; false otherwise.
     */
    public boolean isValidPhoneNumber(EditText textEditText, String message){
        String value = textEditText.getText().toString();
        String password="^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
        Pattern pattern= Pattern.compile(password);
        Matcher matcher=pattern.matcher(value);
        if (value.isEmpty() || !matcher.matches()){
            textEditText.setError(message);
            return false;
        }
        return true;
    }

    /**
     * Hides the keyboard.
     * <p>
     * This method hides the keyboard when called by using the provided view's window token.
     *
     * @param view The view from which to obtain the window token.
     */
    public void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
