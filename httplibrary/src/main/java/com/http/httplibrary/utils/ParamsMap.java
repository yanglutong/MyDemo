package com.http.httplibrary.utils;

import java.util.HashMap;

/**
 * 项目名：2007
 * 包名：  com.example.liangxq.myapplication2001.utils
 * 文件名：ParamsMap
 * 创建者：杨路通
 * 创建时间：2020/10/30  11:21
 * 描述：TODO
 */
public class ParamsMap {


    private ParamsMap(){

    }

    private static volatile ParamsMap instance;

    public static ParamsMap getInstance(){

        if (instance == null) {
            synchronized (ParamsMap.class){
                if (instance == null) {
                    instance=new ParamsMap();
                }
            }
        }
        return instance;
    }
    private HashMap<String, Object> objectObjectHashMap;

    public ParamsMap add(String key, Object value) {
          objectObjectHashMap = new HashMap<>();
        if (objectObjectHashMap != null) {
            objectObjectHashMap.put(key, value);
        }
        return this;
    }

    public HashMap<String, Object> getObjectObjectHashMap() {
        if(objectObjectHashMap==null){
            objectObjectHashMap=new HashMap<String,Object>();
        }
        return objectObjectHashMap;
    }
}
