package com.zair.geometry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LauncherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void onTheoryClick(View view){
        Intent intent = new Intent(LauncherActivity.this, Theory.class);
        startActivity(intent);
    }

    public void onSignInClick(View view) {
        Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void onSolveClick(View view) {
        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
        startActivity(intent);
    }
}