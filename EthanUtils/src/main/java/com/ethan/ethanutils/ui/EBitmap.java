package com.ethan.ethanutils.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

/**
 * Bitmap采样压缩
 *
 * @author Ethan 2021/11/14
 */
public class EBitmap {

    /**
     * 提供图片文件路径,使用临近采样压缩Bitmap
     *
     * @param path      图片文件路径
     * @param imageView 图片显示窗口
     * @return 压缩后的Bitmap
     */
    public static Bitmap getNearestNeighbourBitmap(String path, ImageView imageView) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 不加载,只想看看Bitmap属性数据
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // 现在options里面有了图片的大小属性 执行之后 options就被修改了
        NearestNeighbourResampling(options, imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 提供图片文件URI,使用临近采样压缩Bitmap
     *
     * @param uri       图片Uri
     * @param imageView 图片显示窗口
     * @return 压缩后的Bitmap
     */
    public static Bitmap getNearestNeighbourBitmap(Uri uri, Context context, ImageView imageView) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 不加载,只想看看Bitmap属性数据
        options.inJustDecodeBounds = true;
        FileDescriptor fd = null;
        try {
            fd = context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor();
            BitmapFactory.decodeFileDescriptor(fd, null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        NearestNeighbourResampling(options, imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    /**
     * 临近采样算法
     *
     * @param options    原options
     * @param destWidth  目标控件宽度
     * @param destHeight 目标控件高度
     */
    private static void NearestNeighbourResampling(BitmapFactory.Options options, int destWidth, int destHeight) {
        // 从options里面拿到Bitmap的宽高属性
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if (srcWidth > destWidth || srcHeight > destHeight) {
            // 图片宽高除以控件高度
            float widthScale = srcWidth / destWidth;
            float heightScale = srcHeight / destHeight;
            // 得到最接近最大缩放率的整数
            inSampleSize = Math.round(Math.max(heightScale, widthScale));
        }

        // 重新设定Options 由于是按值传递 也就是直接修改了源options
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
    }

    /**
     * 提供图片文件路径,使用双线性采样压缩Bitmap
     *
     * @param path      图片文件路径
     * @param imageView 图片显示窗口
     * @return 压缩后的Bitmap
     */
    public static Bitmap getBilinearBitmap(String path, ImageView imageView) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return BilinearResampling(bitmap, imageView, true);
    }

    /**
     * 双线性采样算法
     *
     * @param bitmap    原Bitmap
     * @param imageView 图片显示窗口
     * @param filter    是否开启双线性过滤
     * @return 压缩后的Bitmap
     */
    public static Bitmap BilinearResampling(Bitmap bitmap, ImageView imageView, boolean filter) {
        return Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), filter);
        // 也可以自己指定矩阵,不过还没搞清楚大小问题
        // Matrix matrix=new Matrix();
        // matrix.setScale(0.3f, 0.5f);
        // return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
