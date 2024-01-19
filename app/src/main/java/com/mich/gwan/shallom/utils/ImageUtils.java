/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 10
 */

package com.mich.gwan.shallom.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ImageUtils {
    // Convert Bitmap to byte array
    public static byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    // Convert byte array to Bitmap
    public static Bitmap byteArrayToBitmap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray,0, byteArray.length);
    }

    // Convert byte array to Base64 String (optimal - for storing as text)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String byteArrayToBase64(byte[] byteArray){
        return Base64.getEncoder().encodeToString(byteArray);
    }

    // Convert Base64 to byte array (Optional - for retrieval)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] base64ToByteArray(String base64String){
        return Base64.getDecoder().decode(base64String);
    }
}
