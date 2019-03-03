package model;

import com.google.gson.Gson;

import java.util.Map;

import api.RetofitManage;
import callback.MyCallBack;

public class ModelImp  implements IModel{
    @Override
    public void requestGet(String url, final Class clazz, final MyCallBack myCallBack) {
        RetofitManage.getInstance().get(url, new RetofitManage.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o=new Gson().fromJson(data,clazz);
                    if (myCallBack!=null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if (myCallBack!=null){
                        myCallBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if (myCallBack!=null){
                    myCallBack.onFail(error);
                }
            }
        });
    }

    @Override
    public void requestPost(String url, Map map, Class clazz, MyCallBack myCallBack) {

    }
}
