package com.ethan.ethanutils;

import okhttp3.*;

/**
 * NOTE:
 *
 * @author wxc 2021/11/22
 * @version 1.0.0
 */
public class OkHttpUtil {
    /**
     * 使用Okhttp发起网络请求
     * @param address URL地址
     * @param callback 网络请求结果监听
     */
    public static void sendRequestWithOkHttp(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        // 异步请求 在enqueue方法里已开好子线程 最终的请求结果会回调到callback中
        client.newCall(request).enqueue(callback);
    }
}
