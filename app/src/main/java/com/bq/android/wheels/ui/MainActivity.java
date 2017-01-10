package com.bq.android.wheels.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bq.android.wheels.R;
import com.bq.android.wheels.WheelsApplication;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

    }
}
