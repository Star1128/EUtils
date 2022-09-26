package com.ethan.ethanutils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *
 * @author Ethan 2021/11/22
 */
public class OkHttpUtil {

    /**
     * 使用Okhttp发起异步网络请求
     * @param address URL地址
     * @param callback 网络请求结果监听
     */
    public static void sendRequestAsync(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        // 异步请求 在enqueue方法里已开好子线程 最终的请求结果会回调到callback中
        client.newCall(request).enqueue(callback);
    }
}
