package com.bq.android.wheels.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bq.android.wheels.R;
import com.bq.android.wheels.utils.Constant;
import com.bq.android.wheels.view.TransitionView;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.GET_DATA_SUCCESS:

                    break;
                case Constant.GET_DTATA_FAILED:

                    break;
                case Constant.NETWORK_ERROR:

                    break;
                case Constant.DATA_ERROR:

                    break;
                case Constant.SERVER_ERROR:

                    break;
                case Constant.STOP_LOADING:

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setLoadingStatus(Constant.LOADING);
        getDataFromServer();
    }

    private void initView() {
        setReloadCallback(new ReloadCallback());
    }

    private void getDataFromServer(){
        Log.d(TAG, "get data from server");
    }


    public class ReloadCallback implements TransitionView.IReloadCallback{
        @Override
        public void networkErrorReload() {
            Log.d(TAG, "network error reload");
        }

        @Override
        public void dataErrorReload() {
            Log.d(TAG, "data error reload");
        }

        @Override
        public void serverErrorReload() {
            Log.d(TAG, "server error reload");
        }

        @Override
        public void timeoutReload() {
            Log.d(TAG, "timeout error reload");
        }
    }
}
