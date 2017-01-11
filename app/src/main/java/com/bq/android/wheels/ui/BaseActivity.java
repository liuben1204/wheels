package com.bq.android.wheels.ui;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bq.android.wheels.R;
import com.bq.android.wheels.view.TitleView;
import com.bq.android.wheels.view.TransitionView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    private TitleView mTitle;
    private TransitionView mTransitionView;
    private FrameLayout mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        super.setContentView(view);

        initView(layoutResID);
    }

    private void initView(int layoutResID) {
        mTitle = (TitleView) findViewById(R.id.title_layout);
        mTransitionView = (TransitionView) findViewById(R.id.transition_view);
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

    public void showLoadingStatus(int flag) {
        mTransitionView.setStatus(flag);
    }

    public void setReloadCallback(TransitionView.IReloadCallback reloadCallback) {
        mTransitionView.setReloadCallback(reloadCallback);
    }

    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}
