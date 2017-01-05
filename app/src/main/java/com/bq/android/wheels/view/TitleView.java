package com.bq.android.wheels.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bq.android.wheels.R;

/**
 * Created by liuben on 17-1-5.
 */

public class TitleView extends LinearLayout {

    private ImageView mBackKey;
    private TextView mTitleText;
    private ImageView mShareIcon;

    public TitleView(Context context) {
        super(context);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_view, this);
        mBackKey = (ImageView) view.findViewById(R.id.back_key);
        mTitleText = (TextView) view.findViewById(R.id.title_text);
        mShareIcon = (ImageView) view.findViewById(R.id.share_icon);

        mBackKey.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
