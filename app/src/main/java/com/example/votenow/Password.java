package com.example.votenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votenow.Retrofit.RetrofitClint;
import com.example.votenow.Retrofit.Server;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Password extends AppCompatActivity {
    EditText input;
    ImageButton next;
    EditText name,password,c_password;
    TextView phone;

    String getPhnNo,getName,getPassword,getCPassword;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Server server;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        next = findViewById(R.id.next);
        name=findViewById(R.id.inputName);
        password=findViewById(R.id.inputPassword);
        c_password=findViewById(R.id.inputConfirmPassword);
        phone=findViewById(R.id.inputPhone);

        getPhnNo=getIntent().getStringExtra("phnNo");
        phone.setText(getPhnNo);



        //Server


       Retrofit retrofitClint = RetrofitClint.getInstance();
        server=retrofitClint.create(Server.class);
        
        
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                check();

            }
        });


        

    }

    private void check() {

        getName=name.getText().toString();
        getPassword=password.getText().toString();
        getCPassword=c_password.getText().toString();

        if(getName.isEmpty()){
            name.setError("Enter Name");
        }else if(getPassword.isEmpty()){
            password.setError("Enter Password");
        }else if(getPassword.length()<4){
            password.setError("Password must be of 4 letters");
        }else if(getCPassword.isEmpty()){
            c_password.setError("Enter Confirm Password");
        }else if(!(getCPassword.equals(getPassword))){
            Toast.makeText(this,"Password did not match with Confirm Password",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
            register();
        }
    }

    private void register() {

        compositeDisposable.add(server.registerUser("gsayantan01@gmail.com","9831516058","123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(Password.this,""+response,Toast.LENGTH_LONG).show();
                    }
                }));
    }
}