package com.example.kivi.base_app.activity;

import android.widget.TextView;

import com.example.kivi.base_app.R;
import com.kivi.base_app_lib.base.BaseActivity;
import com.kivi.base_app_lib.receiver.NetworkChangedReceiver;

/**
 * @description: Created by kivi on 2017/9/14.
 */

public class NetActivity extends BaseActivity implements NetworkChangedReceiver.NetworkChangedInterface {
    private TextView tv_net;

    @Override
    protected int setLayoutView() {
        return R.layout.activity_net;
    }

    @Override
    protected void initView() {
        NetworkChangedReceiver.add(this);
        tv_net = bind(R.id.tv_net);

    }

    @Override
    protected void bindListener() {

    }

    @Override
    protected void customAction() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkChangedReceiver.remove(this);
    }

    @Override
    public void mobileNet() {
        tv_net.setText("mobile net");
    }

    @Override
    public void wifiNet() {
        tv_net.setText("wifi net");

    }

    @Override
    public void unconnectedNet() {
        tv_net.setText("unconnected net");

    }
}
