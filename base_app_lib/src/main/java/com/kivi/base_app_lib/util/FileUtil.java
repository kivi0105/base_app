package com.kivi.base_app_lib.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * @description: Created by kivi on 2017/4/18.
 */

public class FileUtil {


    /**
     * install apk
     *
     * @param context
     * @param filePath
     * @return
     */
    public static Intent openApkFile(Context context, String filePath) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(filePath));
        } else {
            uri = Uri.fromFile(new File(filePath));
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }


}
