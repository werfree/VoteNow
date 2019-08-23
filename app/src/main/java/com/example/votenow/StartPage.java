package com.example.votenow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    LinearLayout logout,acnt,createVote;
    ImageButton next;
    EditText vCode;
    String getvCode,name,id,phoneNumber;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor meditor;


    boolean aBoolean=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        next=findViewById(R.id.next);
        logout=findViewById(R.id.logoutLayout);
        acnt=findViewById(R.id.acntLayout);
        createVote=findViewById(R.id.createVoteLayout);
        vCode=findViewById(R.id.inputText);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        meditor = sharedPreferences.edit();

        Intent intent=getIntent();

        name=intent.getStringExtra("name");
        id=intent.getStringExtra("id");
        phoneNumber=intent.getStringExtra("phone");

        Toast.makeText(this,name+" "+id,Toast.LENGTH_SHORT).show();


        invisibleActionbar();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean)
                visibleActionbar();
                else
                    invisibleActionbar();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextIntent();
            }
        });
    }

    private void nextIntent() {
        getvCode=vCode.getText().toString();
        if(getvCode.isEmpty()){
            vCode.setError("Vote Code is Empty");
        }
        else{
            verifivation();
        }
    }

    private void verifivation() {
        Intent intent=new Intent(StartPage.this,Verification.class);
        startActivity(intent);
    }


    private void logout() {
        Intent intent = new Intent(StartPage.this,Login.class);
        meditor.putString("number", "null");
        meditor.putString("password","null");
        meditor.commit();
        startActivity(intent);
    }

    private void visibleActionbar() {
        aBoolean=false;
        logout.setVisibility(View.VISIBLE);
        acnt.setVisibility(View.VISIBLE);
        createVote.setVisibility(View.VISIBLE);
        next.setVisibility(View.INVISIBLE);

    }

    private void invisibleActionbar() {
        aBoolean=true;
        logout.setVisibility(View.INVISIBLE);
        acnt.setVisibility(View.INVISIBLE);
        createVote.setVisibility(View.INVISIBLE);
        next.setVisibility(View.VISIBLE);
    }
}
