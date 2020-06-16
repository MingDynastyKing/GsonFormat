package com.example.myapplication.gson;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayNullTypeAdapter<E> extends TypeAdapter<Object> {
    private static final String TAG = "ArrayNullTypeAdapter";
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Log.d(TAG, "create() called with:  typeToken = [" + typeToken + "]");
            Type type = typeToken.getType();
//            if (!(type instanceof GenericArrayType || type instanceof Class && ((Class<?>) type).isArray())) {
//                return null;
//            }

            Class<? super T> rawType = typeToken.getRawType();
            if (!List.class.isAssignableFrom(rawType)) {
                return null;
            }
            Type elementType = $Gson$Types.getCollectionElementType(type, rawType);
            TypeAdapter<?> elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType));

//            Type componentType = $Gson$Types.getArrayComponentType(type);
//            TypeAdapter<?> componentTypeAdapter = gson.getAdapter(TypeToken.get(componentType));
            ArrayTypeAdapter<?> arrayTypeAdapter = new ArrayTypeAdapter(
                    gson, elementTypeAdapter, $Gson$Types.getRawType(elementType));
            return new ArrayNullTypeAdapter(arrayTypeAdapter);
        }
    };

    private final ArrayTypeAdapter<?> componentTypeAdapter;

    public ArrayNullTypeAdapter(ArrayTypeAdapter<?> componentTypeAdapter) {
        this.componentTypeAdapter = componentTypeAdapter;
    }

    @Override public Object read(JsonReader in) throws IOException {
        Log.d(TAG, "read() called with: in = [" + in + "]");
        if (in.peek() != JsonToken.BEGIN_ARRAY) {
            in.skipValue();
            return null;
        }
        Object read1 = componentTypeAdapter.read(in);

        if (!(read1 instanceof Object[])) {
            return null;
        }
        Object[] objects = (Object[]) read1;
        List<Object> objectArrayList = new ArrayList<>();
        Collections.addAll(objectArrayList, objects);
        return objectArrayList;
    }

    @Override public void write(JsonWriter out, Object arrayList) throws IOException {
        Log.d(TAG, "write() called with: out = [" + out + "], arrayList = [" + arrayList + "]");
        if (arrayList == null) {
            out.nullValue();
            return;
        }
        if (!(arrayList instanceof List)) {
            out.nullValue();
            return;
        }
        List list = (List) arrayList;
        if (list.size() == 0) {
            out.beginArray();
            out.endArray();
            return;
        }

        int size = list.size();
        Object array = Array.newInstance(list.get(0).getClass(), size);
        for (int i = 0; i < size; i++) {
            Array.set(array, i, list.get(i));
        }
        componentTypeAdapter.write(out, array);

    }
}
