package com.example.myclient2.presenter;

/**
 * Created by iddan on 13/08/17.
 */

public interface LoginView {

    // Show validation error
    void showValidationError();

    // Login success
    void loginSuccess();

    // Login Error
    void loginError();
}
