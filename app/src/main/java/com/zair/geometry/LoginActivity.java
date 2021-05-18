package com.zair.geometry;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText edLogin;
    EditText edPassword;
    FirebaseAuth mAuth;
    TextView tvUserEmail;
    Button bSignUp;
    Button bSignIn;
    Button bComeBack;
    Button bSignOut;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
        tvUserEmail = findViewById(R.id.tvUserEmail);
        bComeBack = findViewById(R.id.bComeBack);
        bSignUp = findViewById(R.id.bSignUp);
        bSignIn = findViewById(R.id.bSignIn);
        bSignOut = findViewById(R.id.bSignOut);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null)
        {
            Toast.makeText(this, "User not null", Toast.LENGTH_SHORT).show();
            bSignOut.setVisibility(View.VISIBLE);
            bComeBack.setVisibility(View.VISIBLE);
            tvUserEmail.setVisibility(View.VISIBLE);
            edLogin.setVisibility(View.GONE);
            edPassword.setVisibility(View.GONE);
            bSignUp.setVisibility(View.GONE);
            bSignIn.setVisibility(View.GONE);
            String userEmail = "Вы вошли как : " + cUser.getEmail();
            tvUserEmail.setText(userEmail);
        }else
        {
            Toast.makeText(this, "User null", Toast.LENGTH_SHORT).show();
            bSignOut.setVisibility(View.GONE);
            bComeBack.setVisibility(View.GONE);
            tvUserEmail.setVisibility(View.GONE);
            edLogin.setVisibility(View.VISIBLE);
            edPassword.setVisibility(View.VISIBLE);
            bSignUp.setVisibility(View.VISIBLE);
            bSignIn.setVisibility(View.VISIBLE);
        }
    }

    public void onClickSignUp(View view)
    {
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString()))
        {
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        sendEmailVer();
                        FirebaseUser user = mAuth.getCurrentUser();
                        assert user != null;
                        if(user.isEmailVerified())
                        {
                            String userEmail = "Вы вошли как : " + user.getEmail();
                            tvUserEmail.setText(userEmail);
                            bSignOut.setVisibility(View.VISIBLE);
                            bComeBack.setVisibility(View.VISIBLE);
                            tvUserEmail.setVisibility(View.VISIBLE);
                            edLogin.setVisibility(View.GONE);
                            edPassword.setVisibility(View.GONE);
                            bSignUp.setVisibility(View.GONE);
                            bSignIn.setVisibility(View.GONE);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Проверьте вашу почту для подтверждения e-mail", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "User sign up successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "User sign up failed", Toast.LENGTH_SHORT).show();
                        bSignOut.setVisibility(View.GONE);
                        bComeBack.setVisibility(View.GONE);
                        tvUserEmail.setVisibility(View.GONE);
                        edLogin.setVisibility(View.VISIBLE);
                        edPassword.setVisibility(View.VISIBLE);
                        bSignUp.setVisibility(View.VISIBLE);
                        bSignIn.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please enter E-mail and Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSignIn(View view)
    {
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString()))
        {
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser user = mAuth.getCurrentUser();
                        assert user != null;
                        if(user.isEmailVerified())
                        {
                            String userEmail = "Вы вошли как : " + user.getEmail();
                            tvUserEmail.setText(userEmail);
                            bSignOut.setVisibility(View.VISIBLE);
                            bComeBack.setVisibility(View.VISIBLE);
                            tvUserEmail.setVisibility(View.VISIBLE);
                            edLogin.setVisibility(View.GONE);
                            edPassword.setVisibility(View.GONE);
                            bSignUp.setVisibility(View.GONE);
                            bSignIn.setVisibility(View.GONE);
                        }else
                        {
                            Toast.makeText(getApplicationContext(), "Проверьте вашу почту для подтверждения e-mail", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "User sign in successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "User sign in failed", Toast.LENGTH_SHORT).show();
                        bSignOut.setVisibility(View.GONE);
                        bComeBack.setVisibility(View.GONE);
                        tvUserEmail.setVisibility(View.GONE);
                        edLogin.setVisibility(View.VISIBLE);
                        edPassword.setVisibility(View.VISIBLE);
                        bSignUp.setVisibility(View.VISIBLE);
                        bSignIn.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public void onClickSignOut(View view)
    {
        FirebaseAuth.getInstance().signOut();
        bSignOut.setVisibility(View.GONE);
        bComeBack.setVisibility(View.GONE);
        tvUserEmail.setVisibility(View.GONE);
        edLogin.setVisibility(View.VISIBLE);
        edPassword.setVisibility(View.VISIBLE);
        bSignUp.setVisibility(View.VISIBLE);
        bSignIn.setVisibility(View.VISIBLE);
    }

    public void onClickStart(View view)
    {
        Intent intent = new Intent(LoginActivity.this, LauncherActivity.class);
        startActivity(intent);
    }

    public void sendEmailVer()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Проверьте вашу почту для подтверждения e-mail", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Send e-mail failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
