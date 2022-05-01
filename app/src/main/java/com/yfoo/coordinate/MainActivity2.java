package com.yfoo.coordinate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.r0adkll.slidr.Slidr;

public class MainActivity2 extends BaseActivity {
    private Toolbar mToolbar;
    private View mViewNeedOffset;
    private SeekBar mSbChangeAlpha;
    private TextView mTvStatusAlpha;
    private int mAlpha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // 设置右滑动返回
        Slidr.attach(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);
        mSbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mSbChangeAlpha.setMax(255);
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                StatusBarUtil.setTranslucentForImageView(MainActivity2.this, mAlpha, mViewNeedOffset);
                mTvStatusAlpha.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSbChangeAlpha.setProgress(0);
    }

    @Override
    protected void setStatusBar() {
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        StatusBarUtil.setTranslucentForImageView(this,0, mViewNeedOffset);
    }
}