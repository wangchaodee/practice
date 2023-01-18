package com.iflytek.staff.chao.ahead;


import sun.nio.ch.IOUtil;

import java.util.Arrays;

/**
 * @author : hamilton
 * @Description: FileChannel 完成文件复制的实践
 * @date Date : 2022年11月14日 16:56
 */
public class FileNIOCopyDemo {

    public static void main(String[] args) {
        // 演示复制资源文件
        nioCopyResourceFile();
        Arrays.asList(3,2,1);
    }

    private static void nioCopyResourceFile() {
        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_FILE;
        String srcPath = IOUtil.getResourcePath(sourcePath);
    }
}
