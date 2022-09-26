package com.ethan.ethanutils;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author Ethan 2021/11/27
 */
public class IOUtil {

    /**
     * 关闭IO流
     * @param closeable IO流的公共接口
     */
    public static void closeStream(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
