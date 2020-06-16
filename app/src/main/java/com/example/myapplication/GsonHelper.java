package com.example.myapplication;

import android.util.Log;

import com.example.myapplication.gson.ArrayNullTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class GsonHelper {
    private static final String TAG = "GsonHelper";

    private static GsonHelper gsonHelper=new GsonHelper();
    private volatile   Gson gson;

    public GsonHelper() {
    }


    public static GsonHelper getInstance(){
        return gsonHelper;
    }


    public synchronized Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(String.class, new JsonDeserializer<String>() {
                        @Override
                        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            Log.d(TAG, "deserialize() called with: json = [" + json + "], typeOfT = [" + typeOfT + "], context = [" + context + "]");
                            String asString = null;
                            try {
                                asString = json.getAsString();
                            } catch (Exception e) {
                                asString = null;
                            }
                            return asString;
                        }
                    })
                    .registerTypeAdapterFactory(ArrayNullTypeAdapter.FACTORY)
                    .create();
        }
        return gson;
    }




}
