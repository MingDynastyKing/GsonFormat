package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("errorCode")
    public String code;
    @SerializedName("errorMsg")
    public String message;
    @SerializedName("data")
    public T result;
}
