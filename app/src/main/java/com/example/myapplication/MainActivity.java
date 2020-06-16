package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.bean.BaseResponseObserver;
import com.example.myapplication.bean.WxArticleChapterBean;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String url = "https://wanandroid.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void testClick1() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        TestWebService testWebService = retrofit.create(TestWebService.class);
        testWebService.showWxChapterInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseResponseObserver<List<WxArticleChapterBean>>() {

                    @Override
                    public void onSuccess(List<WxArticleChapterBean> wxArticleChapterBeans) {
                        String s = GsonHelper.getInstance().getGson().toJson(wxArticleChapterBeans);
                        Log.d(TAG, "onSuccess() called with: listBaseResponse = [" + s + "]");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        Log.d(TAG, "onError() called with: e = [" + e + "]");
                    }
                });


    }

    public void testClick2() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        TestWebService testWebService = retrofit.create(TestWebService.class);
        testWebService.showWxChapterInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseResponseObserver<String>() {

                    @Override
                    public void onSuccess(String s) {
                        Log.d(TAG, "onSuccess() called with: listBaseResponse = [" + s + "]");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        Log.d(TAG, "onError() called with: e = [" + e + "]");
                    }
                });


    }

    public void testClick(View view) {
        testClick1();
    }
}