package com.example.myclient2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myclient2.R;
import com.example.myclient2.presenter.login.LoginPresenter;
import com.example.myclient2.presenter.login.LoginPresenterImp;
import com.example.myclient2.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initial standard view
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_pass);
        btLogin = (Button) findViewById(R.id.bt_login);

        presenter = new LoginPresenterImp(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    // This function called when error validation
    @Override
    public void showValidationError() {
        Toast.makeText(this, "Please enter valid username and password!", Toast.LENGTH_SHORT).show();
    }

    // This function called when success login
    @Override
    public void loginSuccess() {
        Toast.makeText(this, "You are successfully logged in!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    // This function called when login error
    @Override
    public void loginError() {
        Toast.makeText(this, "Invalid login credentials!", Toast.LENGTH_SHORT).show();
    }
}
