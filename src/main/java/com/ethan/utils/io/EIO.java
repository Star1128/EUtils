package com.ethan.utils.io;

import com.ethan.utils.log.ELog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Ethan 2021/11/27
 */
public class EIO {

    /**
     * 关闭IO流
     *
     * @param closeable IO流的公共接口
     */
    public static void closeStream(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(File file) {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                closeStream(br);
            }
        }
        return builder.toString();
    }

    public static void writeToFile(String content, File file) {
        BufferedWriter bw = null;
        try {
            // 如果文件不存在，创建文件
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    ELog.e("创建文件失败");
                }
            }
            bw = new BufferedWriter(new FileWriter(file)); // 默认覆盖写入
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                closeStream(bw);
            }
        }
    }
}
