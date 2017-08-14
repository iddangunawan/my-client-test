package com.example.myclient2.presenter.login;

import android.text.TextUtils;

import com.example.myclient2.view.LoginView;

/**
 * Created by iddan on 13/08/17.
 */

public class LoginPresenterImp implements LoginPresenter {

    // Login view
    private LoginView loginView;

    // Create a new login presenter
    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            loginView.showValidationError();
        } else {
            if (username.equals("admin") && password.equals("admin")) {
                loginView.loginSuccess();
            } else {
                loginView.loginError();
            }
        }
    }
}
