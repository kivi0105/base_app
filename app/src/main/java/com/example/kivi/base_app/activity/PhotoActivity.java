package com.example.kivi.base_app.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kivi.base_app.R;
import com.example.permissonlibrary.PermissionCallback;
import com.example.permissonlibrary.PermissionUtil;
import com.kivi.base_app_lib.base.BaseActivity;
import com.kivi.base_app_lib.util.DateUtil;
import com.kivi.base_app_lib.util.PhotoUtil;

/**
 * @description: Created by kivi on 2017/7/26.
 */

public class PhotoActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_take_photo;
    private ImageView iv_photo;


    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public final static String PHOTO_NAME = "photo.jpg";
    public String[] permissions = new String[]{
            Manifest.permission.CAMERA
    } ;
    public String explanation = "开启权限，否则无法使用";


    private static final String TAG = "PhotoActivity";

    @Override
    protected int setLayoutView() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initView() {
        btn_take_photo = bind(R.id.btn_take_photo);
        iv_photo = bind(R.id.iv_photo);

    }

    @Override
    protected void bindListener() {
        btn_take_photo.setOnClickListener(this);


    }

    @Override
    protected void customAction() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_photo:
                PermissionUtil.with(this)
                        .addPermissions(permissions)
                        .addExplanation(explanation)
                        .addCallback(new PermissionCallback() {
                            @Override
                            public void granted() {
                                openCamera();
                            }

                            @Override
                            public void denied(String s) {
                                Log.i(TAG, "denied: "+s);
                            }
                        })
                        .request();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
//                    Bundle extras = data.getExtras();
//                    Bitmap imageBitmap = (Bitmap) extras.get("data");
//                    iv_photo.setImageBitmap(imageBitmap);
                    //---------------------



                    break;
            }
        }
    }

    private void openCamera(){
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
        //---------------------
//        PhotoUtil photoUtil = new PhotoUtil();
//        photoUtil.takePhotoForResult();
//        PhotoUtil.takePhotoForResult(PhotoActivity.this,REQUEST_IMAGE_CAPTURE,PHOTO_NAME);

    }


}
