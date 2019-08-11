package com.example.votenow.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClint {
    private static String url="mongodb+srv://E-Voting-App:e-voting-app@e-voting-app-7ooq0.mongodb.net/test?retryWrites=true&w=majority";


    private static Retrofit instance;
    public static Retrofit getInstance(){
        if(instance == null)
            instance=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return instance;

    }
}
