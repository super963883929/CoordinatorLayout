package com.yfoo.coordinate;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jaeger.library.StatusBarUtil;
import com.r0adkll.slidr.Slidr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class ScrollingActivity extends AppCompatActivity {

    private CollapsingToolbarLayout toolBarLayout;
    private Toolbar toolbar;
    private ImageView img_head;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Utils.setStatusBarTransparent(this);
        Slidr.attach(this);

        setTitle("协调布局之滚动折叠头部");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("协调布局之滚动折叠头部");
            AppCompatActivity appCompatActivity = (AppCompatActivity) this;
            appCompatActivity.setSupportActionBar(toolbar);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitleTextColor(Color.WHITE);
        }
        setSupportActionBar(toolbar);
        toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        img_head = findViewById(R.id.img_head);
        appBarLayout = findViewById(R.id.app_bar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 全屏沉淀式状态栏
     */
    public void initStatusBar() {
        // 延伸显示区域到刘海
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            // 延伸显示区域到刘海
            getWindow().setAttributes(layoutParams);
        }
    }
}