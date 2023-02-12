package com.iflytek.staff.chao.file;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author : hamilton
 * @Description: FileChannel 完成文件复制的实践
 * @date Date : 2022年11月14日 16:56
 */
public class FileNIO {

    public static void main(String[] args) {
        // 演示复制资源文件
        try {
            fileCopy(FileConfig.FILE_RESOURCE_SRC_FILE,FileConfig.FILE_RESOURCE_DEST_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileCopy(String src, String dist) throws IOException {
        /* 获得源文件的输入字节流 */
        FileInputStream fin = new FileInputStream(src);

        /* 获取输入字节流的文件通道 */
        FileChannel fcin = fin.getChannel();

        /* 获取目标文件的输出字节流 */
        FileOutputStream fout = new FileOutputStream(dist);

        /* 获取输出字节流的文件通道 */
        FileChannel fcout = fout.getChannel();

        /* 为缓冲区分配 1024 个字节 */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {

            /* 从输入通道中读取数据到缓冲区中 */
            int r = fcin.read(buffer);

            /* read() 返回 -1 表示 EOF */
            if (r == -1) {
                break;
            }

            /* 切换读写 */
            buffer.flip();

            /* 把缓冲区的内容写入输出文件中 */
            fcout.write(buffer);

            /* 清空缓冲区 */
            buffer.clear();
        }
    }


}
