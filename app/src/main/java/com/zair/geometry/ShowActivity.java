package com.zair.geometry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ShowActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvDescription;
    ImageView imageView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        imageView4 = findViewById(R.id.imageView4);
        getIntentFromTheory();
    }

    private void getIntentFromTheory()
    {
        Intent intent = getIntent();
        if(intent != null)
        {
            Picasso.get().load(intent.getStringExtra("user_image")).into(imageView4);
            tvName.setText(intent.getStringExtra("user_name"));
            tvDescription.setText(intent.getStringExtra("user_description"));
        }
    }
}
