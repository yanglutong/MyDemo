package com.example.mvplibrary.base.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.model
 * 文件名：ModelFactory
 * 创建者：杨路通
 * 创建时间：2020/11/7  13:51
 * 描述：TODO
 *
 * 创建model的工厂类
 */
public class ModelFactory {

    private static Map<Class,BaseModel>modles=new HashMap<>();

    public static <T extends BaseModel> T createModel(Class<T>mClass){
        T t=null;
        if(modles!=null){
            if(mClass!=null){
                t= (T) modles.get(mClass);
            }
        }else{
            try {
                t=mClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            modles.put(mClass,t);
        }
        return t;
    }
}
