package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;

    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args;
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @NonNull
    @Override
    public Type getRawType() {
        return raw;
    }

    @Nullable
    @Override
    public Type getOwnerType() {
        return null;
    }
}
