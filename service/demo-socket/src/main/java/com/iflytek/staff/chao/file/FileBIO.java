package com.iflytek.staff.chao.file;

import java.io.*;

/**
 * @author : hamilton
 * @Description: 文件操作 BIO方式
 * @date Date : 2023年02月11日 14:47
 */
public class FileBIO {

    public static void copyFile(String src, String dist) throws IOException {
        // 直接从磁盘读
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);
        // 从内存读  缓存大小 8192
        BufferedInputStream buffered= new BufferedInputStream(in);
        byte[] buffer = new byte[ 1024];
        int cnt;

        // read() 最多读取 buffer.length 个字节
        // 返回的是实际读取的个数
        // 返回 -1 的时候表示读到 eof，即文件尾
        while ((cnt = buffered.read(buffer)) != -1) {
            out.write(buffer, 0, cnt);
        }

        in.close();
        out.close();
    }

    public static void readFileContent(String src) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(src));

        String line ;
        while ((line = reader.readLine()) != null) {
            System.out.println(  line);
        }

        reader.close();
    }

}
