package com.example.myapplication.bean;

import android.util.Log;

import com.example.myapplication.BaseResponse;
import com.example.myapplication.GsonHelper;
import com.example.myapplication.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;

public abstract class BaseResponseObserver<T> implements Observer<ResponseBody> {
    private static final String TAG = "BaseResponseObserver";
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        String str = null;
        try {
            str = responseBody.string();
            Log.d(TAG, "onNext() called with: str = [" + str + "]");
//            str = "{\"data\":\"data\",\"errorCode\":-1,\"errorMsg\":\"账号密码不匹配！\"}";
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                Type ty = new ParameterizedTypeImpl(BaseResponse.class, new Type[]{types[0]});
                BaseResponse<T> data = GsonHelper.getInstance().getGson().fromJson(str, ty);
                onSuccess(data.result);
            }
        } catch (Exception e) {
            onError(e);
        }

    }

    public abstract void onSuccess(T t);

    @Override
    public void onError(@NonNull Throwable e) {

    }
}
