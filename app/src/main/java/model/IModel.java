package model;

import java.util.Map;

import callback.MyCallBack;

public interface IModel<T> {
    void requestGet(String url, Class clazz, MyCallBack myCallBack);
    void requestPost(String url, Map<String,String>map,Class clazz,MyCallBack myCallBack);
}
