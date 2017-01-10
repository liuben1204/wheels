package com.bq.android.wheels.ui;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bq.android.wheels.R;
import com.bq.android.wheels.WheelsApplication;
import com.bq.android.wheels.view.TitleView;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    private TitleView mTitle;
    private FrameLayout mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        super.setContentView(view);

        initView(layoutResID);
    }

    private void initView(int layoutResID) {
        mTitle = (TitleView) findViewById(R.id.title_layout);
        mContent = (FrameLayout) findViewById(R.id.content);
        View view = getLayoutInflater().inflate(layoutResID, null);
        mContent.addView(view, 0);
    }

    public void setBackKeyListener(View.OnClickListener listener) {
        mTitle.setBackKeyListener(listener);
    }

    public void setTitleText(String title) {
        mTitle.setTitleText(title);
    }

    public void setShareIconListener(View.OnClickListener listener) {
        mTitle.setShareListener(listener);
    }

}
