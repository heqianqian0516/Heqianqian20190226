package presenter;

import java.util.Map;

import callback.MyCallBack;
import model.ModelImp;
import view.IView;

public class PresenterImp implements Ipresenter {
    private IView mIView;
    private ModelImp model;

    public PresenterImp(IView iView){
        this.mIView=iView;
        model = new ModelImp();
    }
    @Override
    public void startRequestGet(String url, Class clazz) {
        model.requestGet(url, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object o) {
                mIView.requestDataSuccess(o);
            }

            @Override
            public void onFail(String error) {
                mIView.requestDataFail(error);
            }
        });
    }

    @Override
    public void startRequestPost(String url, Map map, Class clazz) {

    }
    public void onDeath(){
        if (mIView!=null){
            mIView=null;
        }
        if (model!=null){
            model=null;
        }
    }
}
