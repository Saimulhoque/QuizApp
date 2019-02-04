package com.forbitbd.quizapp.ui.signup;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.forbitbd.quizapp.R;
import com.sdsmdg.tastytoast.TastyToast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,SignUpContract.View{

    private TextView tvSignUp,tvLogin,tvForgotPassword;

    private SignUpPresenter mPresenter;

    private TextInputLayout tiEmail,tiPassword;
    private EditText etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        mPresenter = new SignUpPresenter(this);


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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up:

                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                boolean valid = mPresenter.validate(email,password);

                if(!valid){
                    return;
                }

                mPresenter.signUp(email,password);

                break;

            case R.id.login:
                startLoginActivity();
                break;
        }
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
    public void startLoginActivity() {
        onBackPressed();
    }

    @Override
    public void showToast(String message,int type) {
        TastyToast.makeText(getApplicationContext(),message,TastyToast.LENGTH_LONG,type);
    }
}
