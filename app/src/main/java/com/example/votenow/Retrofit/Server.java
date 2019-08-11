package com.example.votenow.Retrofit;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableReduceMaybe;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Server {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("name")String name,
                                    @Field("phn")String phn,
                                    @Field("passw")String passw);


}
