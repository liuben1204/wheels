package com.bq.android.wheels.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bq.android.wheels.R;
import com.bq.android.wheels.utils.Constant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liuben on 17-1-5.
 */

public class TransitionView extends FrameLayout {
    private static final String TAG = "TransitionView";

    private View mView;
    private LinearLayout mLoadingLayout;
    private ImageView mLoadingImage;
    private TextView mLoadingText;

    private IReloadCallback mReloadCallback;
    private Timer mTimer;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public interface IReloadCallback {
        void networkErrorReload();

        void dataErrorReload();

        void serverErrorReload();

        void timeoutReload();
    }

    public void setReloadCallback(IReloadCallback reloadCallback) {
        mReloadCallback = reloadCallback;
    }

    public TransitionView(Context context) {
        super(context);
        initView(context);
    }

    public TransitionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TransitionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.transition_view, this);
        mLoadingLayout = (LinearLayout) mView.findViewById(R.id.loading_layout);
        mLoadingImage = (ImageView) mLoadingLayout.findViewById(R.id.image);
        mLoadingText = (TextView) mLoadingLayout.findViewById(R.id.text);
        setStatus(GONE);
    }

    public void setStatus(int flag) {
        mView.setVisibility(VISIBLE);
        switch (flag) {
            case Constant.GONE:
                stopTimer();
                mView.setVisibility(View.GONE);
                break;
            case Constant.LOADING:
                startTimer();
                mLoadingImage.setImageResource(R.mipmap.ic_launcher);
                mLoadingText.setText(R.string.wheel_loading);
                break;
            case Constant.STOP_LOADING:
                stopTimer();
                mView.setVisibility(View.GONE);
                break;
            case Constant.NETWORK_ERROR:
                stopTimer();
                mLoadingImage.setImageResource(R.mipmap.ic_launcher);
                mLoadingText.setText(R.string.wheel_network_error);
                mLoadingImage.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReloadCallback.networkErrorReload();
                        setStatus(Constant.LOADING);
                    }
                });
                break;
            case Constant.DATA_ERROR:
                stopTimer();
                mLoadingImage.setImageResource(R.mipmap.ic_launcher);
                mLoadingText.setText(R.string.wheel_data_error);
                mLoadingImage.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReloadCallback.dataErrorReload();
                        setStatus(Constant.LOADING);
                    }
                });
                break;
            case Constant.SERVER_ERROR:
                stopTimer();
                mLoadingImage.setImageResource(R.mipmap.ic_launcher);
                mLoadingText.setText(R.string.wheel_server_error);
                mLoadingImage.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReloadCallback.serverErrorReload();
                        setStatus(Constant.LOADING);
                    }
                });
                break;
            default:
                break;
        }
    }

    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setStatus(Constant.NETWORK_ERROR);
                        }
                    });
                    mReloadCallback.timeoutReload();
                }
            }, 20 * 1000);
        }
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
