package com.bq.android.wheels.utils;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuben on 16-12-15.
 */

public class WheelsNetworkManager {
    private static final String TAG = "WheelsNetworkManager";

    private static volatile WheelsNetworkManager mWheelsNetworkManager = null;
    private OkHttpClient mOkHttpClient;

    private WheelsNetworkManager() {
        mOkHttpClient = new OkHttpClient();
    }

    public static WheelsNetworkManager getInstance() {
        if (mWheelsNetworkManager == null) {
            synchronized (WheelsNetworkManager.class) {
                if (mWheelsNetworkManager == null) {
                    mWheelsNetworkManager = new WheelsNetworkManager();
                }
            }
        }
        return mWheelsNetworkManager;
    }

    public interface NetworkCallBack{
        public void success(Object object);
        public void fail();
    }

    public String getSyncString(String url) {
        Request request = new Request.Builder().get().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAsyncString(String url, final NetworkCallBack networkCallBack) {
        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                networkCallBack.fail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                networkCallBack.success(response.body().string());
            }
        });
    }

}
