package com.jzy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @ClassName FileUtils
 * @Author JinZhiyun
 * @Description 文件工具类
 * @Date 2020/5/29 15:36
 * @Version 1.0
 **/
public class FileUtils extends org.apache.commons.io.FileUtils {
    public static void main(String[] args) throws IOException {
        FileUtils.write(new File("C:\\Users\\92970\\Desktop\\1.txt"), "aa", true);

    }
}
