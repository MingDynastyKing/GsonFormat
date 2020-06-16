package com.example.myapplication;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TestWebService {

    @GET("/wxarticle/chapters/json")
    public Observable<ResponseBody> showWxChapterInfo();
    @POST("/user/login")
    public Observable<ResponseBody> login();

}
