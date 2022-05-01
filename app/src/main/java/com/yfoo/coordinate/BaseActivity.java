package com.yfoo.coordinate;


import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;

/**
 * Created by Jaeger on 16/2/14.
 *
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.no_color));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 开启沉浸状态栏
     */
    public void openImmerseStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            if (isNavigationBarShow()) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            if (!isNavigationBarShow()) {
                window.setNavigationBarColor(Color.TRANSPARENT);
            }

        } else {

            // 实现透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 实现透明导航栏
            if (!isNavigationBarShow()) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }

        }

    }

    /**
     * 取状态栏高度
     *
     * @return
     */
    public int getStateBarHigh() {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                return this.getResources().getDimensionPixelSize(Integer.parseInt(c.getField("status_bar_height").get(c.newInstance()).toString()));
            } catch (Exception e1) {
                e1.printStackTrace();
                return 0;
            }
        }
        Resources resources = this.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /**
     * 导航栏是否显示
     *
     * @return
     */
    public boolean isNavigationBarShow() {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (Build.MANUFACTURER.equals("Xiaomi") && Settings.Global.getInt(this.getContentResolver(), "force_fsg_nav_bar", 0) != 0) {
            return false;
        }
        DisplayMetrics outMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        int height1 = outMetrics.heightPixels;
        this.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        if ((height1 - outMetrics.heightPixels) - getStateBarHigh() > 0) {
            return true;
        }
        return false;
    }
}
