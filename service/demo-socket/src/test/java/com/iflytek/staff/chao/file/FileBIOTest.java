package com.iflytek.staff.chao.file;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2023年02月11日 14:49
 */
public class FileBIOTest {


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void copyFile() {

        try {
            FileBIO.copyFile(FileConfig.FILE_RESOURCE_SRC_FILE,FileConfig.FILE_RESOURCE_DEST_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFileContent() {
        try {
            FileBIO.readFileContent(FileConfig.FILE_RESOURCE_SRC_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}