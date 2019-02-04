package com.forbitbd.quizapp.ui.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.ui.main.MainActivity;
import com.forbitbd.quizapp.ui.signup.SignUpActivity;
import com.forbitbd.quizapp.util.AppPreference;
import com.sdsmdg.tastytoast.TastyToast;

public class Login extends AppCompatActivity implements LoginContract.View,View.OnClickListener {

    private LoginPresenter mPresenter;


    private TextView tvSignUp,tvLogin,tvForgotPassword;

    private TextInputLayout tiEmail,tiPassword;
    private EditText etEmail,etPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this, AppPreference.getInstance(getApplicationContext()));

        initView();
    }

    private void initView() {
        tvSignUp = findViewById(R.id.sign_up);
        tvLogin = findViewById(R.id.login);
        tvForgotPassword = findViewById(R.id.forgot_password);


        tvSignUp.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        tiEmail = findViewById(R.id.ti_email);
        tiPassword = findViewById(R.id.ti_password);

        mPresenter.setSaveEmailAndPassword();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void startSignUpActivity() {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void startMainActivity() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void renderEmail(String email) {
        etEmail.setText(email);
    }

    @Override
    public void renderPassword(String password) {
        etPassword.setText(password);
    }

    @Override
    public void clearPreError() {
        tiEmail.setErrorEnabled(false);
        tiPassword.setErrorEnabled(false);
    }

    @Override
    public void showToastMessage(String message, int fieldId) {
        switch (fieldId){
            case 1:
                etEmail.requestFocus();
                tiEmail.setError(message);
                break;

            case 2:
                etPassword.requestFocus();
                tiPassword.setError(message);
                break;
        }
    }

    @Override
    public void showToast(String message, int type) {
        TastyToast.makeText(getApplicationContext(),message,TastyToast.LENGTH_LONG,type);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up:
                mPresenter.signupClick();
                break;

            case R.id.login:
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                boolean valid = mPresenter.validate(user);

                if(!valid){
                    return;
                }

                mPresenter.login(user);
                break;
        }
    }
}
