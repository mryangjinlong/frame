package com.zmaxfilm.util;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/11/15.
 */
public class JsonUtil {
    /**
     * 将字符串转换成T类型的对象
     * @param <T>
     * @param s
     * @param c
     * @return
     */
    public static <T> T fromJson(String s, Class<T> c) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(s, c);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
