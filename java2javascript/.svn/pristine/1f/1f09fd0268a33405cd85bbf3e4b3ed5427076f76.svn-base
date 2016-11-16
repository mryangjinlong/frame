package com.zmaxfilm.util;

import java.io.File;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/12.
 */
public class FileUtils {
    //获取类路径下文件的绝对路径
    public static URL getAbsolutePath(String fileName){
        return FileUtils.class.getResource("/"+ fileName );
    }
    //判断文件是否存在
    public static boolean isExsit(String filePath){
        File file = new File(filePath);
        return file.exists();
    }

}
