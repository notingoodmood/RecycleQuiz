package com.stufy.playground.IOHelper;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class COSHelper {

    private static OkHttpClient okHttpClient;

    //存储桶的根地址
    private static final String BucketURL="https://questions-1257936688.cos.ap-chengdu.myqcloud.com/";

    public static JSONObject ReadJSON(String key){
        if(okHttpClient==null) okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(BucketURL+key)
                .get()
                .build();
        try{
            return JSONObject.parseObject(okHttpClient.newCall(request).execute().body().string());
        }catch (IOException e){
            Log.e("NetWork","网络连接失败!!!\nURL:"+request.toString());
            e.printStackTrace();
        }
        return null;
    }

}
