package com.cxp.coordinatordemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CollapsingToolbarLayout collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        final TextView tv = (TextView) findViewById(R.id.textView);
        iv.setScaleX(1);
        iv.setScaleY(1);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int measuredHeight1 = tv.getMeasuredHeight();
                int[] lo = new int[2];
                rl.getLocationOnScreen(lo);
                int measuredHeight = appBarLayout.getMeasuredHeight();
                Log.e("message", "appBarLayout高度：" + measuredHeight + ",title高度：" + measuredHeight1 + ",头像顶部：" + lo[1]);
                Log.e("message", "滑动高度：" + verticalOffset);
                float f = verticalOffset / (float) measuredHeight;
                iv.setScaleX(1 + f);
                iv.setScaleY(1 + f);
                ViewCompat.setAlpha(rl, 1 + f);
                if (measuredHeight1 >= lo[1]) {
                    ViewCompat.setAlpha(tv, 0);
                } else
                    ViewCompat.setAlpha(tv, 1 + f);
            }
        });

    }
}
