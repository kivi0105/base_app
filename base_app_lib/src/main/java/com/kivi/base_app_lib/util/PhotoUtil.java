package com.kivi.base_app_lib.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description: Created by kivi on 2017/7/26.
 */

public class PhotoUtil {

    private String filePath;

    {
        filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                                .getAbsolutePath();
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }



    /**
     * 拍照,重写onActivityResult方法
     *
     * @param activity      activity对象
     * @param takePhotoCode 标记码
     * @param photoName     文件名
     */
    public void takePhotoForResult(Activity activity, int takePhotoCode, String photoName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //获取拍照后的原始大小的图片,预览图则不需要
        Uri imageUri = Uri.fromFile(new File(filePath, photoName));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, takePhotoCode);
    }
    /**
     * 压缩并保存图片,减少OOM的可能
     *
     * @param filePath    原始文件路径
     * @param newFilePath 压缩后文件路径
     * @param reqWidth    压缩后的宽
     * @param reqHeight   压缩后的高
     * @return
     */
    public static Bitmap compressBitmapAndSave(String filePath, String newFilePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;  //只返回图片的宽高信息
        BitmapFactory.decodeFile(filePath, options);    //inJustDecodeBounds为true时，bitmap为null
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        //根据压缩后的比例来设置inSampleSize
        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round((float) height / (float) reqHeight);
            int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        options.inJustDecodeBounds = false;     //为显示下面的bitmap
        options.inSampleSize = inSampleSize;    //分配更少空间
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        File photo = new File(newFilePath);
        File parentFile = new File(photo.getParent());
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(photo);
            photo.createNewFile();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    /**
     * 保存缩略图
     *
     * @param bitmap
     * @param path
     */
    private void saveThumbnail(Bitmap bitmap, String path) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //提示信息
            return;
        }
        File photo = new File(path);
        File parentFile = new File(photo.getParent());
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(photo);
            photo.createNewFile();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 剪裁图片
     *
     * @param activity      当前activity
     * @param data          从相册中获取图片后返回的data
     * @param cropPhotoPath 剪裁后的图片保存位置
     * @param cropPhotoCode 标记码
     */
    private static final String TAG = "PhotoUtil";
    public static void cropPhotoForResult(Activity activity, Intent data, String cropPhotoPath, int cropPhotoCode) {
        File file = new File(cropPhotoPath);
        File parentFile = new File(file.getParent());
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //获取相册中的图片路径,进入系统剪裁界面
        if (data.getData() != null) {
            Uri uri = data.getData();
            Log.i(TAG, "cropPhotoForResult: "+uri.getPath());
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            //裁剪框的比例，1：1
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //裁剪后输出图片的尺寸大小
            intent.putExtra("outputX", 500);
            intent.putExtra("outputY", 500);
            //黑边处理
            intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded", true);
            //图片格式
            intent.putExtra("outputFormat", "JPEG");
            intent.putExtra("noFaceDetection", true);
            //true直接返回bitmap
            intent.putExtra("return-data", false);
            //返回路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cropPhotoPath);
            activity.startActivityForResult(intent, cropPhotoCode);
        }
    }


    /**
     * 缩放图片
     * @param bitmap    原始bitmap
     * @param w         宽
     * @param h         高
     * @return          缩放后的bitmap
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }

}
