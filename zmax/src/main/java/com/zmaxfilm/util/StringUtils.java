package com.zmaxfilm.util;

/**
 * Created by Administrator on 2016/11/15.
 */
public class StringUtils {

    public static boolean isEmpty(Object str){

        if(str==null){
            return false;
        }
        if (!(str instanceof Object)){
            return false;
        }
        if("".equals(str)){
            return false;
        }

        return true;
    }

}
