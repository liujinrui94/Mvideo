package com.ys.video.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * @author:liujinrui
 * @Date:2019/1/23
 * @Description:
 */
public class FileUtils {

    public static String UriToString(Uri selectedImage, Activity constant) {

        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = constant.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            if (picturePath == null || picturePath.equals("null")) {
                return "";
            }
            return picturePath;
        } else {
            File file = new File(selectedImage.getPath());
            if (!file.exists()) {
                return "";

            }
           return file.getAbsolutePath();
        }
    }
}
