package com.example.votenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Password extends AppCompatActivity {
    EditText input;
    ImageButton next;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        input = findViewById(R.id.inputText);
        next = findViewById(R.id.next);
        imageView = findViewById(R.id.imageView);

        input.setHint("Password");
        input.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        imageView.setImageResource(R.drawable.password);
    }
}