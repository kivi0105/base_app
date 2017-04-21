package com.kivi.base_app_lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @description:
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutView());
        initView();
        bindListener();
        customAction();

    }

    protected abstract int setLayoutView();

    protected abstract void initView();

    protected abstract void bindListener();

    protected abstract void customAction();

    protected <T extends View> T bind(int viewId) {
        return (T) findViewById(viewId);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


}
