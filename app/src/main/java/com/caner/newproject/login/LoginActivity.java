package com.caner.newproject.login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.caner.newproject.R;
import com.caner.newproject.dashboard.DashboardActivity;
import com.caner.newproject.databinding.ActivityLoginBinding;
import com.caner.newproject.utils.ActivityUtil;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import static com.caner.newproject.utils.validator.Validator.isValid;

public class LoginActivity extends Activity {

    private LoginViewModel viewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new LoginViewModel();
        binding.setViewModel(viewModel);

        //binding.editText.setText("canerulku4@gmail.com");
        //binding.editText2.setText("Aero1991");


        binding.button.setOnClickListener(v -> {
            if (isValid()) {
                if (viewModel.isSignIn()) {
                    viewModel.createUser(binding.editText.getText().toString(), binding.editText2.getText().toString())
                            .addOnCompleteListener(this::signInTappedResult);
                } else {
                    viewModel.loginWithEmail(binding.editText.getText().toString(), binding.editText2.getText().toString())
                            .addOnCompleteListener(this::loginTappedResult);
                }
            }
        });
        binding.tvSignIn.setOnClickListener(v -> viewModel.setSignIn(!viewModel.isSignIn()));
    }

    public void loginTappedResult(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            ActivityUtil.startActivity(this, DashboardActivity.class);
        } else {
            Toast.makeText(LoginActivity.this, task.getException() != null ? task.getException().getMessage() : "Error!", Toast.LENGTH_LONG).show();
        }
    }

    public void signInTappedResult(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            Toast.makeText(LoginActivity.this, "User created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, task.getException() != null ? task.getException().getMessage() : "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}
