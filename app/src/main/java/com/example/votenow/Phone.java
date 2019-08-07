package com.example.votenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Phone extends AppCompatActivity {
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

        input.setHint("Phone Number");
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        imageView.setImageResource(R.drawable.phone);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Phone.this,Password.class);
                startActivity(intent);
            }
        });
    }
}
