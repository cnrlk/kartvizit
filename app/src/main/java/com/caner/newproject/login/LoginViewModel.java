package com.caner.newproject.login;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class LoginViewModel extends BaseObservable {
    private LoginResource loginResource = new LoginResource();
    private boolean isSignIn = false;

    public Task<AuthResult> loginWithEmail(String email, String password) {
        return loginResource.loginUserWithEmail(email, password);
    }

    public Task<AuthResult> createUser(String email, String password) {
        return loginResource.createUserWithEmail(email, password);
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    @Bindable
    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
        notifyChange();
    }
}
