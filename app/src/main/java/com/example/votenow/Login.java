package com.example.votenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    ImageButton next;
    EditText phn,password;
    TextView register;

    String getPhone,getPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phn=findViewById(R.id.inputPhone);
        password=findViewById(R.id.inputPassword);
        next=findViewById(R.id.next);
        register=findViewById(R.id.register);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Phone.class);
                startActivity(intent);
            }
        });
    }

    private void check() {

        getPhone=phn.getText().toString();
        getPassword=password.getText().toString();

        if (getPhone.isEmpty()) {
            phn.setError("Enter the Phone Number");
        } else if (getPhone.length() != 10) {
            phn.setError("Phone Number Must be of 10digits");
        }else if(getPassword.isEmpty()){
            password.setError("Enter Password");
        }else if(getPassword.length()<4){
            password.setError("Password must be of 4 letters");
        }else {
            getPhone="+91"+getPhone;
            Toast.makeText(this,"Successful Login: "+getPhone,Toast.LENGTH_LONG).show();
        }
    }
}
