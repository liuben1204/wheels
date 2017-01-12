package com.bq.android.wheels.utils;

import com.google.gson.Gson;

/**
 * Created by liuben on 16-12-28.
 */

public class JsonManager {
    public static <T> T parseJson(String jsonString, Class<T> type){
        T result = null;
        Gson gson = new Gson();
        result = gson.fromJson(jsonString, type);

        return result;
    }
}
