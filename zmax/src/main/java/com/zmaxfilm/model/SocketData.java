package com.zmaxfilm.model;

import com.google.gson.Gson;

/**
 * 表示socket传输的数据    "{aim:0,type:0,sign:0,data:\"{id:1212}\"}"
 * @author jimmy
 * Created by Administrator on 2016/11/15.
 */
public class SocketData {
    //AIM的类型
    public static Integer AIM_DEVICE_STATE  = 0;                //设备状态
    public static Integer AIM_HTML_HASNEW = 1;                  //通知有新的HTML文件
    public static Integer AIM_ADVERTISEMENT_HASNEW = 2;          //通知有新的广告文件

    //type的类型
    public static Integer TYPE_REQUEST = 0;            //请求
    public static Integer TYPE_RESPONSE = 1;           //响应

    private Integer aim ;    //请求（响应目的）
    private Integer type;    //类型
    private Integer sign;    //一串不重复字符串用来匹配请求与响应 ，一个请求对应一个响应
    private String data;    //json字符串，表示具体的数据内容

    public SocketData() {
    }
    public SocketData(Integer aim, Integer type, Integer sign, String data){
        this.aim = aim;
        this.type = type;
        this.sign = sign;
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("aim:"+this.getAim()+",");
        sb.append("type:"+this.getType()+",");
        sb.append("sign:"+this.getSign()+",");
        sb.append("data:"+this.getData());

        sb.append("}");
        return sb.toString();
    }

    public Integer getAim() {
        return aim;
    }

    public void setAim(Integer aim) {
        this.aim = aim;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
