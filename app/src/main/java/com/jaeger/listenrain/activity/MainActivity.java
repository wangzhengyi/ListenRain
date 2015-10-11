package com.jaeger.listenrain.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jaeger.listenrain.R;
import com.jaeger.listenrain.base.BaseActivity;
import com.jaeger.listenrain.fragment.ListenFragment;
import com.jaeger.listenrain.fragment.SujinFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {
    private ViewPager vpHomePage;
    private TabLayout tabPageTitle;
    private RelativeLayout rlPlayControl;
    private ImageView ivPlayStatus;
    private boolean isPlaying;
    private Map<Integer, Fragment> mapFragment = new HashMap<>();
    private CharSequence[] pageTitle = {"素锦", "听雨"};

    @Override
    protected void initIntentParam(Intent intent) {
        super.initIntentParam(intent);
    }

    @Override
    protected void beforeInitView() {
        smoothSwitchScreen();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        vpHomePage = (ViewPager) findViewById(R.id.vp_home_page);
        tabPageTitle = (TabLayout) findViewById(R.id.tab_page_title);

//        rlPlayControl = (RelativeLayout) findViewById(R.id.rl_play_control);
//        ivPlayStatus = (ImageView) findViewById(R.id.iv_play_status);

    }

    @Override
    protected void setViewStatus() {
        vpHomePage.setAdapter(new HomePageAdapter(getSupportFragmentManager()));
        tabPageTitle.setupWithViewPager(vpHomePage);

//        rlPlayControl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.rl_play_control:
//                isPlaying = !isPlaying;
//                if (isPlaying) {
//                    ivPlayStatus.setImageResource(R.drawable.ic_play);
//                } else {
//                    ivPlayStatus.setImageResource(R.drawable.ic_pause);
//                }
//                break;

        }

    }

    private void smoothSwitchScreen() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            ViewGroup rootView = ((ViewGroup) this.findViewById(android.R.id.content));
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            rootView.setPadding(0, statusBarHeight, 0, 0);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private class HomePageAdapter extends FragmentStatePagerAdapter {

        public HomePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return switchFragment(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }
    }

    private Fragment switchFragment(int position) {
        Fragment fragment = mapFragment.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new SujinFragment();
                    break;
                case 1:
                    fragment = new ListenFragment();
                    break;
            }
            mapFragment.put(position, fragment);
        }
        return fragment;
    }
}
